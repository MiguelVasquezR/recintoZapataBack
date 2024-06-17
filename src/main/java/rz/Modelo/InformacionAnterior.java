package rz.Modelo;

public class InformacionAnterior {

    private String id;
    private String cantidadPersonas;
    private String fecha;
    private String horaInicio;
    private String estatus;
    private String id_evento;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCantidadPersonas() {
        return cantidadPersonas;
    }

    public void setCantidadPersonas(String cantidadPersonas) {
        this.cantidadPersonas = cantidadPersonas;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getId_evento() {
        return id_evento;
    }

    public void setId_evento(String id_evento) {
        this.id_evento = id_evento;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    @Override
    public String toString() {
        return "InformacionAnterior{" +
                "id='" + id + '\'' +
                ", cantidadPersonas='" + cantidadPersonas + '\'' +
                ", fecha='" + fecha + '\'' +
                ", horaInicio='" + horaInicio + '\'' +
                ", estatus='" + estatus + '\'' +
                ", id_evento='" + id_evento + '\'' +
                '}';
    }
}
