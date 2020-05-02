package es.dipujaen.batch.batchejemplo1.models;

public class Persona {

    private String Id;
    private String Nombre;

    

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    @Override
    public String toString() {
        return "Persona [Id=" + Id + ", Nombre=" + Nombre + "]";
    }

    public Persona() {
    }

    public Persona(String id, String nombre) {
        Id = id;
        Nombre = nombre;
    }

    

    
}