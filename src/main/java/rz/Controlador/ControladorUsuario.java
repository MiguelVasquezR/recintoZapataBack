package rz.Controlador;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import rz.DAO.DAOUsuario;
import rz.Modelo.Usuario;
import rz.Seguridad.Encriptacion.Encriptar;
import rz.Seguridad.Token.Token;
import spark.Request;
import spark.Response;

public class ControladorUsuario {
    private static  Gson gson = new Gson();
    private static DAOUsuario daoUsuario = new DAOUsuario();
    private static Encriptar encriptar = new Encriptar();
    private static Map<String, String> map = new HashMap<>();

    public static JsonObject crearUsuario(Request req, Response res) {
        Usuario usuario = gson.fromJson(req.body(), Usuario.class);
        usuario.setId(UUID.randomUUID().toString());

        JsonObject passwordEncriptado = encriptar.encryptPassword(usuario.getContrasena());
        usuario.setContrasena(passwordEncriptado.get("contraseña").getAsString());
        usuario.setSalt(passwordEncriptado.get("salt").getAsString());

        System.out.println(usuario.toString());


        String mensaje = daoUsuario.crearUsuario(usuario);
        if (mensaje.equals("Se ha creado")){
            String token = Token.generateJwt(usuario);
            res.header("jwt", token);
        }
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("mensaje",mensaje);
        return jsonObject;
    }

    public static JsonObject iniciarSesion1(Request req, Response res){
        Usuario usuario = gson.fromJson(req.body(), Usuario.class);
        Usuario usuarioBD = daoUsuario.obtenerUsuario(usuario.getUsuario());
        String pass = encriptar.validarPassword(usuario.getContrasena(), Base64.getDecoder().decode(usuarioBD.getSalt()));
        JsonObject jsonObject = new JsonObject();
        if (usuarioBD!=null && usuarioBD.getContrasena().equals(pass)){
            String token = Token.generateJwt(usuarioBD);
            jsonObject.addProperty("token", token);
            jsonObject.addProperty("mensaje", "Sesión iniciada");
        }else{
            jsonObject.addProperty("mensaje", "Usuario o contraseña incorrectos");
        }
        return jsonObject;
    }

    //Método para iniciar sesión sin contar con un token
    public static JsonObject iniciarSesion(Request req, Response res){
        Usuario usuario = gson.fromJson(req.body(), Usuario.class);
        Usuario usuarioBD = daoUsuario.obtenerUsuario(usuario.getUsuario()); //Obtenemos el usuario de la base de datos
        String pass = encriptar.validarPassword(usuario.getContrasena(), Base64.getDecoder().decode(usuarioBD.getSalt())); //Validamos la contraseña
        JsonObject jsonObject = new JsonObject();

        System.out.println(usuarioBD.toString());

        if (usuarioBD.getContrasena().equals(pass)){
                if (map.containsKey(usuarioBD.getId())){ //Si el usuario ya tiene un token, lo eliminamos
                    System.out.println("Ya tiene un token");
                    jsonObject.addProperty("Mensaje", "Sesión iniciada");
                    jsonObject.addProperty("rol", usuarioBD.getRol());
                }else{
                    System.out.println("No tiene un token");
                    Token token = new Token();
                    String jwt = token.generateJwt(usuarioBD);
                    map.put(usuarioBD.getId(), jwt); //Guardamos el token en un mapa (Simulando una base de datos)
                    jsonObject.addProperty("token", jwt);
                    jsonObject.addProperty("Mensaje", "Sesión iniciada");
                    jsonObject.addProperty("rol", usuarioBD.getRol());
                }
        }else{
            jsonObject.addProperty("Mensaje", "Usuario o contraseña incorrectos");
        }

        return jsonObject;
    }


    public static JsonObject validarToken(Request req, Response res){
        String token = req.headers("jwt");
        JsonObject jsonObject = Token.validarToken(token);
        return jsonObject;
    }

    public static JsonObject cambiarPassword(Request req, Response res){
        Usuario usuario = gson.fromJson(req.body(), Usuario.class);

        JsonObject passwordEncriptado = encriptar.encryptPassword(usuario.getContrasena());
        usuario.setContrasena(passwordEncriptado.get("contraseña").getAsString());
        usuario.setSalt(passwordEncriptado.get("salt").getAsString());

        String mensaje = daoUsuario.cambiarPassword(usuario);
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("mensaje",mensaje);
        return jsonObject;
    }

}