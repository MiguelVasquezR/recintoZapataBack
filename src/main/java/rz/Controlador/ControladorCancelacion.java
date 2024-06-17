package rz.Controlador;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import rz.DAO.DAOCancelacion;
import rz.Modelo.CancelacionEvento;
import spark.Request;
import spark.Response;

import java.util.UUID;

public class ControladorCancelacion {

    private static Gson gson = new Gson();
    private static DAOCancelacion daoCancelacion = new DAOCancelacion();


    public static String cancelarEvento(Request req, Response res){
        CancelacionEvento cancelacionEvento = gson.fromJson(req.body(), CancelacionEvento.class);
        cancelacionEvento.setId(UUID.randomUUID().toString());
        String msj = daoCancelacion.cancelarEvento(cancelacionEvento);
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("mensaje", msj);
        return gson.toJson(jsonObject);
    }

}
