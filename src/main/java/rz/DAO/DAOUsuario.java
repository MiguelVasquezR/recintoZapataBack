package rz.DAO;

import rz.Conexion.Conexion;
import rz.Modelo.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DAOUsuario {
    private static Conexion conexion = new Conexion();

    public String crearUsuario(Usuario usuario){
        Connection c = null;
        PreparedStatement ps = null;
        try{
            c = conexion.getConnection();
            ps = c.prepareStatement("insert into usuario(id, usuario, contrasena, rol, salt, id_persona) values (?,?,?,?,?,?)");
            ps.setString(1, usuario.getId());
            ps.setString(2, usuario.getUsuario());
            ps.setString(3, usuario.getContrasena());
            ps.setString(4, usuario.getRol());
            ps.setString(5, usuario.getSalt());
            ps.setString(6, usuario.getId_persona());
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
                c.close();
                if(c.isClosed()){
                    conexion.closeConnection();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public Usuario obtenerUsuario(String usuario){
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Usuario usuario1 = new Usuario();
        try{
            c = conexion.getConnection();
            ps = c.prepareStatement("select * from usuario where usuario = ?");
            ps.setString(1, usuario);
            rs = ps.executeQuery();
            if (rs.next()){
                usuario1.setId(rs.getString("id"));
                usuario1.setUsuario(rs.getString("usuario"));
                usuario1.setContrasena(rs.getString("contrasena"));
                usuario1.setRol(rs.getString("rol"));
                usuario1.setSalt(rs.getString("salt"));
                usuario1.setId_persona(rs.getString("id_persona"));
            }
            return usuario1;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }finally {
            try{
                c.close();
                if(c.isClosed()){
                    conexion.closeConnection();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public String cambiarPassword(Usuario usuario){
        Connection c = null;
        PreparedStatement ps = null;
        try{
            c = conexion.getConnection();
            ps = c.prepareStatement("update usuario set contrasena = ?, salt = ? where id = ?");
            ps.setString(1, usuario.getContrasena());
            ps.setString(2, usuario.getSalt());
            ps.setString(3, usuario.getId());
            int res = ps.executeUpdate();
            if (res>0){
                return "Contraseña cambiada";
            }else {
                return "No se cambio la contraseña";
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            return "Hubo una excepción";
        }finally {
            try{
                c.close();
                if(c.isClosed()){
                    conexion.closeConnection();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }


}
