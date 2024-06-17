package rz.Modelo;

public class CancelacionEvento {

    private String id;
    private String motivo;
    private String id_evento;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getId_evento() {
        return id_evento;
    }

    public void setId_evento(String id_evento) {
        this.id_evento = id_evento;
    }

    @Override
    public String toString() {
        return "CancelacionEvento{" +
                "id='" + id + '\'' +
                ", motivo='" + motivo + '\'' +
                ", id_evento='" + id_evento + '\'' +
                '}';
    }
}
