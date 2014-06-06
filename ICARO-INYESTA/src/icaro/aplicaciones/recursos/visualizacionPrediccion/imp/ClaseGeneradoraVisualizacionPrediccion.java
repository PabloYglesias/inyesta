package icaro.aplicaciones.recursos.visualizacionPrediccion.imp;

import icaro.aplicaciones.recursos.visualizacionPrediccion.ItfUsoVisualizadorPrediccion;
import icaro.aplicaciones.recursos.visualizacionPrediccion.imp.gui.PanelPrediccionUsuario;
import icaro.infraestructura.entidadesBasicas.NombresPredefinidos;
import icaro.infraestructura.patronRecursoSimple.imp.ImplRecursoSimple;
import icaro.infraestructura.recursosOrganizacion.recursoTrazas.ItfUsoRecursoTrazas;
import icaro.infraestructura.recursosOrganizacion.recursoTrazas.imp.componentes.InfoTraza;

import javax.swing.JOptionPane;


/**
 * 
 *@author     shA
 *@created    30 de noviembre de 2007
 */

public class ClaseGeneradoraVisualizacionPrediccion extends ImplRecursoSimple implements ItfUsoVisualizadorPrediccion {

	private static final long serialVersionUID = 1L;

	//Informaci?n del agente controlador de la interfaz
	/**
	 * @uml.property  name="nombreAgentePrediccion"
	 */
	private String nombredeEsteRecurso;

  //  private String nombreAgenteAreportar = "AgenteAplicacionPrediccion1";
	/**
	 * @uml.property  name="tipoAgentePrediccion"
	 */
	private String tipoAgentePrediccion;
	
	//Ventana que gestiona este visualizador
	/**
	 * @uml.property  name="ventanaPrediccionUsuario"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private PanelPrediccionUsuario ventanaPrediccionUsuario;
        private NotificacionesEventosVisPrediccion notifAgente;
	/**
	 * @uml.property  name="trazas"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private ItfUsoRecursoTrazas trazas; //trazas del sistema
	
  	public ClaseGeneradoraVisualizacionPrediccion(String id) throws Exception{
  		super(id);
        nombredeEsteRecurso = id;
	trazas = NombresPredefinidos.RECURSO_TRAZAS_OBJ;
// Se crea el panel y las notificaciones
        
        notifAgente = new NotificacionesEventosVisPrediccion(this);
        this.ventanaPrediccionUsuario = new PanelPrediccionUsuario(this,notifAgente );
  	this.inicializa();
        
	}

  	
  	
  	
  	private void inicializa() {
  		
  		ventanaPrediccionUsuario.setPosicion(850,100);
  		trazas.aceptaNuevaTraza(new InfoTraza(nombredeEsteRecurso,
  				"Inicializando recurso",
  				InfoTraza.NivelTraza.debug));
  	}
  	
  	


	
	/**
	 * @return
	 * @uml.property  name="nombreAgentePrediccion"
	 */
	public String getNombreAgentePrediccion() {
		return identAgenteAReportar ;
	}
        public String getNombredeEsteRecurso() {
		return nombredeEsteRecurso;
	}
	/**
	 * @param nombreAgentePrediccion
	 * @uml.property  name="nombreAgentePrediccion"
	 */
//	public void setNombreAgentePrediccion(String nombreAgentePrediccion) {
//		this.nombreAgenteAreportar = nombreAgentePrediccion;
//	}

	/**
	 * @return
	 * @uml.property  name="tipoAgentePrediccion"
	 */
	public String getTipoAgentePrediccion() {
		return tipoAgentePrediccion;
	}

	/**
	 * @param tipoAgentePrediccion
	 * @uml.property  name="tipoAgentePrediccion"
	 */
	public void setTipoAgentePrediccion(String tipoAgentePrediccion) {
		this.tipoAgentePrediccion = tipoAgentePrediccion;
	}
    @Override
     public void setIdentAgenteAReportar(String nombreAgente) {
		this.identAgenteAReportar = nombreAgente;
                this.notifAgente.setIdentificadorAgenteAReportar(identAgenteAReportar);
	}
        public String getNombreAgenteAReportar() {
		return identAgenteAReportar;
	}
    @Override
    public void mostrarVisualizadorPrediccion(String nombreAgente, String tipo) {
		this.identAgenteAReportar = nombreAgente;
                this.notifAgente.setIdentificadorAgenteAReportar(identAgenteAReportar);
                System.out.println("El agente al que se deben enviar los eventos es :"+nombreAgente);
		this.tipoAgentePrediccion = tipo;
   
		this.ventanaPrediccionUsuario.mostrar();
		trazas.aceptaNuevaTraza(new InfoTraza(nombredeEsteRecurso,
  				"Mostrando visualizador...",
  				InfoTraza.NivelTraza.debug));
	}
 
    @Override
	public void cerrarVisualizadorPrediccion() {
		this.ventanaPrediccionUsuario.ocultar();
		trazas.aceptaNuevaTraza(new InfoTraza(nombredeEsteRecurso,
  				"Cerrando visualizador...",
  				InfoTraza.NivelTraza.debug));
	}  
  
    @Override
	public void mostrarMensajeInformacion(String titulo,String mensaje) {
	/*Muestra el mensaje y avisa al gestor para finalizar*/
		
		trazas.aceptaNuevaTraza(new InfoTraza(nombredeEsteRecurso,
  				"Mostrando mensaje de informacion",
  				InfoTraza.NivelTraza.debug));
		JOptionPane.showMessageDialog(ventanaPrediccionUsuario,mensaje,titulo,JOptionPane.INFORMATION_MESSAGE);
		/*
		try {
			ItfUsoAgenteReactivo itfUsoAgente = (ItfUsoAgenteReactivo) itfUsoRepositorioInterfaces
			.obtenerInterfaz(NombresPredefinidos.ITF_USO+NombresPredefinidos.NOMBRE_AGENTE_APLICACION+"Prediccion");
			itfUsoAgente.aceptaEvento(new EventoInput("termina","VisualizadorPrediccion",NombresPredefinidos.NOMBRE_AGENTE_APLICACION+"Prediccion"));
			
		} catch (Exception e) {
			System.out.println("Ha habido un error al enviar el evento termina al agente");
			e.printStackTrace();
		}
		*/
	}
  
    @Override
	public void mostrarMensajeAviso(String titulo,String mensaje) {
      	trazas.aceptaNuevaTraza(new InfoTraza(nombredeEsteRecurso,
  				"Mostrando mensaje de aviso",
  				InfoTraza.NivelTraza.debug));
      	JOptionPane.showMessageDialog(ventanaPrediccionUsuario,mensaje,titulo,JOptionPane.WARNING_MESSAGE);
	}
	
    @Override
	public void mostrarMensajeError(String titulo,String mensaje) {
      	trazas.aceptaNuevaTraza(new InfoTraza(nombredeEsteRecurso,
  				"Mostrando mensaje de error",
  				InfoTraza.NivelTraza.debug));
      	JOptionPane.showMessageDialog(ventanaPrediccionUsuario,mensaje,titulo,JOptionPane.ERROR_MESSAGE);
	}	
	
	@Override
	public void termina() {
		this.ventanaPrediccionUsuario.destruir();
		try {
			trazas.aceptaNuevaTraza(new InfoTraza(nombredeEsteRecurso,
	  				"Terminando recurso",
	  				InfoTraza.NivelTraza.debug));
			super.termina();
			
		} catch (Exception e) {
			this.itfAutomata.transita("error");
			e.printStackTrace();
		}
	}
	
		
}