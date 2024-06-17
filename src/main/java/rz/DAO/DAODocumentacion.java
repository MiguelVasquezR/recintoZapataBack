package rz.DAO;

import rz.Conexion.Conexion;
import rz.Modelo.Comprobante;
import rz.Modelo.Documentacion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DAODocumentacion {

    private static Conexion conexion = new Conexion();

    public String crearDocumentacion(Documentacion documentacion){
        Connection connection = null;
        PreparedStatement ps = null;
        try{
            connection = conexion.getConnection();
            ps = connection.prepareStatement("insert into Documentacion (id, linkContrato, linkReglamento, linkINE, id_evento) values(?,?,?,?,?)");
            ps.setString(1, documentacion.getID());
            ps.setString(2, documentacion.getLinkContrato());
            ps.setString(3, documentacion.getLinkReglamente());
            ps.setString(4, documentacion.getLinkINE());
            ps.setString(5, documentacion.getId_evento());
            int res = ps.executeUpdate();
            if (res > 0) {
                return "Documentacion creada";
            }else{
                return "No se ha podido crear la documentacion";
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
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

    public int verificarExistenciaDocumentacion(String id){
        Connection connection = null;
        PreparedStatement ps = null;
        try{
            connection = conexion.getConnection();
            ps = connection.prepareStatement("select * from Documentacion where id_evento = ?");
            ps.setString(1, id);
            if (ps.executeQuery().next()){
                return 1;
            }else{
                return 0;
            }
        }catch (Exception e){
            return 0;
        }finally {
            try {
                connection.close();
                if (connection.isClosed()) {
                    conexion.closeConnection();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public String crearComprobante(Comprobante comprobante){
        Connection connection = null;
        PreparedStatement ps = null;
        try{
            connection = conexion.getConnection();
            ps = connection.prepareStatement("insert into Comprobante (id, linkComprobante, id_evento) values(?,?,?)");
            ps.setString(1, comprobante.getId());
            ps.setString(2, comprobante.getLinkComprobante());
            ps.setString(3, comprobante.getId_evento());
            int res = ps.executeUpdate();
            if (res > 0) {
                return "Comprobante creado";
            }else{
                return "No se ha podido crear la documentacion";
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
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

    public int existeComprobante(String id){
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            connection = conexion.getConnection();
            ps = connection.prepareStatement("select count(id) as total from Comprobante where id_evento = ?");
            ps.setString(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("total");
            }else{
                return 0;
            }
        }catch (Exception e){
            return 0;
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

    public Documentacion obtenerDocumentacion(String idEvento) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Documentacion documentacion = new Documentacion();
        try {
            connection = conexion.getConnection();
            ps = connection.prepareStatement("select * from Documentacion where id_evento = ?");
            ps.setString(1, idEvento);
            rs = ps.executeQuery();
            if (rs.next()) {
                documentacion.setID(rs.getString("id"));
                documentacion.setLinkContrato(rs.getString("linkContrato"));
                documentacion.setLinkReglamente(rs.getString("linkReglamento"));
                documentacion.setLinkINE(rs.getString("linkINE"));
                documentacion.setId_evento(rs.getString("id_evento"));
            }
        } catch (Exception e) {
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
        return documentacion;
    }

    public ArrayList<Comprobante> obtenerComprobante(String idEvento) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        ArrayList<Comprobante> comprobantes = new ArrayList<>();
        try {
            connection = conexion.getConnection();
            ps = connection.prepareStatement("select * from Comprobante where id_evento = ?");
            ps.setString(1, idEvento);
            rs = ps.executeQuery();
            Comprobante comprobante;
            while (rs.next()) {
                comprobante = new Comprobante();
                comprobante.setId(rs.getString("id"));
                comprobante.setLinkComprobante(rs.getString("linkComprobante"));
                comprobante.setId_evento(rs.getString("id_evento"));
                comprobantes.add(comprobante);
            }
        } catch (Exception e) {
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
        return comprobantes;
    }


    //Métodos para editar la documentación y borrar el dato
    public String editarContrato(String id){
        Connection connection = null;
        PreparedStatement ps = null;
        try{
            connection = conexion.getConnection();
            ps = connection.prepareStatement("update Documentacion set linkContrato = ? where id = ?");
            ps.setString(1, "");
            ps.setString(2, id);
            int res = ps.executeUpdate();
            if (res > 0) {
                return "Actualizada";
            }else{
                return "No se ha podido actualizar la documentacion";
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
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

    public String editarReglamento(String id){
        Connection connection = null;
        PreparedStatement ps = null;
        try{
            connection = conexion.getConnection();
            ps = connection.prepareStatement("update Documentacion set linkReglamento = ? where id = ?");
            ps.setString(1, "");
            ps.setString(2, id);
            int res = ps.executeUpdate();
            if (res > 0) {
                return "Actualizada";
            }else{
                return "No se ha podido actualizar la documentacion";
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
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

    public String editarIne(String id){
        Connection connection = null;
        PreparedStatement ps = null;
        try{
            connection = conexion.getConnection();
            ps = connection.prepareStatement("update Documentacion set linkINE = ? where id = ?");
            ps.setString(1, "");
            ps.setString(2, id);
            int res = ps.executeUpdate();
            if (res > 0) {
                return "Actualizada";
            }else{
                return "No se ha podido actualizar la documentacion";
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
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

    public String editarComprobante(String id){
        Connection connection = null;
        PreparedStatement ps = null;
        try{
            connection = conexion.getConnection();
            ps = connection.prepareStatement("delete from Comprobante where id = ?");
            ps.setString(1, id);
            int res = ps.executeUpdate();
            if (res > 0) {
                return "Actualizada";
            }else{
                return "No se ha podido actualizar la documentacion";
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
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