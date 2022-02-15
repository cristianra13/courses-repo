package com.reservation.business.repository;

import com.reservation.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Interface para definir las operaciones con cliente
 */
public interface ClienteRepository extends JpaRepository<Cliente, String> {
    /**
     * Método para buscar clientes por apellido
     *
     * @param apellido
     * @return
     */
    List<Cliente> findByApellido(String apellido);

    Cliente findByIdentificacion(String identificacion);
}
