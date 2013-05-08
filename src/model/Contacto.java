package model;

public class Contacto {
	private String nombre;
	private String direccion;
	private String telefono;
	
	public Contacto() {
		this.nombre = " ";
		this.direccion = " ";
		this.telefono = " ";
	}

	public Contacto(String n, String d, String t) {
		this.nombre = n;
		this.direccion = d;
		this.telefono = t;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

}
