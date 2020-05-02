package es.dipujaen.batch.tr.RGCJuntaNov19.models;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class gtt_cargos {

    @Id
    private String ID_ANCO;    
    private String ID_PADRE_IMPO;
    private String ID_FIJU;
    private String REF_EXTERNA;
    private String IMPORTE_ANCO;
    private String ID_JUNTA;
    private String ID_VALOR;
    private String REF_EXT_JUNTA;
    private String NUM_LIQ_R1JU;
    private String REF_EXTERNA_VALO;

    public String getID_ANCO() {
        return ID_ANCO;
    }

    public void setID_ANCO(final String iD_ANCO) {
        ID_ANCO = iD_ANCO;
    }

    public String getID_PADRE_IMPO() {
        return ID_PADRE_IMPO;
    }

    public void setID_PADRE_IMPO(final String iD_PADRE_IMPO) {
        ID_PADRE_IMPO = iD_PADRE_IMPO;
    }

    public String getID_FIJU() {
        return ID_FIJU;
    }

    public void setID_FIJU(final String iD_FIJU) {
        ID_FIJU = iD_FIJU;
    }

    public String getREF_EXTERNA() {
        return REF_EXTERNA;
    }

    public void setREF_EXTERNA(final String rEF_EXTERNA) {
        REF_EXTERNA = rEF_EXTERNA;
    }

    public String getIMPORTE_ANCO() {
        return IMPORTE_ANCO;
    }

    public void setIMPORTE_ANCO(final String iMPORTE_ANCO) {
        IMPORTE_ANCO = iMPORTE_ANCO;
    }

    public String getID_JUNTA() {
        return ID_JUNTA;
    }

    public void setID_JUNTA(final String iD_JUNTA) {
        ID_JUNTA = iD_JUNTA;
    }

    public String getID_VALOR() {
        return ID_VALOR;
    }

    public void setID_VALOR(final String iD_VALOR) {
        ID_VALOR = iD_VALOR;
    }

    public String getREF_EXT_JUNTA() {
        return REF_EXT_JUNTA;
    }

    public void setREF_EXT_JUNTA(final String rEF_EXT_JUNTA) {
        REF_EXT_JUNTA = rEF_EXT_JUNTA;
    }

    public String getNUM_LIQ_R1JU() {
        return NUM_LIQ_R1JU;
    }

    public void setNUM_LIQ_R1JU(final String nUM_LIQ_R1JU) {
        NUM_LIQ_R1JU = nUM_LIQ_R1JU;
    }

    public String getREF_EXTERNA_VALO() {
        return REF_EXTERNA_VALO;
    }

    public void setREF_EXTERNA_VALO(final String rEF_EXTERNA_VALO) {
        REF_EXTERNA_VALO = rEF_EXTERNA_VALO;
    }

}