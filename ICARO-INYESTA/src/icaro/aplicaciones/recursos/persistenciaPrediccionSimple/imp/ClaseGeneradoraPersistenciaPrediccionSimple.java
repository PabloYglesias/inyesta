/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package icaro.aplicaciones.recursos.persistenciaPrediccionSimple.imp;

import icaro.aplicaciones.informacion.dominioClases.aplicacionPrediccion.Confianza.InfoCompletaExpertosConfianza;
import icaro.aplicaciones.informacion.dominioClases.aplicacionPrediccion.Confianza.InfoExpertoPredictor;
import icaro.aplicaciones.informacion.dominioClases.aplicacionPrediccion.Confianza.InfoPrediccionConfianzaDTO;
import icaro.aplicaciones.informacion.dominioClases.aplicacionPrediccion.Confianza.ListaPrediccionConfianza;
import icaro.aplicaciones.informacion.dominioClases.aplicacionPrediccion.InfoPrediccionResultado.Ganador;
import icaro.aplicaciones.recursos.persistenciaAccesoBD.imp.ErrorEnRecursoException;
import icaro.aplicaciones.recursos.persistenciaPrediccionSimple.ItfUsoPersistenciaPrediccionSimple;
import icaro.infraestructura.patronRecursoSimple.imp.ImplRecursoSimple;
import icaro.infraestructura.recursosOrganizacion.recursoTrazas.imp.componentes.InfoTraza;
import java.io.IOException;
import java.io.Serializable;
import java.rmi.Remote;

