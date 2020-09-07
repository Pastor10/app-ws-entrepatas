package app.ws.entrepatas.controller;

import app.ws.entrepatas.dto.response.PublicacionResponseDto;
import app.ws.entrepatas.service.ReporteService;
import app.ws.entrepatas.util.UtilDate;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/reporte")
@CrossOrigin("*")
public class ReporteController {

    @Autowired
    ReporteService reporteService;

    @PreAuthorize("hasAuthority('ROLE_REPORTE')")
    @GetMapping("publicaciones")
    @ApiOperation(value = "reporte de publicaciones de los ultimos 12 meses ")
    public ResponseEntity<?> findAllPublicaciones(@RequestHeader(value="Authorization") String authorization){
        List<PublicacionResponseDto> reportAdoptados = reporteService.findCondicion(1L);
        List<PublicacionResponseDto> reportEncontrados = reporteService.findCondicion(2L);
        List<PublicacionResponseDto> reportPerdidos = reporteService.findCondicion(3L);


        List<LocalDate> ultimosMeses = UtilDate.obtenerUltimos12meses();

        List<String> adoptados = new ArrayList<>();
        List<String> encontrados = new ArrayList<>();
        List<String> perdidos = new ArrayList<>();
        List<String> fechas = new ArrayList<>();
        for (LocalDate date: ultimosMeses) {
            Date dia = UtilDate.localDateConvertToDate(date);
            String fecha = new SimpleDateFormat("MMMM '-' YYYY").format(dia);
            Integer count= 1;
            for (PublicacionResponseDto adop: reportAdoptados) {

                Integer month = adop.getFecha().getMonth()+1;
                if(date.getMonthValue()==month){
                    adoptados.add(String.valueOf(adop.getCantidad()));
                break;
                }else {
                    if (count.equals(reportAdoptados.size())){
                        adoptados.add("0");
                        break;
                    }
                }
                count = count+1;
            }
            fechas.add(fecha);
        }

        for (LocalDate date: ultimosMeses) {
            Integer count= 1;
            for (PublicacionResponseDto enc: reportEncontrados) {

                Integer month = enc.getFecha().getMonth()+1;
                if(date.getMonthValue()==month){
                    encontrados.add(String.valueOf(enc.getCantidad()));
                    break;
                }else {
                    if (count.equals(reportEncontrados.size())){
                        encontrados.add("0");
                        break;
                    }
                }
                count = count+1;
            }

        }

        for (LocalDate date: ultimosMeses) {
            Integer count= 1;
            for (PublicacionResponseDto per: reportPerdidos) {

                Integer month = per.getFecha().getMonth()+1;
                if(date.getMonthValue()==month){
                    perdidos.add(String.valueOf(per.getCantidad()));
                    break;
                }else {
                    if (count.equals(reportPerdidos.size())){
                        perdidos.add("0");
                        break;
                    }
                }
                count = count+1;
            }

        }

        Map<String, List<String>> response = new HashMap<>();
        response.put("adoptados", adoptados);
        response.put("encontrados", encontrados);
        response.put("perdidos", perdidos);
        response.put("fechas", fechas);


        return ResponseEntity.ok().body(response);
    }


}
