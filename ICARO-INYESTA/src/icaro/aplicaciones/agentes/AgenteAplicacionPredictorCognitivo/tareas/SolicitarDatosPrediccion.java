/*
 * SolicitarDatos.java
 *
 * Creado 23 de abril de 2007, 12:52
 *
 * Telefonica I+D Copyright 2006-2007
 */
package icaro.aplicaciones.agentes.AgenteAplicacionPredictorCognitivo.tareas;

import icaro.aplicaciones.informacion.dominioClases.aplicacionPrediccion.VocabularioSistemaPrediccion;
import icaro.aplicaciones.recursos.persistenciaPrediccionBD.ItfUsoPersistenciaPrediccionBD;
import icaro.aplicaciones.recursos.visualizacionPrediccion.ItfUsoVisualizadorPrediccion;
import icaro.infraestructura.entidadesBasicas.NombresPredefinidos;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.CausaTerminacionTarea;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.Objetivo;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.Tarea;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author F Garijo
 */
public class SolicitarDatosPrediccion extends Tarea {

    private String identAgenteOrdenante;
    private Objetivo contextoEjecucionTarea = null;

    @Override
    public void ejecutar(Object... params) {
        String identDeEstaTarea = this.getIdentTarea();
        String identRecursoVisualizacionPrediccion = (String) params[0];
        try {
            // Se busca la interfaz del visualizador en el repositorio de interfaces 
            ItfUsoVisualizadorPrediccion visualizadorPrediccion = (ItfUsoVisualizadorPrediccion) NombresPredefinidos.REPOSITORIO_INTERFACES_OBJ.obtenerInterfaz(
                    NombresPredefinidos.ITF_USO + identRecursoVisualizacionPrediccion);
            if (visualizadorPrediccion != null) {
                visualizadorPrediccion.mostrarVisualizadorPrediccion(this.getAgente().getIdentAgente(), NombresPredefinidos.TIPO_COGNITIVO);
            } else {
                identAgenteOrdenante = this.getAgente().getIdentAgente();
                this.generarInformeConCausaTerminacion(identDeEstaTarea, contextoEjecucionTarea, identAgenteOrdenante, "Error-AlObtener:Interfaz:" + identRecursoVisualizacionPrediccion, CausaTerminacionTarea.ERROR);
            }
        } catch (Exception e) {
            this.generarInformeConCausaTerminacion(identDeEstaTarea, contextoEjecucionTarea, identAgenteOrdenante, "Error-Prediccion:Interfaz:" + identRecursoVisualizacionPrediccion, CausaTerminacionTarea.ERROR);
            e.printStackTrace();
        }
    }
}
