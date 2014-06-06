/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package icaro.aplicaciones.informacion.dominioClases.aplicacionPrediccion.Confianza;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class InfoCompletaExpertosConfianza 
{
    List<InfoExpertoPredictor> ListaExpertosPredictores;

    public List<InfoExpertoPredictor> getListaExpertosPredictores() {
        return ListaExpertosPredictores;
    }

    public void setListaExpertosPredictores(List<InfoExpertoPredictor> ListaExpertosPredictores) {
        this.ListaExpertosPredictores = ListaExpertosPredictores;
    }
    
    public int getNumExpertos()
    {
        int nRet=0;
        
        if(ListaExpertosPredictores != null)
        {
            nRet = ListaExpertosPredictores.size();
        }
        
        return nRet;
    }
    public InfoExpertoPredictor getExperto(int nIndex)
    {
        InfoExpertoPredictor Predictor = null;
        
        if(ListaExpertosPredictores != null && nIndex < ListaExpertosPredictores.size())
        {
            Predictor = ListaExpertosPredictores.get(nIndex);
        }
        
        return Predictor;
    }

    public void insertaExperto(InfoExpertoPredictor Predictor1) 
    {
        if(ListaExpertosPredictores == null)
        {
            ListaExpertosPredictores = new ArrayList<InfoExpertoPredictor>();  
        }
        ListaExpertosPredictores.add(Predictor1);
    }
}
