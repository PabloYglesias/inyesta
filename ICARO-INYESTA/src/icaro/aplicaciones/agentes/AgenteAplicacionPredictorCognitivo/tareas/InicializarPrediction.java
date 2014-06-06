/*
 * SolicitarDatos.java
 *
 * Creado 23 de abril de 2007, 12:52
 *
 * Telefonica I+D Copyright 2006-2007
 */
package icaro.aplicaciones.agentes.AgenteAplicacionPredictorCognitivo.tareas;
import icaro.aplicaciones.agentes.AgenteAplicacionPredictorCognitivo.objetivos.DefinirMiEquipo;
import icaro.aplicaciones.agentes.AgenteAplicacionPredictorCognitivo.objetivos.ObtenerPrediccion;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.Focus;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.TareaSincrona;
import icaro.infraestructura.recursosOrganizacion.recursoTrazas.imp.componentes.InfoTraza;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.MisObjetivos;
/**
 * 
 * @author F Garijo
 */
public class InicializarPrediction extends TareaSincrona {

    @Override
   public void ejecutar(Object... params) {
	try {   
        //     Objetivo objetivoEjecutantedeTarea = (Objetivo)params[0];
             String identTarea = this.getIdentTarea();
             String nombreAgenteEmisor = this.getIdentAgente();
             this.getItfConfigMotorDeReglas().setDepuracionActivationRulesDebugging(true);
             this.getItfConfigMotorDeReglas().setfactHandlesMonitoring_afterActivationFired_DEBUGGING(true);
             this.getEnvioHechos().insertarHechoWithoutFireRules(new Focus());
             this.getEnvioHechos().insertarHecho(new MisObjetivos());
             this.getEnvioHechos().insertarHecho(new ObtenerPrediccion());
            // this.getEnvioHechos().insertarHecho(new DefinirMiEquipo());
             
       } catch (Exception e) 
       {
            e.printStackTrace();
            trazas.aceptaNuevaTraza(new InfoTraza(this.getIdentAgente(), "Error al ejecutar la tarea : "+this.getIdentTarea() + e, InfoTraza.NivelTraza.error));
       }
    }
   
}

