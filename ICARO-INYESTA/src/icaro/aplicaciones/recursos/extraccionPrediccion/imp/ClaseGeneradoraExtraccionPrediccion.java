/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package icaro.aplicaciones.recursos.ExtraccionPrediccion.imp;

import icaro.aplicaciones.informacion.dominioClases.aplicacionPrediccion.Confianza.InfoExpertoPredictor;
import icaro.aplicaciones.informacion.dominioClases.aplicacionPrediccion.InformationExtraction.InfoHistoricoClubs;
import icaro.aplicaciones.informacion.dominioClases.aplicacionPrediccion.InformationExtraction.InfoHistoricoTemporadas;
import icaro.aplicaciones.informacion.dominioClases.aplicacionPrediccion.InformationExtraction.InfoTemporada;
import icaro.aplicaciones.recursos.ExtraccionPrediccion.ItfUsoExtraccionPrediccion;
import icaro.aplicaciones.recursos.extraccionPrediccion.imp.ExtraccionPrediccionImp;
import icaro.aplicaciones.recursos.persistenciaAccesoBD.imp.ErrorEnRecursoException;
import icaro.infraestructura.patronRecursoSimple.imp.ImplRecursoSimple;
import icaro.infraestructura.recursosOrganizacion.recursoTrazas.imp.componentes.InfoTraza;
import java.io.Serializable;
import java.rmi.Remote;

public class ClaseGeneradoraExtraccionPrediccion extends ImplRecursoSimple implements
        ItfUsoExtraccionPrediccion {

    private static final long serialVersionUID = 1L;

    private ExtraccionPrediccionImp Extraccion;
    public ClaseGeneradoraExtraccionPrediccion(String id) throws Exception {

        super(id);

        try {

            Extraccion = new ExtraccionPrediccionImp();
            trazas.aceptaNuevaTraza(new InfoTraza(this.getId(),
                    "Creando el extractor de datos ... " + id,
                    InfoTraza.NivelTraza.debug));

            //ExtraccionPrediccionImp.iniciar();
            //this.itfAutomata.transita("error");
            //throw e;
        } catch (Exception e) {
            this.trazas.aceptaNuevaTraza(new InfoTraza(id,
                    " Se ha producido un error creando el extractor de datos",
                    InfoTraza.NivelTraza.error));
            this.itfAutomata.transita("error");
            throw e;
        }
    }

    @Override
    public InfoHistoricoClubs ExtraerHistoricoEquipos() throws Exception {
        
        return  Extraccion.ExtraerHistoricoEquipos();
    }

    @Override
    public InfoExpertoPredictor ExtraerExpertoQuiniela() throws Exception {
       return  Extraccion.ExtraerExpertoQuiniela();
    }

    public InfoTemporada ExtraerJornadasHistorico(int nTemporada) throws Exception {
       throw new UnsupportedOperationException("Not supported yet."); // return  Extraccion.ExtraerTemporada(nTemporada);
    }
    @Override
    public InfoTemporada ExtraerCalendario() throws Exception {
        return  Extraccion.ExtractCalendar();
    }
    @Override
    public void termina() {
        trazas.aceptaNuevaTraza(new InfoTraza(this.getId(),
                "Terminando recurso",
                InfoTraza.NivelTraza.debug));
        try {

            ExtraccionPrediccionImp.terminar();
            super.termina();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public InfoTemporada ExtraerTemporada(int nTemporada) throws Exception 
    {
        return  Extraccion.ExtraerTemporada(nTemporada);
    }

    @Override
    public InfoHistoricoTemporadas ExtraerHistoricoTemporadasCompleto() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}