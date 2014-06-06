/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package icaro.aplicaciones.agentes.AgenteAplicacionPredictorCognitivo.informacion;

import icaro.aplicaciones.informacion.estructuraEquipos.InfoEquipoAgentes;
import icaro.aplicaciones.informacion.estructuraEquipos.PredictorStatus;
import icaro.aplicaciones.informacion.estructuraEquipos.*;
import icaro.aplicaciones.informacion.dominioClases.aplicacionPrediccion.*;
import icaro.infraestructura.entidadesBasicas.NombresPredefinidos;
import icaro.infraestructura.recursosOrganizacion.configuracion.ItfUsoConfiguracion;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PAblo C. Cañizares
 */
public class InfoParaDecidirPrediccion implements Serializable {

    private ArrayList confirmacionesAgentes, evaluacionesRecibidas;//resto de agentes que forman mi equipo
    private String identEquipo = null;
    private String nombreAgente;
    
    private ArrayList<String> agentesAplicacionDefinidos, agentesEquipo, respuestasAgentes;
    private int prediccionesEsperadas, confirmacionesEsperadas;

    public boolean todasLasPrediccionesRecibidas=false;
    public int numeroPrediccionesRecibidas;
    private List<InfoPrediccionValidada> m_oList;


    public InfoParaDecidirPrediccion(String identAgente, InfoEquipoAgentes equipo) {
        try {
            identEquipo = equipo.getTeamId();

            agentesEquipo = equipo.getTeamMemberIDs();
            nombreAgente = identAgente;
            respuestasAgentes = new ArrayList();
            confirmacionesAgentes = new ArrayList();
            evaluacionesRecibidas = new ArrayList();
            numeroPrediccionesRecibidas = 0;

            m_oList = new ArrayList();
            // Inicializamos para cada agente las respuestas, empates, confirmaciones
            String aux;
            for (int i = 0; i < agentesEquipo.size(); i++) {
                respuestasAgentes.add("");
                confirmacionesAgentes.add("");
                evaluacionesRecibidas.add(-5);
            }
            confirmacionesEsperadas = agentesEquipo.size() - 1;
        } catch (Exception ex) {
            Logger.getLogger(InfoParaDecidirPrediccion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public synchronized void insertarPrediccion(InfoPrediccionValidada Prediccion)
    {
        numeroPrediccionesRecibidas++;
        m_oList.add(Prediccion);
        
        if(numeroPrediccionesRecibidas == agentesEquipo.size())
            todasLasPrediccionesRecibidas = true;
    }
    public synchronized ArrayList ObtenerNombreAgentesDelEquipoRegistrados(String identEquipo, String identAgente) {
        try {
            ArrayList agentesRegistrados = NombresPredefinidos.REPOSITORIO_INTERFACES_OBJ.nombresInterfacesRegistradas();

            agentesEquipo = new ArrayList();
            String aux;
            for (int i = 0; i < agentesRegistrados.size(); i++) {
                aux = (String) agentesRegistrados.get(i);
                //filtramos los Gestores. Mandamos solo a las itf de uso de los agentes, y excluimos el propio agente
                if (aux.contains(identEquipo) && aux.contains("Itf_Uso") && !aux.contains(identAgente)) {
                    String identAgenteRegistrado = aux.replaceFirst("Itf_Uso_", "");
                    agentesEquipo.add(identAgenteRegistrado);
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(InfoParaDecidirPrediccion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return agentesEquipo;
    }

    private ArrayList getNombreAgentesEquipoDefinidos(String identAgente, String identEquipo) {
        try {
            if (identEquipo != null) {

                agentesEquipo = new ArrayList();
                String aux;
                for (int i = 0; i < agentesAplicacionDefinidos.size(); i++) {
                    aux = (String) agentesAplicacionDefinidos.get(i);
                    //Excluimos el propio agente
                    if (!aux.contains(identAgente)) // esto hay que quitarlo y nombrar los agentes como es debido  *********
                    {
                        if (identEquipo.equals(VocabularioEquipos.IdentEquipoJerarquico)) {
                            agentesEquipo.add(aux);
                        } else if (aux.contains(identEquipo) && !aux.contains(identAgente)) {
                            agentesEquipo.add(aux);
                        }
                    }
                }
            }
            // se deberia poner un mensaje de error en las trazas, pero ya saldra mas adelante
        } catch (Exception ex) {
            Logger.getLogger(InfoParaDecidirPrediccion.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return agentesEquipo;
    }

    public ArrayList getIdentsAgentesEquipo() {
        if (identEquipo != null) {
            return agentesEquipo;
        } else {
            return null;
        }
    }

    public String getIdenEquipo() {
        return identEquipo;
    }

    public synchronized void inicializarInfoParaDecidir(String idInfoDecision) {
        // Inicializamos para cada agente las respuestas, empates, confirmaciones
        for (int i = 0; i < agentesEquipo.size(); i++) 
        {
            respuestasAgentes.add("");
            confirmacionesAgentes.add("");
            evaluacionesRecibidas.add(0);
        }
    }

    public synchronized int numRespuestasRecibidas() {
        int respRecibidas = 0;
        for (int i = 0; i < respuestasAgentes.size(); i++) {
            if (((String) respuestasAgentes.get(i)) != "") {
                respRecibidas++;
            }
        }
        return respRecibidas;
    }

    public synchronized int numEvaluacionesRecibidas() {
        int evalRecibidas = 0;
        for (int i = 0; i < respuestasAgentes.size(); i++) {
            if (((Integer) evaluacionesRecibidas.get(i)) > 0) {
                evalRecibidas++;
            }
        }
        return evalRecibidas;
    }

    public synchronized String getIdentAgente() {
        return nombreAgente;
    }

    public synchronized boolean tengoTodasLasPredicciones() 
    {

        return todasLasPrediccionesRecibidas;
        //    return (agentesEquipo.size() == numEvaluacionesRecibidas());
    }

    public synchronized void addConfirmacionRealizacionObjetivo(AceptacionPropuesta confObjetivo) {
					  //          ArrayList p = paquete;
        //          String eval = (String)p.get(0);
        String identObj = confObjetivo.getmsgAceptacionPropuesta();
        String idAgenteEmisorEvaluacion = confObjetivo.getIdentAgente();
        //           trazas.aceptaNuevaTraza(new InfoTraza(nombreAgente, "Confirmacion Recibida. Dice Objetivo :" + identObj +" Emisor: "+idAgenteEmisorEvaluacion, InfoTraza.NivelTraza.info));
        //actualizar las respuestas
        confirmacionesAgentes.set(agentesEquipo.indexOf(idAgenteEmisorEvaluacion), identObj);//guardamos la respuesta
    }

     //funcion que sirve para ver con cuantos empata

    public synchronized ArrayList getRespuestas() {
            //mandar un mensaje a los agentes que no nos han enviado la respuesta aun
        //enviamos el mensaje y la info adicional, que es de donde viene
        return respuestasAgentes;
    }


    public synchronized Boolean tengoTodasLasPrediccionesEsperadas() {
        return (numeroPrediccionesRecibidas == respuestasAgentes.size());
    }

    public synchronized void initPrediccionesRecibidas() {
            //mandar un mensaje a los agentes que no nos han enviado la respuesta aun
        //enviamos el mensaje y la info adicional, que es de donde viene
        prediccionesEsperadas = 0;
    }

    public synchronized Integer getPrediccionesRecibidas() {
            //mandar un mensaje a los agentes que no nos han enviado la respuesta aun
        //enviamos el mensaje y la info adicional, que es de donde viene
        return numeroPrediccionesRecibidas;
    }

    public synchronized ArrayList<String> getAgentesEquipo() {

        return agentesEquipo;
    }

    public List<InfoPrediccionValidada> getListaPredicciones() {
        return m_oList;
    }


}
