/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package icaro.aplicaciones.agentes.AgenteAplicacionPlanificadorCognitivo.tareas;

/**
 *
 * @author FGarijo
 */
import icaro.aplicaciones.informacion.dominioClases.aplicacionAcceso.InfoAccesoSinValidar;
import icaro.aplicaciones.informacion.dominioClases.aplicacionPrediccion.InfoPrediccionSinValidar;
import icaro.aplicaciones.informacion.dominioClases.aplicacionPrediccion.InfoPrediccionValidada;
import icaro.aplicaciones.informacion.dominioClases.aplicacionPrediccion.VocabularioSistemaPrediccion;
import icaro.aplicaciones.recursos.persistenciaPrediccionSimple.ItfUsoPersistenciaPrediccionSimple;
import icaro.aplicaciones.recursos.visualizacionAcceso.imp.NotificacionesEventosVisAcceso;

import icaro.aplicaciones.recursos.visualizacionPrediccion.ItfUsoVisualizadorPrediccion;
import icaro.infraestructura.entidadesBasicas.NombresPredefinidos;
import icaro.infraestructura.entidadesBasicas.PerformativaUsuario;
import icaro.infraestructura.entidadesBasicas.comunicacion.AdaptadorRegRMI;
import icaro.infraestructura.entidadesBasicas.comunicacion.EventoRecAgte;
import icaro.infraestructura.entidadesBasicas.comunicacion.InfoContEvtMsgAgteReactivo;
import icaro.infraestructura.entidadesBasicas.interfaces.InterfazUsoAgente;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.CausaTerminacionTarea;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.Objetivo;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.Tarea;
import icaro.infraestructura.recursosOrganizacion.recursoTrazas.imp.componentes.InfoTraza;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pablo C. Cañizares
 */
public class ValidarDatosPlanificacion extends Tarea {

    private String identAgenteOrdenante;
    private Objetivo contextoEjecucionTarea = null;
    private InterfazUsoAgente itfUsoAgenteReceptor;
    
    @Override
    public void ejecutar(Object... params) 
    {
        String IdentRecursoVisualizacionPrediccion = VocabularioSistemaPrediccion.IdentRecursoVisualizacionPrediccionInicial;
        String IdentRecursoPersistencia = VocabularioSistemaPrediccion.IdentRecursoPersistenciaPrediccion;
        String IdentRecursoValidacion = VocabularioSistemaPrediccion.IdentRecursoValidacion;
        
//	Se extraen  los datos de los parametros
       // PerformativaUsuario infoUsuario = (PerformativaUsuario) params[0];
       // Object[] parametrosPerfortiva = (Object[]) infoUsuario.getParametros();
        
	InfoContEvtMsgAgteReactivo infoUsuario = (InfoContEvtMsgAgteReactivo) params[0];
        Object [] parametrosInfoCont = (Object [])infoUsuario.getvaloresParametrosAccion();
        InfoPrediccionSinValidar infoPrediccion = (InfoPrediccionSinValidar)parametrosInfoCont[0];
        String equipo1 = infoPrediccion.tomaEquipo1();
        String equipo2 = infoPrediccion.tomaEquipo2();
        String fecha = infoPrediccion.tomaFecha();
        
        try {
            // Se obtienen las interfaces de los recursos. Si no se pueden obtener las interfaces se debe generar un informe de tarea
            String identTarea = this.getIdentTarea();
            identAgenteOrdenante = this.getIdentAgente();

            //Tomamos los datos a validar 
            
            ItfUsoPersistenciaPrediccionSimple itfUsoPersistencia = (ItfUsoPersistenciaPrediccionSimple) NombresPredefinidos.REPOSITORIO_INTERFACES_OBJ.obtenerInterfaz(
                    NombresPredefinidos.ITF_USO + IdentRecursoPersistencia);
            
            if (itfUsoPersistencia == null) {
                this.generarInformeConCausaTerminacion(identTarea, contextoEjecucionTarea, identAgenteOrdenante, "Error-AlObtener:Interfaz_" + IdentRecursoPersistencia, CausaTerminacionTarea.ERROR);
            } else 
            {
                //Los mandamos al recurso de validacion
                boolean resultadoValidacion = itfUsoPersistencia.compruebaPartido(infoPrediccion.tomaEquipo1(),
                        infoPrediccion.tomaEquipo2(), infoPrediccion.tomaFecha());

                String contenidoInformeTarea;
                if (resultadoValidacion) {
                    contenidoInformeTarea = VocabularioSistemaPrediccion.ResultadoPrediccion_DatosValidados;
                } else {
                    contenidoInformeTarea = VocabularioSistemaPrediccion.ResultadoPrediccion_DatosNoValidados;
                }
               
               this.generarInformeOK(identTarea, contextoEjecucionTarea, identAgenteOrdenante, contenidoInformeTarea);
            }
        } catch (Exception e) {
            e.printStackTrace();
            this.generarInformeConCausaTerminacion(this.getIdentTarea(), contextoEjecucionTarea, identAgenteOrdenante, "Error-AlObtener:Interfaz_Recurso_Persistencia", CausaTerminacionTarea.ERROR);
        }
    }

    void enviarEventoaOtroAgente(EventoRecAgte eventoaEnviar, String IdentAgenteReceptor) 
    {

        // Este método crea un evento con la información de entrada y se le envía al agente REACTIVO que se indique por medio de
        // la  interfaz de uso
        //     EventoRecAgte eventoaEnviar = null;

        // Se verifica que el identificador del agente a enviar el evento esta definido Si no esta definido se saca un
        // mensaje de error y se deja que se produzca una excepcion.

        if (IdentAgenteReceptor == null)
        {
            
        } else {

            try {
                itfUsoAgenteReceptor = (InterfazUsoAgente) NombresPredefinidos.REPOSITORIO_INTERFACES_OBJ.obtenerInterfaz(NombresPredefinidos.ITF_USO + IdentAgenteReceptor);
            } catch (Exception e) {
                Logger.getLogger(NotificacionesEventosVisAcceso.class.getName()).log(Level.SEVERE, null, e);
                //      Logger.error("Ha habido un problema enviar un  evento al agente "+IdentAgenteReceptor);
                NombresPredefinidos.RECURSO_TRAZAS_OBJ.aceptaNuevaTraza(new InfoTraza("Test",
                        "Ha habido un problema enviar el evento con informacion" + eventoaEnviar + "al   agente " + IdentAgenteReceptor,
                        InfoTraza.NivelTraza.error));
            }

            try {
                itfUsoAgenteReceptor.aceptaEvento(eventoaEnviar);
                NombresPredefinidos.RECURSO_TRAZAS_OBJ.aceptaNuevaTraza(new InfoTraza("PredictorReactivo",
                        "Se envia el evento con input :  " + eventoaEnviar + "al   agente " + IdentAgenteReceptor,
                        InfoTraza.NivelTraza.debug));
            } catch (Exception e) {
//			Logger.error("Ha habido un problema enviar un  evento al agente "+IdentAgenteReceptor);
                Logger.getLogger(NotificacionesEventosVisAcceso.class.getName()).log(Level.SEVERE, null, e);
                NombresPredefinidos.RECURSO_TRAZAS_OBJ.aceptaNuevaTraza(new InfoTraza("PredictorReactivo",
                        "Ha habido un problema enviar el evento con informacion " + eventoaEnviar + "al   agente " + IdentAgenteReceptor,
                        InfoTraza.NivelTraza.error));
            }
        }
    }    
}
