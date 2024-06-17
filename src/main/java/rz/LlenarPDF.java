package rz;

import com.google.gson.JsonObject;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import rz.DAO.DAOEvento;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class LlenarPDF {

    public String llenarContrato(String id) {
        DAOEvento daoEvento = new DAOEvento();
        JsonObject evento = daoEvento.obtenerInformacionPDF(id);
        String NumContrato = evento.get("numContrato").getAsString();

        java.util.Date date = new java.util.Date();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        String fechaFormateada = format.format(date);


        String NombreArrendatario = evento.get("nombre").getAsString() + " " + evento.get("paterno").getAsString() + " " + evento.get("materno").getAsString();
        String DireccionCliente = evento.get("calle").getAsString() + "#" + evento.get("numero").getAsString() + ",Col." + evento.get("colonia").getAsString() + "," +  evento.get("cp").getAsString() + "," + evento.get("ciudad").getAsString();
        String RFCCliente = "";
        String CorreoCliente = evento.get("email").getAsString();
        String TelefonoCliente = evento.get("telefono").getAsString();
        String CapacidadEvento = evento.get("cantidadPersona").getAsString();
        String TipoEvento = evento.get("tipo").getAsString();

        String fechaCompleto = evento.get("fecha").getAsString();
        String[] fechaPartida = fechaCompleto.split("-");

        String DiaEvento = fechaPartida[2];
        String MesEvento = obtenerMes(fechaPartida[1]);
        String AnoEvento = fechaPartida[0];


        String HRInicio = evento.get("horaInicio").getAsString();

        //pasar a hora y sumar 8 horas
        String HRFin = sumarHoras(HRInicio);

        double precioTotal = evento.get("precio").getAsDouble();

        String PrecioTotal = evento.get("precio").getAsString();
        String PrecioTotalLetra = NumeroEnLetra.convertirNumeroALetra((int) precioTotal);

        String MitadPrecio = String.valueOf(precioTotal / 2);

        

        String MitadPrecioLetra = NumeroEnLetra.convertirNumeroALetra((int) precioTotal);
        String RestantePago = String.valueOf(precioTotal / 2);
        String RestanteLetra = NumeroEnLetra.convertirNumeroALetra((int) precioTotal / 2);


        String DiaFirma = String.valueOf(date.getDay());
        int mesInt = date.getMonth();
        String mes = String.valueOf("0"+ (mesInt + 1));
        String MesFirma = obtenerMes(mes);

        LocalDate fechaActual = LocalDate.now();
        int anoActual = fechaActual.getYear() % 100;
        String AnoFirma = String.valueOf(anoActual);



        try {
            File archivoPDF = new File("src/Documentos/ContratoFormulario.pdf");
            PDDocument documento = PDDocument.load(archivoPDF);

            // Obtener el formulario acro del documento
            PDAcroForm acroForm = documento.getDocumentCatalog().getAcroForm();

            // Verificar que el formulario acro no sea nulo
            if (acroForm != null) {
                // Rellenar los campos del formulario
                acroForm.getField("NumContrato").setValue(NumContrato);
                acroForm.getField("FechaAhora").setValue(fechaFormateada);
                acroForm.getField("NombreArrendatario").setValue(NombreArrendatario);
                acroForm.getField("DireccionCliente").setValue(DireccionCliente);
                acroForm.getField("RFCCliente").setValue(RFCCliente);
                acroForm.getField("CorreoCliente").setValue(CorreoCliente);
                acroForm.getField("TelefonoCliente").setValue(TelefonoCliente);
                acroForm.getField("CapacidadEvento").setValue(CapacidadEvento);
                acroForm.getField("TipoEvento").setValue(TipoEvento);
                acroForm.getField("DiaEvento").setValue(DiaEvento);
                acroForm.getField("MesEvento").setValue(MesEvento);
                acroForm.getField("AnoEvento").setValue(AnoEvento);
                acroForm.getField("HRInicio").setValue(HRInicio);
                acroForm.getField("HRFin").setValue(HRFin);
                acroForm.getField("PrecioTotal").setValue(PrecioTotal);
                acroForm.getField("PrecioTotalLetra").setValue(PrecioTotalLetra.toUpperCase());
                acroForm.getField("MitadPrecioTotal").setValue(MitadPrecio);
                acroForm.getField("MitadPrecioTotalLetra").setValue(RestanteLetra.toUpperCase());
                acroForm.getField("RestantePago").setValue(RestantePago);
                acroForm.getField("RestanteLetra").setValue(RestanteLetra.toUpperCase());
                acroForm.getField("DiaFirma").setValue(DiaFirma);
                acroForm.getField("MesFirma").setValue(MesFirma);
                acroForm.getField("AnoFirma").setValue(AnoFirma);

                acroForm.flatten();

                String nombreArchivoNew = "Contrato" + "-" + evento.get("nombre").getAsString() + "-"+ NumContrato + ".pdf";

                // Guardar los cambios en el documento
                documento.save("src/Documentos/" + nombreArchivoNew);
                documento.close();
                System.out.println("Formulario rellenado y protegido correctamente.");
                return nombreArchivoNew;
            } else {
                System.out.println("El formulario acro es nulo. No se puede continuar.");
                return "";
            }

        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }

    }

    public static String sumarHoras(String horaOriginal) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalTime tiempoOriginal = LocalTime.parse(horaOriginal, formatter);
        LocalTime tiempoSumado = tiempoOriginal.plusHours(8);
        String horaSumada = tiempoSumado.format(formatter);
        return horaSumada;
    }

    private static String obtenerMes(String mes){

        switch (mes){
            case "01":
                return "Enero";
            case "02":
                return "Febrero";
            case "03":
                return "Marzo";
            case "04":
                return "Abril";
            case "05":
                return "Mayo";
            case "06":
                return "Junio";
            case "07":
                return "Julio";
            case "08":
                return "Agosto";
            case "09":
                return "Septiembre";
            case "10":
                return "Octubre";
            case "11":
                return "Noviembre";
            case "12":
                return "Diciembre";
            default:
                return "";
        }

    }


    public String llenarComprobante(String id){
        try{
            DAOEvento daoEvento = new DAOEvento();
            JsonObject evento = daoEvento.obtenerInformacionPDF(id);
            File archivoPDF = new File("src/Documentos/ComprobatePagoEventoRZ.pdf");
            PDDocument documento = PDDocument.load(archivoPDF);
            PDAcroForm acroForm = documento.getDocumentCatalog().getAcroForm();
            Date date = new Date();
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            String fechaFormateada = format.format(date);
            String nombreCliente = evento.get("nombre").getAsString() + " " + evento.get("paterno").getAsString() + " " + evento.get("materno").getAsString();
            if (acroForm != null) {
                acroForm.getField("FechaExpedición").setValue(fechaFormateada);
                acroForm.getField("Razon").setValue("Motivo: Pago de Evento Completado");
                acroForm.getField("NombreCliente").setValue("Cliente: " + nombreCliente);
                acroForm.getField("Cantidad").setValue("Cantidad de Personas: "+evento.get("cantidadPersona").getAsString());
                acroForm.getField("Anticipo").setValue("Precio Total: $" + evento.get("precio").getAsString());
                acroForm.getField("FechaEvento").setValue( "Fecha del Evento: " + evento.get("fecha").getAsString());
                acroForm.getField("HoraEvento").setValue( "Hora de Inicio: " + evento.get("horaInicio").getAsString());
                acroForm.flatten();

                String nombreArchivoNew = "Comprobante_Pago" + "-" + nombreCliente+".pdf";
                documento.save("src/Documentos/" + nombreArchivoNew);
                documento.close();
                System.out.println("Formulario rellenado y protegido correctamente.");
                return nombreArchivoNew;
            } else {
                System.out.println("El formulario acro es nulo. No se puede continuar.");
                return "";
            }
        }catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }



}

