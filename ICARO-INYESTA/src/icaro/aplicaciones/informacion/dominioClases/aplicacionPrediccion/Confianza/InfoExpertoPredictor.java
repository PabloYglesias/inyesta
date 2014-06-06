/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package icaro.aplicaciones.informacion.dominioClases.aplicacionPrediccion.Confianza;

import icaro.aplicaciones.informacion.dominioClases.aplicacionPrediccion.InformationExtraction.InfoJornada;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class InfoExpertoPredictor 
{
    String NombreExperto;
    ListaPrediccionConfianza ListaPredicciones;

    public InfoExpertoPredictor()
    {
        ListaPredicciones = new ListaPrediccionConfianza();
    }
    public String getNombreExperto() {
        return NombreExperto;
    }

    public void setNombreExperto(String NombreExperto) {
        this.NombreExperto = NombreExperto;
    }

    public int getNumPredicciones() 
    {
        int nRet=0;
        
        if(ListaPredicciones != null)
            nRet = ListaPredicciones.getNumPredicciones();
        
        return nRet;
    }
    
    public InfoPrediccionConfianzaDTO getPrediccion(int nIndex) 
    {
        InfoPrediccionConfianzaDTO prediccion=null;
        
        if(ListaPredicciones != null)
            prediccion = ListaPredicciones.getPrediccion(nIndex);
        
        return prediccion;
    }    

    public void setListaPredicciones(ListaPrediccionConfianza ListaPredicciones) {
        this.ListaPredicciones = ListaPredicciones;
    }

    public void insertaPrediccion(InfoPrediccionConfianzaDTO PrediccionExperto) 
    {
        
        ListaPredicciones.insertaPrediccion(PrediccionExperto);
    }
}
