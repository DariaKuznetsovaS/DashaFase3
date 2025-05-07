package org.example.Modelo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EnfrentamientoDAO {

    static Connection con = BD.getConnection();
    public EnfrentamientoDAO() {
    }

    public static void altaEnfrentamiento(Enfrentamiento enfrentamiento) {
        String sql = "INSERT INTO ENFRENTAMIENTOS (HORA, FECHA_ENF, EQUIPO_ATACANTE, EQUIPO_DEFENSOR, ID_JORNADA) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setTime(1, Time.valueOf(enfrentamiento.getHoraEnfrentamiento()));
            ps.setDate(2, Date.valueOf(enfrentamiento.getFechEnfrentamiento()));
            ps.setInt(3, enfrentamiento.getEquipoAtacante().getIdEquipo());
            ps.setInt(4, enfrentamiento.getEquipoDefensor().getIdEquipo());
            ps.setInt(5, enfrentamiento.getJornada().getIdJornada());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static ArrayList<Enfrentamiento> selectAllEnfrentamientos(int idJornada) {
        ArrayList<Enfrentamiento> enfrentamientos = new ArrayList<>();
        try {
            String sql = "SELECT * FROM ENFRENTAMIENTOS WHERE ID_JORNADA = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idJornada);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) { // ← CAMBIADO DE "if" A "while"
                Enfrentamiento e = new Enfrentamiento();
                e.setIdEnfrentamiento(rs.getInt("ID_ENFRENTAMIENTO"));
                e.setHoraEnfrentamiento(rs.getTime("HORA").toLocalTime());
                e.setFechEnfrentamiento(rs.getDate("FECHA_ENF").toLocalDate());
                //lo de abajo igual esta mal
                e.setEquipoAtacante(EquipoDAO.buscarEquipoInt(rs.getInt("EQUIPO_ATACANTE")));
                e.setEquipoDefensor(EquipoDAO.buscarEquipoInt(rs.getInt("EQUIPO_DEFENSOR")));
                e.setEquipoGanador(EquipoDAO.buscarEquipoInt(rs.getInt("EQUIPO_GANADOR")));
                e.setJornada(JornadaDAO.buscarJornada(rs.getInt("ID_JORNADA")));
                enfrentamientos.add(e);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return enfrentamientos;
    }


    public void asignarGanadorEnfrentamiento(Enfrentamiento enfrentamiento) {
        try {
            String sql = "UPDATE enfrentamientos SET equipo_ganador = ? WHERE id_enfrentamiento = ? AND id_jornada = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, enfrentamiento.getEquipoGanador().getIdEquipo());
            ps.setInt(2, enfrentamiento.getIdEnfrentamiento());
            ps.setInt(3, enfrentamiento.getJornada().getIdJornada());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}