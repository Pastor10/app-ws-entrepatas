package app.ws.entrepatas.service.impl;

import app.ws.entrepatas.exception.NoExistEntityException;
import app.ws.entrepatas.model.PerfilEntity;
import app.ws.entrepatas.model.RoleEntity;
import app.ws.entrepatas.model.RoleName;
import app.ws.entrepatas.repository.PerfilRepository;
import app.ws.entrepatas.repository.RoleRepository;
import app.ws.entrepatas.service.PerfilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PerfilServiceImpl implements PerfilService {

    @Autowired
    private PerfilRepository repository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    @Transactional
    public PerfilEntity create(PerfilEntity o) {
        o.setId(null);
        List<RoleEntity> roleTempList = o.getRoles();
        List<RoleName> names = new ArrayList<>();
        for (RoleEntity role : roleTempList) {
            names.add(role.getName());
        }
        List<RoleEntity> roleList = roleRepository.findByNameIn(names);
        o.setRoles(roleList);
        return repository.save(o);
    }

    @Override
    public PerfilEntity update(PerfilEntity o) throws NoExistEntityException {
        if (repository.findById(o.getId()).isPresent()) {
            List<RoleEntity> roleTempList = o.getRoles();
            List<RoleName> names = new ArrayList<>();
            for (RoleEntity role : roleTempList) {
                names.add(role.getName());
            }
            List<RoleEntity> roleList = roleRepository.findByNameIn(names);
            o.setRoles(roleList);
            return repository.save(o);
        } else {
            throw new NoExistEntityException("No existe");
        }
    }

    @Override
    public void delete(Long id) throws NoExistEntityException {
        Optional<PerfilEntity> o = repository.findById(id);
        if (o.isPresent()) {
            repository.delete(o.get());
        } else {
            throw new NoExistEntityException("No existe");
        }
    }

    @Override
    public Optional<PerfilEntity> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<PerfilEntity> findAll() {
        return repository.findAll();
    }

    @Override
    public List<PerfilEntity> findByNombreIsContainingIgnoreCase(String nombre) {
        System.out.println("nombre: " + nombre);
       // return repository.findByNombreIsContainingIgnoreCase(nombre);
        return null;
    }
}
