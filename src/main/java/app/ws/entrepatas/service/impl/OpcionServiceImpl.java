package app.ws.entrepatas.service.impl;

import app.ws.entrepatas.enums.ErrorCode;
import app.ws.entrepatas.exception.ServiceException;
import app.ws.entrepatas.model.OpcionEntity;
import app.ws.entrepatas.repository.OpcionRepository;
import app.ws.entrepatas.service.OpcionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OpcionServiceImpl implements OpcionService {

    @Autowired
    OpcionRepository opcionRepository;

    @Override
    public OpcionEntity findById(Long id) {
        return opcionRepository.findById(id).orElseThrow(()-> new ServiceException(ErrorCode.V002));
    }
}
