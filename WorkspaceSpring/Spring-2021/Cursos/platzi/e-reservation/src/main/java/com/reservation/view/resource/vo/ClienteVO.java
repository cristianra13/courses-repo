package com.reservation.view.resource.vo;

import com.reservation.model.Reserva;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

/**
 * Clase que representa la tabla Cliente
 *
 * @author Cristian Real
 */
@Data
public class ClienteVO {
    private String identificacion;
    private String nombre;
    private String apellido;
    private String direccion;
    private String telefono;
    private String email;
}
