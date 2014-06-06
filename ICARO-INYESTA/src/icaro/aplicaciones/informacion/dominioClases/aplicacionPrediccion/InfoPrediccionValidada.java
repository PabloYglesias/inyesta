package icaro.aplicaciones.informacion.dominioClases.aplicacionPrediccion;

import icaro.aplicaciones.informacion.dominioClases.aplicacionPrediccion.InfoPrediccionResultado.Ganador;
import java.io.Serializable;

public class InfoPrediccionValidada implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String autor;

    public InfoPrediccionResultado Resultado;
    /**
     * @uml.property name="usuario"
     */
    private String equipo1;
    private String equipo2;
    private String fecha;

    private String resultado;
    private String descripcionMotivo;

    
    public InfoPrediccionValidada(String equipo1, String equipo2, String fecha) {
        this.equipo1 = equipo1;
        this.equipo2 = equipo2;
        this.fecha = fecha;
        
        Resultado = new InfoPrediccionResultado();
    }

    public InfoPrediccionValidada(InfoPrediccionValidada PrediccionAResolver) {

        this.equipo1 = PrediccionAResolver.equipo1;
        this.equipo2 = PrediccionAResolver.equipo1;
        this.fecha = PrediccionAResolver.fecha;
        
        Resultado = new InfoPrediccionResultado();
    }

    public void insertaResultado(String resultado, String descripcionMotivo) {
        this.resultado = resultado;
        this.descripcionMotivo = descripcionMotivo;
    }

    public String tomaEquipo1() {
        return equipo1;
    }

    public String tomaEquipo2() {
        return equipo2;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String tomaFecha() {
        return fecha;
    }

    public String tomaResultado() {
        return resultado;
    }

    public String tomaDescripcionMotivo() {
        return descripcionMotivo;
    }

    public InfoPrediccionResultado getResultado()
    {
        return Resultado;
    }

    public void setGanador(Ganador ganador) 
    {
        Resultado.setGanador(ganador);
    }
}
