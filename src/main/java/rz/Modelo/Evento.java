package rz.Modelo;

public class Evento {

    private String id;
    private String tipo;
    private String cantidadPersonas;
    private String color;
    private String precio;
    private String horaInicio ;
    private String fecha;
    private String numContrato;
    private String IDPersona;
    private String estado;

    public Evento(){}

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

    public String getCantidadPersonas() {
        return cantidadPersonas;
    }

    public void setCantidadPersonas(String cantidadPersonas) {
        this.cantidadPersonas = cantidadPersonas;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getNumContrato() {
        return numContrato;
    }

    public void setNumContrato(String numContrato) {
        this.numContrato = numContrato;
    }

    public String getIDPersona() {
        return IDPersona;
    }

    public void setIDPersona(String IDPersona) {
        this.IDPersona = IDPersona;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Evento{" +
                "id='" + id + '\'' +
                ", tipo='" + tipo + '\'' +
                ", cantidadPersonas='" + cantidadPersonas + '\'' +
                ", color='" + color + '\'' +
                ", precio='" + precio + '\'' +
                ", horaInicio='" + horaInicio + '\'' +
                ", fecha='" + fecha + '\'' +
                ", numContrato='" + numContrato + '\'' +
                ", IDPersona='" + IDPersona + '\'' +
                '}';
    }
}
