package rz.Controlador;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import rz.DAO.DAODocumentacion;
import rz.DAO.DAOEvento;
import rz.LlenarPDF;
import rz.Modelo.Comprobante;
import rz.Modelo.Documentacion;
import rz.Seguridad.Token.Token;
import spark.Request;
import spark.Response;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class ControladorDocumentos {
    private static Gson gson = new Gson();
    private static DAODocumentacion daoDocumentacion = new DAODocumentacion();


    public static Object obtenerDocumentos(Request req, Response res){
        String token = req.queryParams("token");
        JsonObject jsonObject = Token.validarToken(token);
        String rol = jsonObject.get("Rol").getAsString();


        if (rol.equals("Cliente")){

            System.out.println("Cliente");
            DAOEvento daoEvento = new DAOEvento();
            JsonObject jsonObject1 = daoEvento.obtenerInformacionPDF(jsonObject.get("ID").getAsString());

            LlenarPDF llenarPDF = new LlenarPDF();
            llenarPDF.llenarContrato("");


        }
        return "";
    }


    public static Object crearDocumentacion(Request req, Response res){
        JsonObject response = new JsonObject();
        String token = req.queryParams("token");
        JsonObject jsonObject = Token.validarToken(token);
        String rol = jsonObject.get("Rol").getAsString();

        if (rol.equals("Cliente")){
            Documentacion documentacion = gson.fromJson(req.body(), Documentacion.class);
            documentacion.setID(UUID.randomUUID().toString());

            System.out.println(documentacion.toString());
            String msj = daoDocumentacion.crearDocumentacion(documentacion);
            if (msj.equals("Documentacion creada")){
                response.addProperty("message", "Documentacion creada");
            }else{
                response.addProperty("message", "No se ha podido crear la documentacion");
            }
        }

        return response;
    }

    public static JsonObject verificarExistenciaDocumentacion(Request req, Response res){
        String id_evento = req.queryParams("id");
        System.out.println(id_evento);
        int res1 = daoDocumentacion.verificarExistenciaDocumentacion(id_evento);

        JsonObject jsonObject = new JsonObject();
        if (res1 == 1){
            jsonObject.addProperty("mensaje", "Existe");
        }else{
            jsonObject.addProperty("mensaje", "No existe");
        }
        return jsonObject;
    }

    public static Object crearComprobante(Request request, Response response) {
        String token = request.queryParams("token");
        JsonObject jsonObject1 = Token.validarToken(token);
        String rol = jsonObject1.get("Rol").getAsString();
        JsonObject jsonObject = new JsonObject();

        String msj = "";
        if(rol.equals("Cliente")){
            Comprobante comprobante = gson.fromJson(request.body(), Comprobante.class);
            comprobante.setId(UUID.randomUUID().toString());
            msj = daoDocumentacion.crearComprobante(comprobante);
        }

        if (msj.equals("Comprobante creado")){
            jsonObject.addProperty("message", "Comprobante creado");
        }else{
            jsonObject.addProperty("message", "No se ha podido crear el comprobante");
        }
        return jsonObject;
    }

    public static JsonObject verificarComprobante(Request req, Response res){
        String id_evento = req.queryParams("id");
        int res1 = daoDocumentacion.existeComprobante(id_evento);

        JsonObject jsonObject = new JsonObject();
        if (res1 == 1){
            jsonObject.addProperty("mensaje", "Falta");
        }else if(res1 >= 2){
            jsonObject.addProperty("mensaje", "Completo");
        }else{
            jsonObject.addProperty("mensaje", "No existe");
        }
        return jsonObject;

    }

    public static Object obtenerDocumentacion(Request request, Response response) {
        String id_evento = request.queryParams("id");
        Documentacion documentacion = daoDocumentacion.obtenerDocumentacion(id_evento);
        return gson.toJson(documentacion);
    }


    public static Object obtenerComprobante(Request request, Response response) {
        String id_evento = request.queryParams("id");
        return gson.toJson(daoDocumentacion.obtenerComprobante(id_evento));
    }

    public static String modificarDocumentacion(Request request, Response response) {
        String id = request.queryParams("id");
        JsonObject jsonObject = gson.fromJson(request.body(), JsonObject.class);
        String tipo = jsonObject.get("tipo").getAsString();
        JsonObject res = new JsonObject();
        String mensaje = "";
        switch (tipo){
            case "contrato":
                mensaje = daoDocumentacion.editarContrato(id);
                break;
            case "reglamento":
                mensaje = daoDocumentacion.editarReglamento(id);
                break;
            case "ine":
                mensaje = daoDocumentacion.editarIne(id);
                break;
            case "comprobante":
                mensaje = daoDocumentacion.editarComprobante(id);
                break;
        }

        res.addProperty("mensaje", mensaje);
        return gson.toJson(res);
    }

    public static String eliminarComprobante(Request request, Response response){
        String id = request.queryParams("id");

        String msj = daoDocumentacion.editarComprobante(id);
        return msj;
    }



}
