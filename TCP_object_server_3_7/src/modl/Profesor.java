package modl;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Profesor implements Serializable {

	int idprofesor; 
	String nombre;
	Asignatura [] asignaturas;
	Especialidad   espe;

	
	public Profesor() {
		// TODO Auto-generated constructor stub
	}


	public Profesor(int idprofesor, String nombre, Asignatura[] asignaturas, Especialidad espe) {
		super();
		this.idprofesor = idprofesor;
		this.nombre = nombre;
		this.asignaturas = asignaturas;
		this.espe = espe;
	}


	public int getIdprofesor() {
		return idprofesor;
	}


	public void setIdprofesor(int idprofesor) {
		this.idprofesor = idprofesor;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public Asignatura[] getAsignaturas() {
		return asignaturas;
	}


	public void setAsignaturas(Asignatura[] asignaturas) {
		this.asignaturas = asignaturas;
	}


	public Especialidad getEspe() {
		return espe;
	}


	public void setEspe(Especialidad espe) {
		this.espe = espe;
	}

}
