package rz.DAO;

import rz.Conexion.Conexion;
import rz.Modelo.Persona;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DAOPersona {

    private static Conexion conexion = new Conexion();

    public String crearPersona(Persona persona){
        Connection c = null;
        PreparedStatement ps = null;
        try{
            c = conexion.getConnection();
            ps = c.prepareStatement("insert into persona (id, nombre, paterno, materno, email, telefono) values (?,?,?,?,?,?)");
            ps.setString(1, persona.getId());
            ps.setString(2, persona.getNombre());
            ps.setString(3, persona.getPaterno());
            ps.setString(4, persona.getMaterno());
            ps.setString(5, persona.getEmail());
            ps.setString(6, persona.getTelefono());
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
                System.out.println("Error al cerrar conexión del DAOPersona");
            }
        }
    }

    public String actualizarPersona(Persona persona){
        Connection c = null;
        PreparedStatement ps = null;
        try{
            c = conexion.getConnection();
            ps = c.prepareStatement("update persona set nombre = ?, paterno = ?, materno = ?, email = ?, telefono = ? where id = ?");
            ps.setString(1, persona.getNombre());
            ps.setString(2, persona.getPaterno());
            ps.setString(3, persona.getMaterno());
            ps.setString(4, persona.getEmail());
            ps.setString(5, persona.getTelefono());
            ps.setString(6, persona.getId());
            int res = ps.executeUpdate();
            if (res>0){
                return "Se ha actualizado";
            }else {
                return "No se ha actualizado";
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
                System.out.println("Error al cerrar conexión del DAOPersona");
            }
        }
    }

    public String eliminarPersona(String id){
        Connection c = null;
        PreparedStatement ps = null;
        try{
            c = conexion.getConnection();
            ps = c.prepareStatement("delete from persona where id = ?");
            ps.setString(1, id);
            int res = ps.executeUpdate();
            if (res>0){
                return "Se ha eliminado";
            }else {
                return "No se ha eliminado";
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
                System.out.println("Error al cerrar conexión del DAOPersona");
            }
        }
    }

    public Persona obtenerPersona(String id){
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        Persona persona = null;
        try{
            c = conexion.getConnection();
            ps = c.prepareStatement("select * from persona where id = ?");
            ps.setString(1, id);
            rs = ps.executeQuery();
            if (rs.next()){
                persona = new Persona();
                persona.setId(rs.getString("id"));
                persona.setNombre(rs.getString("nombre"));
                persona.setPaterno(rs.getString("paterno"));
                persona.setMaterno(rs.getString("materno"));
                persona.setEmail(rs.getString("email"));
                persona.setTelefono(rs.getString("telefono"));
            }

            return persona;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }finally {
            try{
                if (!c.isClosed()){
                    c.close();
                }
            }catch (Exception e) {
                System.out.println("Error al cerrar conexión del DAOPersona");
            }
        }
    }

}
