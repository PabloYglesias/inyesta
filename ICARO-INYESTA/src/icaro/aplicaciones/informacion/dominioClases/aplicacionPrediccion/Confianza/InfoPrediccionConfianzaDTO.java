/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package icaro.aplicaciones.informacion.dominioClases.aplicacionPrediccion.Confianza;

import icaro.aplicaciones.informacion.dominioClases.aplicacionPrediccion.InfoPrediccionResultado;
import icaro.aplicaciones.informacion.dominioClases.aplicacionPrediccion.InfoPrediccionResultado.Ganador;

/**
 *  Contiene los datos referentes
 * @author usuario_local
 */
public class InfoPrediccionConfianzaDTO {

    int nIdActa;
    int nIdJornada;
    String strNombrePredictor;
    String strEquipoLocal;
    String strEquipoVisitante;
    String strFecha;
    String PrediccionString;
    
    InfoPrediccionResultado Resultado;

    public int getnIdActa() {
        return nIdActa;
    }

    public void setnIdActa(int nIdActa) {
        this.nIdActa = nIdActa;
    }
    InfoPrediccionResultado Prediccion;
    
    public InfoPrediccionConfianzaDTO()
    {
        Resultado = new InfoPrediccionResultado();
        Prediccion = new InfoPrediccionResultado();
    }
    public String getStrFecha() {
        return strFecha;
    }

    public void setStrFecha(String strFecha) {
        this.strFecha = strFecha;
    }
    // int nPrediccion
    //int ResultadoReal
    boolean bAcierto;
    
    public int getnIdJornada() {
        return nIdJornada;
    }

    public void setnIdJornada(int nIdJornada) {
        this.nIdJornada = nIdJornada;
    }

    public String getNombrePredictor() {
        return strNombrePredictor;
    }

    public void setNombrePredictor(String strNombrePredictor) {
        this.strNombrePredictor = strNombrePredictor;
    }

    public String getEquipoLocal() {
        return strEquipoLocal;
    }

    public void setEquipoLocal(String strEquipoLocal) {
        this.strEquipoLocal = strEquipoLocal;
    }

    public String getEquipoVisitante() {
        return strEquipoVisitante;
    }

    public void setEquipoVisitante(String strEquipoVisitante) {
        this.strEquipoVisitante = strEquipoVisitante;
    }

    public boolean isAcierto() {
        return bAcierto;
    }

    public void setAcierto(boolean bAcierto) {
        this.bAcierto = bAcierto;
    }

    public InfoPrediccionResultado getResultado() {
      return Prediccion;
    }

    public void setPrediccion(Ganador Resultado) 
    {
      Prediccion.setGanador(Resultado);
    }

    public void setPrediccionString(String text)
    {
        PrediccionString = text;
        Ganador g = Ganador.eNONE;
        
        if(PrediccionString == "1")
            g = Ganador.eLOCAL;
        else if(PrediccionString == "X" || PrediccionString == "x")
            g = Ganador.eEMPATE;
        else if(PrediccionString == "2")
            g = Ganador.eVISITANTE;
        
        Prediccion.setGanador(g);
    }
        
}
