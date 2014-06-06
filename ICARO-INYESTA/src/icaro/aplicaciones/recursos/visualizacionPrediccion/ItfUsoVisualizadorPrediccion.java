package icaro.aplicaciones.recursos.visualizacionPrediccion;

import icaro.infraestructura.patronRecursoSimple.ItfUsoRecursoSimple;

/**
 * 
 *@author     Felipe Polo
 *@created    30 de noviembre de 2007
 */

public interface ItfUsoVisualizadorPrediccion extends ItfUsoRecursoSimple{

	public void mostrarVisualizadorPrediccion(String nombreAgente,String tipo) throws Exception;
	public void cerrarVisualizadorPrediccion() throws Exception;
  	public void mostrarMensajeInformacion(String titulo,String mensaje) throws Exception;
  	public void mostrarMensajeAviso(String titulo,String mensaje) throws Exception;
  	public void mostrarMensajeError(String titulo,String mensaje) throws Exception;	
}