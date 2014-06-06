/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package icaro.infraestructura.entidadesBasicas.componentesBasicos.automatas.clasesImpAutomatas;

import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.*;

/**
 *
 * @author Francisco J Garijo
 */
public class InformeTemporizacion extends Informe{
	

    public InformeTemporizacion (String  identEmisor,String msgTimeout){
        super (identEmisor,msgTimeout);
        
    }
    
    public String getMsgTimeOut()   {
        return (String)this.contenidoInforme;
    }
}
