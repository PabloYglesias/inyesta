package icaro.aplicaciones.recursos.ExtraccionPrediccion;

import icaro.aplicaciones.informacion.dominioClases.aplicacionPrediccion.Confianza.InfoExpertoPredictor;
import icaro.aplicaciones.informacion.dominioClases.aplicacionPrediccion.InformationExtraction.InfoHistoricoClubs;
import icaro.aplicaciones.informacion.dominioClases.aplicacionPrediccion.InformationExtraction.InfoTemporada;
import icaro.aplicaciones.informacion.dominioClases.aplicacionPrediccion.InformationExtraction.InfoHistoricoTemporadas;
import icaro.infraestructura.patronRecursoSimple.ItfUsoRecursoSimple;

public interface ItfUsoExtraccionPrediccion extends ItfUsoRecursoSimple 
{	
	public InfoHistoricoClubs ExtraerHistoricoEquipos() throws Exception;
	public InfoExpertoPredictor ExtraerExpertoQuiniela() throws Exception;
	public InfoTemporada ExtraerTemporada(int nTemporada) throws Exception;
        public InfoHistoricoTemporadas ExtraerHistoricoTemporadasCompleto() throws Exception;        
        public InfoTemporada ExtraerCalendario() throws Exception;
}