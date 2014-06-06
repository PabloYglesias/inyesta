/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package icaro.aplicaciones.informacion.dominioClases.aplicacionPrediccion.Confianza;

import icaro.aplicaciones.informacion.dominioClases.aplicacionPrediccion.InfoPrediccionResultado;

/**
 *
 * @author User
 */
public class InfoPrediccionConfianzaExperto 
{

        float RatioPrediccion;
        int PrediccionesAcertadas;
        int TotalPredicciones;        
        InfoPrediccionResultado Resultado; 
        
    public float getRatioPrediccion() {
        return RatioPrediccion;
    }

    public void setRatioPrediccion(float RatioPrediccion) {
        this.RatioPrediccion = RatioPrediccion;
    }

    public int getPrediccionesAcertadas() {
        return PrediccionesAcertadas;
    }

    public void setPrediccionesAcertadas(int PrediccionesAcertadas) {
        this.PrediccionesAcertadas = PrediccionesAcertadas;
    }

    public int getTotalPredicciones() {
        return TotalPredicciones;
    }

    public void setTotalPredicciones(int TotalPredicciones) {
        this.TotalPredicciones = TotalPredicciones;
    }

    public InfoPrediccionResultado getResultado() {
        return Resultado;
    }

    public void setResultado(InfoPrediccionResultado Resultado) {
        this.Resultado = Resultado;
    }

    
}
