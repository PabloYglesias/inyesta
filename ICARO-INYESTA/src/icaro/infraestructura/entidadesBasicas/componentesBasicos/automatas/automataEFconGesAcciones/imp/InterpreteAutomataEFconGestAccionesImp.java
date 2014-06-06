/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package icaro.infraestructura.entidadesBasicas.componentesBasicos.automatas.automataEFconGesAcciones.imp;

import icaro.infraestructura.entidadesBasicas.componentesBasicos.automatas.automataEFconGesAcciones.InterpreteAutomataEFconGestAcciones;
import icaro.infraestructura.entidadesBasicas.componentesBasicos.automatas.automataEFconGesAcciones.ItfAutomataEFconGestAcciones;
import icaro.infraestructura.entidadesBasicas.componentesBasicos.automatas.automataEFsinAcciones.imp.*;
import icaro.infraestructura.entidadesBasicas.componentesBasicos.automatas.clasesImpAutomatas.TablaEstadosAutomataEFinputObjts;
import icaro.infraestructura.entidadesBasicas.componentesBasicos.automatas.clasesImpAutomatas.TransicionAutomataEF;
import icaro.infraestructura.entidadesBasicas.componentesBasicos.automatas.gestorAcciones.ItfGestorAcciones;
import icaro.infraestructura.patronAgenteReactivo.control.acciones.AccionesSemanticasAgenteReactivo;
import icaro.infraestructura.recursosOrganizacion.recursoTrazas.ItfUsoRecursoTrazas;
import java.io.Serializable;
import java.util.logging.Level;

import org.apache.log4j.Logger;

/**
 *
 * @author FGarijo
 */
public class InterpreteAutomataEFconGestAccionesImp extends InterpreteAutomataEFconGestAcciones{
//        implements ItfAutomataEFconGestAcciones, Serializable{
	
	
	public static final String ESTADO_CREADO = "creado";
	public static final String ESTADO_ARRANCADO = "arrancado";
	public static final String ESTADO_FALLO_ARRANQUE = "falloArranque";
	public static final String ESTADO_ERROR = "error";
	public static final String ESTADO_ACTIVO = "activo";
	public static final String ESTADO_FALLO_TEMPORAL = "falloTemporal";
	public static final String ESTADO_PARADO = "parado";
	public static final String ESTADO_TERMINANDO = "terminando";
	public static final String ESTADO_TERMINADO = "terminado";

	/**
	 * Indica si se deben mostrar mensajes de depuracin o no
	 * @uml.property  name="dEBUG"
	 */
	public boolean DEBUG = false;
//        private AccionesSemanticasAgenteReactivo accionesSemAgteReactivo ;
        private String accionesSemAgteReactivoSimpleName;

	/**
	 * Controla la profundidad de las trazas
	 * @uml.property  name="traza"
	 */
	protected int traza = 0;

	/**
	 * @uml.property  name="estadoActual"
	 */
//	private String estadoActual;

	/**
	 * Tabla que representa los estados del autmata
	 * @uml.property  name="interpTablaEstados"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
//	private InterpreteTablaEstadosAutomataSinAcciones interpTablaEstados;
//    private TablaEstadosAutomataEF interpTablaEstados;
	/**
	 *  No se muestra traza
	 */
	public final static int NIVEL_TRAZA_DESACTIVADO = 0;
	/**
	 *  Slo se muestra una indicacin cuando existe una transicin de estados
	 */
	public final static int NIVEL_TRAZA_SOLO_TRANSICIONES = 1;
	/**
	 *  Se muestra todo lo posible en la traza
	 */
	public final static int NIVEL_TRAZA_TODO = 2;

	/**
	 * @uml.property  name="logger"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private transient Logger logger = Logger.getRootLogger();
	
	/**
	 * @uml.property  name="trazas"
	 * @uml.associationEnd  readOnly="true"
	 */
	protected ItfUsoRecursoTrazas trazas;
	

