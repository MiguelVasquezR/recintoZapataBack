package rz.Modelo;

public class AprobacionDocumentacion {

    private String ID;
    private String estatusContrato;
    private String  estatusReglamento;
    private String estatusINE;
    private String estatusComprobante1;
    private String estatusComprobante2;
    private String id_evento;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getEstatusContrato() {
        return estatusContrato;
    }

    public void setEstatusContrato(String estatusContrato) {
        this.estatusContrato = estatusContrato;
    }

    public String getEstatusReglamento() {
        return estatusReglamento;
    }

    public void setEstatusReglamento(String estatusReglamento) {
        this.estatusReglamento = estatusReglamento;
    }

    public String getEstatusINE() {
        return estatusINE;
    }

    public void setEstatusINE(String estatusINE) {
        this.estatusINE = estatusINE;
    }

    public String getEstatusComprobante1() {
        return estatusComprobante1;
    }

    public void setEstatusComprobante1(String estatusComprobante1) {
        this.estatusComprobante1 = estatusComprobante1;
    }

    public String getEstatusComprobante2() {
        return estatusComprobante2;
    }

    public void setEstatusComprobante2(String estatusComprobante2) {
        this.estatusComprobante2 = estatusComprobante2;
    }

    public String getId_evento() {
        return id_evento;
    }

    public void setId_evento(String id_evento) {
        this.id_evento = id_evento;
    }

    @Override
    public String toString() {
        return "AprobacionDocumentacion{" +
                "ID='" + ID + '\'' +
                ", estatusContrato='" + estatusContrato + '\'' +
                ", estatusReglamento='" + estatusReglamento + '\'' +
                ", estatusINE='" + estatusINE + '\'' +
                ", estatusComprobante1='" + estatusComprobante1 + '\'' +
                ", estatusComprobante2='" + estatusComprobante2 + '\'' +
                ", id_evento='" + id_evento + '\'' +
                '}';
    }
}
