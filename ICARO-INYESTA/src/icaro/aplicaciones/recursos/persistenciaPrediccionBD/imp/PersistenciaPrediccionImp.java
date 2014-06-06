package icaro.aplicaciones.recursos.persistenciaPrediccionBD.imp;

import icaro.aplicaciones.informacion.dominioClases.aplicacionPrediccion.Confianza.InfoCompletaExpertosConfianza;
import icaro.aplicaciones.informacion.dominioClases.aplicacionPrediccion.Confianza.InfoExpertoPredictor;
import icaro.aplicaciones.informacion.dominioClases.aplicacionPrediccion.Confianza.InfoPrediccionConfianzaDTO;
import icaro.aplicaciones.informacion.dominioClases.aplicacionPrediccion.InfoPrediccionResultado;
import icaro.aplicaciones.informacion.dominioClases.aplicacionPrediccion.InformationExtraction.InfoClubs;
import icaro.aplicaciones.informacion.dominioClases.aplicacionPrediccion.InformationExtraction.InfoHistoricoClubs;
import icaro.aplicaciones.informacion.dominioClases.aplicacionPrediccion.InformationExtraction.InfoHistoricoTemporadas;
import icaro.aplicaciones.informacion.dominioClases.aplicacionPrediccion.InformationExtraction.InfoJornada;
import icaro.aplicaciones.informacion.dominioClases.aplicacionPrediccion.InformationExtraction.InfoPartido;
import icaro.aplicaciones.informacion.dominioClases.aplicacionPrediccion.InformationExtraction.InfoTemporada;
import icaro.aplicaciones.recursos.persistenciaPrediccionBD.imp.util.ScriptRunner;
import icaro.infraestructura.entidadesBasicas.NombresPredefinidos;
import icaro.infraestructura.entidadesBasicas.descEntidadesOrganizacion.DescInstanciaRecursoAplicacion;
import icaro.infraestructura.recursosOrganizacion.configuracion.ItfUsoConfiguracion;
import icaro.infraestructura.recursosOrganizacion.repositorioInterfaces.imp.ClaseGeneradoraRepositorioInterfaces;
import java.io.FileReader;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Proporciona los servicios de Prediccion a la bbdd con mysql
 *
 * @author PAblo C. Cañizares
 *
 */
public class PersistenciaPrediccionImp {

    /**
     * Nombre de la BBDD con la que se trabaja
     */
//	static public  final String nombreBD= "bbddejemplo";
//        static public  final String nombreBD;
    static public String nombreBD;
    /**
     * Nombre de usuario de Prediccion (con privilegios de root) a la bbdd
     */
    static private String LOGIN;//= Configuracion.obtenerParametro("MYSQL_USER");
    //static private  final String LOGIN="root";
    /**
     * Clave de Prediccion correspondiente con el usuario indicado
     */
    static private String PASSWORD;//= Configuracion.obtenerParametro("MYSQL_PASSWORD");
    //static private  final String PASSWORD= "adminwww";
    /**
     * Url en d�nde se situa la bbdd
     */
    static private String URL_CONEXION;//= Configuracion.obtenerParametro("MYSQL_URL");
    //static private  final String URL_CONEXION="jdbc:mysql://localhost:3306/";
    /**
     * Objeto resultante de la comunicaci�n establecida con la bbdd
     */
    private static Connection conn = null;

    /**
     * Script de creacion de tablas de la bbdd
     */
    static private String ejecutable;//= Configuracion.obtenerParametro("MYSQL_SCRIPT_ITEMS");
    //static private final String ejecutable="createTablasItems.bat";

    private Statement query;
    /**
     * Resultado de la consulta a la base de datos
     */
    private ResultSet resultado;

    /**
     * Constructor por defecto
     *
     */
    public PersistenciaPrediccionImp() {
    }

