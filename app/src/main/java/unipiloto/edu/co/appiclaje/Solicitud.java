package unipiloto.edu.co.appiclaje;

public class Solicitud {
    private int id;
    private String tipo;
    private String peso;
    private String direccion;

    public Solicitud(String tipo, String peso, String direccion) {
        this.tipo = tipo;
        this.peso = peso;
        this.direccion = direccion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
