/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package icaro.infraestructura.entidadesBasicas.componentesBasicos.automatas.automataEFconGesAcciones;

import icaro.infraestructura.entidadesBasicas.ConfiguracionTrazas;
import icaro.infraestructura.entidadesBasicas.NombresPredefinidos;
import icaro.infraestructura.entidadesBasicas.componentesBasicos.automatas.*;
import icaro.infraestructura.entidadesBasicas.componentesBasicos.automatas.clasesImpAutomatas.TablaEstadosAutomataEFinputObjts;
import icaro.infraestructura.entidadesBasicas.componentesBasicos.automatas.clasesImpAutomatas.XMLParserTablaEstadosAutomataEFinputObj;
import icaro.infraestructura.entidadesBasicas.excepciones.ExcepcionEnComponente;
import icaro.infraestructura.recursosOrganizacion.recursoTrazas.ItfUsoRecursoTrazas;
import icaro.infraestructura.recursosOrganizacion.recursoTrazas.imp.componentes.InfoTraza;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;

/**
 *
 * @author FGarijo
 */
public class FactoriaAutomataEFconGestAcciones {
    private static FactoriaAutomataEFconGestAcciones instancia;
    private XMLParserTablaEstadosAutomataEFinputObj xmLParserTablaEstadosAutomata = null;
    private TablaEstadosAutomataEFinputObjts tablaEstadosAutomata;
    
    public static FactoriaAutomataEFconGestAcciones instancia(){
        Log log = LogFactory.getLog(FactoriaAutomataEFconGestAcciones.class);
        if(instancia==null){
            String clase = System.getProperty("icaro.infraestructura.entidadesBasicas.componentesBasicos.automatas.",
            		"icaro.infraestructura.entidadesBasicas.componentesBasicos.automatas.FactoriaAutomataEFconGestAcciones");
            try{
                instancia = (FactoriaAutomataEFconGestAcciones)Class.forName(clase).newInstance();
            }catch(Exception ex){
                log.error("La clase no se encuentra en la ruta especificada",ex);
            }
            
        }
        return instancia;
    }
    
//public abstract ProcesadorEventosAbstracto crearControl(ItfConsumidorPercepcion percConsumidor, AutomataEFEAbstracto automata,
//												  ItfProductorPercepcion percProductor, String nombreDelControl);
public   InterpreteAutomataEFconGestAcciones crearInterpreteAutomataEFconGestAcciones(String rutaCarpetaAcciones, String nombreFicheroTablaEstados
            ,Boolean activarTrazas)throws ExcepcionEnComponente{
        new ConfiguracionTrazas(logger);

        trazas = NombresPredefinidos.RECURSO_TRAZAS_OBJ;
        if (xmLParserTablaEstadosAutomata==null)xmLParserTablaEstadosAutomata = new XMLParserTablaEstadosAutomataEFinputObj();

//         accionesSemanticasEspecificas.setLogger(logger);
       
        // crea las Acciones semnticas referidas al contenedor de acciones

        // crea el automata de control
   //     AutomataEFEAbstracto ac = null;

            try {
//                verificar que existe la ruta donde deben encontrarse las acciones
//               tablaEstadosAutomata = xmLParserTablaEstadosAutomata.extraeTablaEstadosDesdeFicheroXML(nombreFicheroTablaEstados);
//                this.accionesSemanticas = new EjecutorDeAccionesImp(accionesSemanticasEspecificas);
//                automataControl = new AutomataEFEImp(nombreFicheroTablaEstados, accionesSemanticas, AutomataEFEImp.NIVEL_TRAZA_DESACTIVADO,nombreAgente );
//                return new InterpreteAutomataEFconGestAcciones(tablaEstadosAutomata,activarTrazas);
                }
                catch (Exception ExcepcionNoSePudoCrearAutomataEFE)
                {
                logger.error("Error al crear un  automata  utilizando el fichero " + nombreFicheroTablaEstados);
                trazas.aceptaNuevaTraza(new InfoTraza("Automata Estados Finitos",
                        "Error al crear un  automata  utilizando el fichero " + nombreFicheroTablaEstados,
                        InfoTraza.NivelTraza.error));
                throw new ExcepcionEnComponente ("FactoriaAutomataEFconGestAcciones", "no se pudo crear el Automata EFE","Automta EFE","automataControl = new AutomataEFEImp(" );
                }
        // Crea el procesador de eventos
     //    this.procesadorEventos = new ProcesadorEventosImp(percConsumidor, automataControl,percProductor, nombreDelAgente);

           // return  itfGestionControlAgteCreado = (ItfGestionControlAgteReactivo) new ProcesadorEventosImp(percConsumidor,automataControl,percProductor, nombreDelAgente);

return null;  // ***********
    //elijo la implementacin adecuada (aunque podra haber ms)

}

 
// **   protected GestorAccionesImp accionesSemanticas;

