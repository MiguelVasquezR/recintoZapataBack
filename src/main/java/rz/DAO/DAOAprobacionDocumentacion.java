package rz.DAO;

import rz.Conexion.Conexion;
import rz.Modelo.AprobacionDocumentacion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DAOAprobacionDocumentacion {

    private static Conexion conexion = new Conexion();

    public String crearConfirmacion(AprobacionDocumentacion ad){
        Connection connection = null;
        PreparedStatement ps = null;
        try{
            connection = conexion.getConnection();
            ps = connection.prepareStatement("insert into AprobacionDocumentacion (ID, id_evento) values (?, ?)");
            ps.setString(1, ad.getID());
            ps.setString(2, ad.getId_evento());
            int res = ps.executeUpdate();
            if (res >= 0){
                return "Confirmacion creada";
            }else{
                return "Error al crear la confirmacion";
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            return "Error al crear la confirmacion";
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

    public String actualizarDato(String id_evento, String campo, String dato){
        Connection con = null;
        PreparedStatement ps = null;
        String quety = "update AprobacionDocumentacion set "+ campo +" = ? where id_evento = ?";
        try{
            con = conexion.getConnection();
            ps = con.prepareStatement(quety);
            ps.setString(1, dato);
            ps.setString(2, id_evento);
            int res = ps.executeUpdate();
            if (res >= 0){
                return "Dato actualizado";
            }else{
                return "Error al actualizar el dato";
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            return "Error al actualizar el dato";
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


    public boolean verificarExistencia(String id){
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = conexion.getConnection();
            ps = con.prepareStatement("select * from AprobacionDocumentacion where id_evento = ?");
            ps.setString(1, id);
            rs = ps.executeQuery();
            if (rs.next()){
                return true;
            }else{
                return false;
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
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

    public AprobacionDocumentacion obtenerValidez(String id){
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = conexion.getConnection();
            ps = con.prepareStatement("select * from AprobacionDocumentacion where id_evento = ?");
            ps.setString(1, id);
            rs = ps.executeQuery();
            if(rs.next()){
                AprobacionDocumentacion ad = new AprobacionDocumentacion();
                ad.setID(rs.getString("ID"));
                ad.setId_evento(rs.getString("id_evento"));
                ad.setEstatusContrato(rs.getString("estatusContrato"));
                ad.setEstatusReglamento(rs.getString("estatusReglamento"));
                ad.setEstatusINE(rs.getString("estatusINE"));
                ad.setEstatusComprobante1(rs.getString("estatusComprobante1"));
                ad.setEstatusComprobante2(rs.getString("estatusComprobante2"));
                return ad;
            }else{
                return null;
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






}
