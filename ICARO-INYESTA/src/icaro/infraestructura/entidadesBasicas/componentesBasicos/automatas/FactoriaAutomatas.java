/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package icaro.infraestructura.entidadesBasicas.componentesBasicos.automatas;

import icaro.infraestructura.entidadesBasicas.componentesBasicos.automatas.automataEFconGesAcciones.InterpreteAutomataEFconGestAcciones;
import icaro.infraestructura.entidadesBasicas.componentesBasicos.automatas.automataEFconGesAcciones.imp.InterpreteAutomataEFconGestAccionesImp;
import icaro.infraestructura.entidadesBasicas.componentesBasicos.automatas.automataEFsinAcciones.imp.TablaEstadosAutomataEF;
import icaro.infraestructura.entidadesBasicas.componentesBasicos.automatas.clasesImpAutomatas.TablaEstadosAutomataEFinputObjts;
import icaro.infraestructura.entidadesBasicas.componentesBasicos.automatas.clasesImpAutomatas.XMLParserTablaEstadosAutomataEFinputObj;
import icaro.infraestructura.entidadesBasicas.componentesBasicos.automatas.gestorAcciones.ItfGestorAcciones;
import icaro.infraestructura.entidadesBasicas.componentesBasicos.automatas.gestorAcciones.imp.GestorAccionesImp;
import icaro.infraestructura.entidadesBasicas.factorias.FactoriaComponenteIcaro;

/**
 *
 * @author FGarijo
 */
public class FactoriaAutomatas extends FactoriaComponenteIcaro{
    
//
//	private static FactoriaAutomatas instance;
//
//	public static FactoriaAutomatas instance() {
//		if (instance == null)
//			instance = new FactoriaAutomatas();
//		return instance;
//	}
	
	public  InterpreteAutomataEFconGestAcciones crearAutomataEFconGestAcciones(
                String identPropietario,String rutaFicheroAutomata,String rutaCarpetaAcciones,Boolean trazar){
            
	XMLParserTablaEstadosAutomataEFinputObj prueba1 =   new XMLParserTablaEstadosAutomataEFinputObj(identPropietario);

         TablaEstadosAutomataEFinputObjts tablaEF =  prueba1.extraeTablaEstadosDesdeFicheroXML(rutaFicheroAutomata, rutaCarpetaAcciones);
         // crear el ejecutor de acciones
         GestorAccionesImp gestAccionesItf = new GestorAccionesImp(identPropietario);
         InterpreteAutomataEFconGestAcciones interpreteAutomata = new InterpreteAutomataEFconGestAccionesImp(tablaEF,trazar);
         gestAccionesItf.setItfAutomataEFconGestAcciones(interpreteAutomata);
         interpreteAutomata.setItfGestorAcciones(gestAccionesItf);
         return interpreteAutomata;
        }
         public static void main(String args[]) {
//         XMLParserTablaEstadosAutomataEFinputObj prueba1 =   new XMLParserTablaEstadosAutomataEFinputObj("Iniciador");
//         prueba1.extraeTablaEstadosDesdeFicheroXML("/icaro/infraestructura/entidadesBasicas/componentesBasicos/automatas/clasesImpAutomatas/automataPrueba.xml", null);
//         String rutaFichero = prueba1.obtenerRutaValidaAutomata (NombresPredefinidos.COMPORTAMIENTO_PORDEFECTO_INICIADOR);
//         prueba1.extraeTablaEstadosDesdeFicheroXML("/icaro/gestores/iniciador/automataPrueba.xml", null);
//         prueba1.extraeTablaEstadosDesdeFicheroXML("/icaro/pruebas/automataPruebas.xml", null);
//             String rutaFicheroAutomata = "/icaro/gestores/iniciador/automataPrueba.xml";  // da error
             String rutaFicheroAutomata = "/icaro/pruebas/automataPruebas.xml";
             String rutaCarpetaAcciones = "icaro.pruebas";
             String identPropietario = "Iniciador";
             String estadoActual;
             Boolean esEstadoFinal;
             InterpreteAutomataEFconGestAcciones interpretePrueba;
            interpretePrueba= FactoriaComponenteIcaro.instanceAtms().
                              crearAutomataEFconGestAcciones(identPropietario,rutaFicheroAutomata,rutaCarpetaAcciones,Boolean.TRUE);
             interpretePrueba.volverAEstadoInicial(); // Estado inicial dela utomata
             estadoActual = interpretePrueba.estadoActual(); // debe dar el estado inicial 
             esEstadoFinal = interpretePrueba.esEstadoFinal(estadoActual); // debe dar falso
             interpretePrueba.cambiarEstado("creandoRecursosNucleoOrganizacion");
             estadoActual = interpretePrueba.estadoActual();
             interpretePrueba.cambiarEstado("creandoRecursosNucleoOrganizacio"); // deben salir las trazas
             interpretePrueba.ejecutarTransicion("existenEntidadesDescripcion", (Object) null);
        }

}
