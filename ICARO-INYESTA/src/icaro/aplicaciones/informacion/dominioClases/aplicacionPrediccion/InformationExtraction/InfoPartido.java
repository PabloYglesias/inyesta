/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package icaro.aplicaciones.informacion.dominioClases.aplicacionPrediccion.InformationExtraction;

import org.jsoup.nodes.Element;

/**
 *
 * @author User
 */
public class InfoPartido 
{
    String FechaPartido;
    String EquipoLocal;
    String EquipoVisitante;
    int GolesLocal;
    int GolesVisitante;
    int NumeroJornada;

    public int getNumeroColegiado() {
        return NumeroColegiado;
    }

    public void setNumeroColegiado(int NumeroColegiado) {
        this.NumeroColegiado = NumeroColegiado;
    }
    String NombreJornada;
    String NombreEstadio;
    int NumeroColegiado;
    public InfoPartido()
    {
        NombreEstadio = "Desconocido";
        NumeroColegiado =  0;
    }

    public String getNombreEstadio() {
        return NombreEstadio;
    }

    public void setNombreEstadio(String NombreEstadio) {
        this.NombreEstadio = NombreEstadio;
    }
    public void setFecha(String fecha) 
    {
        FechaPartido = fecha;
    }

    public String getFechaPartido() {
        return FechaPartido;
    }

    public void setFechaPartido(String FechaPartido) {
        this.FechaPartido = FechaPartido;
    }

    public String getEquipoLocal() {
        return EquipoLocal;
    }
    
    public String getEquipoVisitante() {
        return EquipoVisitante;
    }

    public void setEquipoVisitante(String EquipoVisitante) {
        this.EquipoVisitante = EquipoVisitante;
    }

    public int getGolesLocal() {
        return GolesLocal;
    }

    public void setGolesLocal(int GolesLocal) {
        this.GolesLocal = GolesLocal;
    }

    public int getGolesVisitante() {
        return GolesVisitante;
    }

    public void setGolesVisitante(int GolesVisitante) {
        this.GolesVisitante = GolesVisitante;
    }

    public int getNumeroJornada() {
        return NumeroJornada;
    }

    public void setNumeroJornada(int NumeroJornada) {
        this.NumeroJornada = NumeroJornada;
    }

    public String getNombreJornada() {
        return NombreJornada;
    }

    public void setNombreJornada(String NombreJornada) {
        this.NombreJornada = NombreJornada;
    }

    public void setGolesLocal(String goles) {
       
        GolesLocal = Integer.parseInt(goles);
    }

    public void setGolesVisitante(String goles) {
       GolesVisitante = Integer.parseInt(goles);
    }

    public void setVisitante(String equipo) {
      EquipoVisitante = equipo;
    }

    public void setEquipoLocal(String equipo) {
       EquipoLocal = equipo;
    }

    public void setJornada(String titulo) 
    {
        String[] tokens = titulo.split(" ");
        NumeroJornada = Integer.parseInt(tokens[1]);
        NombreJornada = titulo;
    }

    public void setJornada(int num) 
    {
        NumeroJornada = num;
        NombreJornada = Integer.toString(num);
    }
}
