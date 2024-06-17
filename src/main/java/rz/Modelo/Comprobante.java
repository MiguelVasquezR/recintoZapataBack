package rz.Modelo;

public class Comprobante {

    private String id;
    private String linkComprobante;
    private String id_evento;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLinkComprobante() {
        return linkComprobante;
    }

    public void setLinkComprobante(String linkComprobante) {
        this.linkComprobante = linkComprobante;
    }

    public String getId_evento() {
        return id_evento;
    }

    public void setId_evento(String id_evento) {
        this.id_evento = id_evento;
    }

    @Override
    public String toString() {
        return "Comprobante{" +
                "id='" + id + '\'' +
                ", linkComprobante='" + linkComprobante + '\'' +
                ", id_evento='" + id_evento + '\'' +
                '}';
    }
}
