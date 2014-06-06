package icaro.aplicaciones.recursos.visualizacionPrediccion.imp;

import icaro.aplicaciones.informacion.dominioClases.aplicacionAcceso.InfoAccesoSinValidar;
import icaro.aplicaciones.informacion.dominioClases.aplicacionPrediccion.InfoPrediccionSinValidar;
import icaro.aplicaciones.recursos.visualizacionPrediccion.imp.ClaseGeneradoraVisualizacionPrediccion;
import icaro.infraestructura.entidadesBasicas.comunicacion.EventoRecAgte;
import icaro.infraestructura.entidadesBasicas.NombresPredefinidos;
import icaro.infraestructura.entidadesBasicas.interfaces.InterfazUsoAgente;
import icaro.infraestructura.patronAgenteReactivo.factoriaEInterfaces.ItfUsoAgenteReactivo;
import icaro.infraestructura.recursosOrganizacion.recursoTrazas.imp.componentes.InfoTraza;
import icaro.infraestructura.recursosOrganizacion.repositorioInterfaces.ItfUsoRepositorioInterfaces;
import icaro.infraestructura.recursosOrganizacion.repositorioInterfaces.imp.ClaseGeneradoraRepositorioInterfaces;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * // *@author shA hAzard
 *
 * @created 30 de noviembre de 2007
 */
public class NotificacionesEventosVisPrediccion {

    protected ItfUsoRepositorioInterfaces itfUsoRepositorioInterfaces;
    protected ClaseGeneradoraVisualizacionPrediccion generadoraVisualizador;
    protected InterfazUsoAgente itfUsoAgenteaReportar;
    protected String identificadorAgenteaReportar;
    protected String identificadordeEsteRecurso;

    private InterfazUsoAgente itfUsoAgenteReceptor;

    public NotificacionesEventosVisPrediccion(ClaseGeneradoraVisualizacionPrediccion generadoraVis) {
// Obtenemos las informaciones que necesitamos de la clase generadora del recurso
        generadoraVisualizador = generadoraVis;
        identificadordeEsteRecurso = generadoraVis.getNombredeEsteRecurso();
 //     identificadorAgenteaReportar = generadoraVis.getNombreAgenteAReportar();

    }

    public void setIdentificadorAgenteAReportar(String identAgenteAReportar) {
        identificadorAgenteaReportar = identAgenteAReportar;
    }

    private void getInformacionAgenteaReportar() throws Exception {
//        identificadorAgenteaReportar = generadoraVisualizador.getNombreAgenteAReportar();
        if (identificadorAgenteaReportar != null) {
            try {
                itfUsoAgenteaReportar = (InterfazUsoAgente) NombresPredefinidos.REPOSITORIO_INTERFACES_OBJ.obtenerInterfaz(NombresPredefinidos.ITF_USO + identificadorAgenteaReportar);
            } catch (Exception e) {
                Logger.getLogger(NotificacionesEventosVisPrediccion.class.getName()).log(Level.SEVERE, null, e);
                //      Logger.error("Ha habido un problema enviar un  evento al agente "+IdentAgenteReceptor);
                NombresPredefinidos.RECURSO_TRAZAS_OBJ.aceptaNuevaTraza(new InfoTraza(identificadordeEsteRecurso,
                        "Ha habido un problema al objtener la interfaz del agente: " + identificadorAgenteaReportar,
                        InfoTraza.NivelTraza.error));
            }
        } else {

            NombresPredefinidos.RECURSO_TRAZAS_OBJ.aceptaNuevaTraza(new InfoTraza(identificadordeEsteRecurso,
                    "El identificador del agente al que se debe mandar el evento no esta definido : " + identificadorAgenteaReportar,
                    InfoTraza.NivelTraza.error));
            throw new Exception(" El identificador del agente al que se debe mandar el evento no esta definido. No se pueden enviar los eventos");
        }
    }

