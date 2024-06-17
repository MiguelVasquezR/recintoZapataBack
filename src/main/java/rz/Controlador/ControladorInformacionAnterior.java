package rz.Controlador;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonToken;
import rz.DAO.DAOInformacionAnterior;
import rz.Modelo.InformacionAnterior;
import spark.Request;
import spark.Response;

import java.util.UUID;

public class ControladorInformacionAnterior {

    private static DAOInformacionAnterior DAOInformacionAnterior = new DAOInformacionAnterior();
    private static Gson gson = new Gson();


    public static JsonObject crearInformacionAnterior(Request request, Response response) {
        JsonObject jsonObject = new JsonObject();
        InformacionAnterior informacionAnterior = gson.fromJson(request.body(), InformacionAnterior.class);
        informacionAnterior.setId(UUID.randomUUID().toString());
        String msj = DAOInformacionAnterior.crearInformacionAnterior(informacionAnterior);
        if (msj.equals("InformacionAnterior creada")){
            response.status(200);
            jsonObject.addProperty("mensaje", "InformacionAnterior creada");
        }else{
            response.status(400);
            jsonObject.addProperty("mensaje", msj);
        }
        return jsonObject;
    }


    public static String listarInformacionAnterior(Request request, Response response){
        String id = request.queryParams("id");
        InformacionAnterior informacionAnterior = DAOInformacionAnterior.obtenerInformacionAnterior(id);
        System.out.println(informacionAnterior);

        if (informacionAnterior != null){

            return gson.toJson(informacionAnterior);
        }else{

            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("mensaje", "Error");

            return gson.toJson(jsonObject);
        }
    }


    public static Object eliminarInformacionAnterior(Request request, Response response) {

        String id = request.queryParams("id");
        String msj = DAOInformacionAnterior.eliminarInformacionAnterior(id);
        JsonObject jsonObject = new JsonObject();
        if (msj.equals("InformacionAnterior eliminada")){
            response.status(200);
            jsonObject.addProperty("mensaje", "InformacionAnterior eliminada");
        }else{
            response.status(400);
            jsonObject.addProperty("mensaje", msj);
        }
        return jsonObject;

    }
}
