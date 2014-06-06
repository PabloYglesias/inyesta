/*
 * PermitirPrediccion.java
 *
 * Creado 23 de abril de 2007, 12:50
 *
 * Telefonica I+D Copyright 2006-2007
 */
package icaro.aplicaciones.agentes.AgenteAplicacionPredictorCognitivo.tareas;

import icaro.aplicaciones.agentes.AgenteAplicacionPredictorCognitivo.informacion.InfoParaDecidirPrediccion;
import icaro.aplicaciones.informacion.dominioClases.aplicacionPrediccion.InfoPrediccionResultado;
import icaro.aplicaciones.informacion.dominioClases.aplicacionPrediccion.InfoPrediccionResultado.Ganador;
import icaro.aplicaciones.informacion.dominioClases.aplicacionPrediccion.InfoPrediccionSinValidar;
import icaro.aplicaciones.informacion.dominioClases.aplicacionPrediccion.InfoPrediccionValidada;
import icaro.aplicaciones.informacion.dominioClases.aplicacionPrediccion.VocabularioSistemaPrediccion;
import icaro.aplicaciones.recursos.visualizacionAcceso.imp.NotificacionesEventosVisAcceso;
import icaro.aplicaciones.recursos.visualizacionPrediccion.ItfUsoVisualizadorPrediccion;
import icaro.infraestructura.entidadesBasicas.NombresPredefinidos;
import icaro.infraestructura.entidadesBasicas.comunicacion.AdaptadorRegRMI;
import icaro.infraestructura.entidadesBasicas.comunicacion.EventoRecAgte;
import icaro.infraestructura.entidadesBasicas.comunicacion.InfoContEvtMsgAgteReactivo;
import icaro.infraestructura.entidadesBasicas.interfaces.InterfazUsoAgente;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.CausaTerminacionTarea;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.Objetivo;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.Tarea;
import icaro.infraestructura.recursosOrganizacion.recursoTrazas.imp.componentes.InfoTraza;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pablo C. Cañizares
 */
public class MostrarPrediccion extends Tarea {

    private String identAgenteOrdenante;
    private Objetivo contextoEjecucionTarea = null;
    private InterfazUsoAgente itfUsoAgenteReceptor;

    @Override
    public void ejecutar(Object... params) {
        //Habría que pasarle la lista de predicciones
        String identDeEstaTarea = this.getIdentTarea();
        String identRecursoVisualizacionPrediccion = (String) params[1];

        InfoParaDecidirPrediccion infoDecisionPred = (InfoParaDecidirPrediccion) params[0];
        InfoPrediccionValidada infoPrediccionFinal = null;

        //Permutamos la posibilidad entre varios y damos el motivo
        infoPrediccionFinal = doCalculate(infoDecisionPred);
        try {
            identAgenteOrdenante = this.getIdentAgente();
            ItfUsoVisualizadorPrediccion visualizadorPrediccion = (ItfUsoVisualizadorPrediccion) NombresPredefinidos.REPOSITORIO_INTERFACES_OBJ
                    .obtenerInterfaz(NombresPredefinidos.ITF_USO + identRecursoVisualizacionPrediccion);
            if (visualizadorPrediccion == null) {
                this.generarInformeConCausaTerminacion(identDeEstaTarea, contextoEjecucionTarea, identAgenteOrdenante, "Error-AlObtener:Interfaz_Recurso:" + identRecursoVisualizacionPrediccion, CausaTerminacionTarea.ERROR);
            } else {

                visualizadorPrediccion.mostrarMensajeInformacion(identDeEstaTarea, "Prediccion tomada: " + infoPrediccionFinal.tomaResultado() + "\n Motivo: " + infoPrediccionFinal.tomaDescripcionMotivo());

                this.generarInformeOK(identDeEstaTarea, contextoEjecucionTarea, identAgenteOrdenante, VocabularioSistemaPrediccion.NotificacionPrediccionAutorizado);
            }
        } catch (Exception e) {
            this.generarInformeConCausaTerminacion(identDeEstaTarea, contextoEjecucionTarea, identAgenteOrdenante, "Error-AlUtilizar:Interfaces_Recurso:" + identRecursoVisualizacionPrediccion, CausaTerminacionTarea.ERROR);
            e.printStackTrace();
        }
    }
    // El método generarInformOK crea un informe de tarea y se lo envía  al  procesador de objetivos
    // Los valores para crear el informe son los siguientes
    // Identificador de la Tarea que genera el informe : "Tarea:PermitirPrediccion"
    // El contexto en el  que se ejecuta la tarea : ( Opcional) Identificador del objetivo en el que se ejecuta la tarea
    // Identificador del agente que ejecuta la  la tarea 
    //Texto del informe del resultado de la ejecución: "Autorizacion_Prediccion_Notificado_Al_Usuario"

