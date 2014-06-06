/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package icaro.aplicaciones.informacion.dominioClases.aplicacionPrediccion.InformationExtraction;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class InfoHistoricoTemporadas 
{
    List<InfoTemporada> ListaTemporadas;
    
    public InfoHistoricoTemporadas()
    {
        ListaTemporadas =  new ArrayList<InfoTemporada>();
    }
    public void InsertarTemporada(InfoTemporada Temp)
    {
        ListaTemporadas.add(Temp);
    }
    public int getNumeroTemporadas()
    {
        return ListaTemporadas.size();
    }      

    public InfoTemporada getTemporada(int nTemp)
    {
        return ListaTemporadas.get(nTemp);
    }
}
