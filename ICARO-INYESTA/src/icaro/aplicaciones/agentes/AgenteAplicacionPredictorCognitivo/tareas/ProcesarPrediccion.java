/*
 * PermitirPrediccion.java
 *
 * Creado 23 de abril de 2007, 12:50
 *
 * Telefonica I+D Copyright 2006-2007
 */
package icaro.aplicaciones.agentes.AgenteAplicacionPredictorCognitivo.tareas;

import icaro.aplicaciones.agentes.AgenteAplicacionPredictorCognitivo.informacion.InfoParaDecidirPrediccion;
import icaro.aplicaciones.informacion.dominioClases.aplicacionPrediccion.InfoPrediccionSinValidar;
import icaro.aplicaciones.informacion.dominioClases.aplicacionPrediccion.InfoPrediccionValidada;
import icaro.aplicaciones.informacion.dominioClases.aplicacionPrediccion.VocabularioSistemaPrediccion;
import icaro.aplicaciones.recursos.visualizacionAcceso.imp.NotificacionesEventosVisAcceso;
import icaro.aplicaciones.recursos.visualizacionPrediccion.ItfUsoVisualizadorPrediccion;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.Tarea;
import icaro.infraestructura.entidadesBasicas.NombresPredefinidos;
import icaro.infraestructura.entidadesBasicas.comunicacion.AdaptadorRegRMI;
import icaro.infraestructura.entidadesBasicas.comunicacion.EventoRecAgte;
import icaro.infraestructura.entidadesBasicas.comunicacion.InfoContEvtMsgAgteReactivo;
import icaro.infraestructura.entidadesBasicas.interfaces.InterfazUsoAgente;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.CausaTerminacionTarea;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.Objetivo;
import icaro.infraestructura.recursosOrganizacion.recursoTrazas.imp.componentes.InfoTraza;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pablo C. Cañizares
 */
public class ProcesarPrediccion extends Tarea {

    private String identAgenteOrdenante;
    private Objetivo contextoEjecucionTarea = null;
    private InterfazUsoAgente itfUsoAgenteReceptor;

    @Override
    public void ejecutar(Object... params)
    {
        
        String identDeEstaTarea = this.getIdentTarea();

        InfoParaDecidirPrediccion infoDecisionPred = (InfoParaDecidirPrediccion) params[0];
        
        InfoContEvtMsgAgteReactivo infoUsuario = (InfoContEvtMsgAgteReactivo) params[1];
        Object[] parametrosInfoCont = (Object[]) infoUsuario.getvaloresParametrosAccion();
        InfoPrediccionValidada infoPrediccion = (InfoPrediccionValidada) parametrosInfoCont[0];

        infoDecisionPred.insertarPrediccion(infoPrediccion);
        
        this.getEnvioHechos().actualizarHecho(infoDecisionPred);
        trazas.aceptaNuevaTraza(new InfoTraza(this.getIdentAgente(), "Insertando prediccion", InfoTraza.NivelTraza.info));
    }

}
