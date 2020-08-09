package app.ws.entrepatas.service.impl;

import app.ws.entrepatas.dto.SendEmailDto;
import app.ws.entrepatas.model.UsuarioEntity;
import app.ws.entrepatas.service.EmailService;
import com.sendgrid.SendGrid;
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

    @Value("${email.templates.templateResetPassword.id}")
    private String emailTemplateResetPassword;

    @Value("${email.templates.templateActiveAccount.id}")
    private String emailTemplateActiveAccount;

    @Value("${email.from}")
    private String fromEmail;

    @Value("${email.name}")
    private String nameEmail;

    @Value("${email.templates.templateActiveAccount.subject}")
    private String subjectEmail;

    @Autowired
    private SendGrid sendGrid;

    @Autowired
    private FreemakerTemplateService freemakerTemplateService;

    @Autowired
    private JavaMailSender emailSender;

//    @Override
//    public Boolean sendEmailActiveAccount(SendEmailDto sendEmailDto) {
//        boolean send = false;
//
//        Email email = new Email(fromEmail,nameEmail);
//
//        Mail mail = new Mail();
//        DynamicTemplatePersonalization template = new DynamicTemplatePersonalization();
//
//        template.addTo(new Email(sendEmailDto.getEmail()));
//        //template.addTo(new Email(sendEmailDto.getEmail()));
//        template.addDynamicTemplateData("subject", subjectEmail);
//        template.addDynamicTemplateData("url", sendEmailDto.getUrl());
//        template.addDynamicTemplateData("usuario", sendEmailDto.getUsuario());
//        //enviando email
//        mail.setFrom(email);
//        mail.addPersonalization(template);
//        mail.setTemplateId(emailTemplateActiveAccount);
//
//        Request request = new Request();
//        Response response = null;
//
//        try {
//            request.setMethod(Method.POST);
//            request.setEndpoint("mail/send");
//            request.setBody(mail.build());
//            response= sendGrid.api(request);
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
//        return send;
//    }

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
                                put("username", user.getPersona().getCorreo());
                                put("url", user.getUuid());
                            }}, "templateMailActiveAccount.ftlh"
                    );
                    helper.setSubject(subjectEmail);
                    helper.setFrom(fromEmail);
                    //helper.setTo(user.getPersona().getCorreo());
                    helper.setTo("luisyum@gmail.com");
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
