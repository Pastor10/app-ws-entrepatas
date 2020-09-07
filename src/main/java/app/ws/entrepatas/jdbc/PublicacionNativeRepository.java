package app.ws.entrepatas.jdbc;

import app.ws.entrepatas.dto.PublicacionDto;
import app.ws.entrepatas.dto.response.PublicacionResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@Repository
public class PublicacionNativeRepository {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;


    public List<PublicacionResponseDto> findAllByCondicion(Long idCondicion) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("idCondicion", idCondicion);

        String sql= "SELECT count(p.id) as cantidad, cast(p.fecha_creacion as date) as fecha  FROM publicacion p " +
                " where p.id_condicion=:idCondicion " +
                " group by MONTH(p.fecha_creacion) ";

        return jdbcTemplate.query(
                sql, parameters, (rs, rowNum) -> PublicacionResponseDto.builder()
                        .cantidad(rs.getInt("cantidad"))
                        .fecha(rs.getDate("fecha"))
                        .build());
    }
}
