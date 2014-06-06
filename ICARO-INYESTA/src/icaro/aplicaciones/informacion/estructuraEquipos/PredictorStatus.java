/*
 *
 * Clase para gestionar las caracter�sticas del Predictor:
 * 
 *    -  idPredictor -> Identificador del Predictor
 */

package icaro.aplicaciones.informacion.estructuraEquipos;

import java.util.ArrayList;
import java.util.List;

public class PredictorStatus {

    private String idPredictor;
    private String idPredictorRol;    
    private List<Integer> predictorCapabilities = new ArrayList<Integer>();        

    public PredictorStatus(String Id) {
        setIdPredictor(Id);
    }
	
	//Constructor sin argumentos
	public  PredictorStatus(){
		
	}
		
	public void setIdPredictor(String id){
		this.idPredictor = id;
	}
		
	public String getIdPredictor(){
		return this.idPredictor;
	}
        public void setIdPredictorRol(String id){
		this.idPredictorRol = id;
	}		
	public String getIdPredictorRol(){
		return this.idPredictorRol;
	}		
	
    @Override
    public String toString(){    	
    	return "Predictor: id->" + this.getIdPredictor();    	    	    	     	
    }
}
