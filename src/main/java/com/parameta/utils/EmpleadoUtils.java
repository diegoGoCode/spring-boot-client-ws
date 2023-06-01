package com.parameta.utils;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

public class EmpleadoUtils {

    public static String calculateTime(Date fechaNacimiento) {
        LocalDate fechaNacimientoLocal = fechaNacimiento.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate fechaActual = LocalDate.now();

        Period periodo = Period.between(fechaNacimientoLocal, fechaActual);

        long anios = periodo.getYears();
        long meses = periodo.getMonths();
        long dias = periodo.getDays();

        return anios + " años, " + meses + " meses y " + dias + " días";
    }
}
