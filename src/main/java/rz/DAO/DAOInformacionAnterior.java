package rz.DAO;

import rz.Conexion.Conexion;
import rz.Modelo.InformacionAnterior;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DAOInformacionAnterior {

    private static Conexion conexion = new Conexion();


    public String crearInformacionAnterior(InformacionAnterior informacionAnterior){
        Connection connection = null;
        PreparedStatement ps = null;
        try{
            connection = conexion.getConnection();
            ps = connection.prepareStatement("insert into InformacionAnterior (id, cantidadPersona, fecha, horaInicio, id_evento, estatus) values(?,?,?,?,?,?)");
            ps.setString(1, informacionAnterior.getId());
            ps.setString(2, informacionAnterior.getCantidadPersonas());
            ps.setString(3, informacionAnterior.getFecha());
            ps.setString(4, informacionAnterior.getHoraInicio());
            ps.setString(5, informacionAnterior.getId_evento());
            ps.setString(6, informacionAnterior.getEstatus());
            int res = ps.executeUpdate();
            if (res > 0) {
                return "InformacionAnterior creada";
            }else{
                return "No se ha podido crear la informacion anterior";
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


    //Método para obtener la información anterior por id y en estatus de proceso
    public InformacionAnterior obtenerInformacionAnterior(String id){
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        InformacionAnterior informacionAnterior = null;
        try{
            connection = conexion.getConnection();
            ps = connection.prepareStatement("select * from InformacionAnterior where id_evento = ? and estatus = 'En Modificacion'");
            ps.setString(1, id);
            rs = ps.executeQuery();
            if (rs.next()){
                informacionAnterior = new InformacionAnterior();
                informacionAnterior.setId(rs.getString("id"));
                informacionAnterior.setCantidadPersonas(rs.getString("cantidadPersona"));
                informacionAnterior.setFecha(rs.getString("fecha"));
                informacionAnterior.setHoraInicio(rs.getString("horaInicio"));
                informacionAnterior.setId_evento(rs.getString("id_evento"));
                informacionAnterior.setEstatus(rs.getString("estatus"));
            }
            return informacionAnterior;
        }catch (Exception e){
            return informacionAnterior;
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


    public String eliminarInformacionAnterior(String id) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = conexion.getConnection();
            ps = connection.prepareStatement("delete from InformacionAnterior where id_evento = ?");
            ps.setString(1, id);
            int res = ps.executeUpdate();
            if (res > 0) {
                return "InformacionAnterior eliminada";
            } else {
                return "No se ha podido eliminar la informacion anterior";
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
