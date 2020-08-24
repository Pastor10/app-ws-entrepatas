package app.ws.entrepatas.service;

import app.ws.entrepatas.dto.CuestionarioDto;
import app.ws.entrepatas.model.CuestionarioEntity;
import app.ws.entrepatas.security.UserPrincipal;

import java.util.List;

public interface CuestionarioService {
    CuestionarioEntity create (CuestionarioEntity cuestionario, UserPrincipal user);
    CuestionarioEntity update (CuestionarioEntity cuestionario,  UserPrincipal user);
    CuestionarioEntity findById (Long id);
}
