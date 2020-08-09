package app.ws.entrepatas.service;


import app.ws.entrepatas.dto.SendEmailDto;
import app.ws.entrepatas.model.UsuarioEntity;

public interface EmailService {
    Boolean sendEmailActiveAccount(UsuarioEntity user);
}
