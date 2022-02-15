package ar.com.mercadolibre;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import javax.ws.rs.QueryParam;
import javax.ws.rs.GET;
import javax.ws.rs.core.MediaType;

import ar.com.mercadolibre.AdnPOJO;
import ar.com.mercadolibre.DetectorAdn;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author palucero
 */
@Path("service")
@Produces({ "application/json" })
public class ServicioMuntate {

	private final Logger logger = Logger.getLogger(ServicioMuntate.class.getName());

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("mutant")
	public Response ProcesarAdn(AdnPOJO dna) {

		try {
			DetectorAdn nuevoElemento = new DetectorAdn();

			if (nuevoElemento.IsMutant(dna)) {
				return Response.ok().build();
			} else {
				return Response.status(403).type("text/plain").entity("Forbidden").build();
			}
		} catch (Exception ex) {
			logger.log(Level.SEVERE, "Excepcion lanzada:", ex.getMessage());
			return Response.status(403).type("text/plain").entity("Forbidden").build();
		}

	}

	@GET
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("ArrayTest")
	public AdnPOJO ObtenerMatrizPrueba(@QueryParam("dimension") int dimension) {

		AdnPOJO dna = new AdnPOJO();
		dna.setDna(GeneradorAleatorio.ListaMatrizAleatoria(dimension));
		return dna;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("TestMutant")
	public Response TestMutantApi(@QueryParam("dimension") int dimension) {

		ar.com.mercadolibre.AdnPOJO dna = new ar.com.mercadolibre.AdnPOJO();

		dna.setDna(GeneradorAleatorio.ListaMatrizAleatoria(dimension));

		ar.com.mercadolibre.DetectorAdn nuevoElemento = new ar.com.mercadolibre.DetectorAdn();

		if (nuevoElemento.IsMutant(dna)) {
			return Response.ok().build();
		} else {
			return Response.status(403).type("text/plain").entity("Forbidden").build();
		}

	}

	@POST
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("stats")
	public StatsPOJO Stats() {
		try {

			RegistroMutante nuevoRegistro = new RegistroMutante();
			nuevoRegistro.Estadisticas();
			StatsPOJO estadisticas = new StatsPOJO();
			estadisticas.setCount_mutant_dna(nuevoRegistro.getCantidadMutante());
			estadisticas.setCount_humnan_dna(nuevoRegistro.getCantidadHumano());
			estadisticas.setRatio(nuevoRegistro.getIndice());
			return estadisticas;
		} catch (Exception ex) {
			logger.log(Level.SEVERE, "Excepcion lanzada:", ex.getMessage());
			return new StatsPOJO();
		}

	}

}
