/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package icaro.aplicaciones.informacion.estructuraEquipos;

import icaro.aplicaciones.informacion.estructuraEquipos.PredictorStatus;
import icaro.infraestructura.entidadesBasicas.NombresPredefinidos;
import icaro.infraestructura.recursosOrganizacion.configuracion.ItfUsoConfiguracion;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author FGarijo
 */
public class InfoEquipoAgentes {

    private String identEquipo;
    private String identAgentePropietario;
    private String identAgenteJefeEquipo;
    private String identMiRolEnEsteEquipo = null;
    private Integer numberOfTeamMembers;
    private boolean inicioContactoConEquipo = false;
    private boolean agentesIdentificados = false;
    private boolean teamAgentIdsDefinidos = false;
    private boolean teamAgentIdsWithMyRolDefinidos = false;
    
    private ItfUsoConfiguracion itfConfig;
    private ArrayList<String> teamPredictorIds;
    private Map<String, PredictorStatus> teamInfoAgentStatus;
    private ArrayList<String> teamPredictorIdsWithMyRol;

    public InfoEquipoAgentes(String agtePropietarioId, String identEquipo) 
    {

        this.identEquipo = identEquipo;
        identAgentePropietario = agtePropietarioId;
        getIdentsAgentesEquipoFromConfig(identEquipo);
        initializeAgentTeamStatus();
    }

    public boolean isAgentesIdentificados() {
        return agentesIdentificados;
    }

    public void setAgentesIdentificados(boolean agentesIdentificados) {
        this.agentesIdentificados = agentesIdentificados;
    }

    private void initializeAgentTeamStatus() {
        teamInfoAgentStatus = new HashMap<String, PredictorStatus>();
        for (int i = 0; i < teamPredictorIds.size(); i++) 
        {
            String aux = (String) teamPredictorIds.get(i);
            teamInfoAgentStatus.put(aux, null);
        }
    }

    private void getIdentsAgentesEquipoFromConfig(String identEquipo) {
        ArrayList agentesRegistrados = this.getIdentsAgentesAplicacionRegistrados();
        teamPredictorIds = new ArrayList();
        String aux;
        Boolean encontrado = false;
        int numberOfRegAgts = agentesRegistrados.size();
        // int j = 0;
        for (int i = 0; i < numberOfRegAgts; i++) {
            aux = (String) agentesRegistrados.get(i);
            //Excluimos el propio agente
            if (aux.contains(identEquipo) & (!aux.contains(identAgentePropietario))) {
                teamPredictorIds.add(aux);
            }
        }
        this.numberOfTeamMembers = teamPredictorIds.size();
    }

    private ArrayList<String> getIdentsAgentesAplicacionRegistrados() {

        try {
            itfConfig = (ItfUsoConfiguracion) NombresPredefinidos.REPOSITORIO_INTERFACES_OBJ.obtenerInterfaz(NombresPredefinidos.ITF_USO + "Configuracion");
            //        teamPredictorIds = itfConfig.getIdentificadoresInstanciasAgentesAplicacion();
            return itfConfig.getIdentificadoresInstanciasAgentesAplicacion();
        } catch (Exception ex) {
            Logger.getLogger(InfoEquipoAgentes.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public synchronized void procesarInfoRolAgente(InfoRolAgente infoRol) {
        if (infoRol.getidentEquipoAgte().equals(identEquipo)) {
            addAgteAmiEquipo(infoRol.getAgteIniciadorId(), infoRol.getidentRolAgte());
        }
    }

    public synchronized String getidentAgentePropietario() {
        return identAgentePropietario;
    }

    public synchronized void addAgteAmiEquipo(String idAgte, String rolEnEquipoId) {
        if (teamInfoAgentStatus.get(idAgte) == null) {
            PredictorStatus estatusAgte = new PredictorStatus();
            estatusAgte.setIdPredictorRol(rolEnEquipoId);
            // si no esta entre los que se han obtenido de la configuracion lo meto y si esta no se hace nada
            if (!teamPredictorIds.contains(idAgte)) {
                teamPredictorIds.add(idAgte);
            }
            teamInfoAgentStatus.put(idAgte, estatusAgte);
            
            if( teamPredictorIds.size() == teamInfoAgentStatus.size())
            {
                teamAgentIdsDefinidos = true;
                agentesIdentificados = true;
            }
        }
    }

    public synchronized ArrayList<String> getTeamMemberIDsWithThisRol(String rolId) {
        ArrayList<String> agtesConMismoRol = new ArrayList();
        //  int indiceagtesConMirol=0;
        PredictorStatus estatusAgte;
        String identAgte;
        for (int i = 0; i < teamPredictorIds.size(); i++) {
            identAgte = teamPredictorIds.get(i);
            if (!identAgte.equals(this.identAgentePropietario)) {
                estatusAgte = teamInfoAgentStatus.get(identAgte);
                if (estatusAgte != null) {
                    if (estatusAgte.getIdPredictorRol().equals(rolId)) {
                        agtesConMismoRol.add(teamPredictorIds.get(i));
                    }
                }
            }
        }
        return agtesConMismoRol;
    }

    public synchronized ArrayList<String> getTeamMemberIDsWithMyRol() {
        if (!teamAgentIdsWithMyRolDefinidos && identMiRolEnEsteEquipo != null) {
            teamPredictorIdsWithMyRol = getTeamMemberIDsWithThisRol(identMiRolEnEsteEquipo);
            if (!teamPredictorIdsWithMyRol.isEmpty()) {
                teamAgentIdsWithMyRolDefinidos = true;
            }
        }
        return teamPredictorIdsWithMyRol;
    }

    public synchronized ArrayList<String> getTeamMemberIDs() {
        return this.teamPredictorIds;
    }

    public synchronized PredictorStatus getTeamMemberStatus(String identMember) {
        return teamInfoAgentStatus.get(identMember);
    }

    public synchronized boolean getinicioContactoConEquipo() {
        return inicioContactoConEquipo;
    }

    public synchronized void setinicioContactoConEquipo() {
        inicioContactoConEquipo = true;
    }

    public synchronized String getTeamMemberRol(String identAgte) {
        if (identMiRolEnEsteEquipo != null && identAgte.equals(identAgentePropietario)) {
            return this.identMiRolEnEsteEquipo;
        } else {
            return teamInfoAgentStatus.get(identAgte).getIdPredictorRol();
        }
    }

    public synchronized void setTeamMemberStatus(String identMember, PredictorStatus robotSt) {
        teamInfoAgentStatus.put(identMember, robotSt);
    }

    public synchronized Boolean isPredictorStatusDefined(String robtId) {
        return teamInfoAgentStatus.containsKey(robtId);
    }

    public synchronized Integer getNumberOfTeamMembers() {
        return numberOfTeamMembers;
    }

    public synchronized String getTeamId() {
        return identEquipo;
    }

    public void setTeamId(String idTeam) {
        identEquipo = idTeam;
    }

    public synchronized String getidentAgenteJefeEquipo() {
        return identAgenteJefeEquipo;
    }

    public void setidentAgenteJefeEquipo(String idAgtejefe) {
        identAgenteJefeEquipo = idAgtejefe;
    }

    public synchronized String getidentMiRolEnEsteEquipo() {
        return identMiRolEnEsteEquipo;
    }

    public void setidentMiRolEnEsteEquipo(String idRolAgte) {
        identMiRolEnEsteEquipo = idRolAgte;
    }
}
