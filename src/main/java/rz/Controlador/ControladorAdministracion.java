package rz.Controlador;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import rz.DAO.DAOAdministracion;
import rz.Modelo.Administracion;
import spark.Request;
import spark.Response;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class ControladorAdministracion {

    private static Gson gson = new Gson();
    private static DAOAdministracion daoAdministracion = new DAOAdministracion();


    public static String crearConcepto(Request req, Response res){
        Administracion administracion = gson.fromJson(req.body(), Administracion.class);
        administracion.setId(UUID.randomUUID().toString());
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        administracion.setFecha(simpleDateFormat.format(date));
        return daoAdministracion.crearConcepto(administracion);
    }

    public static String generarReporte(Request req, Response res){
        String fechaInicio = req.queryParams("inicio");
        String fechaFin = req.queryParams("fin");

        String data = gson.toJson(daoAdministracion.generarReporte(fechaInicio, fechaFin));
        return data;
    }


}