    public static void crearEsquema(String idDescripcionIstanciaRecurso) throws Exception {
        try {
            if (obtenerParametrosBDPersistencia(idDescripcionIstanciaRecurso)) {
                conectar();
            } else {
                System.out.println("--------------Hubo algun Problema al obtener los parametros de la BD----------------");

            }

        } catch (Exception e) {

            e.printStackTrace();
        }

    }

    private static boolean obtenerParametrosBDPersistencia(String idDescripcionIstanciaRecurso) throws Exception {
        Boolean obtenerParametrosPersistencia = false;
        try {
            ItfUsoConfiguracion config = (ItfUsoConfiguracion) ClaseGeneradoraRepositorioInterfaces.instance().obtenerInterfaz(NombresPredefinidos.ITF_USO + NombresPredefinidos.CONFIGURACION);
            DescInstanciaRecursoAplicacion descRecurso = config.getDescInstanciaRecursoAplicacion(idDescripcionIstanciaRecurso);
            nombreBD = descRecurso.getValorPropiedad("MYSQL_NAME_BD");
            LOGIN = descRecurso.getValorPropiedad("MYSQL_USER");
            PASSWORD = descRecurso.getValorPropiedad("MYSQL_PASSWORD");
            URL_CONEXION = descRecurso.getValorPropiedad("MYSQL_URL");
// Esto se podría quitar pq el npmbre de la BD no se pone en la URL
            URL_CONEXION = URL_CONEXION.replaceAll(nombreBD, "");
            ejecutable = descRecurso.getValorPropiedad("MYSQL_SCRIPT_ITEMS");
            obtenerParametrosPersistencia = true;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ErrorEnRecursoException("Ha habido un problema al obtener la configuracion del recurso de persistencia");
        }
        try {
            Class.forName("com.mysql.jdbc.Driver");
            //            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException e) {
            // TODO Bloque catch generado autom�ticamente
            e.printStackTrace();
            throw new ErrorEnRecursoException("Ha habido un problema  con la conexion con la base de datos (instanciando el driver connector com.mysql.jdbc.Driver)");

        }
        return obtenerParametrosPersistencia;
    }

    private static boolean crearTablas() throws Exception {
        ScriptRunner runner = new ScriptRunner(conn, false, true);
        boolean esOK = false;
        try {
            runner.runScript(new FileReader(ejecutable));
            esOK = true;
        } catch (IOException e) {
            throw new Exception("Ha habido un error al leer el fichero " + ejecutable, e);
        } catch (SQLException e1) {
            throw new Exception("Ha habido un error al ejecutar el script SQL " + ejecutable, e1);
        }
        return esOK;
    }

    /**
     * Realiza una conexion (principio de la comunicaci�n) sobre la bbdd
     *
     * @throws ErrorEnRecursoException
     */
    private static void conectar() throws ErrorEnRecursoException {

        try {

            conn = DriverManager.getConnection(URL_CONEXION, LOGIN, PASSWORD);

        } catch (SQLException e) {
            e.printStackTrace();

            throw new ErrorEnRecursoException("Ha habido un problema con la conexion con la base de datos " + URL_CONEXION + "\nusuario " + LOGIN
                    + "\npassword " + PASSWORD);
            // TODO Bloque catch generado autom�ticamente

        }

    }

