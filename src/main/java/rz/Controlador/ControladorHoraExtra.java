package rz.Controlador;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import rz.DAO.DAOHoraExtra;
import rz.Modelo.HoraExtra;
import spark.Request;
import spark.Response;

import java.util.UUID;

public class ControladorHoraExtra {

    private static Gson gson = new Gson();
    private static DAOHoraExtra daoHoraExtra = new DAOHoraExtra();

    public static String agregarHoraExtra(Request req, Response res){
        HoraExtra horaExtra = gson.fromJson(req.body(), HoraExtra.class);
        horaExtra.setId(UUID.randomUUID().toString());
        String msj = daoHoraExtra.agregarHoraExtra(horaExtra);
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("mensaje", msj);
        return gson.toJson(jsonObject);
    }


}
