/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package icaro.aplicaciones.agentes.AgenteAplicacionPredictorCognitivo.tareas.Equipo;

import icaro.aplicaciones.informacion.dominioClases.aplicacionPrediccion.InfoPrediccionSinValidar;
import icaro.aplicaciones.informacion.estructuraEquipos.InfoEquipoAgentes;
import icaro.aplicaciones.informacion.estructuraEquipos.InfoRolAgente;
import icaro.aplicaciones.informacion.estructuraEquipos.VocabularioEquipos;
import icaro.infraestructura.entidadesBasicas.comunicacion.InfoContEvtMsgAgteReactivo;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.TareaSincrona;
import icaro.infraestructura.recursosOrganizacion.recursoTrazas.imp.componentes.InfoTraza;
/**
 *
 * @author FGarijo
 */
public class ProcesarInfoRolAgente extends TareaSincrona {
    
    @Override
    public void ejecutar(Object... params) {
            try {
                
                InfoContEvtMsgAgteReactivo infoUsuario = (InfoContEvtMsgAgteReactivo) params[1];
                Object [] parametrosInfoCont = (Object [])infoUsuario.getvaloresParametrosAccion();
                InfoRolAgente infoRolRecibido = (InfoRolAgente)parametrosInfoCont[0];
                
                InfoEquipoAgentes  miEquipo = (InfoEquipoAgentes)params[0];                
                
                miEquipo.procesarInfoRolAgente(infoRolRecibido);
                // Si el equipo es jerarquico y el Rol es de agente asignador de tareas entonces
                // se obtiene su identificador y se le pone como  jefe en el equipo
                  if (infoRolRecibido.getidentRolAgte().equals(VocabularioEquipos.IdentRolAgteDistribuidorTareas))
                    miEquipo.setidentAgenteJefeEquipo(infoRolRecibido.getAgteIniciadorId());
                  
                  this.getEnvioHechos().actualizarHecho(miEquipo);
                  trazas.trazar(this.getIdentAgente(), "Se procesa el InfoRol recibido :  "+ infoRolRecibido.toString(), InfoTraza.NivelTraza.debug);
               }         
            catch(Exception e) {
                  e.printStackTrace();
            }
    }
}
