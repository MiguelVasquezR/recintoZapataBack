package rz.Modelo;

public class HoraExtra {

    private String id;
    private String horaExtra;
    private String precio;
    private String id_evento;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHoraExtra() {
        return horaExtra;
    }

    public void setHoraExtra(String horaExtra) {
        this.horaExtra = horaExtra;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getId_evento() {
        return id_evento;
    }

    public void setId_evento(String id_evento) {
        this.id_evento = id_evento;
    }

    @Override
    public String toString() {
        return "HoraExtra{" +
                "id='" + id + '\'' +
                ", cantidad='" + horaExtra + '\'' +
                ", precio='" + precio + '\'' +
                ", id_evento='" + id_evento + '\'' +
                '}';
    }
}
