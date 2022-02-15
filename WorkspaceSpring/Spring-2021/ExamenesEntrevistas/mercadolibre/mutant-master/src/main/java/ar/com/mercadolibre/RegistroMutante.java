package ar.com.mercadolibre;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RegistroMutante extends Thread {
	
	private final Logger logger = Logger.getLogger(RegistroMutante.class.getName());

	private int esMutante;

	private long cantidadMutante;
	private long cantidadHumano;
	private float indice;
	
	public RegistroMutante() {
		cantidadMutante=0;
		cantidadHumano=0;
		indice=0;
	}

	public void run() {
		try {

			String instanceConnectionName = "complete-sector-222611:southamerica-east1:mutantdb";
			String databaseName = "registros";
			String username = "root";
			String password = "root";

			String uriJDBC = "jdbc:mysql://google/" + databaseName + "?cloudSqlInstance=" + instanceConnectionName
					+ "&socketFactory=com.google.cloud.sql.mysql.SocketFactory&user=" + username + "&password="
					+ password + "&useSSL=false";

			Connection connection = null;
			PreparedStatement preparedStatement = null;

			try {
				connection = DriverManager.getConnection(uriJDBC);
				preparedStatement = connection.prepareStatement("INSERT INTO Cantidades (EsMutante) values (?)");
				preparedStatement.setInt(1, esMutante);
				preparedStatement.executeUpdate();

			} catch (SQLException ex) {
				logger.log(Level.SEVERE, "Excepcion lanzada:", ex.getMessage());
			} finally {
				if (preparedStatement != null) {
					preparedStatement.close();
				}

				if (connection != null) {
					connection.close();
				}
			}
		} catch (SQLException ex) {
			logger.log(Level.SEVERE, "Excepcion lanzada:", ex.getMessage());
		} catch (Exception ex) {
			logger.log(Level.SEVERE, "Excepcion lanzada:", ex.getMessage());
		}
	}

	public void Estadisticas() {

		try {

			String instanceConnectionName = "complete-sector-222611:southamerica-east1:mutantdb";
			String databaseName = "registros";
			String username = "root";
			String password = "root";

			String uriJDBC = "jdbc:mysql://google/" + databaseName + "?cloudSqlInstance=" + instanceConnectionName
					+ "&socketFactory=com.google.cloud.sql.mysql.SocketFactory&user=" + username + "&password="
					+ password + "&useSSL=false";

			Connection connection = null;
			PreparedStatement preparedStatement = null;

			try {
				connection = DriverManager.getConnection(uriJDBC);
				preparedStatement = connection.prepareStatement("select sum(EsMutante) as mutantes,count(*) as total from Cantidades;");
				ResultSet resultado = preparedStatement.executeQuery();
				resultado.next();
				cantidadMutante = resultado.getLong("mutantes");
				long total = 0;
				total = resultado.getLong("total");
				cantidadHumano = total - cantidadMutante;
				if (total > 0) {
					indice = ((float)cantidadMutante / total);
				} else {
					indice = 0;
				}

			} catch (SQLException ex) {
				logger.log(Level.SEVERE, "Excepcion lanzada:", ex.getMessage());
			} finally {
				if (preparedStatement != null) {
					preparedStatement.close();
				}

				if (connection != null) {
					connection.close();
				}
			}		
		} catch (Exception ex) {
			logger.log(Level.SEVERE, "Excepcion lanzada:", ex.getMessage());
		}

	}
	

	public void setEsMutante(int esMutante) {
		this.esMutante = esMutante;
	}

	public long getCantidadMutante() {
		return cantidadMutante;
	}

	public long getCantidadHumano() {
		return cantidadHumano;
	}

	public float getIndice() {
		return indice;
	}

}
