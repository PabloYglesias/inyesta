package icaro.aplicaciones.informacion.dominioClases.aplicacionPrediccion;

import java.io.Serializable;

public class InfoPrediccionSinValidar implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private String equipo1;
        private String equipo2;
	private String fecha;
		
	
	public InfoPrediccionSinValidar (String equipo1, String equipo2, String fecha) 
        {
		
		this.equipo1 = equipo1;
		this.equipo2 = equipo2;
                this.fecha = fecha;
		
	}
	
	public String tomaEquipo1() {
		return equipo1;
	}
	
	public String tomaEquipo2() {
		return equipo2;
	}
	public String tomaFecha() {
		return fecha;
	}        
	
}