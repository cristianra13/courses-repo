package com.reservation.model;

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
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cliente")
// otra forma de generar querys
@NamedQuery(name="Cliente.findByIdentificacion", query="select u  from Cliente u where u.identificacion = ?1")
public class Cliente {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    private String id;
    private String identificacion;
    private String nombre;
    private String apellido;
    private String direccion;
    private String telefono;
    private String email;
    @OneToMany(mappedBy = "cliente")
    private Set<Reserva> reservas;
}
