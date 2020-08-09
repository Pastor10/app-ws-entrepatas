package app.ws.entrepatas.service.impl;

import app.ws.entrepatas.model.UbigeoEntity;
import app.ws.entrepatas.repository.UbigeoRepository;
import app.ws.entrepatas.service.UbigeoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UbigeoServiceImpl implements UbigeoService {

    @Autowired
    UbigeoRepository ubigeoRepository;

    @Override
    public List<UbigeoEntity> searchCity(String filter) {
        List<UbigeoEntity> list = ubigeoRepository.findFirst20ByNombreContainingAndCodProvinciaNotAndCodDistritoNot(filter, "00", "00");
        list.stream().forEach(d -> {
            UbigeoEntity departamento = ubigeoRepository.findByCodDepartamentoAndCodProvinciaAndCodDistrito(d.getCodDepartamento(), "00", "00");
            UbigeoEntity provincia = ubigeoRepository.findByCodDepartamentoAndCodProvinciaAndCodDistrito(d.getCodDepartamento(), d.getCodProvincia(), "00");
            d.setDescripcion(String.format("%s, %s, %s", departamento.getNombre(), provincia.getNombre(), d.getNombre()));
        });
        return list;
    }

    @Override
    public UbigeoEntity getCity(String codDep, String codProv, String codDist) {
        UbigeoEntity departamento = ubigeoRepository.findByCodDepartamentoAndCodProvinciaAndCodDistrito(codDep, "00", "00");
        UbigeoEntity provincia = ubigeoRepository.findByCodDepartamentoAndCodProvinciaAndCodDistrito(codDep, codProv, "00");
        UbigeoEntity distrito = ubigeoRepository.findByCodDepartamentoAndCodProvinciaAndCodDistrito(codDep, codProv, codDist);
        distrito.setDescripcion(String.format("%s, %s, %s", departamento.getNombre(), provincia.getNombre(), distrito.getNombre()));
        return distrito;
    }
}
