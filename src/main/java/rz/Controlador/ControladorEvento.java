package rz.Controlador;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import rz.DAO.DAODireccion;
import rz.DAO.DAOEvento;
import rz.DAO.DAOPersona;
import rz.DAO.DAOUsuario;
import rz.Email;
import rz.Modelo.Evento;
import rz.Modelo.Persona;
import rz.Modelo.Usuario;
import rz.Seguridad.Token.Token;
import spark.Request;
import spark.Response;

import java.security.SecureRandom;
import rz.Seguridad.Encriptacion.Encriptar;
import java.util.UUID;

public class ControladorEvento {

    private static Gson gson = new Gson();
    private static Encriptar encriptar = new Encriptar();
    private static DAOEvento daoEvento = new DAOEvento();
    private static final String CHAR_LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String CHAR_UPPER = CHAR_LOWER.toUpperCase();
    private static final String NUMBER = "0123456789";
    private static final String OTHER_CHAR = "!@#$%&*()_+-=[]?";
    private static final String PASSWORD_ALLOW_BASE = CHAR_LOWER + CHAR_UPPER + NUMBER + OTHER_CHAR;
    private static SecureRandom random = new SecureRandom();

    public static JsonObject crearEvento(Request request, Response response) {
        Evento evento = gson.fromJson(request.body(), Evento.class);
        evento.setId(UUID.randomUUID().toString());
        evento.setNumContrato(UUID.randomUUID().toString());
        String mensaje = daoEvento.crearEvento(evento);
        JsonObject jsonObject = new JsonObject();

        if (mensaje.equals("Se ha creado")) {
            DAOPersona daoPersona = new DAOPersona();
            Persona persona = daoPersona.obtenerPersona(evento.getIDPersona());

            String nombre = persona.getNombre();
            String paterno = persona.getPaterno();
            String materno = persona.getMaterno();
            String nombreCorto = nombre.substring(0, 2) + paterno.substring(0, 2) + materno;
            String password = generatePassword(12);

            Usuario usuario = new Usuario();
            usuario.setId(UUID.randomUUID().toString());
            usuario.setUsuario(nombreCorto);
            usuario.setContrasena(password);
            usuario.setRol("Cliente");
            usuario.setId_persona(persona.getId());

            JsonObject passwordEncriptado = encriptar.encryptPassword(usuario.getContrasena());
            usuario.setContrasena(passwordEncriptado.get("contraseña").getAsString());
            usuario.setSalt(passwordEncriptado.get("salt").getAsString());

            DAOUsuario daoUsuario = new DAOUsuario();
            daoUsuario.crearUsuario(usuario);

            Email email = new Email();
            email.registro(persona.getEmail(), password, nombreCorto);
            jsonObject.addProperty("mensaje", "Completado");
        }else{
            jsonObject.addProperty("mensaje", "No Completado");
        }

        return jsonObject;
    }

    public static String obtenerEventos(Request request, Response response) {
        return gson.toJson(daoEvento.obtenerEventos());
    }

    public static String generatePassword(int length) {
        if (length < 8) {
            throw new IllegalArgumentException("La longitud mínima de la contraseña debe ser al menos 8 caracteres.");
        }
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(PASSWORD_ALLOW_BASE.length());
            sb.append(PASSWORD_ALLOW_BASE.charAt(randomIndex));
        }
        return sb.toString();
    }


    public static Object obtenerEventoID(Request request, Response response) {
        String id = request.queryParams("id");
        return gson.toJson(daoEvento.obtenerEventoID(id));
    }

    public static Object obtenerEventoIDToken(Request req, Response res){
        String tokenString = req.queryParams("token");
        JsonObject jsonObject = Token.validarToken(tokenString);
        System.out.println(jsonObject.toString());
        String id = jsonObject.get("ID").getAsString();

        System.out.println(id);
        JsonObject data = daoEvento.obtenerEventobyIDPersona(id);
        return gson.toJson(data);
    }

    public static Object eliminarEvento(Request request, Response response) {
        String id = request.queryParams("id");
        return gson.toJson(daoEvento.eliminarEvento(id));
    }


    public static Object actualizarEvento(Request request, Response response) {
        Evento evento = gson.fromJson(request.body(), Evento.class);
        System.out.println(evento.toString());
        return gson.toJson(daoEvento.actualizarEvento(evento));
    }

    public static Object actualizarEventoCliente(Request request, Response response) {
        Evento evento = gson.fromJson(request.body(), Evento.class);
        return gson.toJson(daoEvento.actualizarEventoCliente(evento));
    }

    public static String verificarFechaEvento(Request req, Response res){
        return gson.toJson(daoEvento.verificarFecha());

    }


}