package com.reservation.view.resource;

import com.reservation.business.service.ClienteService;
import com.reservation.model.Cliente;
import com.reservation.view.resource.vo.ClienteVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Clase que representa servicio web  del cliente
 */
@RestController
@RequestMapping("/api/cliente")
// Documentación del APi para Swagger
@Api(tags = "cliente")
public class ClienteResource {
    private final ClienteService clienteService;


    public ClienteResource(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping
    // representa la documentación de una operacióin
    @ApiOperation(value = "Crear Cliente", notes = "Servicio para crear un nuevo cliente")
    // documentamos las respuestas
    @ApiResponses(value = {@ApiResponse(code = 201, message = "Cliente creado correctamente"),
                            @ApiResponse(code = 400, message = "Solicitud ivalida")})
    public ResponseEntity<Cliente> crearCliente(@RequestBody ClienteVO clienteVo) {
        Cliente cliente = new Cliente();
        cliente.setNombre(clienteVo.getNombre());
        cliente.setApellido(cliente.getApellido());
        cliente.setDireccion(clienteVo.getDireccion());
        cliente.setTelefono(clienteVo.getTelefono());
        cliente.setEmail(clienteVo.getEmail());

        return new ResponseEntity<>(clienteService.guardarCliente(cliente), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    // representa la documentación de una operacióin
    @ApiOperation(value = "Actualizar Cliente", notes = "Servicio para actualizar un cliente")
    // documentamos las respuestas
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Cliente actualizado correctamente"),
            @ApiResponse(code = 404, message = "Cliente no ecnontrado")})
    public ResponseEntity<Cliente> actualizarCliente(@PathVariable String id, ClienteVO clienteVo) {
        Cliente cliente = clienteService.findByIdentificacion(id);
        if (cliente == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        cliente.setNombre(clienteVo.getNombre());
        cliente.setApellido(cliente.getApellido());
        cliente.setDireccion(clienteVo.getDireccion());
        cliente.setTelefono(clienteVo.getTelefono());
        cliente.setEmail(clienteVo.getEmail());

        return new ResponseEntity<>(clienteService.actualizarCliente(cliente), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    // representa la documentación de una operacióin
    @ApiOperation(value = "Eliminar Cliente", notes = "Servicio para eliminar un cliente")
    // documentamos las respuestas
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Cliente eliminado correctamente"),
            @ApiResponse(code = 404, message = "Cliente no ecnontrado")})
    public ResponseEntity<?> eliminarCliente(@PathVariable String id) {
        Cliente cliente = clienteService.findByIdentificacion(id);
        if (cliente == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        clienteService.eliminarCliente(cliente);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    // representa la documentación de una operacióin
    @ApiOperation(value = "Buscar Clientes", notes = "Servicio para listar todos los clientes")
    // documentamos las respuestas
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Clientes encontrados"),
            @ApiResponse(code = 404, message = "No se encontro algún cliente")})
    public ResponseEntity<?> buscarClientes() {
        List<Cliente> allClientes = clienteService.findAll();

        return new ResponseEntity<>(allClientes, HttpStatus.OK);
    }
}