	/**
	 *  Crea un autmata XML
	 *
	 *@param  NombreFicheroDescriptor  Nombre del fichero que contiene el autmata
	 *@param  nivelTraza               Profundidad de las trazas (usar constantes
	 *      definidas estticas en esta clase)
	 */
	public InterpreteAutomataEFconGestAccionesImp(TablaEstadosAutomataEFinputObjts tablaEstados,Boolean activarTrazas){
	
            super (tablaEstados,activarTrazas );
            accionesSemAgteReactivoSimpleName =AccionesSemanticasAgenteReactivo.class.getSimpleName();
            
    //    XMLParserTablaAutomataSinAcciones parser = new XMLParserTablaAutomataSinAcciones();
		//String ruta = tid.tecHabla.agentes.componentes.infraestructura.configuracion.Configuracion.obtenerParametro("RUTA_FICHEROS_DEFINICION_AUTOMATAS");

	//	interpTablaEstados = parser.extraeTablaEstadosDesdeFicheroXML(NombreFicheroDescriptor);

		// colocamos el autmata en el estado inicial
//		this.interpTablaEstados =intpTablaEstados;
//        estadoActual = interpTablaEstados.dameEstadoInicial();
//
//		// actualizamos el DEBUG para las trazas
//		if (nivelTraza == 2)
//			DEBUG = true;
//		else
//			DEBUG = false;
//
//		traza = nivelTraza;
//        String NombreFicheroDescriptor = interpTablaEstados.dameFicheroDefEstados();

//		logger.debug("Usando el automata de ciclo de vida del fichero: " + NombreFicheroDescriptor);
//		logger.debug(this.toString());
		/*try{
	    	trazas = (ItfUsoRecursoTrazas)RepositorioInterfaces.instance().obtenerInterfaz(
	    			NombresPredefinidos.ITF_USO+NombresPredefinidos.RECURSO_TRAZAS);
	    }catch(Exception e){
	    	System.out.println("No se pudo usar el recurso de trazas");
	    }*/
	}

        @Override
        public boolean ejecutarTransicion(Object input, Object... params){
            
            String inputAutomata = input.getClass().getSimpleName();
           
            if( inputAutomata.equals("String")){ // Si el input es de tipo String Tomamos como input la cadena que representa el input
                inputAutomata = (String)input;
            }
//           if( tablaEstadosAutomata.esInputValidoDeEstado(estadoActual, inputAutomata)){
               TransicionAutomataEF transicion = this.tablaEstadosAutomata.getTransicion(estadoActual()+inputAutomata);
               if (transicion == null){
                   // mensaje de error el input no es valido en el estado actual 
                   return false;
           }
            Class accion = transicion.getClaseAccion();
            if (accion == null){
            this.cambiaEstado(transicion.getidentEstadoSiguiente());
            return true;
            } 
            if (accion.getSuperclass().getSimpleName().equalsIgnoreCase(accionesSemAgteReactivoSimpleName)){
                String metodoId = transicion.getIdentMetodoAccion();
                if (metodoId!=null )
                    try {
                    // se trata de una claseAcciones semanticas, ejecutamos el metodo de la clase
                        this.itfGestAcciones.ejecutarMetodo(accion,metodoId, params);
                        return true;
                    } catch (Exception ex) {
                        java.util.logging.Logger.getLogger(InterpreteAutomataEFconGestAccionesImp.class.getName()).log(Level.SEVERE, null, ex);
                        return false;
                        }
                    }
            try { // se trata de una claseAcciones semanticas donde no se ha especificado el metodo, o de una accion sincrona o asincron
                   this.itfGestAcciones.ejecutarAccion(accion, params);
                    return true;
                } catch (Exception ex) {
                            java.util.logging.Logger.getLogger(InterpreteAutomataEFconGestAccionesImp.class.getName()).log(Level.SEVERE, null, ex);
                            return false;
                        }
}
        @Override
  public boolean transita(Object input){
      return ejecutarTransicion( input, (Object[]) null);
  }
	/**
	 *  Dice si el automata se encuentra en un estado final o no
	 *
	 *@return    est en estado final o no
	 */
//        @Override
//	public boolean esEstadoFinal(String identEstado)
//	{
//		return (interpTablaEstados.esEstadoFinal(identEstado));
//	}

