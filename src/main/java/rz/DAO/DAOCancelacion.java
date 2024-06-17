package rz.DAO;

import rz.Conexion.Conexion;
import rz.Modelo.CancelacionEvento;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class DAOCancelacion {

    private static Conexion conexion = new Conexion();

    public String cancelarEvento(CancelacionEvento ce){
        Connection con = null;
        PreparedStatement ps = null;
        DAOEvento daoEvento = new DAOEvento();
        try{
            con = conexion.getConnection();
            String query = "INSERT INTO CancelacionEvento (id, id_evento, motivo) values (?,?,?)";
            ps = con.prepareStatement(query);
            ps.setString(1, ce.getId());
            ps.setString(2, ce.getId_evento());
            ps.setString(3, ce.getMotivo());
            int rs = ps.executeUpdate();
            if(rs > 0) {
                String msj = daoEvento.cancelarEvento(ce.getId_evento());
                if (msj.equals("Evento cancelado")){
                    return "Evento cancelado";
                }else{
                    return "Error al cancelar evento";
                }
            }else{
                return "Error al cancelar evento";
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            return "Error al cancelar evento";
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
