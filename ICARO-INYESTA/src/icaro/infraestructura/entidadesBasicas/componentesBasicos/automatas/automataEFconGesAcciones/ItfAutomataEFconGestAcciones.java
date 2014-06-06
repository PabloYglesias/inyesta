package icaro.infraestructura.entidadesBasicas.componentesBasicos.automatas.automataEFconGesAcciones;

import icaro.infraestructura.entidadesBasicas.componentesBasicos.automatas.automataEFsinAcciones.*;

/**
 * Interfaz de uso de un autmata de ciclo de vida de un Recurso
 *@author     lvaro Rodrguez
 *@created    1 de Febrero de 2007
 */
public interface ItfAutomataEFconGestAcciones {
	/**
	 *  Dice si el automata se encuentra en un estado final o no
	 *
	 *@return    est en estado final o no
	 */
	public boolean estasEnEstadoFinal();


	/**
	 *  Admite un input y lo procesa segun ta tabla de estados, ejecutando la
	 *  transicin correspondiente
	 *  Devuelve falso si no ha podido hacer la transición o true si la ha efectuado correctamente
	 *@param  input  Input a procesar
	 */
	public boolean transita(Object input);

	/**
	 * 	Dice si el recurso est en estado activo, es decir, que puede
	 *  ejecutar mtodos
	 * 
	 * @return est en estado activo o no
	 */
        public boolean ejecutarTransicion(Object input, Object... params);
	public boolean estadoActivo();
	
	/**
	 *  Imprime la tabla de estados y el estado actual del autmata
	 *
	 *@return    Cadena con la informacin
	 */
        @Override
	public String toString();

    public String estadoActual();


	/**
	 *  Devuelve el autmata a su estado inicial
	 */
	public void volverAEstadoInicial();
}
