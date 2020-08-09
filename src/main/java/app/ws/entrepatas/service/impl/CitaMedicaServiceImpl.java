package app.ws.entrepatas.service.impl;

import app.ws.entrepatas.enums.EstadoClinico;
import app.ws.entrepatas.enums.EstadoPublicacion;
import app.ws.entrepatas.jdbc.CitaMedicaNativeRepository;
import app.ws.entrepatas.model.CitaMedicaEntity;
import app.ws.entrepatas.model.PublicacionEntity;
import app.ws.entrepatas.repository.CitaMedicaRepository;
import app.ws.entrepatas.repository.PublicacionRepository;
import app.ws.entrepatas.repository.TratamientoMedicoRepository;
import app.ws.entrepatas.service.CitaMedicaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
@Slf4j
public class CitaMedicaServiceImpl implements CitaMedicaService {

    @Autowired
    CitaMedicaRepository citaMedicaRepository;

    @Autowired
    TratamientoMedicoRepository tratamientoMedicoRepository;

    @Autowired
    CitaMedicaNativeRepository citaMedicaNativeRepository;

    @Autowired
    PublicacionRepository publicacionRepository;



    @Override
    public CitaMedicaEntity create(CitaMedicaEntity model) {

        try{
            model.setFechaCreacion(LocalDateTime.now());
            model.setEliminado(Boolean.FALSE);

            Integer ultimaCita = getCitaUltimaByIdAnimal(model.getAnimal().getId());
            Integer nuevaCita = ultimaCita +1;
            model.setNumero(String.format("%08d", nuevaCita));

            model.getListaTratamiento().forEach(item->{
                item.setCitaMedica(model);
            });

            if (model.getEstadoClinico().equals(EstadoClinico.ALTA)){
                PublicacionEntity modelExist=  publicacionRepository.findByAnimal_Id(model.getAnimal().getId());
                modelExist.setFechaModificacion(LocalDateTime.now());
                modelExist.setEstadoPublicacion(EstadoPublicacion.APROBADO);
                publicacionRepository.save(modelExist);

            }

        }catch (Exception e){
            log.error("Error al crear cita: ", e.getMessage());
            e.printStackTrace();
        }


        return citaMedicaRepository.save(model);

    }

    @Override
    public List<CitaMedicaEntity> findAll() {
        return null;
    }

    @Override
    public Integer getCitaUltimaByIdAnimal(Long id) {
        List<String> lista =citaMedicaNativeRepository.getUltimaCitaMedica(id);
        Integer numero=0;
        if (lista.size()>0){
            String valor = lista.get(0);
            if (valor!=null){
                numero= Integer.parseInt(valor);
            }

        }
        return numero;
    }
}
