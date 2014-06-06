/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package icaro.aplicaciones.informacion.dominioClases.aplicacionPrediccion.Confianza;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author usuario_local
 */
public class ListaPrediccionConfianza 
{
    String strNombrePredictor;
    String strFechaInicio;
    String strFechaFin;
    List<InfoPrediccionConfianzaDTO> m_oList;
    
    public ListaPrediccionConfianza () 
    {
       m_oList =  new ArrayList<InfoPrediccionConfianzaDTO>();
    }
    public String getStrNombrePredictor() {
        return strNombrePredictor;
    }

    public void setStrNombrePredictor(String strNombrePredictor) {
        this.strNombrePredictor = strNombrePredictor;
    }

    public String getStrFechaInicio() {
        return strFechaInicio;
    }

    public void setStrFechaInicio(String strFechaInicio) {
        this.strFechaInicio = strFechaInicio;
    }

    public String getStrFechaFin() {
        return strFechaFin;
    }

    public void setStrFechaFin(String strFechaFin) {
        this.strFechaFin = strFechaFin;
    }

    public void insertaPrediccion(InfoPrediccionConfianzaDTO Prediccion)
    {
        m_oList.add(Prediccion);
    }
    
    public InfoPrediccionConfianzaDTO getPrediccion(int nIndex)
    {
        InfoPrediccionConfianzaDTO pRet=null;
        
        if(nIndex < m_oList.size())
            pRet = m_oList.get(nIndex);
        
        return pRet;
    }
    public int getNumPredicciones()
    {
        int nRet=0;
        
        if(m_oList != null)
            nRet = m_oList.size();
        
        return nRet;
    }
}
