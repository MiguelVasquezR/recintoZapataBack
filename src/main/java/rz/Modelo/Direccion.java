package rz.Modelo;

public class
Direccion {

    private String id;
    private String calle;
    private String numero;
    private String colonia;
    private String cp;
    private String ciudad;
    private String IDPersona;

    public Direccion() {
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getColonia() {
        return colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getIDPersona() {
        return IDPersona;
    }

    public void setIDPersona(String IDPersona) {
        this.IDPersona = IDPersona;
    }

    @Override
    public String toString() {
        return "Direccion{" +
                "id='" + id + '\'' +
                ", calle='" + calle + '\'' +
                ", numero='" + numero + '\'' +
                ", colonia='" + colonia + '\'' +
                ", cp='" + cp + '\'' +
                ", ciudad='" + ciudad + '\'' +
                ", IDPersona='" + IDPersona + '\'' +
                '}';
    }
}
