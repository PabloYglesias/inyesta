package icaro.aplicaciones.recursos.persistenciaPrediccionSimple;

import icaro.aplicaciones.informacion.dominioClases.aplicacionPrediccion.Confianza.InfoCompletaExpertosConfianza;
import icaro.aplicaciones.informacion.dominioClases.aplicacionPrediccion.Confianza.ListaPrediccionConfianza;
import icaro.infraestructura.patronRecursoSimple.ItfUsoRecursoSimple;

public interface ItfUsoPersistenciaPrediccionSimple extends ItfUsoRecursoSimple {
	public boolean compruebaEquipo(String equipo) throws Exception;
	public boolean compruebaPartido(String equipo1, String equipo2, String fecha) throws Exception;
        public boolean compruebaPrediccion(String equipo1, String equipo2, String fecha) throws Exception;
	public void insertaPrediccion (String equipo1, String equipo2, String fecha) throws Exception;
        public ListaPrediccionConfianza damePredicciones(String strNombrePredictor, String fechaInicio, String fechaFinal) throws Exception;
        public InfoCompletaExpertosConfianza dameExpertos() throws Exception;
      //  public InfoPrediccionExperto dameExpertos() throws Exception;
}