package rz.DAO;

import com.google.gson.JsonObject;
import rz.Conexion.Conexion;
import rz.Modelo.Notificacion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DAONotificacion {

    private static Conexion conexion = new Conexion();

    public String crearNotificacion(Notificacion notificacion){
        Connection connection = null;
        PreparedStatement ps = null;
        try{
            connection = conexion.getConnection();
            ps = connection.prepareStatement("insert into Notificacion (id, titulo, mensaje, estatus, id_evento, fecha) values(?,?,?,?,?,?)");
            ps.setString(1, notificacion.getID());
            ps.setString(2, notificacion.getTitulo());
            ps.setString(3, notificacion.getMensaje());
            ps.setString(4, notificacion.getEstatus());
            ps.setString(5, notificacion.getId_evento());
            ps.setString(6, notificacion.getFecha());
            int res = ps.executeUpdate();
            if (res > 0) {
                return "Notificacion creada";
            }else{
                return "No se ha podido crear la notificacion";
            }
        }catch (Exception e){
            return "Ha ocurrido un error";
        }finally {
            try{
                connection.close();
                if(connection.isClosed()){
                    conexion.closeConnection();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public ArrayList<JsonObject> listarNotificaciones(){
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<JsonObject> notificaciones = new ArrayList<>();
        try{
            connection = conexion.getConnection();
            ps = connection.prepareStatement("SELECT n.id as IDNotificacion, n.fecha as FechaNotificacion, n.mensaje, n.estatus, n.titulo, p.nombre, p.paterno, p.materno, e.fecha as FechaEvento\n" +
                    "FROM Notificacion AS n\n" +
                    "         JOIN Evento AS e ON e.id = n.id_evento\n" +
                    "         JOIN Persona AS p ON p.id = e.id_persona;");
            rs = ps.executeQuery();
            while (rs.next()){
                JsonObject notificacion = new JsonObject();
                notificacion.addProperty("IDNotificacion", rs.getString("IDNotificacion"));
                notificacion.addProperty("FechaNotificacion", rs.getString("FechaNotificacion"));
                notificacion.addProperty("mensaje", rs.getString("mensaje"));
                notificacion.addProperty("titulo", rs.getString("titulo"));
                notificacion.addProperty("nombre", rs.getString("nombre"));
                notificacion.addProperty("paterno", rs.getString("paterno"));
                notificacion.addProperty("estatus", rs.getString("estatus"));
                notificacion.addProperty("materno", rs.getString("materno"));
                notificacion.addProperty("FechaEvento", rs.getString("FechaEvento"));
                notificaciones.add(notificacion);
            }
            return notificaciones;
        }catch (Exception e){
            return notificaciones;
        }finally {
            try{
                connection.close();
                if(connection.isClosed()){
                    conexion.closeConnection();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public String actualizarEstatusNotificacion(Notificacion notificacion) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = conexion.getConnection();
            ps = connection.prepareStatement("update Notificacion set estatus = ? where id = ?");
            ps.setString(1, notificacion.getEstatus());
            ps.setString(2, notificacion.getID());
            int res = ps.executeUpdate();
            if (res > 0) {
                return "Estatus actualizado";
            } else {
                return "No se ha podido actualizar el estatus";
            }
        } catch (Exception e) {
            return "Ha ocurrido un error";
        }finally {
            try{
                connection.close();
                if(connection.isClosed()){
                    conexion.closeConnection();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
