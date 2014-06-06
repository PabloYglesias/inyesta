/*
 * SolicitarDatos.java
 *
 * Creado 23 de abril de 2007, 12:52
 *
 * Telefonica I+D Copyright 2006-2007
 */
package icaro.aplicaciones.agentes.AgenteAplicacionPlanificadorCognitivo.tareas;

import icaro.aplicaciones.agentes.AgenteAplicacionPlanificadorCognitivo.informacion.InfoParaPlanificar;
import icaro.aplicaciones.informacion.dominioClases.aplicacionPrediccion.Confianza.InfoCompletaExpertosConfianza;
import icaro.aplicaciones.informacion.dominioClases.aplicacionPrediccion.InformationExtraction.InfoClubs;
import icaro.aplicaciones.informacion.dominioClases.aplicacionPrediccion.InformationExtraction.InfoHistoricoClubs;
import icaro.aplicaciones.informacion.dominioClases.aplicacionPrediccion.InformationExtraction.InfoTemporada;
import icaro.aplicaciones.informacion.dominioClases.aplicacionPrediccion.VocabularioSistemaPrediccion;
import icaro.aplicaciones.recursos.persistenciaPrediccionBD.ItfUsoPersistenciaPrediccionBD;
import icaro.aplicaciones.recursos.persistenciaPrediccionSimple.ItfUsoPersistenciaPrediccionSimple;
import icaro.infraestructura.entidadesBasicas.NombresPredefinidos;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.CausaTerminacionTarea;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.Objetivo;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.Tarea;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pablo C. Cañizares
 *
 */
public class ComprobarDatosPlanificacion extends Tarea {

    private String identAgenteOrdenante;
    private Objetivo contextoEjecucionTarea = null;

    @Override
    public void ejecutar(Object... params) {
        String identDeEstaTarea = this.getIdentTarea();
        //String identRecursoVisualizacionPrediccion = (String) params[0];
        InfoParaPlanificar InfoPlanificar = (InfoParaPlanificar) params[0];
        boolean resultadoComprobacion = true;

        try {
            // Se busca la interfaz de persistencia en el repositorio de interfaces y se busca lo que queramos buscar
            ItfUsoPersistenciaPrediccionBD persistenciaPrediccion = (ItfUsoPersistenciaPrediccionBD) NombresPredefinidos.REPOSITORIO_INTERFACES_OBJ.obtenerInterfaz(
                    NombresPredefinidos.ITF_USO + VocabularioSistemaPrediccion.IdentRecursoPersistenciaBDPrediccion);
            if (persistenciaPrediccion != null) {
                boolean bHistoricoTemporada, bComprobarEquipos, bExtraerPredictores;
                resultadoComprobacion = false;

                bHistoricoTemporada = ComprobarTemporada(persistenciaPrediccion);
                bComprobarEquipos = ComprobarEquipos(persistenciaPrediccion);
                bExtraerPredictores = ComprobarPredictores(persistenciaPrediccion);
                
                //Extraemos todas las predicciones
                //Si algo no encaja lo marcamos para extraer
                //forzamos a todos a que se descargue
                //InfoPlanificar.setExpertosPredictores(true);
                //InfoPlanificar.setExtraerCalendario(true);
                InfoPlanificar.setExtraerHistoricoEquipos(bComprobarEquipos);
                InfoPlanificar.setExtraerHistoricoTemporada(bHistoricoTemporada);
                //InfoPlanificar.setExtraerHistoricoTemporadasCompleto(true);

                if(bHistoricoTemporada || bComprobarEquipos || bExtraerPredictores)
                {
                    resultadoComprobacion=true;
                }
            } else {
                identAgenteOrdenante = this.getAgente().getIdentAgente();
                this.generarInformeConCausaTerminacion(identDeEstaTarea, contextoEjecucionTarea, identAgenteOrdenante, "Error-AlObtener:Interfaz:" + VocabularioSistemaPrediccion.IdentRecursoPersistenciaPrediccion, CausaTerminacionTarea.ERROR);
            }

            //generamos el informe
            String contenidoInformeTarea;

            if (resultadoComprobacion) {
                contenidoInformeTarea = VocabularioSistemaPrediccion.ResultadoComprobacionPlanificador_DatosNoExisten;
            } else {
                contenidoInformeTarea = VocabularioSistemaPrediccion.StandBy;
            }
            this.generarInformeOK(identDeEstaTarea, contextoEjecucionTarea, identAgenteOrdenante, contenidoInformeTarea);

        } catch (Exception e) {
            this.generarInformeConCausaTerminacion(identDeEstaTarea, contextoEjecucionTarea, identAgenteOrdenante, "Error-Prediccion:Interfaz:" + VocabularioSistemaPrediccion.IdentRecursoPersistenciaPrediccion, CausaTerminacionTarea.ERROR);
            e.printStackTrace();
        }
    }

    private boolean ComprobarTemporada(ItfUsoPersistenciaPrediccionBD persistenciaPrediccion) {
        boolean bHistoricoTemporada=false;
        try {
            
            //Extraemos el calendario, y miramos la fecha que toque
            int year = Calendar.getInstance().get(Calendar.YEAR);
            int month = Calendar.getInstance().get(Calendar.MONTH);
            
            //Si es antes de septiembre, la temporada corresponde al año anterior
            //nosotros representamos la temporada 2013-2014 como la temporada 2013, año en el que empieza
            if (month < 8) {
                year--;
            }
            
            InfoTemporada temporada = persistenciaPrediccion.getTemporada(year);
            
            if (temporada != null) {
                bHistoricoTemporada = false;
            } else {
                bHistoricoTemporada = true;
            }
            
            
        } catch (Exception ex) {
            
        }
        return bHistoricoTemporada;
    }

    private boolean ComprobarEquipos(ItfUsoPersistenciaPrediccionBD persistenciaPrediccion) 
    {
        boolean bComprobarEquipos=false;

        InfoHistoricoClubs Clubs = null;
        try {
            Clubs = persistenciaPrediccion.getHistoricoEquipos();


            if (Clubs != null) {
                bComprobarEquipos = false;
            } else {
                bComprobarEquipos = true;
            }
        } catch (Exception ex) {
            Logger.getLogger(ComprobarDatosPlanificacion.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return bComprobarEquipos;        
    }

    private boolean ComprobarPredictores(ItfUsoPersistenciaPrediccionBD persistenciaPrediccion) 
    {
        boolean bExtraerPredictores=false;

        InfoCompletaExpertosConfianza Expertos = null;
        try {
            Expertos = persistenciaPrediccion.getListaInfoExpertos();
        } catch (Exception ex) {
            Logger.getLogger(ComprobarDatosPlanificacion.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (Expertos != null) {
            bExtraerPredictores = false;
        } else {
            bExtraerPredictores = true;
        }
        
        return bExtraerPredictores;         
    }
}
