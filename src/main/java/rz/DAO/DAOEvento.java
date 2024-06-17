package rz.DAO;

import com.google.gson.JsonObject;
import rz.Conexion.Conexion;
import rz.Modelo.Evento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DAOEvento {

    private static Conexion conexion = new Conexion();

    public String crearEvento(Evento evento){
        Connection c = null;
        PreparedStatement ps = null;
        try{
            c = conexion.getConnection();
            ps = c.prepareStatement("insert into evento (id, tipo, cantidadPersona, color, precio, horaInicio, fecha, numContrato, id_persona, estado) values (?,?,?,?,?,?,?,?,?,?)");
            ps.setString(1, evento.getId());
            ps.setString(2, evento.getTipo());
            ps.setString(3, evento.getCantidadPersonas());
            ps.setString(4, evento.getColor());
            ps.setString(5, evento.getPrecio());
            ps.setString(6, evento.getHoraInicio());
            ps.setString(7, evento.getFecha());
            ps.setString(8, evento.getNumContrato());
            ps.setString(9, evento.getIDPersona());
            ps.setString(10, evento.getEstado());

            int res = ps.executeUpdate();
            if (res>0){
                return "Se ha creado";
            }else {
                return "No se ha insertado";
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            return "Hubo una excepción";
        }finally {
            try{
                if (!c.isClosed()){
                    c.close();
                }
            }catch (Exception e) {
                System.out.println("Error al cerrar conexión del DAOEvento");
            }
        }
    }

    public ArrayList<Evento> obtenerEventos(){
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Evento> eventos = new ArrayList<>();
        try{
            connection = conexion.getConnection();
            ps = connection.prepareStatement("SELECT * FROM evento;");
            rs = ps.executeQuery();
            while (rs.next()){
                Evento evento = new Evento();
                evento.setId(rs.getString("id"));
                evento.setTipo(rs.getString("tipo"));
                evento.setCantidadPersonas(rs.getString("cantidadPersona"));
                evento.setColor(rs.getString("color"));
                evento.setPrecio(rs.getString("precio"));
                evento.setHoraInicio(rs.getString("horaInicio"));
                evento.setFecha(rs.getString("fecha"));
                evento.setNumContrato(rs.getString("numContrato"));
                evento.setIDPersona(rs.getString("id_persona"));
                eventos.add(evento);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return eventos;
    }


    public String obtenerCorreoCliente(String IDPersona){
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            connection = conexion.getConnection();
            ps = connection.prepareStatement("SELECT * FROM evento AS e, persona AS p WHERE e.id_persona = p.id AND p.id = ?;");
            ps.setString(1, IDPersona);
            rs = ps.executeQuery();
            if (rs.next()){
                return rs.getString("email");
            }else {
                return "No se ha encontrado el correo";
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            return "Hubo una excepción";
        }
    }


    public JsonObject obtenerEventoPorId(String id){
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        JsonObject evento = new JsonObject();
        try{
            connection = conexion.getConnection();
            ps = connection.prepareStatement("SELECT * FROM evento WHERE id = ?;");
            ps.setString(1, id);
            rs = ps.executeQuery();
            if (rs.next()){
                evento.addProperty("id", rs.getString("id"));
                evento.addProperty("tipo", rs.getString("tipo"));
                evento.addProperty("cantidadPersona", rs.getString("cantidadPersona"));
                evento.addProperty("color", rs.getString("color"));
                evento.addProperty("precio", rs.getString("precio"));
                evento.addProperty("horaInicio", rs.getString("horaInicio"));
                evento.addProperty("fecha", rs.getString("fecha"));
                evento.addProperty("numContrato", rs.getString("numContrato"));
                evento.addProperty("id_persona", rs.getString("id_persona"));
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return evento;
    }


    public JsonObject obtenerEventoID(String id) {
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
        try{
            con = conexion.getConnection();
            ps = con.prepareStatement("select e.id as IDEvento, e.tipo, e.cantidadPersona, e.precio, e.horaInicio, e.fecha, e.estado, p.id as IDPersona, p.nombre, p.paterno, p.materno, p.telefono, p.email from evento as e, persona as p where e.id_persona = p.id and e.id = ?");
            ps.setString(1, id);
            rs = ps.executeQuery();
            JsonObject evento = new JsonObject();
            if (rs.next()){
                evento.addProperty("IDEvento", rs.getString("IDEvento"));
                evento.addProperty("tipo", rs.getString("tipo"));
                evento.addProperty("cantidadPersona", rs.getString("cantidadPersona"));
                evento.addProperty("precio", rs.getString("precio"));
                evento.addProperty("horaInicio", rs.getString("horaInicio"));
                evento.addProperty("fecha", rs.getString("fecha"));
                evento.addProperty("estado", rs.getString("estado"));
                evento.addProperty("IDPersona", rs.getString("IDPersona"));
                evento.addProperty("nombre", rs.getString("nombre"));
                evento.addProperty("paterno", rs.getString("paterno"));
                evento.addProperty("materno", rs.getString("materno"));
                evento.addProperty("telefono", rs.getString("telefono"));
                evento.addProperty("email", rs.getString("email"));
                return evento;
            }else{
                return null;
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }


    public JsonObject obtenerEventobyIDPersona(String id) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = conexion.getConnection();
            ps = con.prepareStatement("select e.id as IDEvento, e.tipo, e.cantidadPersona, e.precio, e.horaInicio, e.fecha, e.estado, p.id as IDPersona, p.nombre, p.paterno, p.materno, p.telefono, p.email from evento as e, persona as p where e.id_persona = p.id and e.id_persona = ?");
            ps.setString(1, id);
            rs = ps.executeQuery();
            JsonObject evento = new JsonObject();
            if (rs.next()){
                evento.addProperty("IDEvento", rs.getString("IDEvento"));
                evento.addProperty("tipo", rs.getString("tipo"));
                evento.addProperty("cantidadPersona", rs.getString("cantidadPersona"));
                evento.addProperty("precio", rs.getString("precio"));
                evento.addProperty("horaInicio", rs.getString("horaInicio"));
                evento.addProperty("fecha", rs.getString("fecha"));
                evento.addProperty("estado", rs.getString("estado"));
                evento.addProperty("IDPersona", rs.getString("IDPersona"));
                evento.addProperty("nombre", rs.getString("nombre"));
                evento.addProperty("paterno", rs.getString("paterno"));
                evento.addProperty("materno", rs.getString("materno"));
                evento.addProperty("telefono", rs.getString("telefono"));
                evento.addProperty("email", rs.getString("email"));
                return evento;
            }else{
                return null;
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public String eliminarEvento(String id) {
        Connection con = null;
        PreparedStatement ps = null;
        try{
            con = conexion.getConnection();
            ps = con.prepareStatement("delete from evento where id = ?");
            ps.setString(1, id);
            int res = ps.executeUpdate();
            if (res>0){
                return "Se ha eliminado";
            }else{
                return "No se ha eliminado";
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            return "Hubo una excepción";
        }finally {
            try {
                if (!con.isClosed()) {
                    con.close();
                }
            } catch (Exception e) {
                System.out.println("Error al cerrar conexión del DAOEvento");
            }
        }
    }

    public Object actualizarEvento(Evento evento) {
        Connection con = null;
        PreparedStatement ps = null;
        try{
            con = conexion.getConnection();
            ps = con.prepareStatement("update evento set fecha = ?, horaInicio = ?, cantidadPersona = ?,precio = ? where id = ?");
            ps.setString(1, evento.getFecha());
            ps.setString(2, evento.getHoraInicio());
            ps.setString(3, evento.getCantidadPersonas());
            ps.setString(4, evento.getPrecio());
            ps.setString(5, evento.getId());
            int res = ps.executeUpdate();
            if (res>0){
                return "Se ha actualizado";
            }else{
                return "No se ha actualizado";
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public JsonObject obtenerInformacionPDF(String id) {

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try{
            connection = conexion.getConnection();
            ps = connection.prepareStatement("select e.tipo, e.cantidadPersona, e.precio, e.horaInicio, e.fecha, e.numContrato, p.nombre, p.paterno, p.materno, p.telefono, p.email, d.calle, d.numero, d.colonia, d.cp, d.ciudad from evento as e, persona as p, direccion as d  where e.id_persona = p.id and d.id_persona = p.id and p.id = ?;");
            ps.setString(1, id);
            rs = ps.executeQuery();
            JsonObject evento = new JsonObject();
            if (rs.next()){
                evento.addProperty("tipo", rs.getString("tipo"));
                evento.addProperty("cantidadPersona", rs.getString("cantidadPersona"));
                evento.addProperty("precio", rs.getString("precio"));
                evento.addProperty("horaInicio", rs.getString("horaInicio"));
                evento.addProperty("fecha", rs.getString("fecha"));
                evento.addProperty("numContrato", rs.getString("numContrato"));
                evento.addProperty("nombre", rs.getString("nombre"));
                evento.addProperty("paterno", rs.getString("paterno"));
                evento.addProperty("materno", rs.getString("materno"));
                evento.addProperty("telefono", rs.getString("telefono"));
                evento.addProperty("email", rs.getString("email"));
                evento.addProperty("calle", rs.getString("calle"));
                evento.addProperty("numero", rs.getString("numero"));
                evento.addProperty("colonia", rs.getString("colonia"));
                evento.addProperty("cp", rs.getString("cp"));
                evento.addProperty("ciudad", rs.getString("ciudad"));
                return evento;
            }else{
                return null;
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
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


    public Object actualizarEventoCliente(Evento evento) {
        Connection con = null;
        PreparedStatement ps = null;
        try{
            con = conexion.getConnection();
            ps = con.prepareStatement("update evento set fecha = ?, horaInicio = ?, cantidadPersona = ?, estado = ? where id = ?");
            ps.setString(1, evento.getFecha());
            ps.setString(2, evento.getHoraInicio());
            ps.setString(3, evento.getCantidadPersonas());
            ps.setString(4, evento.getEstado());
            ps.setString(5, evento.getId());
            int res = ps.executeUpdate();
            if (res>0){
                return "Se ha actualizado";
            }else{
                return "No se ha actualizado";
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }finally {
            try{
                con.close();
                if(con.isClosed()){
                    conexion.closeConnection();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public ArrayList<String> verificarFecha(){
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<String> fechas = new ArrayList<>();
        try{
            connection = conexion.getConnection();
            ps = connection.prepareStatement("select fecha from Evento;");
            rs = ps.executeQuery();
            while (rs.next()){
                fechas.add(rs.getString("fecha"));
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
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
        return fechas;
    }

    public String cancelarEvento(String id){
        Connection con = null;
        PreparedStatement ps = null;
        try{
            con = conexion.getConnection();
            ps = con.prepareStatement("update Evento set estado = 'Cancelado' where id = ?;");
            ps.setString(1, id);
            int rs = ps.executeUpdate();
            if(rs > 0) {
                return "Evento cancelado";
            }else{
                return "Error al cancelar evento";
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            return "Error al cancelar evento";
        }finally {
            try{
                con.close();
                if(con.isClosed()){
                    conexion.closeConnection();
                }
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }



}
