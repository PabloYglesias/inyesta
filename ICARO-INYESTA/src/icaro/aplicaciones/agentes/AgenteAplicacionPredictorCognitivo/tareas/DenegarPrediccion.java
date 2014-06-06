/*
 * DenegarPrediccion.java
 
 */
package icaro.aplicaciones.agentes.AgenteAplicacionPredictorCognitivo.tareas;


import icaro.aplicaciones.informacion.dominioClases.aplicacionPrediccion.VocabularioSistemaPrediccion;
import icaro.aplicaciones.recursos.visualizacionPrediccion.ItfUsoVisualizadorPrediccion;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.Tarea;
import icaro.infraestructura.entidadesBasicas.NombresPredefinidos;
import icaro.infraestructura.entidadesBasicas.comunicacion.AdaptadorRegRMI;
import icaro.infraestructura.entidadesBasicas.comunicacion.MensajeSimple;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.CausaTerminacionTarea;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.Objetivo;


/**
 * 
 * @author Francisco J Garijo
 */
public class DenegarPrediccion extends Tarea {
        private String identAgenteOrdenante;
        private Objetivo contextoEjecucionTarea = null;
        private String identRecursoVisualizacionPrediccion =VocabularioSistemaPrediccion.IdentRecursoVisualizacionPrediccionInicial;
	@Override
	public void ejecutar(Object... params) {
            String identDeEstaTarea=getClass().getSimpleName();
		try {
		    identAgenteOrdenante = this.getAgente().getIdentAgente();
                    ItfUsoVisualizadorPrediccion visualizadorPrediccion = (ItfUsoVisualizadorPrediccion) NombresPredefinidos.REPOSITORIO_INTERFACES_OBJ
					.obtenerInterfaz(NombresPredefinidos.ITF_USO + identRecursoVisualizacionPrediccion);
                   if (visualizadorPrediccion==null) this.generarInformeConCausaTerminacion(identDeEstaTarea, contextoEjecucionTarea, identAgenteOrdenante, "Error-AlObtener:Interfaz_Recurso:"+identRecursoVisualizacionPrediccion, CausaTerminacionTarea.ERROR);
                   else visualizadorPrediccion.mostrarMensajeError("Prediccion denegada", "Identificador de equipos o fecha incorrectas. Introduzcalas de nuevo");
		} catch (Exception e) {
                  this.generarInformeConCausaTerminacion(identDeEstaTarea, contextoEjecucionTarea, identAgenteOrdenante, "Error-AlObtener:Interfaces_Recursos", CausaTerminacionTarea.ERROR);
                  e.printStackTrace();
		}
	}
}
