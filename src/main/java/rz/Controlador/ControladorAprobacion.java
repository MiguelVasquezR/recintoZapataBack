package rz.Controlador;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import rz.DAO.DAOAprobacionDocumentacion;
import rz.Modelo.AprobacionDocumentacion;
import spark.Request;
import spark.Response;

import java.util.UUID;

public class ControladorAprobacion {

    private static Gson gson = new Gson();
    private static DAOAprobacionDocumentacion daoAprobacionDocumentacion = new DAOAprobacionDocumentacion();


    public static String editarCampo(Request req, Response res){
        JsonObject data = gson.fromJson(req.body(), JsonObject.class);
        String id_evento = data.get("id_evento").getAsString();
        String campo = data.get("campo").getAsString();
        String dato = data.get("dato").getAsString();

        String msj = "";
        if (daoAprobacionDocumentacion.verificarExistencia(id_evento)){
             msj = daoAprobacionDocumentacion.actualizarDato(id_evento, campo, dato);
        }else{
            AprobacionDocumentacion aprobacionDocumentacion = new AprobacionDocumentacion();
            aprobacionDocumentacion.setID(UUID.randomUUID().toString());
            aprobacionDocumentacion.setId_evento(id_evento);
            daoAprobacionDocumentacion.crearConfirmacion(aprobacionDocumentacion);
            msj = daoAprobacionDocumentacion.actualizarDato(id_evento, campo, dato);
        }
        JsonObject respuesta = new JsonObject();
        respuesta.addProperty("mensaje", msj);
        return gson.toJson(respuesta);
    }


    public static String obtenerValides(Request req, Response res){
        String id = req.queryParams("id");
        return gson.toJson(daoAprobacionDocumentacion.obtenerValidez(id));
    }

}
