package icaro.aplicaciones.recursos.persistenciaPrediccionBD.imp;

import icaro.aplicaciones.informacion.dominioClases.aplicacionPrediccion.Confianza.InfoCompletaExpertosConfianza;
import icaro.aplicaciones.informacion.dominioClases.aplicacionPrediccion.InformationExtraction.InfoHistoricoClubs;
import icaro.aplicaciones.informacion.dominioClases.aplicacionPrediccion.InformationExtraction.InfoHistoricoTemporadas;
import icaro.aplicaciones.informacion.dominioClases.aplicacionPrediccion.InformationExtraction.InfoTemporada;
import icaro.aplicaciones.recursos.persistenciaPrediccionBD.ItfUsoPersistenciaPrediccionBD;
import icaro.infraestructura.patronRecursoSimple.imp.ImplRecursoSimple;
import icaro.infraestructura.recursosOrganizacion.recursoTrazas.imp.componentes.InfoTraza;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClaseGeneradoraPersistenciaPrediccionBD extends ImplRecursoSimple implements
        ItfUsoPersistenciaPrediccionBD {

    private static final long serialVersionUID = 1L;
//	private ItfUsoRecursoTrazas trazas;
//	private ConsultaBBDD consulta;
    private PersistenciaPrediccionImp PrediccionBD;

    public ClaseGeneradoraPersistenciaPrediccionBD(String id) throws Exception {

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
            PrediccionBD = new PersistenciaPrediccionImp();
            PrediccionBD.crearEsquema(id);
            trazas.aceptaNuevaTraza(new InfoTraza(this.getId(),
                    "Creando el esquema " + id,
                    InfoTraza.NivelTraza.debug));
//			Se hace una consulta de prueba para ver si funciona lo creado
            try {
                compruebaUsuario("root", "toor");

            } catch (Exception e) {
                e.printStackTrace();
                this.trazas.aceptaNuevaTraza(new InfoTraza(id,
                        "El script de Creacion de la BD y de las Tablas se ha ejecutado satisfactoriamente, pero al realizar la prueba de Prediccion a la BD Se ha producido un error: " + e.getMessage()
                        + ": Verificar el que el nombre de la BD definido en el Script de creacion Coincida con"
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

    public boolean compruebaUsuario(String usuario, String password)
            throws ErrorEnRecursoException {
        try {

            //               Boolean resconsulta = consulta.compruebaUsuario(usuario, password);
            Boolean resconsulta = PrediccionBD.compruebaUsuario(usuario, password);
            this.trazas.aceptaNuevaTraza(new InfoTraza(id,
                    "Comprobando usuario " + usuario + " Resultado consulta = " + resconsulta,
                    InfoTraza.NivelTraza.debug));
            return resconsulta;
        } catch (Exception e) {
            e.printStackTrace();
            this.trazas.aceptaNuevaTraza(new InfoTraza(id,
                    "Comprobando usuario " + usuario + " Se ha producido un error: " + e.getMessage(),
                    InfoTraza.NivelTraza.error));
            this.itfAutomata.transita("error");
            return false;

        }

    }

    public boolean compruebaNombreUsuario(String usuario)
            throws ErrorEnRecursoException {
        trazas.aceptaNuevaTraza(new InfoTraza(this.getId(),
                "Comprobando nombre de usuario " + usuario,
                InfoTraza.NivelTraza.debug));
//		return consulta.compruebaNombreUsuario(usuario);
        return PrediccionBD.compruebaNombreUsuario(usuario);

    }

    public void insertaUsuario(String usuario, String password)
            throws ErrorEnRecursoException {
        trazas.aceptaNuevaTraza(new InfoTraza(this.getId(),
                "Insertando usuario " + usuario,
                InfoTraza.NivelTraza.debug));
//		consulta.insertaUsuario(usuario, password);
        PrediccionBD.insertaUsuario(usuario, password);

    }

    @Override
    public void termina() {
        trazas.aceptaNuevaTraza(new InfoTraza(this.getId(),
                "Terminando recurso",
                InfoTraza.NivelTraza.debug));
        //	PrediccionBBDD.desconectar();
        PrediccionBD.desconectar();
        try {
            super.termina();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public InfoTemporada getTemporada(int temp) throws ErrorEnRecursoException {
        return PrediccionBD.getTemporada(temp);
    }

    @Override
    public InfoHistoricoClubs getHistoricoEquipos() throws ErrorEnRecursoException {
        return PrediccionBD.getHistoricoEquipos();
    }

    @Override
    public InfoCompletaExpertosConfianza getListaInfoExpertos() throws ErrorEnRecursoException 
    {
        return PrediccionBD.getListaExpertos();
    }

    public boolean insertarHistoricoClubs(InfoHistoricoClubs infoHistClubs) throws ErrorEnRecursoException 
    {
        return PrediccionBD.insertaHistorico(infoHistClubs);

    }

    @Override
    public boolean insertarTemporadas(InfoHistoricoTemporadas infoHistTemporada) throws ErrorEnRecursoException 
    {
        return PrediccionBD.insertarTemporada(infoHistTemporada);
    }

    @Override
    public boolean insertarHistoricoTemporada(InfoTemporada infoTemporada) throws ErrorEnRecursoException 
    {
        return PrediccionBD.insertarTemporada(infoTemporada);
    }


}
