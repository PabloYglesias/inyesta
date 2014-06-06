/*
 * PermitirPrediccion.java
 *
 * Creado 23 de abril de 2007, 12:50
 *
 * Telefonica I+D Copyright 2006-2007
 */
package icaro.aplicaciones.agentes.AgenteAplicacionPredictorCognitivo.tareas;

import icaro.aplicaciones.informacion.dominioClases.aplicacionPrediccion.InfoPrediccionSinValidar;
import icaro.aplicaciones.informacion.dominioClases.aplicacionPrediccion.VocabularioSistemaPrediccion;
import icaro.aplicaciones.recursos.visualizacionAcceso.imp.NotificacionesEventosVisAcceso;
import icaro.aplicaciones.recursos.visualizacionPrediccion.ItfUsoVisualizadorPrediccion;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.Tarea;
import icaro.infraestructura.entidadesBasicas.NombresPredefinidos;
import icaro.infraestructura.entidadesBasicas.comunicacion.AdaptadorRegRMI;
import icaro.infraestructura.entidadesBasicas.comunicacion.EventoRecAgte;
import icaro.infraestructura.entidadesBasicas.comunicacion.InfoContEvtMsgAgteReactivo;
import icaro.infraestructura.entidadesBasicas.interfaces.InterfazUsoAgente;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.CausaTerminacionTarea;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.Objetivo;
import icaro.infraestructura.recursosOrganizacion.recursoTrazas.imp.componentes.InfoTraza;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author F Garijo
 */
public class SolicitarPrediccion extends Tarea {

    private String identAgenteOrdenante;
    private Objetivo contextoEjecucionTarea = null;
    private InterfazUsoAgente itfUsoAgenteReceptor;
    
    @Override
    public void ejecutar(Object... params) {
        String identDeEstaTarea = this.getIdentTarea();
        String identRecursoVisualizacionPrediccion = (String) params[0];	
        
        
        try {
            identAgenteOrdenante = this.getIdentAgente();
            ItfUsoVisualizadorPrediccion visualizadorPrediccion = (ItfUsoVisualizadorPrediccion) NombresPredefinidos.REPOSITORIO_INTERFACES_OBJ
                    .obtenerInterfaz(NombresPredefinidos.ITF_USO + identRecursoVisualizacionPrediccion);
            if (visualizadorPrediccion == null) {
                this.generarInformeConCausaTerminacion(identDeEstaTarea, contextoEjecucionTarea, identAgenteOrdenante, "Error-AlObtener:Interfaz_Recurso:" + identRecursoVisualizacionPrediccion, CausaTerminacionTarea.ERROR);
            } else 
            {
                
                visualizadorPrediccion.mostrarMensajeInformacion(identDeEstaTarea, "Datos validos, consultando la predicción con nuestros expertos");
                //visualizadorPrediccion.cerrarVisualizadorPrediccion();
                
                this.generarInformeOK(identDeEstaTarea, contextoEjecucionTarea, identAgenteOrdenante, VocabularioSistemaPrediccion.NotificacionPrediccionAutorizado);
            }
        } catch (Exception e) {
            this.generarInformeConCausaTerminacion(identDeEstaTarea, contextoEjecucionTarea, identAgenteOrdenante, "Error-AlUtilizar:Interfaces_Recurso:" + identRecursoVisualizacionPrediccion, CausaTerminacionTarea.ERROR);
            e.printStackTrace();
        }
    }
        // El método generarInformOK crea un informe de tarea y se lo envía  al  procesador de objetivos
    // Los valores para crear el informe son los siguientes
    // Identificador de la Tarea que genera el informe : "Tarea:PermitirPrediccion"
    // El contexto en el  que se ejecuta la tarea : ( Opcional) Identificador del objetivo en el que se ejecuta la tarea
    // Identificador del agente que ejecuta la  la tarea 
    //Texto del informe del resultado de la ejecución: "Autorizacion_Prediccion_Notificado_Al_Usuario"


}
