package rz;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Email {

    private static String remitente = "documentalletras@gmail.com";
    private static String password = "ebdd txxw qxgt kalt";

    public static void SendEmail(String destinatario){

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(remitente, password);
            }
        });

        try {

            // Crear un objeto MimeMessage
            MimeMessage message = new MimeMessage(session);

            // Configurar el remitente y el destinatario
            message.setFrom(new InternetAddress(remitente));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));

            // Establecer el asunto del correo electrónico


            // Crear el cuerpo del mensaje en formato HTML
            MimeBodyPart htmlPart = new MimeBodyPart();
            String mensajeHTML =
                    "<html>" +
                            "<head>" +
                                "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">" + // Encabezado MIME para indicar que es contenido HTML
                            "</head>" +
                            "<body style='margin: 0; padding: 0; font-family: Arial, sans-serif; width: 100%;'>" +
                                "<table width='100%' bgcolor='#0E4D44' style='color: #fff; text-align: center;'>" +
                                    "<tr>" +
                                        "<td>" +
                                            "<img style=\"width: 100px;\" src=\"cid:imagen\">" +
                                        "</td>" +
                                        "<td>" +
                                            "<h1>Recinto Zapata</h1>" +
                                        "</td>" +
                                    "</tr>" +
                                "</table>" +

                                "<div style='width: 100%; background-color: #fff; text-align: center;'>" +
                                    "<h2>Su evento ha sido registrado</h2>" +
                                    "<p>Fecha del Evento: 12/09/2024</p>" +
                                    "<p style=\"padding-left: 10px; padding-right: 10px; \">Usted puede seguir con el procedimiento sobre su evento.</p>" +
                                    "<p style=\"padding-left: 10px; padding-right: 10px; \">Recuerde subir su documentación firmada</p>" +
                                    "<div style='background-color: #f2f2f2; width: 30%; padding: 10px; margin: 0 auto;'>" +
                                        "<p>Usuario: 123456789</p>" +
                                        "<p>Contraseña: 123456789</p>" +
                                    "</div>" +
                                "</div>" +
                                "<div style='width: 100%; background-color: #fff; margin-top: 40px;  text-align: center;'>" +
                                    "<a style=\"background-color:#0E4D44; padding:10px 5px; color:#fff; text-decoration:none;\" href='http://localhost:8080/'>Iniciar Sesión</a>" +
                                "</div>" +
                            "</body>" +
                    "</html>";

            message.setSubject("Evento Registrado");
            htmlPart.setContent(mensajeHTML, "text/html");

            MimeBodyPart adjuntoPart = new MimeBodyPart();
            adjuntoPart.attachFile("src/main/java/rz/Logo.png");
            adjuntoPart.setContentID("<imagen>");

            MimeBodyPart contro = new MimeBodyPart();
            contro.attachFile("src/Documentos/Contrato27221dbf-9ad6-4083-a067-add364258e28.pdf");

            MimeBodyPart reglamento = new MimeBodyPart();
            reglamento.attachFile("src/Documentos/Reglamento.pdf");

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(htmlPart);
            multipart.addBodyPart(adjuntoPart);
            multipart.addBodyPart(contro);
            multipart.addBodyPart(reglamento);

            MimeBodyPart textoPlanoPart = new MimeBodyPart();
            textoPlanoPart.setText("Este es un mensaje de correo electrónico con formato HTML adjunto.");

            multipart.addBodyPart(textoPlanoPart);
            message.setContent(multipart);

            Transport.send(message);
            System.out.println("Correo electrónico enviado correctamente.");
        } catch (MessagingException e) {
            System.out.println("Error al enviar el correo: " + e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static void registro(String destinatario, String contrasena, String usuario){

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(remitente, password);
            }
        });

        try {

            // Crear un objeto MimeMessage
            MimeMessage message = new MimeMessage(session);

            // Configurar el remitente y el destinatario
            message.setFrom(new InternetAddress(remitente));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));

            // Establecer el asunto del correo electrónico


            // Crear el cuerpo del mensaje en formato HTML
            MimeBodyPart htmlPart = new MimeBodyPart();
            String mensajeHTML = "<html>" +
                    "<head>" +
                    "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">" + // Encabezado MIME para indicar que es contenido HTML
                    "</head>" +
                    "<body style='margin: 0; padding: 0; font-family: Arial, sans-serif; width: 100%;'>" +
                    "<table width='100%' bgcolor='#0E4D44' style='color: #fff; text-align: center;'>" +
                    "<tr>" +
                    "<td>" +
                    "<img style=\"width: 100px;\" src=\"cid:imagen\">" +
                    "</td>" +
                    "<td>" +
                    "<h1>Recinto Zapata</h1>" +
                    "</td>" +
                    "</tr>" +
                    "</table>" +

                    "<div style='width: 100%; background-color: #fff; text-align: center;'>" +
                    "<h2>Registro Exitoso</h2>" +
                    "<p>Gracias por registrarse en Recinto Zapata</p>" +
                    "<p style=\"padding-left: 10px; padding-right: 10px; \">Usted puede seguir con el procedimiento sobre su evento.</p>" +
                    "<p style=\"padding-left: 10px; padding-right: 10px; \">Recuerde subir su documentación firmada</p>" +
                    "<div style='background-color: #f2f2f2; width: 30%; padding: 10px; margin: 0 auto;'>" +
                    "<p>Usuario: "+ usuario +"</p>" +
                    "<p>Contraseña: "+ contrasena +"</p>" +
                    "</div>" +
                    "</div>" +
                    "<div style='width: 100%; background-color: #fff; margin-top: 40px;  text-align: center;'>" +
                    "<a style=\"background-color:#0E4D44; padding:10px 5px; color:#fff; text-decoration:none;\" href='http://localhost:8080/'>Iniciar Sesión</a>" +
                    "</div>" +
                    "</body>" +
                    "</html>";

            message.setSubject("Evento Registrado");
            htmlPart.setContent(mensajeHTML, "text/html");

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(htmlPart);

            message.setContent(multipart);

            Transport.send(message);
            System.out.println("Correo electrónico enviado correctamente.");
        } catch (MessagingException e) {
            System.out.println("Error al enviar el correo: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
