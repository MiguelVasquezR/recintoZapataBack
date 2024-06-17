package rz.Modelo;

public class Documentacion {


    private String ID;
    private String linkContrato;
    private String linkReglamente;
    private String linkINE;
    private String id_evento;

    public Documentacion(){}

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getLinkContrato() {
        return linkContrato;
    }

    public void setLinkContrato(String linkContrato) {
        this.linkContrato = linkContrato;
    }

    public String getLinkReglamente() {
        return linkReglamente;
    }

    public void setLinkReglamente(String linkReglamento) {
        this.linkReglamente = linkReglamento;
    }

    public String getLinkINE() {
        return linkINE;
    }

    public void setLinkINE(String linkINE) {
        this.linkINE = linkINE;
    }

    public String getId_evento() {
        return id_evento;
    }

    public void setId_evento(String id_evento) {
        this.id_evento = id_evento;
    }

    @Override
    public String toString() {
        return "Documentacion{" +
                "ID='" + ID + '\'' +
                ", linkContrato='" + linkContrato + '\'' +
                ", linkReglamento='" + linkReglamente + '\'' +
                ", linkINE='" + linkINE + '\'' +
                ", id_evento='" + id_evento + '\'' +
                '}';
    }
}
