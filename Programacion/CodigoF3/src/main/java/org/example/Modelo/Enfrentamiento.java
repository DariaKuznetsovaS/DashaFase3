package org.example.Modelo;

import java.time.LocalDate;
import java.time.LocalTime;
/**
 * Representa un enfrentamiento entre dos equipos en una jornada específica,
 * incluyendo la fecha, hora y el equipo ganador del enfrentamiento.
 */
public class Enfrentamiento {
    private int idEnfrentamiento;
    private LocalDate fechEnfrentamiento;
    private LocalTime horaEnfrentamiento;
    private Equipo equipoAtacante;
    private Equipo equipoDefensor;
    private Jornada jornada;
    private Equipo EquipoGanador;

    public Enfrentamiento() {

    }

    public Enfrentamiento(LocalDate fechEnfrentamiento, LocalTime horaEnfrentamiento, Equipo equipoAtacante, Equipo equipoDefensor, Jornada jornada) {
        this.fechEnfrentamiento = fechEnfrentamiento;
        this.horaEnfrentamiento = horaEnfrentamiento;
        this.equipoAtacante = equipoAtacante;
        this.equipoDefensor = equipoDefensor;
        this.jornada = jornada;
    }

    public int getIdEnfrentamiento() {
        return idEnfrentamiento;
    }

    public void setIdEnfrentamiento(int idEnfrentamiento) {
        this.idEnfrentamiento = idEnfrentamiento;
    }

    public LocalDate getFechEnfrentamiento() {
        return fechEnfrentamiento;
    }

    public void setFechEnfrentamiento(LocalDate fechEnfrentamiento) {
        this.fechEnfrentamiento = fechEnfrentamiento;
    }

    public LocalTime getHoraEnfrentamiento() {
        return horaEnfrentamiento;
    }

    public void setHoraEnfrentamiento(LocalTime horaEnfrentamiento) {
        this.horaEnfrentamiento = horaEnfrentamiento;
    }

    public Equipo getEquipoAtacante() {
        return equipoAtacante;
    }

    public void setEquipoAtacante(Equipo equipoAtacante) {
        this.equipoAtacante = equipoAtacante;
    }

    public Equipo getEquipoDefensor() {
        return equipoDefensor;
    }

    public void setEquipoDefensor(Equipo equipoDefensor) {
        this.equipoDefensor = equipoDefensor;
    }

    public Jornada getJornada() {
        return jornada;
    }

    public void setJornada(Jornada jornada) {
        this.jornada = jornada;
    }

    public Equipo getEquipoGanador() {
        return EquipoGanador;
    }

    public void setEquipoGanador(Equipo equipoGanador) {
        EquipoGanador = equipoGanador;
    }

}