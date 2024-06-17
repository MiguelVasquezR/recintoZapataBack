package rz.Controlador;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import rz.DAO.DAOPersona;
import rz.Modelo.Persona;
import spark.Request;
import spark.Response;

import java.util.UUID;

public class ControladorPersona {

    private static Gson gson = new Gson();
    private static DAOPersona daoPersona = new DAOPersona();

    public static JsonObject crearPersona(Request req, Response res) {
        Persona persona = gson.fromJson(req.body(), Persona.class);
        persona.setId(UUID.randomUUID().toString());
        String mensaje = daoPersona.crearPersona(persona);
        JsonObject jsonObject = new JsonObject();
        if (mensaje.equals("Se ha creado")){
            jsonObject.addProperty("mensaje",mensaje);
            jsonObject.addProperty("id",persona.getId());
        }else{
            jsonObject.addProperty("mensaje",mensaje);
        }
        return jsonObject;
    }

    public static JsonObject actualizarPersona(Request req, Response res) {
        Persona persona = gson.fromJson(req.body(), Persona.class);
        String mensaje = daoPersona.actualizarPersona(persona);
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("mensaje",mensaje);
        return jsonObject;
    }

    public static JsonObject eliminarPersona(Request req, Response res) {
        String id = req.queryParams("id");
        String mensaje = daoPersona.eliminarPersona(id);
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("mensaje",mensaje);
        return jsonObject;
    }

    public static String obtenerPersonas(Request req, Response res) {
        String id = req.queryParams("id");
        Persona persona = daoPersona.obtenerPersona(id);
        return gson.toJson(persona);
    }




}
