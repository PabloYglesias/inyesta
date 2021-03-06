/*
 * SolicitarDatos.java
 *
 * Creado 23 de abril de 2007, 12:52
 *
 * Telefonica I+D Copyright 2006-2007
 */
package icaro.aplicaciones.agentes.AgenteAplicacionAccesoCognitivo.comportamientoAlta.tareas;

import icaro.aplicaciones.agentes.AgenteAplicacionAccesoCognitivo.tareas.*;
import icaro.aplicaciones.recursos.visualizacionAcceso.ItfUsoVisualizadorAcceso;
import icaro.aplicaciones.recursos.visualizacionAccesoAlta.ItfUsoVisualizadorAccesoAlta;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.Tarea;
import icaro.infraestructura.entidadesBasicas.NombresPredefinidos;
import icaro.infraestructura.entidadesBasicas.comunicacion.AdaptadorRegRMI;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.CausaTerminacionTarea;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.InformeDeTarea;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.Objetivo;




/**
 * 
 * @author F Garijo
 */
public class SolicitarDatosAccesoAlta extends Tarea {

	/** Crea una nueva instancia de SolicitarDatos */
//    private String identDeEstaTarea= "SolicitarDatosAcceso";
    private String identAgenteOrdenante;
    private Objetivo contextoEjecucionTarea = null;

	@Override
	public void ejecutar(Object... params) {
		
      //    String   identRecursoVisualizacionAcceso = "VisualizacionAcceso1";
          String identDeEstaTarea=getClass().getSimpleName();
          String identRecursoVisualizacionAcceso = (String)params[0];
                    try {
         // Se busca la interfaz del visualizador en el repositorio de interfaces y si no esta se busa en los repositorios remotos
		ItfUsoVisualizadorAccesoAlta visualizadorAcceso = (ItfUsoVisualizadorAccesoAlta) NombresPredefinidos.REPOSITORIO_INTERFACES_OBJ.obtenerInterfaz(
						NombresPredefinidos.ITF_USO + identRecursoVisualizacionAcceso);
                
                if (visualizadorAcceso==null)
                    visualizadorAcceso = (ItfUsoVisualizadorAccesoAlta)AdaptadorRegRMI.getItfRecursoRemoto(identRecursoVisualizacionAcceso, NombresPredefinidos.ITF_USO);
		if (visualizadorAcceso!=null)
                    visualizadorAcceso.mostrarVisualizadorAcceso(this.getAgente().getIdentAgente(), NombresPredefinidos.TIPO_COGNITIVO);
                else {
                    identAgenteOrdenante = this.getAgente().getIdentAgente();
                     this.generarInformeConCausaTerminacion(identDeEstaTarea, contextoEjecucionTarea, identAgenteOrdenante, "Error-AlObtener:Interfaz:"+identRecursoVisualizacionAcceso, CausaTerminacionTarea.ERROR);

                        }
                    } catch(Exception e) {
                        this.generarInformeConCausaTerminacion(identDeEstaTarea, contextoEjecucionTarea, identAgenteOrdenante, "Error-Acceso:Interfaz:"+identRecursoVisualizacionAcceso, CausaTerminacionTarea.ERROR);
			e.printStackTrace();
		}
	}
}
