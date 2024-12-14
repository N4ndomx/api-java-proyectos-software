package com.uv.psw.email;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String emailUsername;

    @Value("${spring.mail.password}")
    private String emailPassword;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    /**
     * Enviar un correo genérico.
     *
     * @param destinatario Dirección de correo del destinatario.
     * @param asunto       Asunto del correo.
     * @param mensaje      Cuerpo del correo.
     */
    public void enviarCorreo(String destinatario, String asunto, String mensaje) {
        try {
            SimpleMailMessage email = new SimpleMailMessage();
            email.setFrom(emailUsername); // Utiliza el email configurado como remitente
            email.setTo(destinatario);
            email.setSubject(asunto);
            email.setText(mensaje);
            mailSender.send(email);
            System.out.println("Correo enviado exitosamente a: " + destinatario);
        } catch (Exception e) {
            System.err.println("Error al enviar el correo: " + e.getMessage());
            throw new RuntimeException("No se pudo enviar el correo.");
        }
    }

    /**
     * Enviar un correo de verificación a un empleado.
     *
     * @param destinatario Dirección de correo del empleado.
     * @param nombre       Nombre del empleado.
     * @param codigo       Código de verificación.
     */
    public void enviarCorreoVerificacion(String destinatario, String nombre, String codigo) {
        String asunto = "Verificación de correo electrónico";
        String mensaje = String.format(
                "Hola %s,\n\nPor favor verifica tu cuenta usando el siguiente código: %s\n\nGracias.",
                nombre, codigo
        );
        enviarCorreo(destinatario, asunto, mensaje);
    }
}
