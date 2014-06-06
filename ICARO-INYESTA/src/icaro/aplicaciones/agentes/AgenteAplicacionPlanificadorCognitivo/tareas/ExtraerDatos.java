/*
 * PermitirPrediccion.java
 *
 * Creado 23 de abril de 2007, 12:50
 *
 * Telefonica I+D Copyright 2006-2007
 */
package icaro.aplicaciones.agentes.AgenteAplicacionPlanificadorCognitivo.tareas;

import icaro.aplicaciones.agentes.AgenteAplicacionPlanificadorCognitivo.informacion.InfoParaPlanificar;
import icaro.aplicaciones.informacion.dominioClases.aplicacionPrediccion.Confianza.InfoExpertoPredictor;
import icaro.aplicaciones.informacion.dominioClases.aplicacionPrediccion.InfoPrediccionSinValidar;
import icaro.aplicaciones.informacion.dominioClases.aplicacionPrediccion.InformationExtraction.InfoHistoricoClubs;
import icaro.aplicaciones.informacion.dominioClases.aplicacionPrediccion.InformationExtraction.InfoHistoricoTemporadas;
import icaro.aplicaciones.informacion.dominioClases.aplicacionPrediccion.InformationExtraction.InfoTemporada;
import icaro.aplicaciones.informacion.dominioClases.aplicacionPrediccion.VocabularioSistemaPrediccion;
import icaro.aplicaciones.recursos.ExtraccionPrediccion.ItfUsoExtraccionPrediccion;
import icaro.aplicaciones.recursos.persistenciaPrediccionBD.ItfUsoPersistenciaPrediccionBD;
import icaro.aplicaciones.recursos.visualizacionAcceso.imp.NotificacionesEventosVisAcceso;
import icaro.aplicaciones.recursos.visualizacionPrediccion.ItfUsoVisualizadorPrediccion;
import icaro.infraestructura.entidadesBasicas.NombresPredefinidos;
import icaro.infraestructura.entidadesBasicas.comunicacion.AdaptadorRegRMI;
import icaro.infraestructura.entidadesBasicas.comunicacion.EventoRecAgte;
import icaro.infraestructura.entidadesBasicas.comunicacion.InfoContEvtMsgAgteReactivo;
import icaro.infraestructura.entidadesBasicas.interfaces.InterfazUsoAgente;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.CausaTerminacionTarea;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.Objetivo;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.Tarea;
import icaro.infraestructura.recursosOrganizacion.recursoTrazas.imp.componentes.InfoTraza;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pablo C. Cañizares
 */
public class ExtraerDatos extends Tarea {

    private String identAgenteOrdenante;
    private Objetivo contextoEjecucionTarea = null;
    private InterfazUsoAgente itfUsoAgenteReceptor;
    
    @Override
    public void ejecutar(Object... params) {
        String identDeEstaTarea = this.getIdentTarea();
        //String identRecursoExtraccion = (String) params[0];	
        
        InfoParaPlanificar InfoPlanificar = (InfoParaPlanificar)params[0];	
        String  identRecursoExtraccion = (String)params[1];
        try {
            identAgenteOrdenante = this.getIdentAgente();
            ItfUsoExtraccionPrediccion extraccionPrediccion = (ItfUsoExtraccionPrediccion) NombresPredefinidos.REPOSITORIO_INTERFACES_OBJ
                    .obtenerInterfaz(NombresPredefinidos.ITF_USO + identRecursoExtraccion);
            
            ItfUsoPersistenciaPrediccionBD persistenciaPrediccion = (ItfUsoPersistenciaPrediccionBD) NombresPredefinidos.REPOSITORIO_INTERFACES_OBJ.obtenerInterfaz(
                    NombresPredefinidos.ITF_USO + VocabularioSistemaPrediccion.IdentRecursoPersistenciaBDPrediccion);
                       
            if (extraccionPrediccion == null || persistenciaPrediccion == null) {
                this.generarInformeConCausaTerminacion(identDeEstaTarea, contextoEjecucionTarea, identAgenteOrdenante, "Error-AlObtener:Interfaz_Recurso:" + identRecursoExtraccion, CausaTerminacionTarea.ERROR);
            } else 
            {
                
                //Extraemos todo lo que nos ha indicado la tarea comprobacion
                if(InfoPlanificar.isExtraerCalendario())
                {
                    InfoTemporada infoCalendario = extraccionPrediccion.ExtraerCalendario();
                    InfoPlanificar.setCalendario(infoCalendario);
                }
                
                //hay que hacer bucle para 
                if(InfoPlanificar.isExpertosPredictores())
                {
                    InfoExpertoPredictor infoExpertoQuini =  extraccionPrediccion.ExtraerExpertoQuiniela();
                    InfoPlanificar.setExpertoPredictor(infoExpertoQuini);
                }
                if(InfoPlanificar.isExtraerHistoricoEquipos())
                {
                    InfoHistoricoClubs infoHistClubs = extraccionPrediccion.ExtraerHistoricoEquipos();
                    InfoPlanificar.setHistoricoClubs(infoHistClubs);
                    persistenciaPrediccion.insertarHistoricoClubs(infoHistClubs);
                }
                if(InfoPlanificar.isExtraerTemporada())
                {
                    InfoTemporada  infoTemporada = extraccionPrediccion.ExtraerTemporada(2009);
                    InfoPlanificar.setTemporada(infoTemporada);
                    persistenciaPrediccion.insertarHistoricoTemporada(infoTemporada);
                }
                
                if(InfoPlanificar.isExtraerHistoricoTemporadasCompleto())
                {
                    InfoHistoricoTemporadas  infoHistTemporada  =  extraccionPrediccion.ExtraerHistoricoTemporadasCompleto();
                    InfoPlanificar.setHistoricoTemporadas(infoHistTemporada);
                    persistenciaPrediccion.insertarTemporadas(infoHistTemporada);
                }

                
                this.generarInformeOK(identDeEstaTarea, contextoEjecucionTarea, identAgenteOrdenante, VocabularioSistemaPrediccion.DatosExtraidosCorrectamente);
            }
                
                
        } catch (Exception e) {
            this.generarInformeConCausaTerminacion(identDeEstaTarea, contextoEjecucionTarea, identAgenteOrdenante, "Error-AlUtilizar:Interfaces_Recurso:" + identRecursoExtraccion, CausaTerminacionTarea.ERROR);
            e.printStackTrace();
        }
    }

}