    /**
     * Realiza la desconexion (fin de la comunicaci�n) sobre la bbdd
     *
     * @throws ErrorEnRecursoException
     */
    public static void desconectar() {

        try {
            conn.close();
        } catch (SQLException e) {
            System.out.println("\nNo se ha podido cerrar la conexi�n con la base de datos: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public boolean compruebaUsuario(String usuario, String password) throws ErrorEnRecursoException {

        boolean estado = false;

        try {
            conectar();
            // 		crearQuery();
            query = conn.createStatement();
            resultado = query.executeQuery("SELECT * FROM " + this.nombreBD + ".usuario U where U.usuario = '"
                    + usuario + "' and U.contrasena = '" + password + "'");
            if (resultado.next()) {
                estado = true;
            } else {
                estado = false;
            }
            resultado.close();
            desconectar();
            return estado;
        } catch (Exception e) {
            throw new ErrorEnRecursoException(e.getMessage());
        }
    }

    public boolean compruebaNombreUsuario(String usuario) throws ErrorEnRecursoException {

        boolean estado = false;

        try {
            conectar();
            //  		crearQuery();
            query = conn.createStatement();
            resultado = query.executeQuery("SELECT * FROM " + this.nombreBD + ".usuario U where U.usuario = '"
                    + usuario + "'");
            if (resultado.next()) {
                estado = true;
            } else {
                estado = false;
            }
            resultado.close();
            desconectar();
            return estado;
        } catch (Exception e) {
            throw new ErrorEnRecursoException(e.getMessage());
        }
    }

    public void insertaUsuario(String usuario, String password) throws ErrorEnRecursoException {

        boolean estado = false;

        try {
            conectar();
            // 		crearQuery();
            query = conn.createStatement();
            query.executeUpdate("INSERT INTO " + this.nombreBD + ".usuario VALUES ('"
                    + usuario + "','" + password + "')");
            desconectar();
        } catch (Exception e) {
            throw new ErrorEnRecursoException(e.getMessage());
        }
    }

    InfoTemporada getTemporada(int temp) throws ErrorEnRecursoException {
        InfoTemporada Temporada = null;
        try {
            Temporada = new InfoTemporada("Temporada " + Integer.toString(temp));
            conectar();
            //  		crearQuery();

            for (int i = 1; i <= 38; i++) {
                InfoJornada Jornada = null;
                query = conn.createStatement();
                String StringQuery = "SELECT * FROM " + this.nombreBD + ".acta A where A.Temporada = '"
                        + temp + "' and A.NumeroJornada = '" + Integer.toString(i) + "'";
                ResultSet rs = query.executeQuery(StringQuery);

                if (rs.next()) {
                    Jornada = new InfoJornada("Jornada  " + Integer.toString(i));
                }

                while (rs.next()) {

                    System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4) + " " + rs.getString(5) + " " + rs.getString(6) + " " + rs.getString(7) + " " + rs.getString(8));
                    InfoPartido Partido = new InfoPartido();
                    Partido.setJornada(i);

                    Partido.setEquipoLocal(rs.getString("NombreEquipoLocal"));
                    Partido.setVisitante(rs.getString("NombreEquipoVisitante"));
                    Partido.setFecha(rs.getString("Fecha"));
                    Partido.setGolesLocal(rs.getString("GolesLocal"));
                    Partido.setGolesLocal(rs.getString("GolesVisitante"));

                    Jornada.InsertarPartido(Partido);
                }
                if(Jornada != null)
                    Temporada.InsertarJornada(Jornada);
            }

            desconectar();

        } catch (Exception e) {
            throw new ErrorEnRecursoException(e.getMessage());
        }
        return Temporada;

    }

    InfoHistoricoClubs getHistoricoEquipos() throws ErrorEnRecursoException {
        InfoHistoricoClubs Clubs = null;
        try {
            Clubs = new InfoHistoricoClubs();
            conectar();
            //  		crearQuery();

            query = conn.createStatement();
            ResultSet rs = query.executeQuery("SELECT * FROM " + this.nombreBD + ".equipo");

            while (rs.next()) {

                InfoClubs Club = new InfoClubs();

                System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4) + " " + rs.getString(5) + " " + rs.getString(6) + " " + rs.getString(7) + " " + rs.getString(8));
                Club.setNombreEquipo(rs.getString("NombreEquipo"));
                Club.setNumeroLigas(rs.getInt("NumeroLigas"));
                Club.setNumeroCopasRey(rs.getInt("NumeroCopas"));
                Club.setNumeroGolesContra(rs.getInt("GC"));
                Club.setNumeroGolesFavor(rs.getInt("GF"));
                Club.setNumeroPartidosEmpatados(rs.getInt("PE"));
                Club.setNumeroPartidosGanados(rs.getInt("PG"));
                Club.setNumeroPartidosJugados(rs.getInt("PJ"));
                Club.setNumeroPartidosPerdidos(rs.getInt("PP"));
                Club.setNumeroPuntos(rs.getInt("Puntos"));
                Club.setNumeroTPrimera(rs.getInt("NT1"));
                Club.setNumeroTSegunda(rs.getInt("NT2"));
                //Club.setNumeroTSegundaB(rs.getInt("NT2B"));
                Club.setNumeroTTercera(rs.getInt("NT3"));

                Clubs.InsertarClub(Club);
            }

