package com.reservation.business.service;

import com.reservation.business.repository.ClienteRepository;
import com.reservation.model.Cliente;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *
 */
@Service
// todos los métodos que NO esten marcados con @Transactional, serán marcados con modo lectura no más
@Transactional(readOnly = true)
public class ClienteService {
    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    /**
     * Método para guardar un cliente en BD
     *
     * @param cliente
     * @return
     */
    @Transactional
    public Cliente guardarCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    /**
     * Método para actualizar un cliente
     *
     * @param cliente
     * @return
     */
    @Transactional
    public Cliente actualizarCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    /**
     * Método para eliminar un cliente
     *
     * @param cliente
     */
    @Transactional
    public void eliminarCliente(Cliente cliente) {
        clienteRepository.delete(cliente);
    }

    /**
     * Método para buscar por identificación de cliente
     *
     * @param identificacion
     * @return
     */
    public Cliente findByIdentificacion(String identificacion) {
        return clienteRepository.findByIdentificacion(identificacion);
    }

    /**
     * Método encargado de buscar todos los clientes
     *
     * @return
     */
    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }
}
