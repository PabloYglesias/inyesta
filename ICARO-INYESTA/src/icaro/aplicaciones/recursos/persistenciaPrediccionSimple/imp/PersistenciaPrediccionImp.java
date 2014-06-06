/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package icaro.aplicaciones.recursos.persistenciaPrediccionSimple.imp;
import com.larvalabs.megamap.MegaMapManager;
import com.larvalabs.megamap.MegaMap;
import com.larvalabs.megamap.MegaMapException;

import java.io.IOException;
import java.io.Serializable;
import java.util.Iterator;
import java.util.Properties;

public class PersistenciaPrediccionImp implements Serializable{
    public static MegaMapManager manager ;
    public static MegaMap tablaEquipos;
    public static MegaMap tablaPartidos;
    public static MegaMap tablaPredicciones;

    public PersistenciaPrediccionImp()
    {

         try {
                // Get Manager singleton
                 manager = MegaMapManager.getMegaMapManager();
                {
                    // Create MegaMap with name "map1"
                    //  (the other two parameters will be discussed later)
                     tablaEquipos = manager.createMegaMap("BdEquipos", true, true);
                    // tablaUsuriosAutorizados = manager.getMegaMap("BdUsuriosAutorizados");
                    // Add some objects with Integer keys
                    tablaEquipos.put("RMadrid", "Test Object 1");
                    tablaEquipos.put("Barcelona", "Test Object 2");
                   

                    // Retrieve and display one of the objects
                    String s = (String) tablaEquipos.get("Barcelona");
                    tablaEquipos.remove("RMadrid");
                    tablaEquipos.remove("Barcelona");
                    System.out.println(s);
                    // Shutdown the manager
     //               manager.shutdown();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

    }
         public static void crearEquipos()
        throws IOException
    {

           try {
            // Get Manager singleton
             manager = MegaMapManager.getMegaMapManager();
            {
                // Create MegaMap with name "map1"
                //  (the other two parameters will be discussed later)
                tablaEquipos = manager.createMegaMap("BdEquipos", true, true);
                tablaPartidos = manager.createMegaMap("BdPartidos", true, true);
               
                // Add some objects with Integer keys
                tablaEquipos.put("usuario1", "Test Object 1");
                tablaEquipos.put("usuario2", "Test Object 2");
//                tablaUsuriosAutorizados.put(new Integer(3), "Test Object 3");
//                tablaUsuriosAutorizados.put(new Integer(4), "Test Object 4");
                // Retrieve and display one of the objects
                String s = (String) tablaEquipos.get("usuario2");
                tablaEquipos.remove("usuario2");
                tablaEquipos.remove("usuario1");
                System.out.println(s);
                // Shutdown the manager
 //               manager.shutdown();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // insert keys and values
        System.out.println();
        System.out.println( "Adding usuarios autorizados ..." );
        tablaEquipos.put( "RMadrid", "1" );
        tablaEquipos.put( "Barcelona", "2" );        
        
        tablaPartidos.put("1-2","01-01-2014");
//        showBDusuarios();


    }
public static boolean compruebaPartido(String equipo1, String equipo2, String fecha)
			throws IOException {

   try {

        if ( tablaEquipos.hasKey(equipo1) && tablaEquipos.hasKey(equipo2) )
        {
            String valorEquipo1 =(String) tablaEquipos.get(equipo1);
            String valorEquipo2 =(String) tablaEquipos.get(equipo2);
            
            String valorFecha;
            valorFecha = (String) tablaPartidos.get(valorEquipo1+"-"+valorEquipo2);
            return (valorFecha.equals(fecha));

        } 
        else
            return false;
    }
    catch (MegaMapException e) {
                        e.printStackTrace();
                        System.out.println( "Problema al comprobar usn y pasw  " + e.getCause() );

                        }

    return false;

}
public static boolean compruebaPrediccion(String equipo1, String equipo2, String fecha)
			throws IOException {

   try {

        if ( tablaEquipos.hasKey(equipo1) && tablaEquipos.hasKey(equipo2) )
        {
            String valorEquipo1 =(String) tablaEquipos.get(equipo1);
            String valorEquipo2 =(String) tablaEquipos.get(equipo2);
            
            String valorFecha;
            valorFecha = (String) tablaPredicciones.get(valorEquipo1+"-"+valorEquipo2);
            return (valorFecha.equals(fecha));

        } 
        else
            return false;
    }
    catch (MegaMapException e) {
                        e.printStackTrace();
                        System.out.println( "Problema al comprobar usn y pasw  " + e.getCause() );

                        }

    return false;

}
public static boolean compruebaEquipo(String equipo1)	throws IOException 
{
    boolean bRet = false;
    if (tablaEquipos.hasKey(equipo1))
    {
        bRet = true;
    }
    
   return bRet;
    
}
 
public static void insertaEquipo(String equipo1, String value)
			throws IOException {
       System.out.println( "Adding equipos ..." );
        tablaEquipos.put( equipo1, value );

}

public static void insertaPrediccion(String equipo1, String equipo2, String fecha)
			throws IOException {
       System.out.println( "Adding equipos ..." );
        //tablaPartidos.put( usuario, password );

}

    public void showBDequipos()
        throws IOException
    {
      
        System.out.println();
        System.out.print( "Fruit basket contains: " );
        Iterator iter = tablaEquipos.getKeys().iterator();
        String usn = (String) iter.next();
        while ( iter.hasNext()) {
            System.out.print( " " + usn );
            usn = (String)iter.next();
        }
        System.out.println();
    }
    public static void terminar(){
         // Shutdown the manager
             manager.shutdown();
    }
}
