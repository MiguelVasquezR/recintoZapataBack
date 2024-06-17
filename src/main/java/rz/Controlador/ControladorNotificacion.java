package rz.Controlador;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import rz.DAO.DAONotificacion;
import rz.Modelo.Notificacion;
import spark.Request;
import spark.Response;

import java.util.UUID;

public class ControladorNotificacion {

    private static Gson gson = new Gson();
    private static rz.DAO.DAONotificacion DAONotificacion = new DAONotificacion();

    public static Object crearNotificacion(Request request, Response response){
        Notificacion notificacion = gson.fromJson(request.body(), Notificacion.class);
        notificacion.setID(UUID.randomUUID().toString());
        JsonObject jsonObject = new JsonObject();

        String msj = DAONotificacion.crearNotificacion(notificacion);
        if(msj.equals("Notificacion creada")){
            response.status(200);
            jsonObject.addProperty("mensaje", msj);
        }else{
            response.status(400);
            jsonObject.addProperty("mensaje", msj);
        }
        return gson.toJson(jsonObject);
    }

    public static String listarNotificaciones(Request request, Response response){
        return gson.toJson(DAONotificacion.listarNotificaciones());
    }


    public static String actualizarEstatusNotificacion(Request request, Response response) {
        Notificacion notificacion = gson.fromJson(request.body(), Notificacion.class);

        String msj = DAONotificacion.actualizarEstatusNotificacion(notificacion);


        return "";
    }
}
