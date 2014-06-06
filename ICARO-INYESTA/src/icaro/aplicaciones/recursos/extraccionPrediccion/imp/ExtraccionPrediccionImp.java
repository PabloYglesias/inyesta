/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package icaro.aplicaciones.recursos.extraccionPrediccion.imp;

import icaro.aplicaciones.informacion.dominioClases.aplicacionPrediccion.Confianza.InfoExpertoPredictor;
import icaro.aplicaciones.informacion.dominioClases.aplicacionPrediccion.Confianza.InfoPrediccionConfianzaDTO;
import icaro.aplicaciones.informacion.dominioClases.aplicacionPrediccion.InformationExtraction.InfoClubs;
import icaro.aplicaciones.informacion.dominioClases.aplicacionPrediccion.InformationExtraction.InfoHistoricoClubs;
import icaro.aplicaciones.informacion.dominioClases.aplicacionPrediccion.InformationExtraction.InfoJornada;
import icaro.aplicaciones.informacion.dominioClases.aplicacionPrediccion.InformationExtraction.InfoPartido;
import icaro.aplicaciones.informacion.dominioClases.aplicacionPrediccion.InformationExtraction.InfoTemporada;

import java.io.IOException;
import java.io.Serializable;
import java.util.StringTokenizer;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ExtraccionPrediccionImp implements Serializable {


    public ExtraccionPrediccionImp() {

        try {
           int i = 0;
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public InfoHistoricoClubs ExtraerHistoricoEquipos() {

        InfoHistoricoClubs HistoricoClubs = new InfoHistoricoClubs("Liga bbva");
        Document doc, doc2;
        try {

            doc2 = Jsoup.connect("http://www.bdfutbol.com/es/e/e.html").get();

            // get all links
            int i = 0;

            Elements tabla = doc2.select("#pest0");
            
            //seleccionar de pest0
            Elements clubes = tabla.select("tbody");

            String Nombretitulo;

            Elements rows = clubes.select("tr");

            boolean bFirst = true;

            for (Element row : rows) {
                //El primero no cuenta

                if (bFirst == false) {
                    InfoClubs Club = new InfoClubs();

                    Elements stats = row.select("td");

                    //Nombre
                    Element nombre = (Element) stats.get(1);
                    Club.setNombreEquipo(nombre.text());

                    //NumeroLigas
                    Element ligas = (Element) stats.get(2);
                    Club.setNumeroLigas(Integer.parseInt(ligas.text()));

                    //Copas del rey
                    Element copas = (Element) stats.get(3);
                    Club.setNumeroCopasRey(Integer.parseInt(copas.text()));

                    //Fundacion
                    Element fundacion = (Element) stats.get(4);
                    Club.setAñoFundacion(Integer.parseInt(fundacion.text()));

                    //TemporadasEnPrimera
                    Element temporadas1 = (Element) stats.get(5);
                    Club.setNumeroTPrimera(Integer.parseInt(temporadas1.text()));

                    //Puntos
                    Element puntos = (Element) stats.get(6);
                    Club.setNumeroPuntos(Integer.parseInt(temporadas1.text()));

                    //PJugados
                    Element pjugados = (Element) stats.get(7);
                    Club.setNumeroPartidosJugados(Integer.parseInt(pjugados.text()));

                    //PGanados
                    Element pganados = (Element) stats.get(8);
                    Club.setNumeroPartidosGanados(Integer.parseInt(pganados.text()));

                    //PEmpatados
                    Element pempatados = (Element) stats.get(9);
                    Club.setNumeroPartidosEmpatados(Integer.parseInt(pempatados.text()));

                    //PEmpatados
                    Element pperdidos = (Element) stats.get(10);
                    Club.setNumeroPartidosPerdidos(Integer.parseInt(pperdidos.text()));

                    //Goles favor
                    Element golesf = (Element) stats.get(11);
                    Club.setNumeroGolesFavor(Integer.parseInt(golesf.text()));

                    //Goles contra
                    Element golesc = (Element) stats.get(12);
                    Club.setNumeroGolesContra(Integer.parseInt(golesc.text()));

                    //Ultima temporada en primera
                    Element ultimaT1 = (Element) stats.get(13);
                    Club.setUltimaTPrimera(Integer.parseInt(ultimaT1.text()));

                    //Ultima temporada en primera
                    Element t2 = (Element) stats.get(14);
                    Club.setNumeroTSegunda(Integer.parseInt(t2.text()));

                    //Ultima temporada en primera
                    Element t2b = (Element) stats.get(15);
                    Club.setNumeroTSegundaB(Integer.parseInt(t2b.text()));

                    //Ultima temporada en primera
                    Element t3 = (Element) stats.get(16);
                    Club.setNumeroTSegundaB(Integer.parseInt(t3.text()));

                    HistoricoClubs.InsertarClub(Club);
                } else {
                    bFirst = false;
                }
                
                if(HistoricoClubs.getNumeroClubes() == 30)
                {  
                    int w=0;
                    i++;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return HistoricoClubs;

    }

    public static InfoExpertoPredictor ExtraerExpertoQuiniela() {

        Document doc, doc2;
        InfoExpertoPredictor Experto = null;
        try {

            doc2 = Jsoup.connect("http://www.quiniela15.com/pronostico-quiniela").get();

            Elements tabla = doc2.select("#forecast_quiniela");
            Elements tablaSelect = tabla.select("tbody");
            String Nombretitulo;
            Experto = new InfoExpertoPredictor();

            Experto.setNombreExperto("Quiniela15-System");

            Elements rows = tablaSelect.select("tr");

            int bFirst = 0;


            for (Element row : rows) {
                //Los tres primeros no cuentan

                if (bFirst == 3) {
                    InfoPrediccionConfianzaDTO PrediccionExperto = new InfoPrediccionConfianzaDTO();
                    PrediccionExperto.setNombrePredictor("Quiniela15-System");

                    Elements stats = row.select("td");

                    //NombreLocal
                    Element equipoLocal = (Element) stats.get(1);
                    PrediccionExperto.setEquipoLocal(equipoLocal.select("strong").text());

                    //NombreVisitante
                    Element equipoVisitante = (Element) stats.get(2);
                    PrediccionExperto.setEquipoVisitante(equipoVisitante.select("strong").text());

                    //Pronostico sistema
                    Element pronosticoSistema = (Element) stats.get(7);
                    PrediccionExperto.setPrediccionString(pronosticoSistema.text());

                    Experto.insertaPrediccion(PrediccionExperto);
                } else {
                    bFirst++;
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return Experto;
    }

    public static InfoTemporada ExtractCalendar() {
        Document doc, doc2;

        InfoTemporada Temporada = null;
        try {

            doc2 = Jsoup.connect("http://www.elmundo.es/elmundodeporte/futbol/liga-primera/calendario.html").get();

            Elements tabla = doc2.select(".calendario-jornadas");
            String Nombretitulo;

            ///PRimero sacamos todas las tablas
            Elements jornadas = tabla.select("li");


            Temporada = new InfoTemporada("2013-2014");
            
            for (Element jornada : jornadas) {
                String NombreJornada, FechaInicio, FechaFin;
                Elements rows = jornada.select("tr");
                int bFirst = 0;

                Elements NombreFechaJornada = jornada.select("p.titulo-columna");

                //Obtenemos nombre y fecha de jornada
                NombreJornada = NombreFechaJornada.text();
                StringTokenizer st = new StringTokenizer(NombreJornada);
                NombreJornada = st.nextToken();
                NombreJornada += " " + st.nextToken();

                FechaInicio = st.nextToken();
                st.nextToken();
                FechaFin = st.nextToken();

                InfoJornada Jornada = new InfoJornada(NombreJornada);
                Jornada.setFechaInicio(FechaInicio);
                Jornada.setFechaFin(FechaFin);

                for (Element row : rows) {
                    InfoPartido Partido = new InfoPartido();

                    Elements stats = row.select("td");

                    Element equipoLocal = (Element) stats.get(1);
                    Partido.setEquipoLocal(equipoLocal.text());

                    Element marcador = (Element) stats.get(2);

                    if (marcador.hasClass("marcador")) {
                        String GolesLocal, GolesVisitante;
                        //si hay marcador es que ya se ha jugado el partido                        
                        StringTokenizer stMarcador = new StringTokenizer(marcador.text());
                        GolesLocal = stMarcador.nextToken();
                        stMarcador.nextToken();
                        GolesVisitante = stMarcador.nextToken();

                    }

                    Element equipoVisitante = (Element) stats.get(3);
                    Partido.setVisitante(equipoVisitante.text());
                }
                Temporada.InsertarJornada(Jornada);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return Temporada;
    }

    public static InfoTemporada ExtraerTemporada(int nTemporada) {

        Document doc, doc2;
        InfoTemporada Temporada=null;
        try {
            int nTemporadaNext = (nTemporada+1) %100;
            String strNombre = Integer.toString(nTemporada)+"-"+Integer.toString(nTemporadaNext);
            String strURL = "http://www.bdfutbol.com/es/t/t"+strNombre+".html";
            
            doc2 = Jsoup.connect(strURL).get();

            // get all links
            int i = 0;

            Temporada = new InfoTemporada(strNombre);
            Temporada.setNumeroTemporada(nTemporada);
            Elements jornadas = doc2.select("div[class=jornada]");
            for (Element jornada : jornadas) {
                String Nombretitulo;
                Element titulo = jornada.select("div[class=titjornada]").first();
                Nombretitulo = titulo.text();
                Elements table = jornada.select("TABLE[class = taulabdf]");
                Elements rows = table.select("tr");

                InfoJornada Jornada = new InfoJornada(Nombretitulo);

                boolean bFirst = true;

                for (Element row : rows) {
                    //El primero no cuenta

                    if (bFirst == false) {
                        InfoPartido Partido = new InfoPartido();

                        // for (Element td: row.children()) 
                        // {
                        Partido.setJornada(titulo.text());
                        //Fecha
                        Element fecha = (Element) row.childNode(0);
                        Partido.setFecha(fecha.text());

                        //Local
                        Element local = (Element) row.childNode(1);
                        Partido.setEquipoLocal(local.text());

                        //GolesLocal
                        Element goleslocal = (Element) row.childNode(2);
                        Partido.setGolesLocal(goleslocal.text());

                        //GolesVisitante
                        Element golesvisitante = (Element) row.childNode(3);
                        Partido.setGolesVisitante(golesvisitante.text());

                        //Visitante
                        Element visitante = (Element) row.childNode(4);
                        Partido.setVisitante(visitante.text());
                        //System.out.println(td.text());
                        // }
                        Jornada.InsertarPartido(Partido);
                    } else {
                        bFirst = false;
                    }
                }
                Temporada.InsertarJornada(Jornada);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return Temporada;

    }

    public static void terminar() 
    {
       
      
    }

    public InfoTemporada ExtraerJornadasHistorico() 
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
