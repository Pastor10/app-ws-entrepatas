package app.ws.entrepatas.runners;

import app.ws.entrepatas.enums.CondicionAnimal;
import app.ws.entrepatas.enums.EstadoAdopcion;
import app.ws.entrepatas.enums.EstadoCivil;
import app.ws.entrepatas.enums.TipoDocumento;
import app.ws.entrepatas.model.*;
import app.ws.entrepatas.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class LoadData implements CommandLineRunner {

    private RoleRepository roleRepository;
    private UsuarioRepository usuarioRepository;
    private PerfilRepository perfilRepository;
    private PersonaRepository personaRepository;
    private TipoDocumentoRepository tipoDocumentoRepository;
    private EstadoCivilRepository estadoCivilRepository;
    private CondicionRepository condicionRepository;

    @Override
    public void run(String... args) throws Exception {
        try {
            TipoDocumentoEntity dni = new TipoDocumentoEntity();
            dni.setNombre(TipoDocumento.DNI);
            dni.setAbreviatura("DNI");
            dni.setEstado(Boolean.TRUE);
            dni.setNumCaracter(8);

            TipoDocumentoEntity pass = new TipoDocumentoEntity();
            pass.setNombre(TipoDocumento.PASAPORTE);
            pass.setAbreviatura("PASAPORTE");
            pass.setEstado(Boolean.TRUE);
            pass.setNumCaracter(12);

            TipoDocumentoEntity ce = new TipoDocumentoEntity();
            ce.setNombre(TipoDocumento.CARNET_DE_EXTRANJERIA);
            ce.setAbreviatura("CARNET EXT");
            ce.setEstado(Boolean.TRUE);
            ce.setNumCaracter(12);

            List<TipoDocumentoEntity> typeDocs = new ArrayList<>();
            typeDocs.add(dni);
            typeDocs.add(pass);
            typeDocs.add(ce);

            List<TipoDocumentoEntity> tipoDocumentosExist = tipoDocumentoRepository.findAll();
            List<TipoDocumentoEntity> tipoDocsSaves = new ArrayList<>();
            for (TipoDocumentoEntity doc : typeDocs){
                try {
                    TipoDocumentoEntity docsSearch = tipoDocumentosExist.stream()
                            .filter(o -> doc.getNombre().equals(o.getNombre()))
                            .findAny()
                            .orElse(null);
                    if(docsSearch == null) {
                        tipoDocumentoRepository.save(doc);
                        tipoDocsSaves.add(doc);
                    }else{
                        tipoDocsSaves.add(docsSearch);
                    }

                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }

            // agregando condicion animal

            List<CondicionEntity> listCondicion = Arrays.asList(
                    CondicionEntity.builder().nombre(CondicionAnimal.ADOPCION).estado(Boolean.TRUE).fechaCreacion(LocalDateTime.now()).build(),
                    CondicionEntity.builder().nombre(CondicionAnimal.ENCONTRADO).estado(Boolean.TRUE).fechaCreacion(LocalDateTime.now()).build(),
                    CondicionEntity.builder().nombre(CondicionAnimal.PERDIDO).estado(Boolean.TRUE).fechaCreacion(LocalDateTime.now()).build()
            );

            List<CondicionEntity> condicionExist = condicionRepository.findAll();
            List<CondicionEntity> condicionSaves = new ArrayList<>();
            for (CondicionEntity con : listCondicion){
                try {
                    CondicionEntity condSearch = condicionExist.stream()
                            .filter(o -> con.getNombre().equals(o.getNombre()))
                            .findAny()
                            .orElse(null);
                    if(condSearch == null) {
                        condicionRepository.save(con);
                        condicionSaves.add(con);
                    }else{
                        condicionSaves.add(condSearch);
                    }

                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }


            // agregando estado civil
            EstadoCivilEntity soltero = new EstadoCivilEntity();
            soltero.setNombre(EstadoCivil.SOLTERO);
            soltero.setFechaCreacion(LocalDateTime.now());
            soltero.setEstado(Boolean.TRUE);

            EstadoCivilEntity casado = new EstadoCivilEntity();
            casado.setNombre(EstadoCivil.CASADO);
            casado.setFechaCreacion(LocalDateTime.now());
            casado.setEstado(Boolean.TRUE);

            EstadoCivilEntity divorciado = new EstadoCivilEntity();
            divorciado.setNombre(EstadoCivil.DIVORCIADO);
            divorciado.setFechaCreacion(LocalDateTime.now());
            divorciado.setEstado(Boolean.TRUE);

            EstadoCivilEntity viudo = new EstadoCivilEntity();
            viudo.setNombre(EstadoCivil.VIUDO);
            viudo.setFechaCreacion(LocalDateTime.now());
            viudo.setEstado(Boolean.TRUE);

            List<EstadoCivilEntity> estadosCiviles = new ArrayList<>();
            estadosCiviles.add(soltero);
            estadosCiviles.add(casado);
            estadosCiviles.add(divorciado);
            estadosCiviles.add(viudo);

            List<EstadoCivilEntity> estadosCivilExist = estadoCivilRepository.findAll();
            List<EstadoCivilEntity> estadosCivilSaves = new ArrayList<>();
            for (EstadoCivilEntity civil : estadosCiviles){
                try {
                    EstadoCivilEntity civilSearch = estadosCivilExist.stream()
                            .filter(o -> civil.getNombre().equals(o.getNombre()))
                            .findAny()
                            .orElse(null);
                    if(civilSearch == null) {
                        estadoCivilRepository.save(civil);
                        estadosCivilSaves.add(civil);
                    }else{
                        estadosCivilSaves.add(civilSearch);
                    }

                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }



            List<RoleEntity> roles = new ArrayList<>();
            roles.add(new RoleEntity(RoleName.ROLE_USER));
            roles.add(new RoleEntity(RoleName.ROLE_PERFIL));

            //REPORT
            roles.add(new RoleEntity(RoleName.ROLE_REPORTE));
            roles.add(new RoleEntity(RoleName.ROLE_REPORTE_NO_COBERTURADO));

            // PUBLICACIONES

            roles.add(new RoleEntity(RoleName.ROLE_PUBLICACION_REGISTRO));
            roles.add(new RoleEntity(RoleName.ROLE_PUBLICACION_LISTADO));
            roles.add(new RoleEntity(RoleName.ROLE_PUBLICACION_HISTORIAL_CLINICO));
            roles.add(new RoleEntity(RoleName.ROLE_PUBLICACION_APROBACION));

            // ADOPCION
            roles.add(new RoleEntity(RoleName.ROLE_ADOPCION_POSTULANTE_LISTADO));
            roles.add(new RoleEntity(RoleName.ROLE_ADOPCION_GENERAR));
            roles.add(new RoleEntity(RoleName.ROLE_ADOPCION_LISTADO));
            roles.add(new RoleEntity(RoleName.ROLE_ADOPCION_DEVOLUCION));

            // EVENTOS
            roles.add(new RoleEntity(RoleName.ROLE_EVENTO_GENERA));
            roles.add(new RoleEntity(RoleName.ROLE_EVENTO_LISTADO));
            roles.add(new RoleEntity(RoleName.ROLE_EVENTO_TIPO));

            // COLABORA REFUGIO
            roles.add(new RoleEntity(RoleName.ROLE_COLABORA_REFUGIO));

            // LOCAL
            roles.add(new RoleEntity(RoleName.ROLE_LOCAL_CREA));
            roles.add(new RoleEntity(RoleName.ROLE_LOCAL_TIPO));

            // VETERINARIA
            roles.add(new RoleEntity(RoleName.ROLE_VETERINARIA));
            roles.add(new RoleEntity(RoleName.ROLE_VETERINARIO));

            // ANIMAL
            roles.add(new RoleEntity(RoleName.ROLE_ANIMAL_RAZA));
            roles.add(new RoleEntity(RoleName.ROLE_ANIMAL_TAMANO));
            roles.add(new RoleEntity(RoleName.ROLE_ANIMAL_TIPO));



            List<RoleEntity> rolesExistent = roleRepository.findAll();
            List<RoleEntity> rolesSaved = new ArrayList<>();
            for (RoleEntity role : roles){
                try {
                    RoleEntity roleSearch = rolesExistent.stream()
                            .filter(o -> role.getName().equals(o.getName()))
                            .findAny()
                            .orElse(null);
                    if(roleSearch == null) {
                        roleRepository.save(role);
                        rolesSaved.add(role);
                    }else{
                        rolesSaved.add(roleSearch);
                    }

                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }


            try{
                List<PerfilEntity> listaPerfiles = Arrays.asList(
                        PerfilEntity.builder().nombre("ADMIN").activo(Boolean.TRUE).build(),
                        PerfilEntity.builder().nombre("VISITANTE").activo(Boolean.TRUE).build()
                );

                List<PerfilEntity> perfilExistent = perfilRepository.findAll();
                List<PerfilEntity> perfilSaved = new ArrayList<>();
                for (PerfilEntity perfil : listaPerfiles){
                    try {
                        PerfilEntity perfilSearch = perfilExistent.stream()
                                .filter(o -> perfil.getNombre().equals(o.getNombre()))
                                .findAny()
                                .orElse(null);
                        if(perfilSearch == null) {
                            perfilRepository.save(perfil);
                            perfilSaved.add(perfil);
                        }else{
                            perfilSaved.add(perfilSearch);
                        }

                    }catch (Exception ex){
                        ex.printStackTrace();
                    }
                }


                PerfilEntity perfilEntity= perfilRepository.findByNombreIsContainingIgnoreCase("ADMIN");
                if (perfilEntity!=null){
                    perfilEntity.setRoles(rolesExistent);
                    perfilEntity = perfilRepository.save(perfilEntity);
                }
//                else {
//                    perfilEntity = perfilRepository.save(PerfilEntity.builder().activo(true).nombre("admin").roles(rolesExistent).build());
//                }

                Optional<UsuarioEntity> uo = usuarioRepository.findByUsername("admin");
                if (!uo.isPresent()){
                    UsuarioEntity u = new UsuarioEntity();
                    PersonaEntity p = new PersonaEntity();
                    p.setNombre("Luis");
                    p.setApePaterno("Pastor");
                    p.setApeMaterno("Rivadeneyra");
                    p.setNombreCompleto("Luis Pastor Rivadeneyra");
                    p.setCelular("952452260");
                    p.setCorreo("luisyum@gmail.com");
                    p.setDireccion("Av. alameda #230");
                    p.setNumeroDocumento("47583574");
                    p.setFechaCreacion(LocalDateTime.now());
                    p.setEliminado(Boolean.FALSE);
                    p.setFechaCreacion(LocalDateTime.now());
                    p.setTipoDocumento(TipoDocumentoEntity.builder().id(1l).build());

                    personaRepository.save(p);

                    u.setPersona(p);
                    u.setUsername("admin");
                    u.setEliminado(Boolean.FALSE);
                    u.setPassword(new BCryptPasswordEncoder().encode("newton"));
                    u.setPerfil(perfilEntity);
                    u.setFechaCreacion(LocalDateTime.now());
                    u.setEstado(Boolean.TRUE);
                    usuarioRepository.save(u);
                }else {
                    uo.get().setPerfil(perfilEntity);
                    usuarioRepository.save(uo.get());
                }
            }catch (Exception ex){
                ex.printStackTrace();
            }

        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
