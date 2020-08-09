package app.ws.entrepatas.service;

import app.ws.entrepatas.model.CondicionEntity;

import java.util.List;

public interface CondicionService {
    List<CondicionEntity> findAll();
}