    /**
     * @return @uml.property name="visualizador"
     */
    public ClaseGeneradoraVisualizacionPrediccion getVisualizador() {
        return generadoraVisualizador;

    }

    public void notificacionCierreSistema() {

        if (identificadorAgenteaReportar == null) {
            try {
                this.getInformacionAgenteaReportar();
            } catch (Exception ex) {
                Logger.getLogger(NotificacionesEventosVisPrediccion.class.getName()).log(Level.SEVERE, null, ex);
                NombresPredefinidos.RECURSO_TRAZAS_OBJ.aceptaNuevaTraza(new InfoTraza(this.identificadordeEsteRecurso,
                        "No se puede enviar el evento pq el identificadorAgenteaReportar es null ",
                        InfoTraza.NivelTraza.error));
            }
        } else {
            try {
                itfUsoAgenteaReportar = (InterfazUsoAgente) NombresPredefinidos.REPOSITORIO_INTERFACES_OBJ.obtenerInterfaz(NombresPredefinidos.ITF_USO + identificadorAgenteaReportar);

            } catch (Exception ex) {
                Logger.getLogger(NotificacionesEventosVisPrediccion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        enviarEventoaOtroAgente(new EventoRecAgte("peticion_terminacion_usuario", identificadordeEsteRecurso, identificadorAgenteaReportar), identificadorAgenteaReportar);

    }

    public void peticionPrediccion(String equipo1, String equipo2, String fecha) {
        try {
      //       nombreAgenteAcceso = visualizador.getNombreAgenteAcceso();
            //        usoAgenteControlador = (ItfUsoAgenteReactivo) ClaseGeneradoraRepositorioInterfaces.instance().obtenerInterfaz(nombreAgenteAcceso);
            itfUsoAgenteaReportar  = (InterfazUsoAgente) NombresPredefinidos.REPOSITORIO_INTERFACES_OBJ.obtenerInterfaz(NombresPredefinidos.ITF_USO + identificadorAgenteaReportar);

        } catch (Exception ex) {
            Logger.getLogger(NotificacionesEventosVisPrediccion.class.getName()).log(Level.SEVERE, null, ex);
        }

        //provoca la petici�n de autentificaci�n
        //  String[] InfoAutenticacion = new String[]{username, password};
        InfoPrediccionSinValidar InfoPrediccion = new InfoPrediccionSinValidar(equipo1, equipo2, fecha);
        /*   usoAgenteControlador.aceptaEvento(new Evento("autenticacion",datosEnvio));
         */
        /*
         try {
         if (itfUsoRepositorioInterfaces == null) {
         itfUsoRepositorioInterfaces = ClaseGeneradoraRepositorioInterfaces.instance();
         }

         if (tipoAgenteAcceso.equals(NombresPredefinidos.TIPO_REACTIVO)) {
         //AgenteAplicacionAcceso
         ItfUsoAgenteReactivo itfUsoAgente = (ItfUsoAgenteReactivo) itfUsoRepositorioInterfaces.obtenerInterfaz(NombresPredefinidos.ITF_USO + nombreAgenteAcceso);
         if (itfUsoAgente != null) {
         // Creación del evento para enviarlo al agente. La informacion del evento es la siguiente
         // msg= "autenticacion"; msgElement= InfoAutenticacion ;
         // origen = "VisualizacionAcceso1"  ( Nombre del recurso de visualizacion) y destino = nombreAgenteAcceso
         itfUsoAgente.aceptaEvento(new EventoRecAgte("autenticacion", InfoAutenticacion, nombredeEsteRecurso, nombreAgenteAcceso));
         }

         // Comentada esta  parte para la versión Mini que no tiene  patrón de agente cognitivo. El recurso no debería enterarse
         // del tipo de agente al que le envía el evento, con la interfaz le vale
         //} else if (tipoAgenteAcceso.equals(NombresPredefinidos.TIPO_COGNITIVO)) {

         //     ItfUsoAgenteCognitivo cognitivo = (ItfUsoAgenteCognitivo) itfUsoRepositorioInterfaces.obtenerInterfaz(NombresPredefinidos.ITF_USO + nombreAgenteAcceso);
         //    InfoAccesoSinValidar sinValidar = new InfoAccesoSinValidar(username, password);
         //     EventoRecAgte evento = new EventoRecAgte("", sinValidar, "Recurso:VisualizacionAcceso", nombreAgenteAcceso);
         //     if (cognitivo != null) {
         //         cognitivo.aceptaEvento(evento);
         //     }
         }

         } catch (Exception e) {
         System.out.println("Ha habido un error al enviar el usuario y el password al agente de acceso ");
         e.printStackTrace();
         }
         */
        enviarEventoaOtroAgente(new EventoRecAgte("validarPrediccion", InfoPrediccion, identificadordeEsteRecurso, identificadorAgenteaReportar), identificadorAgenteaReportar);

    }

    public void enviarEventoaOtroAgente(EventoRecAgte eventoaEnviar, String IdentAgenteReceptor) {

   // Este método crea un evento con la información de entrada y se le envía al agente REACTIVO que se indique por medio de
        // la  interfaz de uso
        //     EventoRecAgte eventoaEnviar = null;
   // Se verifica que el identificador del agente a enviar el evento esta definido Si no esta definido se saca un
        // mensaje de error y se deja que se produzca una excepcion.
        if (IdentAgenteReceptor == null) {
            try {
                this.getInformacionAgenteaReportar();
            } catch (Exception ex) {
                Logger.getLogger(NotificacionesEventosVisPrediccion.class.getName()).log(Level.SEVERE, null, ex);
                NombresPredefinidos.RECURSO_TRAZAS_OBJ.aceptaNuevaTraza(new InfoTraza(this.identificadordeEsteRecurso,
                        "No se puede enviar el evento pq el identificadorAgenteaReportar es null ",
                        InfoTraza.NivelTraza.error));
            }

        }
        //   if (itfUsoAgenteReceptor == null){
        try {
            itfUsoAgenteReceptor = (InterfazUsoAgente) NombresPredefinidos.REPOSITORIO_INTERFACES_OBJ.obtenerInterfaz(NombresPredefinidos.ITF_USO + IdentAgenteReceptor);
        } catch (Exception e) {
            Logger.getLogger(NotificacionesEventosVisPrediccion.class.getName()).log(Level.SEVERE, null, e);
            //      Logger.error("Ha habido un problema enviar un  evento al agente "+IdentAgenteReceptor);
            NombresPredefinidos.RECURSO_TRAZAS_OBJ.aceptaNuevaTraza(new InfoTraza(identificadordeEsteRecurso,
                    "Ha habido un problema enviar el evento con informacion" + eventoaEnviar + "al   agente " + IdentAgenteReceptor,
                    InfoTraza.NivelTraza.error));
        }

        try {
            itfUsoAgenteReceptor.aceptaEvento(eventoaEnviar);
            NombresPredefinidos.RECURSO_TRAZAS_OBJ.aceptaNuevaTraza(new InfoTraza(identificadordeEsteRecurso,
                    "Se envia el evento con input :  " + eventoaEnviar + "al   agente " + IdentAgenteReceptor,
                    InfoTraza.NivelTraza.debug));
        } catch (Exception e) {
//			Logger.error("Ha habido un problema enviar un  evento al agente "+IdentAgenteReceptor);
            Logger.getLogger(NotificacionesEventosVisPrediccion.class.getName()).log(Level.SEVERE, null, e);
            NombresPredefinidos.RECURSO_TRAZAS_OBJ.aceptaNuevaTraza(new InfoTraza(identificadordeEsteRecurso,
                    "Ha habido un problema enviar el evento con informacion " + eventoaEnviar + "al   agente " + IdentAgenteReceptor,
                    InfoTraza.NivelTraza.error));
        }
    }

}
