/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package icaro.aplicaciones.informacion.dominioClases.aplicacionPrediccion;


/**
 *
 * @author User
 */
public class InfoPrediccionResultado 
{
    public enum Ganador 
    {
       eNONE,
       eLOCAL,
       eEMPATE,
       eVISITANTE
    }
    public InfoPrediccionResultado()
    {
        Ganador = Ganador.eNONE;
    }
    Ganador Ganador;// 1 x ó 2    
    //Lo suyo sería poner el resultado numérico

    public Ganador getGanador() {
        return Ganador;
    }

    public void setGanador(Ganador Ganador) {
        this.Ganador = Ganador;
    }
}
