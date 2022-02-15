package com.reservation.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * Clase que representa la tabla reserva
 *
 * @author Cristian Real
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "reserva")
public class Reserva {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    private String id;
    @Temporal(TemporalType.DATE)
    private Date fechaIngresoReserva;
    @Temporal(TemporalType.DATE)
    private Date fechaSalidaReserva;
    private int cantidadPersonasRes;
    private String descripcionReserva;
    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;
}
