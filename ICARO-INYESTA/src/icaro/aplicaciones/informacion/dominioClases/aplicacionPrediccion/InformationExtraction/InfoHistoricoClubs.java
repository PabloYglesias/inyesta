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
public class InfoHistoricoClubs 
{   
    String NombreHistorico;
    List<InfoClubs> ListaClubes;
    
    public InfoHistoricoClubs(String NombreHistorico)
    {       
          ListaClubes =  new ArrayList<InfoClubs>();
    }

    public InfoHistoricoClubs() 
    {
        ListaClubes =  new ArrayList<InfoClubs>();
    }
    
    public void InsertarClub(InfoClubs Club)
    {
        ListaClubes.add(Club);
    }
    public int getNumeroClubes()
    {
        return ListaClubes.size();
    } 

    public InfoClubs getClub(int i) 
    {
        InfoClubs ret=null;
        if(i<=ListaClubes.size())
            ret = ListaClubes.get(i);
        return ret;
    }
}
