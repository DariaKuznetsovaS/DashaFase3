package org.example.Modelo;

import org.example.Controladores.JugadorController;

import java.sql.*;
import java.util.ArrayList;

public class JugadorDAO {
    static Connection con = BD.getConnection();

    public JugadorDAO() {
    }

// =============================================
// == OPERACIONES DE CONSULTA (SELECT)
// =============================================
    public ArrayList<Jugador> selectObjetosJugador() {
        ArrayList<Jugador> jugadores = new ArrayList<>();
    /**
     * Obtiene una lista de jugadores con su ID y nickname.
     * @return Lista de objetos Jugador con ID y nickname.
     */
        try {
            String sql = "SELECT * FROM JUGADORES";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Jugador j = new Jugador();
                j.setNombre(rs.getString("NOMBRE"));
                j.setApellido(rs.getString("APELLIDO"));
                j.setNacionalidad(rs.getString("NACIONALIDAD"));
                j.setFechaNacimiento(rs.getDate("FECHA_NAC").toLocalDate());
                j.setNickname(rs.getString("NICKNAME"));
                j.setSueldo(rs.getDouble("SUELDO"));
                j.setEquipo(EquipoDAO.buscarEquipo(rs.getString("ID_EQUIPO")));
                jugadores.add(j);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return jugadores;
    }



    /**
     * Busca un jugador en la base de datos por su nombre.
     * @param nombreJugador Nombre del jugador a buscar.
     * @return Objeto Jugador con los detalles del jugador, o null si no se encuentra.
     */
    public static Jugador buscarJugador(String nombreJugador) {
       Jugador j = new Jugador();
       j.setNickname(nombreJugador);
        try {
            String sql = "SELECT * FROM JUGADORES WHERE NICKNAME = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nombreJugador);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                j.setIdJugador(rs.getInt("ID_JUGADOR"));
                j.setNombre(rs.getString("NOMBRE"));
                j.setApellido(rs.getString("APELLIDO"));
                j.setNacionalidad(rs.getString("NACIONALIDAD"));
                j.setFechaNacimiento(rs.getDate("FECHA_NAC").toLocalDate());
                j.setNickname(rs.getString("NICKNAME"));
                j.setSueldo(rs.getDouble("SUELDO"));
                j.setEquipo(EquipoDAO.buscarEquipo(rs.getString("ID_EQUIPO")));
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
        return j;
    }

// =============================================
// == OPERACIONES DE INSERCIÓN (INSERT)
// =============================================
    /**
     * Inserta un nuevo jugador en la base de datos.
     * @param jugador Objeto Jugador con los datos a insertar.
     */
    public static void altaJugador(Jugador jugador) {
        try {
            String sql = "INSERT INTO jugadores VALUES(?,?,?,?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, jugador.getIdJugador());
            ps.setString(2, jugador.getNombre());
            ps.setString(3, jugador.getApellido());
            ps.setString(4, jugador.getNacionalidad());
            ps.setDate(5, Date.valueOf(jugador.getFechaNacimiento()));
            ps.setString(6, jugador.getNickname());
            ps.setDouble(7, jugador.getSueldo());
            ps.setInt(8, jugador.getEquipo().getIdEquipo());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

// =============================================
// == OPERACIONES DE ACTUALIZACIÓN (UPDATE)
// =============================================
    /**
     * Modifica los detalles de un jugador en la base de datos.
     * @param jugador Objeto Jugador con los nuevos datos.
     * @param jugadorAnterior Objeto del jugador para identificar el registro a modificar.
     */
    public static void modificarJugador(Jugador jugador, Jugador jugadorAnterior) {
        try {
            String sql = "UPDATE JUGADORES SET id_jugador = ?,NOMBRE = ?," +
                         "apellido = ?,nacionalidad = ?,fecha_nac = ?,nickname = ?,sueldo = ?," +
                         "id_equipo = ? WHERE NICKNAME = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, jugadorAnterior.getIdJugador());
            ps.setString(2, jugador.getNombre());
            ps.setString(3, jugador.getApellido());
            ps.setString(4, jugador.getNacionalidad());
            ps.setDate(5, Date.valueOf(jugador.getFechaNacimiento()));
            ps.setString(6, jugador.getNickname());
            ps.setDouble(7, jugador.getSueldo());
            ps.setInt(8, jugador.getEquipo().getIdEquipo());
            ps.setString(9, jugadorAnterior.getNickname());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

// =============================================
// == OPERACIONES DE ELIMINACIÓN (DELETE)
// =============================================
    /**
     * Elimina un jugador de la base de datos por su nombre.
     * @param nombreJugador Nombre del jugador a eliminar.
     */

    public static void borrarJugador(String nombreJugador) {
        try {
            String sql = "DELETE FROM JUGADORES WHERE NICKNAME = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nombreJugador);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

}
