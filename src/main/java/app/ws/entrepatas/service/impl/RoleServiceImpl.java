package app.ws.entrepatas.service.impl;

import app.ws.entrepatas.model.RoleEntity;
import app.ws.entrepatas.repository.RoleRepository;
import app.ws.entrepatas.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository repository;
    @Override
    public List<RoleEntity> findAll() {
        return repository.findAll();
    }
}
