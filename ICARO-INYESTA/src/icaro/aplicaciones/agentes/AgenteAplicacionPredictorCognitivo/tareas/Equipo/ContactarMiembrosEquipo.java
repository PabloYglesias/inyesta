/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package icaro.aplicaciones.agentes.AgenteAplicacionPredictorCognitivo.tareas.Equipo;

import icaro.aplicaciones.informacion.estructuraEquipos.InfoRolAgente;
import icaro.aplicaciones.informacion.estructuraEquipos.VocabularioEquipos;

import icaro.aplicaciones.informacion.estructuraEquipos.InfoEquipoAgentes;
import icaro.aplicaciones.informacion.estructuraEquipos.PredictorStatus;
import icaro.aplicaciones.recursos.visualizacionAcceso.imp.NotificacionesEventosVisAcceso;
import icaro.infraestructura.entidadesBasicas.NombresPredefinidos;
import icaro.infraestructura.entidadesBasicas.comunicacion.EventoRecAgte;
import icaro.infraestructura.entidadesBasicas.interfaces.InterfazUsoAgente;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.TareaSincrona;
import icaro.infraestructura.recursosOrganizacion.recursoTrazas.ItfUsoRecursoTrazas;
import icaro.infraestructura.recursosOrganizacion.recursoTrazas.imp.componentes.InfoTraza;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Francisco J Garijo
 */
public class ContactarMiembrosEquipo extends TareaSincrona {

    private InterfazUsoAgente agenteReceptor;
    private ArrayList<String> agentesEquipo;//resto de agentes que forman mi equipo                                
    private int mi_eval, mi_eval_nueva;
    private String nombreAgenteEmisor;
    private InterfazUsoAgente itfUsoAgenteReceptor;

   // private TimeOutRespuestas tiempoSinRecibirRespuesta;  //no usado
    @Override
    public void ejecutar(Object... params) {
        try {
            PredictorStatus miStatus = (PredictorStatus) params[0];
            InfoEquipoAgentes miEquipo = (InfoEquipoAgentes) params[1];
            nombreAgenteEmisor = this.getAgente().getIdentAgente();
            agentesEquipo = miEquipo.getTeamMemberIDs();
            trazas.aceptaNuevaTraza(new InfoTraza(nombreAgenteEmisor, "Se Ejecuta la Tarea :" + identTarea, InfoTraza.NivelTraza.debug));
            //            trazas.aceptaNuevaTraza(new InfoTraza(nombreAgenteEmisor, "Enviamos la evaluacion " + miEvaluacion, InfoTraza.NivelTraza.masterIA));          
            InfoRolAgente mirol = new InfoRolAgente(nombreAgenteEmisor, miEquipo.getTeamId(), miStatus.getIdPredictorRol(), VocabularioEquipos.IdentMisionEquipo);
            trazas.aceptaNuevaTraza(new InfoTraza(nombreAgenteEmisor, "Enviamos informacion de Rol " + mirol.toString(), InfoTraza.NivelTraza.info));

            enviarEventoGrupo(mirol, agentesEquipo);
            //this.getComunicator().informaraGrupoAgentes(mirol, agentesEquipo);

            miEquipo.setinicioContactoConEquipo();
            miEquipo.setidentMiRolEnEsteEquipo(miStatus.getIdPredictorRol());
            this.getEnvioHechos().actualizarHechoWithoutFireRules(miEquipo);
            trazas.aceptaNuevaTraza(new InfoTraza(nombreAgenteEmisor, "Numero de agentes de los que espero respuesta:" + agentesEquipo.size(), InfoTraza.NivelTraza.info));
            this.generarInformeTemporizadoFromConfigProperty(VocabularioEquipos.IdentTareaTimeOutContactarMiembrosEquipo, null, nombreAgenteEmisor, null);
            // en le caso de que ya la haya enviado la evaluacion no hago nada
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void enviarEventoGrupo(InfoRolAgente mirol, ArrayList<String> identsAgteReceptores) {
        int numeroAgentesEquipo = identsAgteReceptores.size();
        if (numeroAgentesEquipo > 0) {
            for (int i = 0; i < numeroAgentesEquipo; i++) 
            {
                enviarEventoaOtroAgente(new EventoRecAgte("identificate", mirol, nombreAgenteEmisor,/* NombresPredefinidos.NOMBRE_AGENTE_APLICACION +*/ identsAgteReceptores.get(i)), identsAgteReceptores.get(i));
            }
        }
    }
        void enviarEventoaOtroAgente(EventoRecAgte eventoaEnviar, String IdentAgenteReceptor)
        {

        // Este método crea un evento con la información de entrada y se le envía al agente REACTIVO que se indique por medio de
        // la  interfaz de uso
        //     EventoRecAgte eventoaEnviar = null;

        // Se verifica que el identificador del agente a enviar el evento esta definido Si no esta definido se saca un
        // mensaje de error y se deja que se produzca una excepcion.

        if (IdentAgenteReceptor == null) {

            } else {

                try {
                    itfUsoAgenteReceptor = (InterfazUsoAgente) NombresPredefinidos.REPOSITORIO_INTERFACES_OBJ.obtenerInterfaz(NombresPredefinidos.ITF_USO + /*NombresPredefinidos.NOMBRE_AGENTE_APLICACION +*/ IdentAgenteReceptor);
                } catch (Exception e) {
                    Logger.getLogger(NotificacionesEventosVisAcceso.class.getName()).log(Level.SEVERE, null, e);
                    //      Logger.error("Ha habido un problema enviar un  evento al agente "+IdentAgenteReceptor);
                    NombresPredefinidos.RECURSO_TRAZAS_OBJ.aceptaNuevaTraza(new InfoTraza("Test",
                            "Ha habido un problema enviar el evento con informacion" + eventoaEnviar + "al   agente " + IdentAgenteReceptor,
                            InfoTraza.NivelTraza.error));
                }

                try {
                    itfUsoAgenteReceptor.aceptaEvento(eventoaEnviar);
                    NombresPredefinidos.RECURSO_TRAZAS_OBJ.aceptaNuevaTraza(new InfoTraza("Test",
                            "Se envia el evento con input :  " + eventoaEnviar + "al   agente " + IdentAgenteReceptor,
                            InfoTraza.NivelTraza.debug));
                } catch (Exception e) {
//			Logger.error("Ha habido un problema enviar un  evento al agente "+IdentAgenteReceptor);
                    Logger.getLogger(NotificacionesEventosVisAcceso.class.getName()).log(Level.SEVERE, null, e);
                    NombresPredefinidos.RECURSO_TRAZAS_OBJ.aceptaNuevaTraza(new InfoTraza("Test",
                            "Ha habido un problema enviar el evento con informacion " + eventoaEnviar + "al   agente " + IdentAgenteReceptor,
                            InfoTraza.NivelTraza.error));
                }
            }
        }
    
}
