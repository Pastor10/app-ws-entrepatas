package app.ws.entrepatas.service.impl;

import app.ws.entrepatas.model.CondicionEntity;
import app.ws.entrepatas.repository.CondicionRepository;
import app.ws.entrepatas.service.CondicionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CondicionServiceImpl implements CondicionService {

    @Autowired
    CondicionRepository condicionRepository;

    @Override
    public List<CondicionEntity> findAll() {
        return condicionRepository.findAll();
    }
}
