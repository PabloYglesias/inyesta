/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package icaro.infraestructura.entidadesBasicas.componentesBasicos.automatas.gestorAcciones.imp;

import icaro.infraestructura.entidadesBasicas.NombresPredefinidos;
import icaro.infraestructura.entidadesBasicas.componentesBasicos.automatas.automataEFconGesAcciones.ItfAutomataEFconGestAcciones;
import icaro.infraestructura.entidadesBasicas.componentesBasicos.automatas.clasesImpAutomatas.Accion;
import icaro.infraestructura.entidadesBasicas.componentesBasicos.automatas.clasesImpAutomatas.AccionAsincrona;
import icaro.infraestructura.entidadesBasicas.componentesBasicos.automatas.clasesImpAutomatas.AccionProxy;
import icaro.infraestructura.entidadesBasicas.componentesBasicos.automatas.clasesImpAutomatas.AccionSincrona;
import icaro.infraestructura.entidadesBasicas.componentesBasicos.automatas.gestorAcciones.ItfGestorAcciones;
import icaro.infraestructura.entidadesBasicas.comunicacion.ComunicacionAgentes;
import icaro.infraestructura.patronAgenteCognitivo.factoriaEInterfacesPatCogn.AgenteCognitivo;
import icaro.infraestructura.patronAgenteCognitivo.procesadorObjetivos.factoriaEInterfacesPrObj.ItfProcesadorObjetivos;
import icaro.infraestructura.patronAgenteReactivo.control.acciones.AccionesSemanticasAgenteReactivo;
import icaro.infraestructura.patronAgenteReactivo.control.acciones.ExcepcionEjecucionAcciones;
import icaro.infraestructura.recursosOrganizacion.recursoTrazas.ItfUsoRecursoTrazas;
import icaro.infraestructura.recursosOrganizacion.recursoTrazas.imp.componentes.InfoTraza;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

/**
 *
 * @author 
 */
public class GestorAccionesImp implements ItfGestorAcciones{
//    private AgenteCognitivo agente;
    private String identPropietario;
    private ItfAutomataEFconGestAcciones envioInputs;
    private ItfUsoRecursoTrazas trazas;
    private String identAccion;
    private ComunicacionAgentes comunicator;
    private AccionAsincrona accionAsinc ;
    private String accionAsincSimpleName;
    private AccionSincrona accionSinc ;
    private String accionSincSimpleName;
    private AccionesSemanticasAgenteReactivo accionesSemAgteReactivo ;
    private String accionesSemAgteReactivoSimpleName;
    private Map<String, AccionSincrona> accionesCreadas;
    private Logger log = Logger.getLogger(GestorAccionesImp.class);
    
//    public GestorAccionesImp(AgenteCognitivo agente,ItfProcesadorObjetivos envioHechos){
    public GestorAccionesImp(String propietarioId){
        this.identPropietario = propietarioId;
//        this.envioInputs = itfautomata;
        accionAsincSimpleName = AccionAsincrona.class.getSimpleName();
        accionSincSimpleName = AccionSincrona.class.getSimpleName();
        this.trazas= NombresPredefinidos.RECURSO_TRAZAS_OBJ;
        comunicator = new ComunicacionAgentes (propietarioId);
        accionesCreadas = new HashMap <String, AccionSincrona>();
    }
    public void setItfAutomataEFconGestAcciones(ItfAutomataEFconGestAcciones itfautomata){
        envioInputs = itfautomata;
    }

