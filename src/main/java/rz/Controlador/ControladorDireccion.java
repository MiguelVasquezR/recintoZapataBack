package rz.Controlador;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import rz.DAO.DAODireccion;
import rz.Modelo.Direccion;
import spark.Request;
import spark.Response;

import java.util.UUID;

public class ControladorDireccion {

    private static Gson gson = new Gson();
    private static DAODireccion daoDireccion = new DAODireccion();


    public static JsonObject crearDireccion(Request req, Response res){
        Direccion direccion = gson.fromJson(req.body(), Direccion.class);
        System.out.println(direccion);


        direccion.setId(UUID.randomUUID().toString());
        String mensaje = daoDireccion.crearDireccion(direccion);

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("mensaje",mensaje);
        return jsonObject;
    }


}
