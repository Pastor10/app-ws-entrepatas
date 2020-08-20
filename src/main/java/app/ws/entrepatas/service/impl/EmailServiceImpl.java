package app.ws.entrepatas.service.impl;

import app.ws.entrepatas.model.AdopcionEntity;
import app.ws.entrepatas.model.PostulanteEntity;
import app.ws.entrepatas.model.UsuarioEntity;
import app.ws.entrepatas.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.util.HashMap;

@Service
public class EmailServiceImpl implements EmailService {


    @Value("${app.url.send-email}")
    private String urlSendEmail;

    @Value("${email.from}")
    private String fromEmail;

    @Value("${email.name}")
    private String nameEmail;

    @Value("${email.templates.templateActiveAccount.subject}")
    private String subjectEmail;

    @Value("${email.templates.templatePostulante.subject}")
    private String subjectEmailPostulante;

    @Autowired
    private FreemakerTemplateService freemakerTemplateService;

    @Autowired
    private JavaMailSender emailSender;


    @Override
    public Boolean sendEmailActiveAccount(UsuarioEntity user) {

        try {

            MimeMessagePreparator preparator = new MimeMessagePreparator() {
                public void prepare(MimeMessage mimeMessage) throws Exception {
                    MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

                    String textMensajeHtml = freemakerTemplateService.buildTemplatePreset(
                            new HashMap<String, Object>() {{
                                put("usuario", user.getPersona().getNombreCompleto());
                                put("password", user.getPersona().getNumeroDocumento());
                                put("username", user.getUsername());
                                put("url", urlSendEmail.concat(user.getUuid()));
                            }}, "templateMailActiveAccount.ftlh"
                    );
                    helper.setSubject(subjectEmail);
                    helper.setFrom(fromEmail);
                    helper.setTo(user.getPersona().getCorreo());
                    helper.setText(textMensajeHtml, true);

                }
            };

            emailSender.send(preparator);

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    @Override
    public Boolean sendEmailPostulante(PostulanteEntity model) {
        try {

            MimeMessagePreparator preparator = new MimeMessagePreparator() {
                public void prepare(MimeMessage mimeMessage) throws Exception {
                    MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

                    String textMensajeHtml = freemakerTemplateService.buildTemplatePreset(
                            new HashMap<String, Object>() {{
                                put("nombres", model.getPersona().getNombreCompleto());
                                put("mascota", model.getPublicacion().getAnimal().getNombre());
                                put("raza", model.getPublicacion().getAnimal().getRaza().getNombre());
                                put("sexo", model.getPublicacion().getAnimal().getSexo());
                                put("edad", model.getPublicacion().getAnimal().getEdad());
                                put("tamano", model.getPublicacion().getAnimal().getTamanoAnimal().getNombre());
                                put("foto", model.getPublicacion().getAnimal().getFoto());
                            }}, "templateMailPostulante.ftlh"
                    );
                    helper.setSubject(subjectEmailPostulante);
                    helper.setFrom(fromEmail);
                    helper.setTo(model.getPersona().getCorreo());
                    helper.setText(textMensajeHtml, true);

                }
            };

            emailSender.send(preparator);

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean sendEmailAdopcion(AdopcionEntity model) {
        try {

            MimeMessagePreparator preparator = new MimeMessagePreparator() {
                public void prepare(MimeMessage mimeMessage) throws Exception {
                    MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

                    String textMensajeHtml = freemakerTemplateService.buildTemplatePreset(
                            new HashMap<String, Object>() {{
                                put("nombres", model.getPersona().getNombreCompleto());
                                put("mascota", model.getAnimal().getNombre());
                                put("raza", model.getAnimal().getRaza().getNombre());
                                put("sexo", model.getAnimal().getSexo());
                                put("edad", model.getAnimal().getEdad());
                                put("tamano", model.getAnimal().getTamanoAnimal().getNombre());
                                put("foto", model.getAnimal().getFoto());
                            }}, "templateMailAdopcion.ftlh"
                    );
                    helper.setSubject(subjectEmailPostulante);
                    helper.setFrom(fromEmail);
                    helper.setTo(model.getPersona().getCorreo());
                    helper.setText(textMensajeHtml, true);

                }
            };

            emailSender.send(preparator);

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
