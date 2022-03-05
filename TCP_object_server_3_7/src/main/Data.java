package main;

import modl.Profesor;

import java.io.Serializable;

public class Data implements Serializable {
    int idProfesor;
    Profesor ProfesorObject;


    public Data() {
        super();
    }

    public Data(int idProfesor, Profesor profesorObject) {
        this.idProfesor = idProfesor;
        ProfesorObject = profesorObject;
    }

    public int getIdProfesor() {
        return idProfesor;
    }

    public void setIdProfesor(int idProfesor) {
        this.idProfesor = idProfesor;
    }

    public Profesor getProfesorObject() {
        return ProfesorObject;
    }

    public void setProfesorObject(Profesor profesorObject) {
        ProfesorObject = profesorObject;
    }
}
