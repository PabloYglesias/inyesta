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
public class InfoJornada 
{
    String NombreJornada;
    int NumeroJornada;
    
    String FechaInicio;
    String FechaFin;
    List<InfoPartido> ListaPartidos;
    
    public InfoJornada(String NombreJornadaIn)
    {
        //parseo de string a int
        NombreJornada = NombreJornadaIn;
        ListaPartidos = new ArrayList<InfoPartido>();
    }

    public InfoPartido getPartido(int nPartido)
    {
        return ListaPartidos.get(nPartido);
    }
    public void InsertarPartido(InfoPartido Partido)
    {
        ListaPartidos.add(Partido);
    }    
    public int getNumeroPartidos()
    {
        return ListaPartidos.size();
    }    

    public void setFechaInicio(String FechaInicioIn) {
       FechaInicio = FechaInicioIn;
    }

    public void setFechaFin(String FechaFinIn) {
        FechaFin = FechaFinIn;
    }
}
