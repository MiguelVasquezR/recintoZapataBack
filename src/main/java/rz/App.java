package rz;

import static spark.Spark.*;

import com.google.gson.Gson;

import com.google.gson.JsonObject;
import rz.Controlador.*;
import rz.Seguridad.Token.Token;
import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class App 
{
    private static Gson gson = new Gson();


    public static void main( String[] args )
    {

        port(getHerokuAssignedPort());

        options("/*", (request, response) -> {
            String accessControlRequestHeaders = request.headers("Access-Control-Request-Headers");
            if (accessControlRequestHeaders != null) {
                response.header("Access-Control-Allow-Headers", accessControlRequestHeaders);
            }
            String accessControlRequestMethod = request.headers("Access-Control-Request-Method");
            if (accessControlRequestMethod != null) {
                response.header("Access-Control-Allow-Methods", accessControlRequestMethod);
            }
            return "OK";
        });
        before((request, response) -> response.header("Access-Control-Allow-Origin", "*"));

        path("/inventario", () -> {
            get("/items", ControladorInventario::obtenerItems);
            get("/item", ControladorInventario::obtenerItem);
            post("/crear", ControladorInventario::CrearItemInventario);
            put("/nuevoCantidad", ControladorInventario::actualizarCantidadActual);
            put("/actualizar", ControladorInventario::actualizarItem);
            delete("/eliminar", ControladorInventario::eliminarItem);

        });

        path("/usuario", () -> {
            post("/crear", ControladorUsuario::crearUsuario);
            post("/iniciarSesion1", ControladorUsuario::iniciarSesion);
            put("/cambiarPassword", ControladorUsuario::cambiarPassword);


            post("/iniciarSesion", ControladorUsuario::iniciarSesion);

        });

        path("/persona", () -> {
            post("/crear", ControladorPersona::crearPersona);
            put("/actualizar", ControladorPersona::actualizarPersona);
            delete("/eliminar", ControladorPersona::eliminarPersona);
            get("/obtener", ControladorPersona::obtenerPersonas);
        });

        path("/direccion", () -> {
            post("/crear", ControladorDireccion::crearDireccion);
        });

        path("/evento", () -> {
            post("/crear", ControladorEvento::crearEvento);
            get("/obtener", ControladorEvento::obtenerEventos);
            get("/obtenerEventoID", ControladorEvento::obtenerEventoID);
            put("/actualizar", ControladorEvento::actualizarEvento);
            delete("/eliminar", ControladorEvento::eliminarEvento);

            get("/obtenerEventoIDToken", ControladorEvento::obtenerEventoIDToken);
            put("/actualizarEventoCliente", ControladorEvento::actualizarEventoCliente);
            get("/verificarFecha", ControladorEvento::verificarFechaEvento);


            put("/cancelar-evento", ControladorCancelacion::cancelarEvento);


        });

        path("/documentos", () -> {
            post("/crear", ControladorDocumentos::crearDocumentacion);
            post("/crear-comprobante", ControladorDocumentos::crearComprobante);
            get("/verificar-existencia-documentacion", ControladorDocumentos::verificarExistenciaDocumentacion);
            get("/verificar-existencia-comprobante", ControladorDocumentos::verificarComprobante);
            get("/obtener-documentacion", ControladorDocumentos::obtenerDocumentacion);
            get("/obtener-comprobante", ControladorDocumentos::obtenerComprobante);

            put("/modificarDocumentacion", ControladorDocumentos::modificarDocumentacion);
            delete("/eliminar-documentacion", ControladorDocumentos::eliminarComprobante);
        });

        path("/notificacion", () -> {
            post("/crear", ControladorNotificacion::crearNotificacion);
            post("/actualizar-estatus", ControladorNotificacion::actualizarEstatusNotificacion);
            get("/listar", ControladorNotificacion::listarNotificaciones);

        });

        path("/informacion-anterior", () -> {
            post("/crear", ControladorInformacionAnterior::crearInformacionAnterior);
            get("/listar", ControladorInformacionAnterior::listarInformacionAnterior);
            delete("/eliminar", ControladorInformacionAnterior::eliminarInformacionAnterior);
        });


        path("/documentos", () -> {
            get("/obtener-documentos", ControladorDocumentos::obtenerDocumentos);
        });

        path("/confirmacionDoc", () -> {
            put("/editarCampo", ControladorAprobacion::editarCampo);
            get("/obtener-validez", ControladorAprobacion::obtenerValides);
        });

        path("/multa", () -> {
            post("/crear", ControladorMulta::crearMulta);
            delete("/eliminar", ControladorMulta::eliminarMulta);
        });

        path("/hora-extra", () -> {
            post("/crear", ControladorHoraExtra::agregarHoraExtra);
        });

        path("/administracion", () -> {
            post("/crear", ControladorAdministracion::crearConcepto);
            get("/reporte", ControladorAdministracion::generarReporte);
        });

        path("/recuerdos", () -> {
            post("/crear", ControladorRecuerdos::crearRecuerdo);
            get("/listar", ControladorRecuerdos::listarRecuerdos);
        });

        get("/descargarArchivos", (request, response) -> {
            LlenarPDF llenarPDF = new LlenarPDF();
            String token = request.queryParams("token");
            JsonObject tokenData = Token.validarToken(token);
            String id = tokenData.get("ID").getAsString();


            String nombreArchivo = llenarPDF.llenarContrato(id);
            System.out.println(nombreArchivo);

            String filePath1 = "src/Documentos/" + nombreArchivo;
            String fileName2 = "Reglamento.pdf";
            String filePath2 = "src/Documentos/" + fileName2;

            File file1 = new File(filePath1);
            File file2 = new File(filePath2);

            if (file1.exists() && file1.isFile() && file2.exists() && file2.isFile()) {
                response.type("application/zip");
                response.header("Content-Disposition", "attachment; filename=\"archivos.zip\"");

                try (ZipOutputStream zipOut = new ZipOutputStream(response.raw().getOutputStream())) {
                    addToZipFile(nombreArchivo, filePath1, zipOut);
                    addToZipFile(fileName2, filePath2, zipOut);
                } catch (IOException e) {
                    e.printStackTrace();
                    response.status(500);
                    return "Error al comprimir los archivos.";
                }
            } else {
                response.status(404);
                return "Al menos uno de los archivos no encontrado.";
            }

            file1.delete();
            return response.raw();
        });

        get("/comprobante", (request, response) -> {
            LlenarPDF llenarPDF = new LlenarPDF();
            String token = request.queryParams("token");
            JsonObject tokenData = Token.validarToken(token);
            String id = tokenData.get("ID").getAsString();

            String nombreArchivo = llenarPDF.llenarComprobante(id);
            File file = new File("src/Documentos/" + nombreArchivo);
            response.type("application/pdf");
            response.header("Content-Disposition", "attachment; filename=\"" + nombreArchivo + "\"");
            try (FileInputStream fis = new FileInputStream(file); OutputStream os = response.raw().getOutputStream()) {
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = fis.read(buffer)) != -1) {
                    os.write(buffer, 0, bytesRead);
                }
                os.flush();
            } catch (Exception e) {
                e.printStackTrace();
                response.status(500);
                return "Error al procesar el archivo.";
            }
            file.delete();
            return response.raw();
        });

        get("/validarToken", (request, response) -> {
            String token = request.queryParams("token");
            JsonObject tokenData = Token.validarToken(token);
            return tokenData.get("Rol").getAsString();
        });

    }
    private static void addToZipFile(String fileName, String filePath, ZipOutputStream zipOut) throws IOException {
        File fileToZip = new File(filePath);
        try (FileInputStream fis = new FileInputStream(fileToZip)) {
            ZipEntry zipEntry = new ZipEntry(fileName);
            zipOut.putNextEntry(zipEntry);
            byte[] bytes = new byte[1024];
            int length;
            while ((length = fis.read(bytes)) >= 0) {
                zipOut.write(bytes, 0, length);
            }
            zipOut.closeEntry();
        }
    }

    static int getHerokuAssignedPort() {        
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567;
    }

}
