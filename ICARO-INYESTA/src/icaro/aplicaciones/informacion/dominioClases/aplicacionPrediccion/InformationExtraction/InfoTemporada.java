/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package icaro.aplicaciones.informacion.dominioClases.aplicacionPrediccion.InformationExtraction;

import icaro.aplicaciones.informacion.dominioClases.aplicacionPrediccion.Confianza.InfoPrediccionConfianzaDTO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class InfoTemporada 
{
    int NumeroTemporada;
    String NombreTemporada;
    List<InfoJornada> ListaJornadas;
    
    public InfoTemporada(String NombreTemporadaIn)
    {
        NombreTemporada= NombreTemporadaIn;
        
          ListaJornadas =  new ArrayList<InfoJornada>();
    }
    
    public int getNumeroTemporada() {
        return NumeroTemporada;
    }

    public void setNumeroTemporada(int NumeroTemporada) {
        this.NumeroTemporada = NumeroTemporada;
    }

    public String getNombreTemporada() {
        return NombreTemporada;
    }

    public void setNombreTemporada(String NombreTemporada) {
        this.NombreTemporada = NombreTemporada;
    }

    public List<InfoJornada> getListaJornadas() {
        return ListaJornadas;
    }

    public void setListaJornadas(List<InfoJornada> ListaJornadas) {
        this.ListaJornadas = ListaJornadas;
    }

    
    public void InsertarJornada(InfoJornada Jornada)
    {
        ListaJornadas.add(Jornada);
    }
    public int getNumeroJornadas()
    {
        return ListaJornadas.size();
    }
    public InfoJornada getJornada(int nJornada)
    {
        InfoJornada ret=null;
        if(nJornada<=ListaJornadas.size())
            ret = ListaJornadas.get(nJornada);
        
        return ret;
    }
}
