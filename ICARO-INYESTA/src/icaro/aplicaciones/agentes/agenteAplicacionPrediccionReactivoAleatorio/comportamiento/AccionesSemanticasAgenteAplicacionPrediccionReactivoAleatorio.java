package icaro.aplicaciones.agentes.agenteAplicacionPrediccionReactivoAleatorio.comportamiento;

import icaro.aplicaciones.informacion.dominioClases.aplicacionPrediccion.InfoPrediccionResultado;
import icaro.aplicaciones.informacion.dominioClases.aplicacionPrediccion.InfoPrediccionResultado.Ganador;
import icaro.aplicaciones.informacion.dominioClases.aplicacionPrediccion.InfoPrediccionSinValidar;
import icaro.aplicaciones.informacion.dominioClases.aplicacionPrediccion.InfoPrediccionValidada;
import icaro.aplicaciones.informacion.estructuraEquipos.InfoRolAgente;
import icaro.aplicaciones.recursos.persistenciaPrediccionSimple.ItfUsoPersistenciaPrediccionSimple;
import icaro.aplicaciones.recursos.visualizacionAcceso.imp.NotificacionesEventosVisAcceso;
import icaro.aplicaciones.recursos.visualizacionPrediccion.ItfUsoVisualizadorPrediccion;
import icaro.infraestructura.entidadesBasicas.NombresPredefinidos;
import icaro.infraestructura.entidadesBasicas.comunicacion.AdaptadorRegRMI;
import icaro.infraestructura.entidadesBasicas.comunicacion.EventoRecAgte;
import icaro.infraestructura.entidadesBasicas.comunicacion.InfoContEvtMsgAgteReactivo;
import icaro.infraestructura.entidadesBasicas.comunicacion.InfoContMsgAgteReactivo;
import icaro.infraestructura.entidadesBasicas.comunicacion.MensajeSimple;
import icaro.infraestructura.entidadesBasicas.interfaces.InterfazGestion;
import icaro.infraestructura.entidadesBasicas.interfaces.InterfazUsoAgente;
import icaro.infraestructura.patronAgenteReactivo.control.acciones.AccionesSemanticasAgenteReactivo;
import icaro.infraestructura.patronAgenteReactivo.factoriaEInterfaces.ItfUsoAgenteReactivo;
import icaro.infraestructura.patronRecursoSimple.ItfUsoRecursoSimple;
import icaro.infraestructura.recursosOrganizacion.configuracion.ItfUsoConfiguracion;
import icaro.infraestructura.recursosOrganizacion.recursoTrazas.ItfUsoRecursoTrazas;
import icaro.infraestructura.recursosOrganizacion.recursoTrazas.imp.componentes.InfoTraza;
import icaro.infraestructura.recursosOrganizacion.repositorioInterfaces.imp.ClaseGeneradoraRepositorioInterfaces;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class AccionesSemanticasAgenteAplicacionPrediccionReactivoAleatorio extends AccionesSemanticasAgenteReactivo {

    /**
     * @uml.property name="visualizacion"
     * @uml.associationEnd
     */
    private ItfUsoVisualizadorPrediccion visualizacion;
    /**
     * @uml.property name="persistencia1"
     * @uml.associationEnd
     */
    private ItfUsoPersistenciaPrediccionSimple Persistencia1;
    /**
     * @uml.property name="agentePrediccion"
     * @uml.associationEnd
     */
    private ItfUsoAgenteReactivo agentePrediccion;
    private ItfUsoRecursoTrazas ItfUsoRecursoTrazas;
    private InterfazUsoAgente itfUsoAgenteReceptor;

    public void arranque() {

        try {
            // visualizacion = (ItfUsoVisualizadorPrediccion) itfUsoRepositorio.obtenerInterfaz(NombresPredefinidos.ITF_USO + "VisualizacionPrediccion1");
            // visualizacion.mostrarVisualizadorPrediccion(this.nombreAgente, NombresPredefinidos.TIPO_REACTIVO);
            trazas.aceptaNuevaTraza(new InfoTraza(this.nombreAgente, "Se acaba de mostrar el visualizador", InfoTraza.NivelTraza.debug));
        } catch (Exception ex) {
            try {
                ItfUsoRecursoTrazas = (ItfUsoRecursoTrazas) ClaseGeneradoraRepositorioInterfaces.instance().obtenerInterfaz(
                        NombresPredefinidos.ITF_USO + NombresPredefinidos.RECURSO_TRAZAS);
                trazas.aceptaNuevaTraza(new InfoTraza(this.nombreAgente, "Ha habido un problema al abrir el visualizador"
                        + " de Prediccion en accion semantica 'arranque()'",
                        InfoTraza.NivelTraza.error));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Debemos responder al master nuestra identidad y rol
     *
     * @param mirol
     */
    public void identificacion(InfoRolAgente mirol) {

        try {
            InfoRolAgente RolPredictor = new InfoRolAgente(this.nombreAgente, mirol.getidentEquipoAgte(), "ReactivoAleatorio", "");

            agentePrediccion = (ItfUsoAgenteReactivo) itfUsoRepositorio.obtenerInterfaz(NombresPredefinidos.ITF_USO + this.nombreAgente);
            // agentePrediccion.aceptaEvento(new EventoRecAgte("envioPrediccion", datosEnvio, this.nombreAgente, NombresPredefinidos.NOMBRE_AGENTE_APLICACION + "Predictor"));
            enviarEventoaOtroAgente(new EventoRecAgte("identificado", RolPredictor, this.nombreAgente, NombresPredefinidos.NOMBRE_AGENTE_APLICACION + "Predictor"), NombresPredefinidos.NOMBRE_AGENTE_APLICACION + "Prediccion1");//TODO:: Ojo con esto
        } catch (Exception ex) {
            Logger.getLogger(AccionesSemanticasAgenteAplicacionPrediccionReactivoAleatorio.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void valida(InfoPrediccionSinValidar infoUsuario) {
        boolean ok = false;
        try {
            Persistencia1 = (ItfUsoPersistenciaPrediccionSimple) itfUsoRepositorio.obtenerInterfaz(NombresPredefinidos.ITF_USO + "Persistencia1");
            ok = Persistencia1.compruebaPartido(infoUsuario.tomaEquipo1(), infoUsuario.tomaEquipo2(), infoUsuario.tomaFecha());
            try {
                ItfUsoRecursoTrazas trazas = (ItfUsoRecursoTrazas) ClaseGeneradoraRepositorioInterfaces.instance().obtenerInterfaz(
                        NombresPredefinidos.ITF_USO + NombresPredefinidos.RECURSO_TRAZAS);
                trazas.aceptaNuevaTraza(new InfoTraza(this.nombreAgente,
                        "Comprobando usuario...",
                        InfoTraza.NivelTraza.debug));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception ex) {
            try {

                ItfUsoRecursoTrazas trazas = (ItfUsoRecursoTrazas) ClaseGeneradoraRepositorioInterfaces.instance().obtenerInterfaz(
                        NombresPredefinidos.ITF_USO + NombresPredefinidos.RECURSO_TRAZAS);
                trazas.aceptaNuevaTraza(new InfoTraza(this.nombreAgente,
                        "Ha habido un problema en la Persistencia1 al comprobar el usuario y el password",
                        InfoTraza.NivelTraza.error));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            agentePrediccion = (ItfUsoAgenteReactivo) itfUsoRepositorio.obtenerInterfaz(NombresPredefinidos.ITF_USO + this.nombreAgente);
            Object[] datosEnvio = new Object[]{infoUsuario.tomaEquipo1(), infoUsuario.tomaEquipo2(), infoUsuario.tomaFecha()};
            if (ok) {
                InfoPrediccionValidada Validada = new InfoPrediccionValidada(infoUsuario.tomaEquipo1(), infoUsuario.tomaEquipo2(), infoUsuario.tomaFecha());

                //realizarPrediccion(Validada);
                agentePrediccion.aceptaEvento(new EventoRecAgte("datosValidos", Validada, this.nombreAgente, NombresPredefinidos.NOMBRE_AGENTE_APLICACION + "Prediccion"));
            } else {
                agentePrediccion.aceptaEvento(new EventoRecAgte("datosNoValidos", datosEnvio, this.nombreAgente, this.nombreAgente));
            }
        } catch (Exception e) {
            try {
                ItfUsoRecursoTrazas trazas = (ItfUsoRecursoTrazas) ClaseGeneradoraRepositorioInterfaces.instance().obtenerInterfaz(
                        NombresPredefinidos.ITF_USO + NombresPredefinidos.RECURSO_TRAZAS);
                trazas.aceptaNuevaTraza(new InfoTraza(this.nombreAgente,
                        "Ha habido un problema enviar el evento usuario Valido/NoValido al agente",
                        InfoTraza.NivelTraza.error));
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public void realizarPrediccion(InfoPrediccionValidada pPrediccion) {
        String resultado;
        int nGana1, nGana2, nGanaX, nAux;

        nGana1 = nGana2 = nGanaX = 0;

        Random rn = new Random();
        for (int i = 0; i < 100; i++) {
            nAux = rn.nextInt() % 3;
            switch (nAux) {
                case 0:
                    nGana1++;
                    break;
                case 1:
                    nGana2++;
                    break;
                case 2:
                    nGanaX++;
                    break;
            }
        }
        if (nGana1 >= nGana2 && nGana1 >= nGanaX) 
        {
            pPrediccion.setGanador(Ganador.eLOCAL);
            resultado = "Gana el equipo " + pPrediccion.tomaEquipo1();
            
        } else if (nGana2 >= nGana1 && nGana2 >= nGanaX) 
        {
            pPrediccion.setGanador(Ganador.eVISITANTE);            
            resultado = "Gana el equipo " + pPrediccion.tomaEquipo2();
        } else 
        {
            pPrediccion.setGanador(Ganador.eEMPATE);            
            resultado = "Empate";
        }

        pPrediccion.setAutor(this.nombreAgente);
        pPrediccion.insertaResultado(resultado, "Algoritmo aleatorio");

        try {
            agentePrediccion = (ItfUsoAgenteReactivo) itfUsoRepositorio.obtenerInterfaz(NombresPredefinidos.ITF_USO + this.nombreAgente);
            Object[] datosEnvio = new Object[]{pPrediccion.tomaEquipo1(), pPrediccion.tomaEquipo2(), pPrediccion.tomaFecha(), pPrediccion.tomaResultado(), pPrediccion.tomaDescripcionMotivo()};
            // agentePrediccion.aceptaEvento(new EventoRecAgte("envioPrediccion", datosEnvio, this.nombreAgente, NombresPredefinidos.NOMBRE_AGENTE_APLICACION + "Predictor"));
            enviarEventoaOtroAgente(new EventoRecAgte("envioPrediccion", pPrediccion, this.nombreAgente, NombresPredefinidos.NOMBRE_AGENTE_APLICACION + "Predictor"), NombresPredefinidos.NOMBRE_AGENTE_APLICACION + "Prediccion1");
        } catch (Exception e) {
            try {
                ItfUsoRecursoTrazas trazas = (ItfUsoRecursoTrazas) ClaseGeneradoraRepositorioInterfaces.instance().obtenerInterfaz(
                        NombresPredefinidos.ITF_USO + NombresPredefinidos.RECURSO_TRAZAS);
                trazas.aceptaNuevaTraza(new InfoTraza(this.nombreAgente,
                        "Ha habido un problema enviar el evento usuario Valido/NoValido al agente",
                        InfoTraza.NivelTraza.error));
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public void mostrarDatosInvalidos(String equipo1, String equipo2, String fecha) {
        InfoPrediccionSinValidar dav = new InfoPrediccionSinValidar(equipo1, equipo2, fecha);

        try {
            visualizacion = (ItfUsoVisualizadorPrediccion) itfUsoRepositorio.obtenerInterfaz(NombresPredefinidos.ITF_USO + "VisualizacionPrediccion1");
            visualizacion.mostrarMensajeError("Prediccion Incorrecta", "El partido " + dav.tomaEquipo1() + " vs " + dav.tomaEquipo2() + " no se encuentra");
        } catch (Exception ex) {
            try {
                ItfUsoRecursoTrazas trazas = (ItfUsoRecursoTrazas) ClaseGeneradoraRepositorioInterfaces.instance().obtenerInterfaz(
                        NombresPredefinidos.ITF_USO + NombresPredefinidos.RECURSO_TRAZAS);
                trazas.aceptaNuevaTraza(new InfoTraza(this.nombreAgente,
                        "Ha habido un problema al abrir el visualizador de Prediccion",
                        InfoTraza.NivelTraza.error));
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        // pedirTerminacionGestorAgentes();
    }

    public void terminacion() {
        try {
            ItfUsoRecursoTrazas trazas = (ItfUsoRecursoTrazas) ClaseGeneradoraRepositorioInterfaces.instance().obtenerInterfaz(
                    NombresPredefinidos.ITF_USO + NombresPredefinidos.RECURSO_TRAZAS);
            trazas.aceptaNuevaTraza(new InfoTraza(this.nombreAgente,
                    "Terminando agente: " + NombresPredefinidos.NOMBRE_AGENTE_APLICACION + "Prediccion1",
                    InfoTraza.NivelTraza.debug));
//		try {
//			this.hebra.finalizar(); // CUIDADO, SI FALLASE LA CREACION DE LOS
//									// RECURSOS ESTA HEBRA
//		} // NO ESTA INICIALIZADA
//		catch (Exception e) {
//			e.printStackTrace();
//			logger.error("GestorOrganizacion: La hebra no ha podido ser finalizada porque no hab�a sido creada.");
//			trazas.aceptaNuevaTraza(new InfoTraza("GestorRecursos",
//					"La hebra no ha podido ser finalizada porque no hab�a sido creada.",
//					InfoTraza.NivelTraza.error));
//		}
            try {
                visualizacion.cerrarVisualizadorPrediccion();
                ((InterfazGestion) this.itfUsoRepositorio
                        .obtenerInterfaz(NombresPredefinidos.ITF_GESTION
                                + NombresPredefinidos.NOMBRE_GESTOR_AGENTES))
                        .termina();

            } catch (Exception ex) {
                ex.printStackTrace();
            }
            try {
                this.itfUsoGestorAReportar.aceptaEvento(new EventoRecAgte(
                        "gestor_agentes_terminado",
                        NombresPredefinidos.NOMBRE_GESTOR_AGENTES,
                        NombresPredefinidos.NOMBRE_GESTOR_ORGANIZACION));
            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (Exception e2) {
            e2.printStackTrace();
        }
        logger.debug("Terminando agente: " + this.nombreAgente);
    }

    public void clasificaError() {
        /*
         *A partir de esta funci�n se debe decidir si el sistema se puede recuperar del error o no.
         *En este caso la pol�tica es que todos los errores son cr�ticos.  
         */
        try {
            agentePrediccion = (ItfUsoAgenteReactivo) itfUsoRepositorio.obtenerInterfaz(NombresPredefinidos.ITF_USO + this.nombreAgente);
            agentePrediccion.aceptaEvento(new EventoRecAgte("errorIrrecuperable", this.nombreAgente, this.nombreAgente));

        } catch (Exception e) {
            try {
                ItfUsoRecursoTrazas trazas = (ItfUsoRecursoTrazas) ClaseGeneradoraRepositorioInterfaces.instance().obtenerInterfaz(
                        NombresPredefinidos.ITF_USO + NombresPredefinidos.RECURSO_TRAZAS);
                trazas.aceptaNuevaTraza(new InfoTraza(this.nombreAgente,
                        "Ha habido un problema enviar el evento usuario Valido/NoValido al agente Prediccion",
                        InfoTraza.NivelTraza.error));
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    void enviarEventoaOtroAgente(EventoRecAgte eventoaEnviar, String IdentAgenteReceptor) {

        // Este método crea un evento con la información de entrada y se le envía al agente REACTIVO que se indique por medio de
        // la  interfaz de uso
        //     EventoRecAgte eventoaEnviar = null;
        // Se verifica que el identificador del agente a enviar el evento esta definido Si no esta definido se saca un
        // mensaje de error y se deja que se produzca una excepcion.
        if (IdentAgenteReceptor == null) {

        } else {

            try {
                itfUsoAgenteReceptor = (InterfazUsoAgente) NombresPredefinidos.REPOSITORIO_INTERFACES_OBJ.obtenerInterfaz(NombresPredefinidos.ITF_USO + IdentAgenteReceptor);
            } catch (Exception e) {
                Logger.getLogger(NotificacionesEventosVisAcceso.class.getName()).log(Level.SEVERE, null, e);
                //      Logger.error("Ha habido un problema enviar un  evento al agente "+IdentAgenteReceptor);
                NombresPredefinidos.RECURSO_TRAZAS_OBJ.aceptaNuevaTraza(new InfoTraza("Test",
                        "Ha habido un problema enviar el evento con informacion" + eventoaEnviar + "al   agente " + IdentAgenteReceptor,
                        InfoTraza.NivelTraza.error));
            }

            try {
                itfUsoAgenteReceptor.aceptaEvento(eventoaEnviar);
                NombresPredefinidos.RECURSO_TRAZAS_OBJ.aceptaNuevaTraza(new InfoTraza("Test",
                        "Se envia el evento con input :  " + eventoaEnviar + "al   agente " + IdentAgenteReceptor,
                        InfoTraza.NivelTraza.debug));
            } catch (Exception e) {
//			Logger.error("Ha habido un problema enviar un  evento al agente "+IdentAgenteReceptor);
                Logger.getLogger(NotificacionesEventosVisAcceso.class.getName()).log(Level.SEVERE, null, e);
                NombresPredefinidos.RECURSO_TRAZAS_OBJ.aceptaNuevaTraza(new InfoTraza("Test",
                        "Ha habido un problema enviar el evento con informacion " + eventoaEnviar + "al   agente " + IdentAgenteReceptor,
                        InfoTraza.NivelTraza.error));
            }
        }
    }

    public void pedirTerminacionGestorAgentes() {
        try {
            visualizacion.cerrarVisualizadorPrediccion();
            this.itfUsoGestorAReportar.aceptaMensaje(new MensajeSimple(new InfoContEvtMsgAgteReactivo("peticion_terminar_todo"), this.nombreAgente, NombresPredefinidos.NOMBRE_GESTOR_AGENTES));
        } catch (Exception e) {
            logger.error("Error al mandar el evento de terminar_todo", e);
            try {
                ItfUsoRecursoTrazas trazas = (ItfUsoRecursoTrazas) ClaseGeneradoraRepositorioInterfaces.instance().obtenerInterfaz(
                        NombresPredefinidos.ITF_USO + NombresPredefinidos.RECURSO_TRAZAS);
                trazas.aceptaNuevaTraza(new InfoTraza(this.nombreAgente,
                        "Error al mandar el evento de terminar_todo",
                        InfoTraza.NivelTraza.error));
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            try {
                agentePrediccion = (ItfUsoAgenteReactivo) itfUsoRepositorio.obtenerInterfaz(NombresPredefinidos.ITF_USO + this.nombreAgente);
                agentePrediccion.aceptaEvento(new EventoRecAgte("error", this.nombreAgente, this.nombreAgente));
            } catch (Exception exc) {
                try {
                    ItfUsoRecursoTrazas trazas = (ItfUsoRecursoTrazas) ClaseGeneradoraRepositorioInterfaces.instance().obtenerInterfaz(
                            NombresPredefinidos.ITF_USO + NombresPredefinidos.RECURSO_TRAZAS);
                    trazas.aceptaNuevaTraza(new InfoTraza(this.nombreAgente,
                            "Fallo al enviar un evento error.",
                            InfoTraza.NivelTraza.error));
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
                logger.error("Fallo al enviar un evento error.", exc);
            }
        }
    }
}
