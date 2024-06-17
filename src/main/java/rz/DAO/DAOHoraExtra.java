package rz.DAO;

import rz.Conexion.Conexion;
import rz.Modelo.HoraExtra;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class DAOHoraExtra {

    private static Conexion conexion = new Conexion();

    public String agregarHoraExtra(HoraExtra horaExtra){
        Connection con = null;
        PreparedStatement ps = null;
        try{
            con = conexion.getConnection();
            String query = "INSERT INTO HoraExtra (id, cantidad, precio, id_evento ) VALUES (?,?,?,?)";
            ps = con.prepareStatement(query);
            ps.setString(1, horaExtra.getId());
            ps.setString(2, horaExtra.getHoraExtra());
            ps.setString(3, horaExtra.getPrecio());
            ps.setString(4, horaExtra.getId_evento());
            int rs = ps.executeUpdate();
            if(rs > 0) {
                return "Hora extra agregada";
            }else{
                return "Error al agregar hora extra";
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            return "Error al agregar hora extra";
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
