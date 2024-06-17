package rz.DAO;

import rz.Conexion.Conexion;
import rz.Modelo.Direccion;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class DAODireccion {

    private static Conexion conexion = new Conexion();

    public String crearDireccion(Direccion direccion){
        Connection c = null;
        PreparedStatement ps = null;

        try{
            c = conexion.getConnection();
            ps = c.prepareStatement("insert into direccion (id, calle, numero, colonia, cp, ciudad, id_persona) values (?,?,?,?,?,?, ?)");
            ps.setString(1, direccion.getId());
            ps.setString(2, direccion.getCalle());
            ps.setString(3, direccion.getNumero());
            ps.setString(4, direccion.getColonia());
            ps.setString(5, direccion.getCp());
            ps.setString(6, direccion.getCiudad());
            ps.setString(7, direccion.getIDPersona());
            int res = ps.executeUpdate();
            if (res>0){
                return "Se ha creado";
            }else {
                return "No se ha insertado";
            }

        }catch (Exception e){
            System.out.printf(e.getMessage());
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
