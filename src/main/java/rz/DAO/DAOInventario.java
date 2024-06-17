package rz.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import rz.Conexion.Conexion;
import rz.Modelo.ItemInventario;

public class DAOInventario {
    private static Conexion conexion = new Conexion();

    public String insertarItem(ItemInventario item) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = conexion.getConnection();
            String query = "INSERT INTO Inventario VALUES(?,?,?,?,?,?,?)";
            ps = con.prepareStatement(query);
            ps.setString(1, item.getId());
            ps.setString(2, item.getNombre());
            ps.setString(3, item.getLinkFoto());
            ps.setInt(4, item.getTotalCantidad());
            ps.setInt(5, item.getCantidadActual());
            ps.setString(6, item.getTipo());
            ps.setFloat(7, item.getPrecioUnitario());
            int res = ps.executeUpdate();
            if (res>0) {
                return "Se inserto correctamente";
            }else{
                return "No se pudo insertar";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Hubo una excepcion";
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
    
    public String actualizarItem(ItemInventario item) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = conexion.getConnection();
            String query = "UPDATE Inventario SET nombre=?, totalCantidad=?, tipo=?, precioUnitario=? WHERE id=?";
            ps = con.prepareStatement(query);
            ps.setString(1, item.getNombre());
            ps.setInt(2, item.getTotalCantidad());
            ps.setString(3, item.getTipo());
            ps.setFloat(4, item.getPrecioUnitario());
            ps.setString(5, item.getId());
            int res = ps.executeUpdate();
            if (res>0) {
                return "Se actualizo correctamente";
            }else{
                return "No se pudo actualizar";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Hubo una excepcion";
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
    
    public String actualizarCantidadActual(ItemInventario item) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = conexion.getConnection();
            String query = "UPDATE Inventario SET cantidadActual=? WHERE id=?";
            ps = con.prepareStatement(query);
            ps.setInt(1, item.getCantidadActual());
            ps.setString(2, item.getId());
            int res = ps.executeUpdate();
            if (res>0) {
                return "Se actualizo correctamente";
            }else{
                return "No se pudo actualizar";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Hubo una excepcion";
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

    public String eliminarItem(String id) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = conexion.getConnection();
            String query = "DELETE FROM Inventario WHERE id=?";
            ps = con.prepareStatement(query);
            ps.setString(1, id);
            int res = ps.executeUpdate();
            if (res>0) {
                return "Se elimino correctamente";
            }else{
                return "No se pudo eliminar";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Hubo una excepcion";
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

    public ArrayList<ItemInventario> obtenerItems() {
        Connection con = null;
        PreparedStatement ps = null;
        ArrayList<ItemInventario> items = new ArrayList<>();
        ResultSet rs = null;
        try {
            con = conexion.getConnection();
            String query = "SELECT * FROM Inventario";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                ItemInventario item = new ItemInventario();
                item.setId(rs.getString("id"));
                item.setNombre(rs.getString("nombre"));
                item.setLinkFoto(rs.getString("linkFoto"));
                item.setTotalCantidad(rs.getInt("totalCantidad"));
                item.setCantidadActual(rs.getInt("cantidadActual"));
                item.setTipo(rs.getString("tipo"));
                item.setPrecioUnitario(rs.getFloat("precioUnitario"));
                items.add(item);
            }
            return items;
        } catch (Exception e) {
            e.printStackTrace();
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

    public ItemInventario obtenerItem(String id) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = conexion.getConnection();
            String query = "SELECT * FROM Inventario WHERE id=?";
            ps = con.prepareStatement(query);
            ps.setString(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                ItemInventario item = new ItemInventario();
                item.setId(rs.getString("id"));
                item.setNombre(rs.getString("nombre"));
                item.setLinkFoto(rs.getString("linkFoto"));
                item.setTotalCantidad(rs.getInt("totalCantidad"));
                item.setCantidadActual(rs.getInt("cantidadActual"));
                item.setTipo(rs.getString("tipo"));
                item.setPrecioUnitario(rs.getFloat("precioUnitario"));
                return item;
            }else{
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
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
