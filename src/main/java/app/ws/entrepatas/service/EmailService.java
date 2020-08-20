package app.ws.entrepatas.service;


import app.ws.entrepatas.dto.SendEmailDto;
import app.ws.entrepatas.model.AdopcionEntity;
import app.ws.entrepatas.model.PostulanteEntity;
import app.ws.entrepatas.model.PublicacionEntity;
import app.ws.entrepatas.model.UsuarioEntity;

public interface EmailService {
    Boolean sendEmailActiveAccount(UsuarioEntity user);
    Boolean sendEmailPostulante(PostulanteEntity model);
    Boolean sendEmailAdopcion(AdopcionEntity model);
}
