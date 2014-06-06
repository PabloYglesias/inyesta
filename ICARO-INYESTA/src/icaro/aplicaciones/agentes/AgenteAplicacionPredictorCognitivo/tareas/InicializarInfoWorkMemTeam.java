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
import icaro.aplicaciones.informacion.estructuraEquipos.InfoEquipoAgentes;
import icaro.aplicaciones.informacion.estructuraEquipos.PredictorStatus;
import icaro.infraestructura.entidadesBasicas.NombresPredefinidos;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.Focus;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.MisObjetivos;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.TareaSincrona;
import icaro.infraestructura.recursosOrganizacion.recursoTrazas.imp.componentes.InfoTraza;
/**
 * 
 * @author F Garijo
 */
public class InicializarInfoWorkMemTeam extends TareaSincrona {
            String miIdentAgte ;
            String identEquipo;
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
             
             miIdentAgte = this.getIdentAgente();
             identEquipo = this.getItfUsoConfiguracion().getValorPropiedadGlobal(NombresPredefinidos.NOMBRE_PROPIEDAD_GLOBAL_EQUIPO_AGENTES);
             
            InfoEquipoAgentes miEquipo = new InfoEquipoAgentes(miIdentAgte, identEquipo);            
            PredictorStatus miStatus = new PredictorStatus(miIdentAgte);
            miStatus.setIdPredictorRol("manager");
            this.getEnvioHechos().insertarHecho(miEquipo);
            this.getEnvioHechos().insertarHecho(miStatus);
            
       } catch (Exception e) 
       {
            e.printStackTrace();
            trazas.aceptaNuevaTraza(new InfoTraza(this.getIdentAgente(), "Error al ejecutar la tarea : "+this.getIdentTarea() + e, InfoTraza.NivelTraza.error));
       }
    }
   
}