            desconectar();

        } catch (Exception e) {
            throw new ErrorEnRecursoException(e.getMessage());
        }
        return Clubs;
    }

    InfoCompletaExpertosConfianza getListaExpertos() throws ErrorEnRecursoException {
        InfoCompletaExpertosConfianza infoExpertos = null;

        try {
            infoExpertos = new InfoCompletaExpertosConfianza();
            conectar();
            //  		crearQuery();

            query = conn.createStatement();
            ResultSet res = query.executeQuery("SELECT * FROM " + this.nombreBD + ".experto");

            while (res.next()) {
                InfoExpertoPredictor Predictor = null;
                String PredictorId, PredictorNombre;
                InfoJornada Jornada = null;

                PredictorId = res.getString("IDExperto");
                PredictorNombre = res.getString("nombre");
                query = conn.createStatement();
                ResultSet rs = query.executeQuery("SELECT * FROM " + this.nombreBD + ".apuestaexperto A where A.IDExperto = '"
                        + PredictorId + "'");

                if (rs.next()) {
                    Predictor = new InfoExpertoPredictor();
                    Predictor.setNombreExperto(PredictorNombre);
                }

                while (rs.next()) {
                    InfoPrediccionConfianzaDTO InfoPrediccion = new InfoPrediccionConfianzaDTO();
                    InfoPrediccion.setnIdActa(rs.getInt("IDActa"));

                    query = conn.createStatement();
                    ResultSet rsPartido = query.executeQuery("SELECT * FROM " + this.nombreBD + ".acta A where A.IDActa = '"
                            + Integer.toString(InfoPrediccion.getnIdActa()) + "'");

                    if (rsPartido.next()) {
                        String local = rsPartido.getString("NombreEquipoLocal");
                        String visitante = rsPartido.getString("NombreEquipoVisitante");
                        String resultado = rsPartido.getString("NombreEquipoVisitante");

                        InfoPrediccion.setEquipoLocal(local);
                        InfoPrediccion.setEquipoVisitante(local);
                        InfoPrediccion.setPrediccionString(resultado);
                    }
                    Predictor.insertaPrediccion(InfoPrediccion);
                }
                infoExpertos.insertaExperto(Predictor);
            }

            desconectar();

        } catch (Exception e) {
            throw new ErrorEnRecursoException(e.getMessage());
        }

        return infoExpertos;
    }

    boolean insertaHistorico(InfoHistoricoClubs infoHistClubs) throws ErrorEnRecursoException {
        boolean estado = false;

        try {
            conectar();

            for (int i = 0; i < infoHistClubs.getNumeroClubes(); i++) {
                InfoClubs Club = infoHistClubs.getClub(i);
                String Sentence;

                Sentence = "INSERT INTO " + this.nombreBD + ".equipo (NombreEquipo, NombreEstadio, NumeroLigas, NumeroCopas, Fundacion";
                Sentence += ", NT1, Puntos, PJ, PG, PE, PP, GF, GC, UT1, NT2, NT3) ";
                Sentence += "VALUES ('" + Club.getNombreEquipo() + "', '" + Club.GetNombreEstadio() + "' , '" + Club.getNumeroLigasString() + "', ";
                Sentence += "'" + Club.getNumeroCopasReyString() + "', '" + Club.getAñoFundacionString() + "' , '" + Club.getNumeroTPrimeraString() + "', ";
                Sentence += "'" + Club.getNumeroPuntosString() + "', '" + Club.getNumeroPartidosJugadosString() + "' , '" + Club.getNumeroPartidosGanadosString() + "', ";
                Sentence += "'" + Club.getNumeroPartidosEmpatadosString() + "', '" + Club.getNumeroPartidosPerdidosString() + "' , '" + Club.getNumeroGolesFavorString() + "', ";
                Sentence += "'" + Club.getNumeroGolesContraString() + "', '" + Club.getUltimaTPrimeraString() + "' , '" + Club.getUltimaTSegundaString() + "', ";
                Sentence += "'" + Club.getUltimaTTerceraString() + "');";

                System.out.println(Sentence);

                try {

                    query = conn.createStatement();
                    int nInt = query.executeUpdate(Sentence);

                    ResultSet rs = query.executeQuery("SELECT DISTINCT LAST_INSERT_ID() FROM equipo;");

                    if (resultado.next()) {
                        estado = true;
                    } else {
                        estado = false;
                    }
                    resultado.close();
                } catch (Exception e) {
                };
            }

            desconectar();

        } catch (Exception e) {
            throw new ErrorEnRecursoException(e.getMessage());
        }
        return estado;
    }

    boolean insertarPartido(int nTemporada, InfoPartido Partido)
    {
        boolean estado = true;
        String Sentence;
        Sentence = "INSERT INTO " + this.nombreBD + ".acta (Temporada, NumeroJornada, NombreEstadio, NombreEquipoLocal, NombreEquipoVisitante, GolesLocal, GolesVisitante";
        Sentence += ", NumeroColegiado, FechaPartido) ";
        Sentence += "VALUES ('" + Integer.toString(nTemporada)+ "', '" + Partido.getNumeroJornada()+ "' , '" + Partido.getNombreEstadio()+ "', ";
        Sentence += "'" + Partido.getEquipoLocal() + "', '" + Partido.getEquipoVisitante() + "' , '" + Partido.getGolesLocal()+ "', ";
        Sentence += "'" + Partido.getGolesVisitante()+ "', '" + Integer.toString(Partido.getNumeroColegiado()) + "' , '" + Partido.getFechaPartido()+ "'); ";
     

        System.out.println(Sentence);

        try {

            query = conn.createStatement();
            int nInt = query.executeUpdate(Sentence);

            ResultSet rs = query.executeQuery("SELECT DISTINCT LAST_INSERT_ID() FROM acta;");

            if (resultado.next()) {
                estado = true;
            } else {
                estado = false;
            }
            resultado.close();        
        } catch (Exception e) {
        };             
        return estado;
    }
    boolean insertarJornada(int nTemporada, InfoJornada Jornada)
    {
        boolean estado = false;
                
        //Insertar los partidos (10)
        for(int i=0;i<Jornada.getNumeroPartidos();i++)
        {
            InfoPartido Partido = Jornada.getPartido(i);
            insertarPartido(nTemporada,Partido);
        }

        return estado;
    }
    public boolean insertarTemporada(InfoTemporada infoTemporada) throws ErrorEnRecursoException 
    {
        boolean estado=true;
        try {
            conectar();

            for (int i = 0; i < infoTemporada.getNumeroJornadas(); i++) 
            {
                InfoJornada Jornada;
                Jornada = infoTemporada.getJornada(i);

                estado &= insertarJornada(infoTemporada.getNumeroTemporada(),Jornada);
            }

            desconectar();

        } catch (Exception e) {
            throw new ErrorEnRecursoException(e.getMessage());
        }
        return estado;
        
    }

    public boolean insertarTemporada(InfoHistoricoTemporadas infoHistTemporada) throws ErrorEnRecursoException 
    {
        
        //Hacer un FOR y llamar a insertarTemporada
        for(int i=0;i<infoHistTemporada.getNumeroTemporadas();i++)
        {
            insertarTemporada(infoHistTemporada.getTemporada(i));
        }
        return false;
    }
}
