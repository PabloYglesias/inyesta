/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package icaro.aplicaciones.agentes.AgenteAplicacionPlanificadorCognitivo.informacion;

import icaro.aplicaciones.informacion.dominioClases.aplicacionPrediccion.Confianza.InfoExpertoPredictor;
import icaro.aplicaciones.informacion.dominioClases.aplicacionPrediccion.InformationExtraction.InfoHistoricoClubs;
import icaro.aplicaciones.informacion.dominioClases.aplicacionPrediccion.InformationExtraction.InfoHistoricoTemporadas;
import icaro.aplicaciones.informacion.dominioClases.aplicacionPrediccion.InformationExtraction.InfoTemporada;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pablo C. Cañizares
 */
public class InfoParaPlanificar implements Serializable 
{

    public InfoHistoricoClubs getHistoricoClubs() {
        return HistoricoClubs;
    }

    public void setHistoricoClubs(InfoHistoricoClubs HistoricoClubs) {
        this.HistoricoClubs = HistoricoClubs;
    }

    public InfoExpertoPredictor getExpertoPredictor() {
        return ExpertoPredictor;
    }

    public void setExpertoPredictor(InfoExpertoPredictor ExpertoPredictor) {
        this.ExpertoPredictor = ExpertoPredictor;
    }

    public InfoTemporada getTemporada() {
        return Temporada;
    }

    public void setTemporada(InfoTemporada Temporada) {
        this.Temporada = Temporada;
    }

    public InfoHistoricoTemporadas getHistoricoTemporadas() {
        return HistoricoTemporadas;
    }

    public void setHistoricoTemporadas(InfoHistoricoTemporadas HistoricoTemporadas) {
        this.HistoricoTemporadas = HistoricoTemporadas;
    }

    public InfoTemporada getCalendario() {
        return Calendario;
    }

    public void setCalendario(InfoTemporada Calendario) {
        this.Calendario = Calendario;
    }

    
    private boolean ExtraerHistoricoEquipos=false;
    private boolean ExpertosPredictores=false;
    private boolean ExtraerHistoricoTemporadasCompleto=false;
    private boolean ExtraerHistoricoTemporada=false;
    private boolean ExtraerCalendario=false;

    private InfoHistoricoClubs HistoricoClubs = null;
    private InfoExpertoPredictor ExpertoPredictor = null;
    private InfoTemporada Temporada=null;
    private InfoHistoricoTemporadas HistoricoTemporadas=null;
    private InfoTemporada Calendario = null;
    
    public boolean isExtraerHistoricoEquipos() {
        return ExtraerHistoricoEquipos;
    }

    public void setExtraerHistoricoEquipos(boolean ExtraerHistoricoEquipos) {
        this.ExtraerHistoricoEquipos = ExtraerHistoricoEquipos;
    }

    public boolean isExpertosPredictores() {
        return ExpertosPredictores;
    }

    public void setExpertosPredictores(boolean ExpertosPredictores) {
        this.ExpertosPredictores = ExpertosPredictores;
    }

    public boolean isExtraerHistoricoTemporadasCompleto() {
        return ExtraerHistoricoTemporadasCompleto;
    }

    public void setExtraerHistoricoTemporadasCompleto(boolean ExtraerHistoricoTemporadasCompleto) {
        this.ExtraerHistoricoTemporadasCompleto = ExtraerHistoricoTemporadasCompleto;
    }

    public boolean isExtraerTemporada() {
        return ExtraerHistoricoTemporada;
    }

    public void setExtraerHistoricoTemporada(boolean ExtraerHistoricoTemporada) {
        this.ExtraerHistoricoTemporada = ExtraerHistoricoTemporada;
    }

    public boolean isExtraerCalendario() {
        return ExtraerCalendario;
    }

    public void setExtraerCalendario(boolean ExtraerCalendario) {
        this.ExtraerCalendario = ExtraerCalendario;
    }

    public String getNombreAgente() {
        return nombreAgente;
    }

    public void setNombreAgente(String nombreAgente) {
        this.nombreAgente = nombreAgente;
    }
    
    private String nombreAgente;
    


    public InfoParaPlanificar(String identAgente) 
    {
        try {

            nombreAgente = identAgente;

        } catch (Exception ex) {
            Logger.getLogger(InfoParaPlanificar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