    private InfoPrediccionValidada doCalculate(InfoParaDecidirPrediccion infoDecisionPred) {
        InfoPrediccionValidada infoPrediccion = null;
        int nLocal, nVisitante, nEmpate;
        int nLocalVeces, nVisitanteVeces, nEmpateVeces;
        Ganador ganadorPeso, ganadorVeces;
        String ganadorNombre, ganadorMotivo;
        
        ganadorNombre = ganadorMotivo = "";
        //recorremos la lista y vamos valorando las predicciones y dandoles puntuacion
        List<InfoPrediccionValidada> Lista = infoDecisionPred.getListaPredicciones();

        nLocal = nVisitante = nEmpate = 0;
        nLocalVeces = nVisitanteVeces = nEmpateVeces = 0;

        if (Lista != null) {
            for (int i = 0; i < Lista.size(); i++) {
                InfoPrediccionValidada infoPred = Lista.get(i);
                String nombreAgente = infoPred.getAutor();
                InfoPrediccionResultado res = infoPred.getResultado();
                int nPeso;

                if (nombreAgente.indexOf("Confianza") != -1) {
                    nPeso = 10;
                } else {
                    nPeso = 3;
                }

                switch (res.getGanador()) {
                    case eLOCAL:
                        nLocalVeces++;
                        nLocal += nPeso;
                        break;

                    case eVISITANTE:
                        nVisitanteVeces++;
                        nVisitante += nPeso;
                        break;
                    case eEMPATE:
                        nEmpateVeces++;
                        nEmpate += nPeso;
                        break;
                }
                //Confianza total
                if (nombreAgente.indexOf("Confianza") != -1) {
                    infoPrediccion = infoPred;
                }

            }
            ganadorPeso = getGanador(nLocal, nVisitante, nEmpate);
            ganadorVeces = getGanador(nLocalVeces, nVisitanteVeces, nEmpateVeces);

            switch (ganadorPeso) {
                case eLOCAL:
                    ganadorNombre = "Gana el equipo local: " + infoPrediccion.tomaEquipo1();
                    
                    break;
                case eVISITANTE:
                    ganadorNombre = "Gana el equipo visitante: " + infoPrediccion.tomaEquipo2();

                    if (ganadorPeso == ganadorVeces) {
                        ganadorMotivo = ganadorNombre + " | Motivo: Confianza total en pesos y mayoría";
                    } else {
                        ganadorMotivo = ganadorNombre + " | Motivo: Confianza en pesos, discrepancia en votación: " + GanadorToString(ganadorVeces);
                    }
                    ganadorNombre = "Gana el equipo visitante: " + infoPrediccion.tomaEquipo2();
                    break;
                case eEMPATE:
                    ganadorNombre = "Empate: " + infoPrediccion.tomaEquipo2();
                    break;
            }

            
            if (ganadorPeso == ganadorVeces) {
                ganadorMotivo = ganadorNombre + " | Motivo: Confianza total en pesos y mayoría";
            } else {
                ganadorMotivo = ganadorNombre + " | Motivo: Confianza en pesos, discrepancia en votación: " + GanadorToString(ganadorVeces);
            }
            infoPrediccion.setGanador(ganadorPeso);
            infoPrediccion.insertaResultado(ganadorNombre, ganadorMotivo);

        }

        return infoPrediccion;
    }

    String GanadorToString(Ganador gan) {
        String strReturn = "";
        switch (gan) {
            case eLOCAL:
                strReturn = "Victoria local";
                break;
            case eVISITANTE:
                strReturn = "Victoria visitante";
                break;
            case eEMPATE:
                strReturn = "Empate";
                break;
        }
        return strReturn;
    }

    Ganador getGanador(int nLocal, int nVisitante, int nEmpate) {
        Ganador ganadorReturn = Ganador.eNONE;
        if (nLocal >= nVisitante && nLocal >= nEmpate) {
            ganadorReturn = Ganador.eLOCAL;

        } else if (nVisitante >= nLocal && nVisitante >= nEmpate) {
            ganadorReturn = Ganador.eVISITANTE;
        } else {
            ganadorReturn = Ganador.eEMPATE;
        }
        return ganadorReturn;
    }

}