    @Override
    public Accion crearAccion(Class clase) throws Exception {
        Accion accion = (Accion)clase.newInstance(); 
//        accion.setEnvioHechos(envioHechos);
//        accion.setAgente(agente);
//        accion.setIdentAgente (agente.getIdentAgente());
        accion.setComunicator(comunicator);
//        String identAccionLong = accion.getClass().getName();
//        identAccion = identAccionLong.substring(identAccionLong.lastIndexOf(".")+1);
        String identAccion = accion.getClass().getSimpleName();
        accion.setIdentAccion(identAccion);
        log.debug("Accion creada:"+clase.getName());
        trazas = NombresPredefinidos.RECURSO_TRAZAS_OBJ;		
        return new AccionProxy(accion);
    }
    @Override
    public AccionSincrona crearAccionSincrona(Class clase) throws Exception {
        String identAccion = clase.getSimpleName();
        if (accionesCreadas.containsKey(identAccion)) return accionesCreadas.get(identAccion);
        AccionSincrona accion = (AccionSincrona)clase.newInstance(); 
        accion.setItfAutomata(envioInputs);
//        accion.setAgente(agente);
//        accion.setIdentAgente (agente.getIdentAgente());
        accion.setComunicator(comunicator);
//        String identAccionLong = accion.getClass().getName();
//        identAccion = identAccionLong.substring(identAccionLong.lastIndexOf(".")+1);
//        String identAccion = accion.getClass().getSimpleName();
        accionesCreadas.put(identAccion, accion);
        accion.setIdentAccion(identAccion);
        log.debug("Accion creada:"+clase.getName());
        accion.setTrazas(NombresPredefinidos.RECURSO_TRAZAS_OBJ);	
        return accion;
    }
    @Override
    public AccionSincrona crearAccionAsincrona(Class clase) throws Exception {
        String identAccion = clase.getSimpleName();
        if (accionesCreadas.containsKey(identAccion)) return accionesCreadas.get(identAccion);
        AccionSincrona accion = (AccionAsincrona)clase.newInstance(); 
//        accion.setEnvioHechos(envioHechos);
//        accion.setAgente(agente);
//        accion.setIdentAgente (agente.getIdentAgente());
        accion.setComunicator(comunicator);
//        String identAccionLong = accion.getClass().getName();
//        identAccion = identAccionLong.substring(identAccionLong.lastIndexOf(".")+1);
//        String identAccion = accion.getClass().getSimpleName();
        accionesCreadas.put(identAccion, accion);
        accion.setIdentAccion(identAccion);
        log.debug("Accion creada:"+clase.getName());
        accion.setTrazas(NombresPredefinidos.RECURSO_TRAZAS_OBJ);	
        return accion;
    }
    public void inicializarAccion(AccionSincrona accion) throws Exception {
    //    AccionSincrona accion = (AccionSincrona)clase.newInstance(); 
        accion.setItfAutomata(envioInputs);
//        accion.setAgente(agente);
//        accion.setIdentAgente (agente.getIdentAgente());
        accion.setComunicator(comunicator);
        accion.setIdentAccion(accion.getClass().getSimpleName());
 //       log.debug("Accion creada:"+clase.getName());
 //       accion.trazas = NombresPredefinidos.RECURSO_TRAZAS_OBJ;	
 //       return accion;
    }
    
