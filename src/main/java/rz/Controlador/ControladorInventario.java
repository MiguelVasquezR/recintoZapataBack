package rz.Controlador;

import java.util.UUID;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import rz.DAO.DAOInventario;
import rz.Modelo.ItemInventario;
import spark.Request;
import spark.Response;

public class ControladorInventario {
    private static DAOInventario daoInventario = new DAOInventario();
    private static Gson gson = new Gson();

    public static JsonObject CrearItemInventario(Request req, Response res){
        ItemInventario item = gson.fromJson(req.body(), ItemInventario.class);
        item.setId(UUID.randomUUID().toString());

        String mensaje = daoInventario.insertarItem(item);
        
        JsonObject respuesta = new JsonObject();
        respuesta.addProperty("mensaje", mensaje);
        return respuesta;
    }

    public static String obtenerItems(Request req, Response res){
        return gson.toJson(daoInventario.obtenerItems());
    }

    public static String actualizarCantidadActual(Request req, Response res){
        ItemInventario item = gson.fromJson(req.body(), ItemInventario.class);
        String mensaje = daoInventario.actualizarCantidadActual(item);
        return mensaje;
    }
    
    public static String actualizarItem(Request req, Response res){
        ItemInventario item = gson.fromJson(req.body(), ItemInventario.class);
        String mensaje = daoInventario.actualizarItem(item);
        return mensaje;
    }

    public static String eliminarItem(Request req, Response res){
        String id = req.queryParams("id");
        String mensaje = daoInventario.eliminarItem(id);
        return mensaje;
    }

    public static String obtenerItem(Request req, Response res){
        String id = req.queryParams("id");
        return gson.toJson(daoInventario.obtenerItem(id));
    }

}