package icaro.infraestructura.entidadesBasicas.factorias;

import icaro.infraestructura.entidadesBasicas.NombresPredefinidos;
import icaro.infraestructura.entidadesBasicas.componentesBasicos.automatas.FactoriaAutomatas;
import icaro.infraestructura.patronRecursoSimple.*;
import icaro.infraestructura.entidadesBasicas.descEntidadesOrganizacion.DescInstanciaRecursoAplicacion;
import icaro.infraestructura.patronAgenteReactivo.control.factoriaEInterfaces.imp.FactoriaControlAgteReactivoInputObjImp0;
import icaro.infraestructura.patronRecursoSimple.imp.FactoriaRecursoSimpleImp2;
import icaro.infraestructura.recursosOrganizacion.configuracion.ItfUsoConfiguracion;
import icaro.infraestructura.recursosOrganizacion.recursoTrazas.ItfUsoRecursoTrazas;
import icaro.infraestructura.recursosOrganizacion.recursoTrazas.imp.ClaseGeneradoraRecursoTrazas;
import icaro.infraestructura.recursosOrganizacion.repositorioInterfaces.ItfUsoRepositorioInterfaces;
import icaro.infraestructura.recursosOrganizacion.repositorioInterfaces.imp.ClaseGeneradoraRepositorioInterfaces;

public  class FactoriaComponenteIcaro {

	private static FactoriaComponenteIcaro instanceFactoriaCompIcaro;
        private static FactoriaRecursoSimple instanceFactoriaRecursoSimple;
        private static FactoriaAutomatas instanceFactoriaAutomatas;
        private static FactoriaControlAgteReactivoInputObjImp0 instanceFactoriaControlAgteReactivoInputObjImp0;
        public ItfUsoRecursoTrazas recursoTrazas ;
        public ItfUsoRepositorioInterfaces repositorioInterfaces;

	public static FactoriaComponenteIcaro instanceFCI() {
		if (instanceFactoriaCompIcaro == null)
			instanceFactoriaCompIcaro = new FactoriaComponenteIcaro();
		return instanceFactoriaCompIcaro;
	}
        public static FactoriaRecursoSimple instanceFRS() {
		if (instanceFactoriaRecursoSimple == null)
			instanceFactoriaRecursoSimple = new FactoriaRecursoSimpleImp2();
		return instanceFactoriaRecursoSimple;
	}
        public static FactoriaAutomatas instanceAtms() {
		if (instanceFactoriaAutomatas == null)
			instanceFactoriaAutomatas = new FactoriaAutomatas();
		return instanceFactoriaAutomatas;
	}
	public static FactoriaControlAgteReactivoInputObjImp0 instanceAgteReactInpObj(){
            if (instanceFactoriaControlAgteReactivoInputObjImp0 == null)
			instanceFactoriaControlAgteReactivoInputObjImp0 = new FactoriaControlAgteReactivoInputObjImp0();
		return instanceFactoriaControlAgteReactivoInputObjImp0;
        }
	public  void crearRecursoSimple(DescInstanciaRecursoAplicacion recurso){
            
        }
        
      public  void crearRepositorioInterfaces(){ 	
       
            try {
            // Se crea el repositorio de interfaces y el recurso de trazas
                if ( NombresPredefinidos.REPOSITORIO_INTERFACES_OBJ != null)
                                 repositorioInterfaces =NombresPredefinidos.REPOSITORIO_INTERFACES_OBJ;
                else    {
                 repositorioInterfaces = ClaseGeneradoraRepositorioInterfaces.instance();         
                 NombresPredefinidos.REPOSITORIO_INTERFACES_OBJ = repositorioInterfaces;
                }
                } catch (Exception e) {
                    String msgUsuario = "Error. No se pudo crear el repositorio de interfaces ";
                    System.err.println(msgUsuario);
                    e.printStackTrace();
               }
      }           
      public  void crearRecursoTrazas(){ 
            try {   
                 if (NombresPredefinidos.RECURSO_TRAZAS_OBJ != null)
                                                    recursoTrazas = NombresPredefinidos.RECURSO_TRAZAS_OBJ ;
                 else{
                 recursoTrazas = ClaseGeneradoraRecursoTrazas.instance();
                 if ( NombresPredefinidos.REPOSITORIO_INTERFACES_OBJ == null) this.crearRepositorioInterfaces();
                 repositorioInterfaces.registrarInterfaz(
                            NombresPredefinidos.ITF_USO + NombresPredefinidos.RECURSO_TRAZAS,recursoTrazas);
                 repositorioInterfaces.registrarInterfaz(
                            NombresPredefinidos.ITF_GESTION + NombresPredefinidos.RECURSO_TRAZAS,recursoTrazas);
                    // Guardamos el recurso de trazas y el repositorio de Itfs en la clase de nombres predefinidos
                 NombresPredefinidos.RECURSO_TRAZAS_OBJ = recursoTrazas;
                 }
            } catch (Exception e) {
                String msgUsuario = "Error. No se pudo crear o registrar el recurso de trazas";
                    System.err.println(msgUsuario);
                    e.printStackTrace();
                //no es error cr�tico
               }
      }
}