        @Override
        public boolean estasEnEstadoFinal()
	{
		return (tablaEstadosAutomata.esEstadoFinal(estadoActual));
	}
	/**
	 *  Admite un input y lo procesa segun la tabla de estados
	 *
	 *@param  input  Input a procesar
	 */
//	public void transita(String input)
//	{
//		String siguiente;
//		// comprobar que es un input reconocido por el estado actual
//		if (interpTablaEstados.esInputValidoDeEstado(estadoActual, input))
//		{
//			siguiente = interpTablaEstados.dameEstadoSiguiente(estadoActual, input);
//			// cambiar al siguiente estado
////			logger.info("Transicion en el ciclo de vida usando input:" + input + "  :" + estadoActual + " -> " + siguiente);
//			cambiaEstado(siguiente);
//		}
//		else
//		{
//			logger.info("AVISO: Input de ciclo de vida ignorado.El input: " + input + " no pertenece a los inputs vlidos para el estado actual: " + estadoActual);
//			/*trazas.aceptaNuevaTraza(new InfoTraza("AutomataCicloVidaRecurso",
//					"AVISO: Input de ciclo de vida ignorado.El input: " + input + " no pertenece a los inputs vlidos para el estado actual: " + estadoActual,
//					InfoTraza.NivelTraza.info));*/
//		}
//
//	}



	/**
	 *  Imprime la tabla de estados y el estado actual del autmata
	 *
	 *@return    Cadena con la informacin
	 */
        @Override
	public String toString()
	{
		String dev = tablaEstadosAutomata.toString();
		dev += "\nEstado actual= " + estadoActual;
		return dev;
	}


	/**
	 *  Devuelve el autmata a su estado inicial
	 */
        @Override
	public void volverAEstadoInicial()
	{
	String estado = this.tablaEstadosAutomata.dameEstadoInicial();	
            this.cambiaEstado(estado);
	}


	/**
	 *  Cambia el estado interno del autmata
	 *
	 *@param  estado  Estado del automata al que cambiamos
	 */
	private void cambiaEstado(String estado)
	{
		estadoActual = estado;
	}

	/**
	 * 	Dice si el recurso est en estado activo, es decir, que puede
	 *  ejecutar mtodos
	 * 
	 * @return est en estado activo o no
	 */
        @Override
	public boolean estadoActivo(){
		return this.estadoActual.equals("activo");
	}

	/**
	 * Dice el estado del autmata en el que se encuentra el recurso
	 * @return el estado en que se encuentra
	 */
        @Override
	public String estadoActual(){
		return this.estadoActual;
	}
	/**
	 *  Programa de pruebas del componente
	 *
	 *@param  args  The command line arguments
	 */
	/*
	public static void main(String[] args)
	{
		// ejemplo de uso del automata de control

		// 1. Creo las acciones semnticas
		tid.tecHabla.agentes.componentes.infraestructura.automata.AutomataControl.funciones fun = new tid.tecHabla.agentes.componentes.infraestructura.automata.AutomataControl.funciones();
		// 1.5 Creo objeto de acciones semnticas
		AccionesSemanticas as = new AccionesSemanticas(fun);

		// 2. Creo el autmata
		AutomataControl automata = new AutomataControl("TablaEstadosPruebaDeTablaEstados.xml", as, tid.tecHabla.agentes.componentes.infraestructura.automata.AutomataControl.NIVEL_TRAZA_TODO);

		// 3. Tengo el autmata disponible para usar

		automata.toString();
		automata.procesaInput("inicia");
		automata.procesaInput("inputU");

		automata.toString();
	}
	*/

	/**
	 *  Clase de pruebas para el componente, slo para pruebas de funcionamiento
	 *  bsicas
	 *
	 *@author     Jorge Gonzlez
	 *@created    26 de septiembre de 2001
	 */
	public static class funciones {
		/**
		 */
		public void accionU()
		{
			System.out.println(">> Comienzo a ejecutar mtodo accionU()");
		}


		/**
		 *  Mtodo del usuario del componente
		 */
		public void inicial()
		{
			System.out.println(">> Comienzo a ejecutar metodo inicial()");
			boolean repite = true;
			while (repite)
			{
			}
		}

	}
	/**
	 * @param logger
	 * @uml.property  name="logger"
	 */
      
	public void setLogger(Logger logger){
		this.logger = logger;
	}
	/**
	 * @return
	 * @uml.property  name="logger"
	 */

	public Logger getLogger(){
		return logger;
	}

}
