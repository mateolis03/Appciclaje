package unipiloto.edu.co.appiclaje;

public class Solicitud {

    private String address;
    private String nickname;
    private String peso;
    private String tipo;

    public Solicitud(String address, String nickname, String peso, String tipo) {
        this.address = address;
        this.nickname = nickname;
        this.peso = peso;
        this.tipo = tipo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
