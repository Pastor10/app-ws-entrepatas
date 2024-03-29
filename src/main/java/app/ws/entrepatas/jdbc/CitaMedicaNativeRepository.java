package app.ws.entrepatas.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CitaMedicaNativeRepository {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public List<String> getUltimaCitaMedica(Long idAnimal) {

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("idAnimal", idAnimal);

        String sql= "select numero from cita_medica" +
                " where id_animal =:idAnimal " +
                " order by fecha_creacion desc " +
                " LIMIT 1 ";


        return jdbcTemplate.query(
                sql, parameters, (rs, rowNum) -> rs.getString("numero"));

    }



}
