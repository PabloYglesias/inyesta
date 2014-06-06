/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package icaro.aplicaciones.agentes.AgenteAplicacionPredictorCognitivo.tareas;
import icaro.aplicaciones.informacion.estructuraEquipos.InfoEquipoAgentes;
import icaro.aplicaciones.agentes.AgenteAplicacionPredictorCognitivo.informacion.InfoParaDecidirPrediccion;

import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.Objetivo;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.TareaSincrona;

/**
 *
 * @author Francisco J Garijo
 */
public class InicializarInfoParaDecidir extends TareaSincrona{

   @Override
   public void ejecutar(Object... params) {
	   try {

             String nombreAgenteEmisor = this.getIdentAgente();

             InfoEquipoAgentes miEquipo = (InfoEquipoAgentes)params[0];
             InfoParaDecidirPrediccion infoPrediccion = new InfoParaDecidirPrediccion(nombreAgenteEmisor,miEquipo );
             this.getEnvioHechos().insertarHecho(infoPrediccion);
             // Activo un timeout para la decision. Cuando venza se decidira que hacer en funcion de la situacion del agente
             // Porque se supone que estoy esperando informaciones que no llegan. 
             
       } catch (Exception e) {
			 e.printStackTrace();
       }
   }

}
