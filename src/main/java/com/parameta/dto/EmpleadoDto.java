package com.parameta.dto;

import com.parameta.utils.MayorEdad;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmpleadoDto {
    private Integer id;
    @NotEmpty(message = "Los nombres son requeridos.")
    private String nombres;
    @NotEmpty(message = "Los apellidos son requeridos.")
    private String apellidos;
    @NotEmpty(message = "El tipo de documento es requerido.")
    private String tipoDocumento;
    @NotEmpty(message = "El numero de documento es requerido")
    private String numeroDocumento;
    @NotNull(message = "La fecha de nacimiento es requerida.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Past(message = "La fecha de nacimiento debe ser del pasado")
    @MayorEdad
    private Date fechaNacimiento;
    @NotNull(message = "La fecha de vinculacion es requerida.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Past(message = "La fecha de vinculacion debe ser del pasado")
    private Date fechaVinculacion;
    @NotEmpty(message = "El cargo es requerido.")
    private String cargo;
    @NotNull(message = "El salario es requerido.")
    private Double salario;
}
