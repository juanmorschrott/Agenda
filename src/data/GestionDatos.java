/** @proyecto: Desarrollo de una agenda simple, con conexion a base de datos MySQL
 *  @prototipo: Prototipo 1.0.1
 *  @since: Prototipo 1
 *  @fuente: GestionDatos.java - 1.0 - 02/03/2013
 *  @descripcion: Clase para la conexion con la base de datos y metodos para 
 *  obtener e insertar datos
 *  @author: Juan Andres Moreno Schrott
 */

package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

import model.Contacto;

public class GestionDatos {
	private static Connection con = null;
	private static Statement sentenceSQL = null;
	private static ResultSet cdr = null;

	private static GestionDatos gestionDatos;
	
	/**
	 * Constructor
	 */
	private GestionDatos() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connect();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			System.err.println("Ha habido un error en la conexion "
					+ "con la base de datos."
					+ "\nEs posible que no tenga instalada "
					+ "ni creada la base de datos."
					+ "o que los datos sean incorrectos" + "\ndetalles: "
					+ e.getMessage());
		}

	}

	public static GestionDatos accesoDatos() {
		if (gestionDatos == null)
			gestionDatos = new GestionDatos();
		return gestionDatos;
	}

	private Connection connect() throws SQLException {

		String URL_DB = "jdbc:mysql://localhost:3306/agenda";
		String user = "usuario";
		String pass = "usuario";

		con = DriverManager.getConnection(URL_DB, user, pass);

		// Testing the connection
		sentenceSQL = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_UPDATABLE);

		System.err.println("\nConnection succeed");

		return con;

	}

	/**
	 * Metodo para insertar datos en base de datos
	 *
	 */
	public static void insertData(Contacto c) {

		try {

			sentenceSQL
					.executeUpdate("INSERT INTO amigo(nombre, direccion, telefono) VALUES("
							+ "'" + c.getNombre() + "', '"
							+ c.getDireccion() + "', '" 
							+ c.getTelefono() + "');");
			
			System.err.println("Datos insertados correctamente");
		} catch (SQLException e) {
			System.err.println("Error al insertar datos: " + e.getMessage());
		}
	}

	/**
	 * Metodo para leer datos en base de datos
	 * 
	 */
	public static Contacto readData(Contacto c) {

		try {
			cdr = sentenceSQL
					.executeQuery("SELECT * FROM amigo WHERE nombre REGEXP "
							+ "'" + c.getNombre() + "*'" + " LIMIT 1");
			
			while (cdr.next()) {

				
				c.setNombre(cdr.getString("nombre"));
				c.setDireccion(cdr.getString("direccion"));
				c.setTelefono(cdr.getString("telefono"));

				System.err.println(c.getNombre() + "\t" + c.getDireccion() + "\t" + c.getTelefono());

				break;

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return c;
	}
	
	/**
	 * Metodo para borrar entradas a la base de datos
	 * 
	 * @param c
	 */
	public static void deleteData(Contacto c) {
		
		try {
			sentenceSQL.executeUpdate("DELETE FROM amigo WHERE nombre = '" + c.getNombre() + "';");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}	
}
