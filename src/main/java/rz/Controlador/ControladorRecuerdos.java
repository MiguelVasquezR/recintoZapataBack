package rz.Controlador;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import rz.Conexion.Conexion;
import spark.Request;
import spark.Response;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.UUID;

public class ControladorRecuerdos {
    private static Gson gson = new Gson();

    private static Conexion conexion = new Conexion();

    public static String crearRecuerdo(Request request, Response response) {
        JsonObject body = gson.fromJson(request.body(), JsonObject.class);
        String id = UUID.randomUUID().toString();
        String url = body.get("url").getAsString();
        Connection connection = conexion.getConnection();
        try{
            PreparedStatement ps = connection.prepareStatement("insert into Recuerdo (id, url) values (?, ?)");
            ps.setString(1, id);
            ps.setString(2, url);
            int res = ps.executeUpdate();
            if (res > 0) {
                return "Recuerdo creado";
            }else{
                return "No se ha podido crear el recuerdo";
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            return  "Excepcion";
        }
    }

    public static String listarRecuerdos(Request request, Response response) {
        Connection connection = conexion.getConnection();
        ArrayList<JsonObject> lista = new ArrayList<>();
        try{
            PreparedStatement ps = connection.prepareStatement("select * from Recuerdo");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("id", rs.getString("id"));
                jsonObject.addProperty("url", rs.getString("url"));
                lista.add(jsonObject);
            }
            return gson.toJson(lista);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
}