    protected InterpreteAutomataEFconGestAcciones automataControl=null;
    /**
	 * @uml.property  name="dEBUG"
	 */
    private boolean DEBUG = false;
    /**
	 * Conocimiento del agente reactivo
	 * @uml.property  name="itfUsoGestorAReportar"
	 * @uml.associationEnd
	 */
    protected ItfAutomataEFconGestAcciones itfAutomataCreado;
    /**
	 * @uml.property  name="logger"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
    private Logger logger = Logger.getLogger(this.getClass().getCanonicalName());
    /**
	 * @uml.property  name="trazas"
	 * @uml.associationEnd
	 */
    protected ItfUsoRecursoTrazas trazas;

//    private ItfProductorPercepcion percepcionProductor;

//    @Override
//    public ProcesadorInfoReactivoImp crearControlAgteReactivo(AccionesSemanticasAgenteReactivo accionesSemanticasEspecificas, String nombreFicheroTablaEstados, AgenteReactivoAbstracto agente)throws ExcepcionEnComponente {
//
//        new ConfiguracionTrazas(logger);
//
//        trazas = NombresPredefinidos.RECURSO_TRAZAS_OBJ;
//
//         accionesSemanticasEspecificas.setLogger(logger);
//       
//        // crea las Acciones semnticas referidas al contenedor de acciones
//
//        // crea el automata de control
//   //     AutomataEFEAbstracto ac = null;
//
//            try {
//                nombreAgente = agente.getIdentAgente();
//                this.accionesSemanticas = new EjecutorDeAccionesImp(accionesSemanticasEspecificas);
//                automataControl = new AutomataEFEImp(nombreFicheroTablaEstados, accionesSemanticas, AutomataEFEImp.NIVEL_TRAZA_DESACTIVADO,nombreAgente );
//                return new ProcesadorInfoReactivoImp(automataControl, accionesSemanticasEspecificas,  agente);
//                }
//                catch (Exception ExcepcionNoSePudoCrearAutomataEFE)
//                {
//                logger.error("Error al crear el automata del agente " + nombreAgente + " utilizando el fichero " + nombreFicheroTablaEstados);
//                trazas.aceptaNuevaTraza(new InfoTraza(nombreAgente,
//                        "Error al crear el automata del agente " + nombreAgente + " utilizando el fichero " + nombreFicheroTablaEstados,
//                        InfoTraza.NivelTraza.error));
//                throw new ExcepcionEnComponente ("AutomataEFEImp", "no se pudo crear el Automata EFE","Automta EFE","automataControl = new AutomataEFEImp(" );
//                }
//        // Crea el procesador de eventos
//     //    this.procesadorEventos = new ProcesadorEventosImp(percConsumidor, automataControl,percProductor, nombreDelAgente);
//
//           // return  itfGestionControlAgteCreado = (ItfGestionControlAgteReactivo) new ProcesadorEventosImp(percConsumidor,automataControl,percProductor, nombreDelAgente);
//
//
//    //elijo la implementacin adecuada (aunque podra haber ms)
//    }
//    @Override
//    public ProcesadorEventosImp crearControlAgteReactivo(AccionesSemanticasAgenteReactivo accionesSemanticasEspecificas, String nombreFicheroTablaEstados, String nombreDelAgente,ItfConsumidorPercepcion percConsumidor,
//			ItfProductorPercepcion percProductor)throws ExcepcionEnComponente {
//        return null;
//    }
//       

    
}
