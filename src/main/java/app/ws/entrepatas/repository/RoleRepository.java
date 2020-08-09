package app.ws.entrepatas.repository;

import app.ws.entrepatas.model.RoleEntity;
import app.ws.entrepatas.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    List<RoleEntity> findByNameIn(List<RoleName> roles);
}
