/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package icaro.aplicaciones.informacion.dominioClases.aplicacionPrediccion.InformationExtraction;

/**
 *
 * @author User
 */
public class InfoClubs {
    
    String NombreEquipo;
    String NombreEstadio;
    int NumeroLigas;
    int NumeroCopasRey;
    int AñoFundacion;
    int NumeroTPrimera;
    int NumeroPuntos;
    
    int NumeroPartidosJugados;
    int NumeroPartidosGanados;
    int NumeroPartidosEmpatados;    

    int NumeroPartidosPerdidos;
    
    int NumeroGolesFavor;
    int NumeroGolesContra;
    
    int UltimaTPrimera;
    
    int NumeroTSegunda;
    int NumeroTSegundaB;
    int NumeroTTercera;
    
    public InfoClubs()
    {
        NombreEstadio = "Los Carmenes";
    }
    public String getNombreEquipo() {
        return NombreEquipo;
    }

    public void setNombreEquipo(String NombreEquipo) {
        this.NombreEquipo = NombreEquipo;
    }

    public int getNumeroLigas() {
        return NumeroLigas;
    }

    public void setNumeroLigas(int NumeroLigas) {
        this.NumeroLigas = NumeroLigas;
    }

    public int getNumeroCopasRey() {
        return NumeroCopasRey;
    }

    public void setNumeroCopasRey(int NumeroCopasRey) {
        this.NumeroCopasRey = NumeroCopasRey;
    }

    public int getAñoFundacion() {
        return AñoFundacion;
    }

    public void setAñoFundacion(int AñoFundacion) {
        this.AñoFundacion = AñoFundacion;
    }

    public int getNumeroTPrimera() {
        return NumeroTPrimera;
    }

    public void setNumeroTPrimera(int NumeroTPrimera) {
        this.NumeroTPrimera = NumeroTPrimera;
    }

    public int getNumeroPuntos() {
        return NumeroPuntos;
    }

    public void setNumeroPuntos(int NumeroPuntos) {
        this.NumeroPuntos = NumeroPuntos;
    }

    public int getNumeroPartidosJugados() {
        return NumeroPartidosJugados;
    }

    public void setNumeroPartidosJugados(int NumeroPartidosJugados) {
        this.NumeroPartidosJugados = NumeroPartidosJugados;
    }

    public int getNumeroPartidosGanados() {
        return NumeroPartidosGanados;
    }

    public void setNumeroPartidosGanados(int NumeroPartidosGanados) {
        this.NumeroPartidosGanados = NumeroPartidosGanados;
    }

    public int getNumeroPartidosPerdidos() {
        return NumeroPartidosPerdidos;
    }

    public void setNumeroPartidosPerdidos(int NumeroPartidosPerdidos) {
        this.NumeroPartidosPerdidos = NumeroPartidosPerdidos;
    }

    public int getNumeroGolesFavor() {
        return NumeroGolesFavor;
    }

    public void setNumeroGolesFavor(int NumeroGolesFavor) {
        this.NumeroGolesFavor = NumeroGolesFavor;
    }

    public int getNumeroGolesContra() {
        return NumeroGolesContra;
    }

    public void setNumeroGolesContra(int NumeroGolesContra) {
        this.NumeroGolesContra = NumeroGolesContra;
    }

    public int getUltimaTPrimera() {
        return UltimaTPrimera;
    }

    public void setUltimaTPrimera(int UltimaTPrimera) {
        this.UltimaTPrimera = UltimaTPrimera;
    }

    public int getNumeroTSegunda() {
        return NumeroTSegunda;
    }

    public void setNumeroTSegunda(int NumeroTSegunda) {
        this.NumeroTSegunda = NumeroTSegunda;
    }

    public int getNumeroTSegundaB() {
        return NumeroTSegundaB;
    }

    public void setNumeroTSegundaB(int NumeroTSegundaB) {
        this.NumeroTSegundaB = NumeroTSegundaB;
    }

    public int getNumeroTTercera() {
        return NumeroTTercera;
    }

    public void setNumeroTTercera(int NumeroTTercera) {
        this.NumeroTTercera = NumeroTTercera;
    }
    
    public int getNumeroPartidosEmpatados() {
        return NumeroPartidosEmpatados;
    }

    public void setNumeroPartidosEmpatados(int NumeroPartidosEmpatados) {
        this.NumeroPartidosEmpatados = NumeroPartidosEmpatados;
    }

    public String GetNombreEstadio() 
    {
        return NombreEstadio;
    }

    public String getNumeroLigasString() 
    {
        return Integer.toString(NumeroLigas);
    }

    public String getNumeroCopasReyString() {
         return Integer.toString(NumeroCopasRey);
    }

    public String getAñoFundacionString() 
    {
        return Integer.toString(AñoFundacion);
    }

    public String getNumeroTPrimeraString() {
        return Integer.toString(NumeroTPrimera);
    }

    public String getNumeroPuntosString() {
        return Integer.toString(NumeroPuntos);
    }

    public String getNumeroPartidosJugadosString() {
       return Integer.toString(NumeroPartidosJugados);
    }

    public String getNumeroPartidosGanadosString() {
        return Integer.toString(NumeroPartidosGanados);
    }

    public String getNumeroPartidosEmpatadosString() {
       return Integer.toString(NumeroPartidosEmpatados);
    }

    public String getNumeroPartidosPerdidosString() 
    {
        return Integer.toString(NumeroPartidosPerdidos);
    }

    public String getNumeroGolesFavorString() {
        return Integer.toString(NumeroGolesFavor);
    }

    public String getNumeroGolesContraString() 
    {
        return Integer.toString(NumeroGolesContra);
    }

    public String getUltimaTPrimeraString() {
        return Integer.toString(UltimaTPrimera);
    }

    
    public String getUltimaTSegundaString() {
       return Integer.toString(NumeroTSegunda); 
    }

    public String getUltimaTTerceraString() {
       return Integer.toString(NumeroTTercera);
    }

}
