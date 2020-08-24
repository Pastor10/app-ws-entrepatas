package app.ws.entrepatas.util;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.service.spi.ServiceException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

@Slf4j
public class UtilDate {

    public static Date getDateWithoutTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return calendar.getTime();
    }

    public static Date getDateWithoutTime(Date fecha) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return calendar.getTime();
    }

    public static Date addHourToDate(Date date, Integer hour, Integer min, Integer sec) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, hour);
        cal.set(Calendar.MINUTE, min);
        cal.set(Calendar.SECOND, sec);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    public static Date stringToDate(String date, String format) {
        if (date == null) return null;
        try {
            return new SimpleDateFormat(format).parse(date);
        } catch (ParseException e) {
            throw new ServiceException("Error en formato de fecha " + date);
        }
    }

    public static LocalDate stringToLocalDate(String date, String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        if (date == null) return null;
        try {
            return  LocalDate.parse(date, formatter);
        } catch (Exception e) {
            throw new ServiceException("Error en formato de fecha " + date);
        }
    }

    public static Date sumarDiasAFecha(Date fecha, int dias) {
        if (dias == 0) return fecha;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha);
        calendar.add(Calendar.DAY_OF_YEAR, dias);
        return calendar.getTime();
    }

    public static String fechaDia(Date fecha) {
        try {
            return new SimpleDateFormat("dd").format(fecha);
        } catch (Exception e) {
            throw new ServiceException("Error en formato Dia de fecha ");
        }
    }

    public static String fechaMes(Date fecha) {
        try {
            return new SimpleDateFormat("MM").format(fecha);
        } catch (Exception e) {
            throw new ServiceException("Error en formato Mes de fecha ");
        }
    }

    public static String aoDia(Date fecha) {
        try {
            return new SimpleDateFormat("YYYY").format(fecha);
        } catch (Exception e) {
            throw new ServiceException("Error en formato año de fecha ");
        }
    }

    public static String fecha(Date fecha, String format) {
        log.info("Conviertiendo fecha: "+fecha.toString()+" a formato: " + format);
        if (fecha == null) return "-";
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
            Instant instant = new Date(fecha.getTime()).toInstant();
            LocalDateTime ldt = instant
                    .atZone(ZoneId.of("America/Bogota"))
                    .toLocalDateTime();

            return ldt.format(formatter);

        } catch (Exception e) {
            throw new ServiceException("Error en formato de fecha ");
        }
    }


    public static String dateToString(Date fecha, String format) {
        try {
            return new SimpleDateFormat(format).format(fecha);
        } catch (Exception e) {
            throw new ServiceException("Error en formato de fecha ");
        }
    }


}
