package icaro.aplicaciones.recursos.persistenciaPrediccionBD;

import icaro.aplicaciones.informacion.dominioClases.aplicacionPrediccion.Confianza.InfoCompletaExpertosConfianza;
import icaro.aplicaciones.informacion.dominioClases.aplicacionPrediccion.InformationExtraction.InfoHistoricoClubs;
import icaro.aplicaciones.informacion.dominioClases.aplicacionPrediccion.InformationExtraction.InfoHistoricoTemporadas;
import icaro.aplicaciones.informacion.dominioClases.aplicacionPrediccion.InformationExtraction.InfoTemporada;
import icaro.infraestructura.patronRecursoSimple.ItfUsoRecursoSimple;

public interface ItfUsoPersistenciaPrediccionBD extends ItfUsoRecursoSimple 
{
	public boolean compruebaUsuario(String usuario, String password) throws Exception;
	public boolean compruebaNombreUsuario(String usuario) throws Exception;
	public void insertaUsuario (String usuario,String password) throws Exception;
        
        

        /*
        
	public InfoHistoricoClubs ExtraerHistoricoEquipos() throws Exception;
	public InfoExpertoPredictor ExtraerExpertoQuiniela() throws Exception;
	public InfoTemporada ExtraerHistoricoTemporada() throws Exception;
        public InfoHistoricoTemporadas ExtraerHistoricoTemporadasCompleto() throws Exception;        
        public InfoTemporada ExtraerCalendario() throws Exception;        
        
        InfoJornada
        InfoPartido
        InfoTemporada
        InfoClubs
        InfoHistoricoClubs
        InfoExpertoPredictor
        InfoPrediccionConfianza
        
        */
        //A partir de aqui INYESTA
    public InfoTemporada getTemporada(int año) throws Exception;
    public InfoHistoricoClubs getHistoricoEquipos() throws Exception;
    public InfoCompletaExpertosConfianza getListaInfoExpertos() throws Exception;

    public boolean insertarHistoricoClubs(InfoHistoricoClubs infoHistClubs) throws Exception;
    public boolean insertarTemporadas(InfoHistoricoTemporadas infoHistTemporada)throws Exception;
    public boolean insertarHistoricoTemporada(InfoTemporada infoTemporada)throws Exception;

    
}