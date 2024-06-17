package rz.DAO;

import org.eclipse.jetty.websocket.server.WebSocketHandler;
import rz.Conexion.Conexion;
import rz.Modelo.Administracion;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class DAOAdministracion {

    private static Conexion conexion = new Conexion();


    public String crearConcepto(Administracion administracion){
        Connection con = null;
        PreparedStatement ps = null;
        try{
            con = conexion.getConnection();
            ps = con.prepareStatement("INSERT INTO Administracion (id, concepto, tipo, fecha, cantidad) VALUES (?, ?, ?, ?, ?)");
            ps.setString(1, administracion.getId());
            ps.setString(2, administracion.getConcepto());
            ps.setString(3, administracion.getTipo());
            ps.setDate(4, convertirFechaFormatoSQL(administracion.getFecha()));
            ps.setDouble(5, administracion.getCantidad());
            int res = ps.executeUpdate();
            if(res > 0) {
                return "Concepto creado";
            }else{
                return "Error al crear concepto";
            }
        }catch (Exception e){
            e.printStackTrace();
            return "Error al crear concepto";
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


    public ArrayList<Administracion> generarReporte(String fechaInicio, String fechaFin) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Administracion> administraciones = new ArrayList<>();
        try{
            Date fechaInicioDate = convertirFechaFormatoSQL(fechaInicio);
            Date fechaFinDate = convertirFechaFormatoSQL(fechaFin);
            con = conexion.getConnection();
            ps = con.prepareStatement("SELECT * FROM Administracion WHERE fecha BETWEEN ? AND ?");
            ps.setDate(1, fechaInicioDate);
            ps.setDate(2, fechaFinDate);
            rs = ps.executeQuery();
            while(rs.next()){
                Administracion administracion = new Administracion();
                administracion.setId(rs.getString("id"));
                administracion.setConcepto(rs.getString("concepto"));
                administracion.setTipo(rs.getString("tipo"));
                administracion.setFecha(rs.getString("fecha"));
                administracion.setCantidad(rs.getDouble("cantidad"));
                administraciones.add(administracion);
            }
            return administraciones;
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

    private static Date convertirFechaFormatoSQL(String fecha){
        try{
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
            java.util.Date fechaUtil = simpleDateFormat.parse(fecha);
            Date fechaInicioDate = new Date(fechaUtil.getTime());
            return  fechaInicioDate;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


}