    @Override
    public void ejecutar(Object... paramsEjecucion) throws Exception {
        
        if(paramsEjecucion==null){
              this.trazas.trazar ( this.getClass().getSimpleName(), "Error en  la ejecucion de la accion: " +
                    " No se han especificado parametros de ejecucion", InfoTraza.NivelTraza.error );
        }
        Class claseAccionEjecutar = (Class)paramsEjecucion[0];
       // Extraccion de parametros y verificacion de la clase
        if ( claseAccionEjecutar == null){
            this.trazas.trazar ( this.getClass().getSimpleName(), "Error en  la ejecucion de la accion: " +
                    " No se ha especificado la clase de las acciones a ejecutar", InfoTraza.NivelTraza.error );
        }
        String superclase = claseAccionEjecutar.getSuperclass().getSimpleName();
        int numparam = paramsEjecucion.length -1;
   
        for (int i = 0; i<numparam; i++){
           paramsEjecucion[i]= paramsEjecucion[i+1];
        }
            paramsEjecucion[numparam]=null;
        if (superclase.equals(accionSincSimpleName) ) {
            accionSinc = crearAccionSincrona(claseAccionEjecutar);
            accionSinc.ejecutar(paramsEjecucion);
        }
        else if (superclase.equals(accionAsincSimpleName)){
             accionAsinc = (AccionAsincrona) claseAccionEjecutar.newInstance();
             inicializarAccion(accionAsinc);
             accionAsinc.setParams(paramsEjecucion);
             accionAsinc.comenzar();
        }
        else {
            this.trazas.trazar ( this.getClass().getSimpleName(), "Error en  la ejecucion de la accion: "+ claseAccionEjecutar.getSimpleName() +
                    " debe extender a AccionSincrona o a AccionAsincrona ", InfoTraza.NivelTraza.error );
        }
        }
    @Override
    public void ejecutarAccion(Class claseAccionEjecutar, Object... paramsEjecucion) throws Exception {
//        Class claseAccionEjecutar = (Class)paramsEjecucion[0];
       // Extraccion de parametros y verificacion de la clase
        String superclase = claseAccionEjecutar.getSuperclass().getSimpleName();
//        int numparam = paramsEjecucion.length ;
        if (superclase.equals(accionSincSimpleName) ) {
            accionSinc = crearAccionSincrona(claseAccionEjecutar);
            accionSinc.ejecutar(paramsEjecucion);
        }
        else if (superclase.equals(accionAsincSimpleName)){
             accionAsinc = (AccionAsincrona) claseAccionEjecutar.newInstance();
             inicializarAccion(accionAsinc);
             accionAsinc.setParams(paramsEjecucion);
             accionAsinc.comenzar();
        }
        else {
            this.trazas.trazar ( this.getClass().getSimpleName(), "Error en  la ejecucion de la accion: "+ claseAccionEjecutar.getSimpleName() +
                    " debe extender a AccionSincrona o a AccionAsincrona ", InfoTraza.NivelTraza.error );
        }
    }       
    @Override
    public synchronized void ejecutarMetodo(Class accion,String identMetodo, Object... paramsEjecucion) throws Exception {
//        protected synchronized void ejecutarAccionBloqueante(String nombre, Object[] parametros) throws ExcepcionEjecucionAcciones {
		
		Class params[] = {};
		Object paramsObj[] = {};
		
		if (paramsEjecucion == null ||(paramsEjecucion.length == 1 && paramsEjecucion[0]==null) ){
                    params = new Class[0];
                    paramsObj = new Object[0];
                }
                else {
			params = new Class[paramsEjecucion.length];
			paramsObj = new Object[paramsEjecucion.length];
			for (int i=0; (i<paramsEjecucion.length &&params[i]!=null); i++) {	
                                params[i] = paramsEjecucion[i].getClass();
				paramsObj[i] = paramsEjecucion[i];
                        }
//		}
//		else {
//			params = new Class[0];
//			paramsObj = new Object[0];
		}

		try	{
//                    Class thisClass = accionesSemanticas.getClass();
//			Object iClass = accionesSemanticas;
//			Class thisClass = this.crearAccionSincrona(accion).getClass();
			Object accionesSemanticas = this.crearAccionSincrona(accion);
			Method thisMethod = accion.getMethod(identMetodo, params);
			thisMethod.invoke(accionesSemanticas, paramsObj);
		}
		catch (IllegalAccessException iae) {
			System.out.println("ERROR en los privilegios de acceso (no es publico) para en metodo: " + identMetodo + " de la clase: " + accion.getClass().getName());
			iae.printStackTrace();
            throw new ExcepcionEjecucionAcciones( "AccionesSemanticasImp", "error al ejecutar un metodo"+ identMetodo + " de la clase: " + accion.getClass().getName(),
                                                          "se ha producido un IlegalAccessIAE");
		}
		catch (NoSuchMethodError nsme) {
			System.out.println("ERROR (NO EXISTE) al invocar el metodo: " + identMetodo + " en la clase: " + accion.getClass().getName());
			nsme.printStackTrace();
            throw new ExcepcionEjecucionAcciones( "AccionesSemanticasImp", "error al ejecutar un metodo"+ identMetodo + " de la clase: " + accion.getClass().getName(),
                                                          "El metodo a invocar no existe. Se ha producido una excepcion NoSuchMethodError");
		}
		catch (NoSuchMethodException nsmee) {
			System.out.println("ERROR (NO EXISTE) al invocar el metodo: " + identMetodo + " en la clase: " + accion.getClass().getName());
			nsmee.printStackTrace();
			System.out.println("Invocando metodo con parametros de sus superclases correspondientes");
			throw new ExcepcionEjecucionAcciones( "AccionesSemanticasImp", "error al ejecutar un metodo"+ identMetodo + " de la clase: " + accion.getClass().getName(),
                                                          "El metodo a invocar no existe. Se ha producido una excepcion NoSuchMethodError");
            //ejecutarAccionBloqueantePolimorfica(nombre, parametros, 1);
		}
		catch (InvocationTargetException ite) {
			System.out.println("ERROR en la ejecucion del metodo: " + identMetodo + " en la clase: " + accion.getClass().getName());
			ite.printStackTrace();
			System.out.println("Excepcion producida en el metodo: ");
			ite.getTargetException().printStackTrace();
		throw new ExcepcionEjecucionAcciones( "AccionesSemanticasImp", "error al ejecutar un metodo"+ identMetodo + " de la clase: " + accion.getClass().getName(),
                                                          "El metodo a invocar no existe. Se ha producido una excepcion InvocationTargetException");
        }
	}
        
 }