public class ClaseGeneradoraPersistenciaPrediccionSimple extends ImplRecursoSimple implements
        ItfUsoPersistenciaPrediccionSimple {

    private static final long serialVersionUID = 1L;
//	private ItfUsoRecursoTrazas trazas;
//	private ConsultaBBDD consulta;
//        public  PersistenciaPrediccionImp PrediccionBD;

    public ClaseGeneradoraPersistenciaPrediccionSimple(String id) throws Exception {

        super(id);
        /*
         try{
         trazas = (ItfUsoRecursoTrazas)this.itfUsoRepositorioInterfaces.obtenerInterfaz(
         NombresPredefinidos.ITF_USO+NombresPredefinidos.RECURSO_TRAZAS);
         }catch(Exception e){
         this.itfAutomata.transita("error");
         System.out.println("No se pudo usar el recurso de trazas");
         }
         */
        try {
//                        PrediccionBD = new PersistenciaPrediccionImp();

            PersistenciaPrediccionImp.crearEquipos();
            trazas.aceptaNuevaTraza(new InfoTraza(this.getId(),
                    "Creando la BD " + id,
                    InfoTraza.NivelTraza.debug));
//			Se hace una consulta de prueba para ver si funciona lo creado
            try {
                PersistenciaPrediccionImp.compruebaEquipo("RMadrid");

            } catch (Exception e) {
                e.printStackTrace();
                this.trazas.aceptaNuevaTraza(new InfoTraza(id,
                        "El script de Creacion de la BD y de las Tablas se ha ejecutado satisfactoriamente, pero al realizar la prueba de Prediccion a la BD Se ha producido un error: " + e.getMessage()
                        + ": Verificar el que el nombre de la BD definido en el Scrip de creacion Coincida con"
                        + "el nombre de la BD definido en la propiedad: MYSQL_NAME_BD",
                        InfoTraza.NivelTraza.error));
                this.itfAutomata.transita("error");
                throw e;
            }


        } catch (Exception e) {
            this.trazas.aceptaNuevaTraza(new InfoTraza(id,
                    " Se ha producido un error en la creación del esquema de la BD : " + e.getMessage()
                    + ": Verificar el que el nombre de la BD definido en el Scrip de creacion Coincida con"
                    + "el nombre de la BD definido en la propiedad: MYSQL_NAME_BD",
                    InfoTraza.NivelTraza.error));
            this.itfAutomata.transita("error");
            throw e;
        }
    }

    @Override
    public boolean compruebaPartido(String equipo1, String equipo2, String fecha)
            throws ErrorEnRecursoException {
        try {

            //               Boolean resconsulta = consulta.compruebaUsuario(usuario, password);
            Boolean resconsulta = PersistenciaPrediccionImp.compruebaPartido(equipo1, equipo2, fecha);
            this.trazas.aceptaNuevaTraza(new InfoTraza(id,
                    "Comprobando partido | " + equipo1 + " vs " + equipo2 + "| Fecha: " + fecha + " Resultado consulta = " + resconsulta,
                    InfoTraza.NivelTraza.debug));
            return resconsulta;
        } catch (Exception e) {
            e.printStackTrace();
            this.trazas.aceptaNuevaTraza(new InfoTraza(id,
                    "Comprobando partido | " + equipo1 + " vs " + equipo2 + "| Fecha: " + fecha + " Se ha producido un error: " + e.getMessage(),
                    InfoTraza.NivelTraza.error));
            this.itfAutomata.transita("error");
            return false;

        }

    }

    @Override
    public boolean compruebaPrediccion(String equipo1, String equipo2, String fecha)
            throws ErrorEnRecursoException {
        try {

            Boolean resconsulta = PersistenciaPrediccionImp.compruebaPrediccion(equipo1, equipo2, fecha);
            this.trazas.aceptaNuevaTraza(new InfoTraza(id,
                    "Comprobando partido | " + equipo1 + " vs " + equipo2 + "| Fecha: " + fecha + " Resultado consulta = " + resconsulta,
                    InfoTraza.NivelTraza.debug));
            return resconsulta;
        } catch (Exception e) {
            e.printStackTrace();
            this.trazas.aceptaNuevaTraza(new InfoTraza(id,
                    "Comprobando partido | " + equipo1 + " vs " + equipo2 + "| Fecha: " + fecha + " Se ha producido un error: " + e.getMessage(),
                    InfoTraza.NivelTraza.error));
            this.itfAutomata.transita("error");
            return false;

        }

    }

    public boolean compruebaEquipo(String equipo)
            throws ErrorEnRecursoException, IOException {
        trazas.aceptaNuevaTraza(new InfoTraza(this.getId(),
                "Comprobando nombre de usuario " + equipo,
                InfoTraza.NivelTraza.debug));
//		return consulta.compruebaNombreUsuario(usuario);
        return PersistenciaPrediccionImp.compruebaEquipo(equipo);

    }

    public void insertaEquipo(String equipo, String value)
            throws ErrorEnRecursoException {
        try {
            trazas.aceptaNuevaTraza(new InfoTraza(this.getId(),
                    "Insertando usuario " + equipo,
                    InfoTraza.NivelTraza.debug));

            PersistenciaPrediccionImp.insertaEquipo(equipo, value);
        } catch (Exception e) {
            e.printStackTrace();
            this.trazas.aceptaNuevaTraza(new InfoTraza(id,
                    "Comprobando usuario " + equipo + " Se ha producido un error: " + e.getMessage(),
                    InfoTraza.NivelTraza.error));
            this.itfAutomata.transita("error");
        }
    }

    @Override
    public void termina() {
        trazas.aceptaNuevaTraza(new InfoTraza(this.getId(),
                "Terminando recurso",
                InfoTraza.NivelTraza.debug));
        try {
            PersistenciaPrediccionImp.terminar();
            super.termina();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insertaPrediccion(String equipo1, String equipo2, String fecha) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ListaPrediccionConfianza damePredicciones(String strNombrePredictor, String fechaInicio, String fechaFinal) throws Exception {

        ListaPrediccionConfianza pListaPredicciones = new ListaPrediccionConfianza();
        
        //Ojo, fusiladisimo
        InfoPrediccionConfianzaDTO pPartido1 = new InfoPrediccionConfianzaDTO();
        InfoPrediccionConfianzaDTO pPartido2 = new InfoPrediccionConfianzaDTO();
        InfoPrediccionConfianzaDTO pPartido3 = new InfoPrediccionConfianzaDTO();
        InfoPrediccionConfianzaDTO pPartido4 = new InfoPrediccionConfianzaDTO();
        
        if(strNombrePredictor.equals("Maldini"))
        {
            pListaPredicciones.setStrNombrePredictor("Maldini");

            pPartido1.setEquipoLocal("RMadrid");
            pPartido1.setEquipoVisitante("Barcelona");
            pPartido1.setnIdJornada(1);
            pPartido1.setStrFecha("01-01-2014");
            pPartido1.setAcierto(true);
            pPartido1.setNombrePredictor("Maldini");
            pPartido1.setPrediccion(Ganador.eLOCAL);
             
            pPartido2.setEquipoLocal("Getafe");
            pPartido2.setEquipoVisitante("RMadrid");
            pPartido2.setnIdJornada(2);
            pPartido2.setStrFecha("02-02-2014");
            pPartido2.setAcierto(true);
            pPartido2.setNombrePredictor("Maldini");
            pPartido2.setPrediccion(Ganador.eVISITANTE);
            
            
            pPartido3.setEquipoLocal("Bayern");
            pPartido3.setEquipoVisitante("RMadrid");
            pPartido3.setnIdJornada(3);
            pPartido3.setStrFecha("03-02-2014");
            pPartido3.setAcierto(true);
            pPartido3.setNombrePredictor("Maldini");
            pPartido3.setPrediccion(Ganador.eVISITANTE);
            
            pPartido4.setEquipoLocal("RMadrid");
            pPartido4.setEquipoVisitante("ATM");
            pPartido4.setnIdJornada(4);
            pPartido4.setStrFecha("04-02-2014");
            pPartido4.setAcierto(true);   
            pPartido4.setNombrePredictor("Maldini");
            pPartido4.setPrediccion(Ganador.eLOCAL);
        }
        else if (strNombrePredictor.equals("Quiniela"))
        {
            pListaPredicciones.setStrNombrePredictor("Quiniela");

            pPartido1.setEquipoLocal("RMadrid");
            pPartido1.setEquipoVisitante("Barcelona");
            pPartido1.setnIdJornada(1);
            pPartido1.setStrFecha("01-01-2014");
            pPartido1.setAcierto(false);
            pPartido1.setNombrePredictor("Quiniela");
            pPartido1.setPrediccion(Ganador.eEMPATE);

            pPartido2.setEquipoLocal("Getafe");
            pPartido2.setEquipoVisitante("RMadrid");
            pPartido2.setnIdJornada(2);
            pPartido2.setStrFecha("02-02-2014");
            pPartido2.setAcierto(false);
            pPartido2.setNombrePredictor("Quiniela");
            pPartido2.setPrediccion(Ganador.eEMPATE);            
            
            pPartido3.setEquipoLocal("Bayern");
            pPartido3.setEquipoVisitante("RMadrid");
            pPartido3.setnIdJornada(3);
            pPartido3.setStrFecha("03-02-2014");
            pPartido3.setAcierto(true);
            pPartido3.setNombrePredictor("Quiniela");
            pPartido3.setPrediccion(Ganador.eEMPATE);            

            
            pPartido4.setEquipoLocal("RMadrid");
            pPartido4.setEquipoVisitante("ATM");
            pPartido4.setnIdJornada(4);
            pPartido4.setStrFecha("04-02-2014");
            pPartido4.setAcierto(true);  
            pPartido4.setNombrePredictor("Quiniela");
            pPartido1.setPrediccion(Ganador.eLOCAL);          
        }
        pListaPredicciones.insertaPrediccion(pPartido1);
        pListaPredicciones.insertaPrediccion(pPartido2);
        pListaPredicciones.insertaPrediccion(pPartido3);
        pListaPredicciones.insertaPrediccion(pPartido4);
       
        try {
            trazas.aceptaNuevaTraza(new InfoTraza(this.getId(),
                    "Obteniendo predicciones " + strNombrePredictor,
                    InfoTraza.NivelTraza.debug));

          //  PersistenciaPrediccionImp.insertaEquipo(equipo, value);
        } catch (Exception e) {
            e.printStackTrace();
            this.trazas.aceptaNuevaTraza(new InfoTraza(id,
                    "Comprobando predictor " + strNombrePredictor + " Se ha producido un error: " + e.getMessage(),
                    InfoTraza.NivelTraza.error));
            this.itfAutomata.transita("error");
        }
        return pListaPredicciones;
    }

    @Override
    public InfoCompletaExpertosConfianza dameExpertos() throws Exception 
    {
       InfoCompletaExpertosConfianza Info = new InfoCompletaExpertosConfianza();
       
        InfoExpertoPredictor Predictor1 = new InfoExpertoPredictor();
        InfoExpertoPredictor Predictor2 = new InfoExpertoPredictor();
        
        Predictor1.setNombreExperto("Maldini");
        Predictor2.setNombreExperto("Quiniela");
        
        Info.insertaExperto(Predictor1);
        Info.insertaExperto(Predictor2);
        
        return Info;
    }
}
