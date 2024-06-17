package rz.Controlador;

import com.google.gson.Gson;
import rz.DAO.DAOMulta;
import rz.Modelo.Multa;
import spark.Request;
import spark.Response;

import java.util.UUID;

public class ControladorMulta {

    private static Gson gson = new Gson();
    private static DAOMulta daoMulta = new DAOMulta();

    public static String crearMulta(Request req, Response res){
        Multa multa = gson.fromJson(req.body(), Multa.class);
        multa.setId(UUID.randomUUID().toString());
        return gson.toJson(daoMulta.crearMulta(multa));
    }

    public static String eliminarMulta(Request req, Response res){
        return gson.toJson(daoMulta.eliminarMulta(req.params("id")));
    }

}