class NumeroEnLetra {

    private static final String[] UNIDADES = {"", "uno", "dos", "tres", "cuatro", "cinco", "seis", "siete", "ocho", "nueve"};
    private static final String[] DECENAS = {"", "diez", "veinte", "treinta", "cuarenta", "cincuenta", "sesenta", "setenta", "ochenta", "noventa"};
    private static final String[] DIEZ_A_DIECINUEVE = {"diez", "once", "doce", "trece", "catorce", "quince", "dieciséis", "diecisiete", "dieciocho", "diecinueve"};
    private static final String[] CENTENAS = {"", "ciento", "doscientos", "trescientos", "cuatrocientos", "quinientos", "seiscientos", "setecientos", "ochocientos", "novecientos"};

    public static String convertirNumeroALetra(int numero) {
        if (numero == 0) {
            return "cero";
        } else if (numero < 0) {
            return "menos " + convertirNumeroALetra(-numero);
        } else if (numero < 10) {
            return UNIDADES[numero];
        } else if (numero < 20) {
            return DIEZ_A_DIECINUEVE[numero - 10];
        } else if (numero < 100) {
            return DECENAS[numero / 10] + ((numero % 10 != 0) ? " y " + UNIDADES[numero % 10] : "");
        } else if (numero < 1000) {
            return CENTENAS[numero / 100] + ((numero % 100 != 0) ? " " + convertirNumeroALetra(numero % 100) : "");
        } else if (numero < 1000000) {
            return convertirNumeroALetra(numero / 1000) + " mil" + ((numero % 1000 != 0) ? " " + convertirNumeroALetra(numero % 1000) : "");
        } else if (numero < 1000000000) {
            return convertirNumeroALetra(numero / 1000000) + " millones" + ((numero % 1000000 != 0) ? " " + convertirNumeroALetra(numero % 1000000) : "");
        } else {
            return "Número demasiado grande";
        }
    }

}

