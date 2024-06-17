package rz.DAO;

import com.google.gson.Gson;
import rz.Conexion.Conexion;
import rz.Modelo.Multa;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class DAOMulta {
    private static Conexion conexion = new Conexion();

    public String crearMulta(Multa multa){
        Connection con = null;
        PreparedStatement ps = null;
        try{
            con = conexion.getConnection();
            ps = con.prepareStatement("insert into Multa values (?,?,?,?)");
            ps.setString(1, multa.getId());
            ps.setString(2, multa.getTipo());
            ps.setString(3, multa.getCantidad());
            ps.setString(4, multa.getId_evento());
            int res = ps.executeUpdate();
            if(res > 0) {
                return "Multa creada";
            }else{
                return "Error al crear la multa";
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            return "Error al crear la multa";
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

    public String eliminarMulta(String id){
        Connection con = null;
        PreparedStatement ps = null;
        try{
            con = conexion.getConnection();
            ps = con.prepareStatement("delete from Multa where id = ?");
            ps.setString(1, id);
            int res = ps.executeUpdate();
            if(res > 0) {
                return "Multa eliminada";
            }else{
                return "Error al eliminar la multa";
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            return "Error al eliminar la multa";
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
