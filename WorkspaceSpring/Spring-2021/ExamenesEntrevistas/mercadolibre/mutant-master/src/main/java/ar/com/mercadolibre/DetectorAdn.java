/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.mercadolibre;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author palucero
 */
public class DetectorAdn {

	private final Logger logger = Logger.getLogger(DetectorAdn.class.getName());

	public void setArrayMatrizCuadrada(char[] array, int posicion) {
		this.MatrizCuadrada[posicion] = array;
	}

	private String[][] Matriz;
	private char[][] MatrizCuadrada;
	 
    

	private boolean ValidarMatriz(String[] data) {
		try {
			for (int i = 0; i < data.length; i++) {

				if (!data[i].matches("[ATCGatcg]*")) {
					logger.log(Level.WARNING, "ERROR: Tiene letras fuera del dominio ATCG");
					return false;
				}

				if (data[i].length() != data.length) {
					logger.log(Level.WARNING, "ERROR: una cadena tiene tamaño distinto al de la matriz");
					return false;
				}

				this.setArrayMatrizCuadrada(data[i].toUpperCase().toCharArray(), i);

			}

			return true;
		} catch (Exception ex) {
			logger.log(Level.SEVERE, "Excepcion lanzada:", ex.getMessage());
			return false;
		}

	}

	@SuppressWarnings("finally")
	public boolean IsMutant(AdnPOJO dna) {

		try {

			String[] data = dna.getDna().toArray(new String[0]);

			MatrizCuadrada = new char[data.length][data.length];

			int longitudMatriz = MatrizCuadrada.length;

			int contadorCadenasEncontradas = 0;

			if (!ValidarMatriz(data)) {
				return false;
			}

			do {
				for (int x = 0; x < longitudMatriz; x++) {
					
					if (contadorCadenasEncontradas < 2) {
						for (int y = 0; y < longitudMatriz; y++) {

							char posicionActual = MatrizCuadrada[x][y];

							// Busqueda Horizontal
							if (contadorCadenasEncontradas < 2) {
								if ((y + 4) <= longitudMatriz) {
									if (BuscaHorizontal(x, y, posicionActual)) {
										contadorCadenasEncontradas++;
 
										logger.log(Level.INFO, "Encontró cadena " + posicionActual + " en Horizontal:" + x + "," + y);
									}
								}
							} else {
								break;
							}

							if (contadorCadenasEncontradas < 2) {
								if ((x + 4) <= longitudMatriz) {
									if (BuscaVertical(x, y, posicionActual)) {
										contadorCadenasEncontradas++;
										 
										
										logger.log(Level.INFO, "Encontró cadena " + posicionActual + " en Vertical:" + x + "," + y);
									}
								}
							} else {
								break;
							}

							if (contadorCadenasEncontradas < 2) {
								if ((y + 4) <= longitudMatriz) {
									if ((x + 4) <= longitudMatriz) {
										if (BuscaDiagonal(x, y, posicionActual)) {
											contadorCadenasEncontradas++;
											
											logger.log(Level.INFO, "Encontró cadena " + posicionActual + " en Diagonal:" + x
													+ "," + y);										
										 
										}
									}
								}
							} else {
								break;
							}
							// Busca por Diagonal Inversa
							if (contadorCadenasEncontradas < 2) {
								if (y >= 3) {
									if ((x + 4) <= longitudMatriz) {
										if (BuscaDiagonalInversa(x, y, posicionActual)) {
											contadorCadenasEncontradas++;
											
											logger.log(Level.INFO, "Encontró cadena " + posicionActual
													+ " en DiagonalInversa:" + x + "," + y);	
											
											 
										}
									}
								}
							} else {
								break;
							}
						}
					} else {
						break;
					}
				}
				break;
			} while (contadorCadenasEncontradas < 2);

			if (contadorCadenasEncontradas == 2) {
				try {

					RegistroMutante grabar = new RegistroMutante();
					grabar.setEsMutante(1);
					grabar.start();

				} catch (Exception ex) {

					logger.log(Level.SEVERE, "Excepcion lanzada:", ex.getMessage());
				} finally {
					return true;
				}

			} else {

				try {

					RegistroMutante grabar = new RegistroMutante();
					grabar.setEsMutante(0);
					grabar.start();

				} catch (Exception ex) {

					logger.log(Level.SEVERE, "Excepcion lanzada:", ex.getMessage());
				} finally {
					return false;
				}
			}
		} catch (Exception ex) {

			logger.log(Level.SEVERE, "Excepcion lanzada:", ex.getMessage());
			return false;
		}

	}

	private boolean BuscaHorizontal(int fila, int posicion, char posicionActual) {

		String cadena = "";
		boolean resultado = false;

		cadena = "" + posicionActual + MatrizCuadrada[fila][posicion + 1] + MatrizCuadrada[fila][posicion + 2]
				+ MatrizCuadrada[fila][posicion + 3];

		switch (cadena) {
		case "AAAA":
			resultado = true;
			break;
		case "TTTT":
			resultado = true;
			break;
		case "CCCC":
			resultado = true;
			break;
		case "GGGG":
			resultado = true;
			break;
		default:
			resultado = false;
			break;

		}

		return resultado;
	}

	private boolean BuscaVertical(int fila, int posicion, char posicionActual) {

		boolean resultado = false;
		String cadena = "";
		cadena = "" + posicionActual + MatrizCuadrada[fila + 1][posicion] + MatrizCuadrada[fila + 2][posicion]
				+ MatrizCuadrada[fila + 3][posicion];

		switch (cadena) {
		case "AAAA":
			resultado = true;
			break;
		case "TTTT":
			resultado = true;
			break;
		case "CCCC":
			resultado = true;
			break;
		case "GGGG":
			resultado = true;
			break;
		default:
			resultado = false;
			break;

		}

		return resultado;
	}

	private boolean BuscaDiagonal(int fila, int posicion, char posicionActual) {
		String cadena = "";
		cadena = "" + posicionActual + MatrizCuadrada[fila + 1][posicion + 1] + MatrizCuadrada[fila + 2][posicion + 2]
				+ MatrizCuadrada[fila + 3][posicion + 3];
		switch (cadena) {
		case "AAAA":
			return true;
		case "TTTT":
			return true;
		case "CCCC":
			return true;
		case "GGGG":
			return true;
		default:
			return false;

		}
	}

	private boolean BuscaDiagonalInversa(int fila, int posicion, char posicionActual) {
		String cadena = "";
		cadena = "" + posicionActual + MatrizCuadrada[fila + 1][posicion - 1] + MatrizCuadrada[fila + 2][posicion - 2]
				+ MatrizCuadrada[fila + 3][posicion - 3];
		switch (cadena) {
		case "AAAA":
			return true;
		case "TTTT":
			return true;
		case "CCCC":
			return true;
		case "GGGG":
			return true;
		default:
			return false;

		}
	}

}
