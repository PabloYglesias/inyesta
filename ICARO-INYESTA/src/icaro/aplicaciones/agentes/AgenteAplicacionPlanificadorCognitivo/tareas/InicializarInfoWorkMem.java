/*
 * SolicitarDatos.java
 *
 * Creado 23 de abril de 2007, 12:52
 *
 * Telefonica I+D Copyright 2006-2007
 */
package icaro.aplicaciones.agentes.AgenteAplicacionPlanificadorCognitivo.tareas;

import icaro.aplicaciones.agentes.AgenteAplicacionPlanificadorCognitivo.informacion.InfoParaPlanificar;
import icaro.aplicaciones.agentes.AgenteAplicacionPlanificadorCognitivo.objetivos.RealizarPlanificacion;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.Focus;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.TareaSincrona;
import icaro.infraestructura.recursosOrganizacion.recursoTrazas.imp.componentes.InfoTraza;

/**
 *
 * @author F Garijo
 */
public class InicializarInfoWorkMem extends TareaSincrona {

    @Override
    public void ejecutar(Object... params) {
        try {
            //     Objetivo objetivoEjecutantedeTarea = (Objetivo)params[0];
            String identTarea = this.getIdentTarea();
            String nombreAgenteEmisor = this.getIdentAgente();
            InfoParaPlanificar miPlanificacion = new InfoParaPlanificar(nombreAgenteEmisor);

            this.getItfConfigMotorDeReglas().setDepuracionActivationRulesDebugging(true);
            this.getItfConfigMotorDeReglas().setfactHandlesMonitoring_afterActivationFired_DEBUGGING(true);
            this.getEnvioHechos().insertarHechoWithoutFireRules(new Focus());
            this.getEnvioHechos().insertarHecho(new RealizarPlanificacion());

            this.getEnvioHechos().insertarHecho(miPlanificacion);

        } catch (Exception e) {
            e.printStackTrace();
            trazas.aceptaNuevaTraza(new InfoTraza(this.getIdentAgente(), "Error al ejecutar la tarea : " + this.getIdentTarea() + e, InfoTraza.NivelTraza.error));
        }
    }

}
