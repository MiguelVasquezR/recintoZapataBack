package rz.Modelo;

public class Multa {

    private String id;
    private String tipo;
    private String cantidad;
    private String id_evento;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getId_evento() {
        return id_evento;
    }

    public void setId_evento(String id_evento) {
        this.id_evento = id_evento;
    }

    @Override
    public String toString() {
        return "Multa{" +
                "tipo='" + tipo + '\'' +
                ", cantidad='" + cantidad + '\'' +
                ", id_evento='" + id_evento + '\'' +
                '}';
    }
}
