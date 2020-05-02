package es.dipujaen.batch.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;



//@Entity
//@Table(name = "glmunfin")
public class Glmunfin implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EmbeddedId
	private GlmunfinIdentity glmunfinIdentity = new GlmunfinIdentity();
	
	private String dmunicip = StringUtils.EMPTY;
	private String xmunicip = StringUtils.EMPTY;
	private int cpostal = 0;
	private int nhabitan = 0;
	private String ccomarca = StringUtils.EMPTY;
	private String cmatmot = StringUtils.EMPTY;
	private String xcfmunic = StringUtils.EMPTY;	
	private Date wbdtimst = new Date();
	
	
	
	public Glmunfin(GlmunfinIdentity glmunfinIdentity, String dmunicip, String xmunicip, int cpostal, int nhabitan,
			String ccomarca, String cmatmot, String xcfmunic, Date wbdtimst) {
		super();
		this.glmunfinIdentity = glmunfinIdentity;
		this.dmunicip = dmunicip;
		this.xmunicip = xmunicip;
		this.cpostal = cpostal;
		this.nhabitan = nhabitan;
		this.ccomarca = ccomarca;
		this.cmatmot = cmatmot;
		this.xcfmunic = xcfmunic;
		this.wbdtimst = wbdtimst;
	}
	public Glmunfin() {
		super();
	}
	public GlmunfinIdentity getGlmunfinIdentity() {
		return glmunfinIdentity;
	}
	public void setGlmunfinIdentity(GlmunfinIdentity glmunfinIdentity) {
		this.glmunfinIdentity = glmunfinIdentity;
	}
	public String getDmunicip() {
		return dmunicip;
	}
	public void setDmunicip(String dmunicip) {
		this.dmunicip = dmunicip;
	}
	public String getXmunicip() {
		return xmunicip;
	}
	public void setXmunicip(String xmunicip) {
		this.xmunicip = xmunicip;
	}
	public int getCpostal() {
		return cpostal;
	}
	public void setCpostal(int cpostal) {
		this.cpostal = cpostal;
	}
	public int getNhabitan() {
		return nhabitan;
	}
	public void setNhabitan(int nhabitan) {
		this.nhabitan = nhabitan;
	}
	public String getCcomarca() {
		return ccomarca;
	}
	public void setCcomarca(String ccomarca) {
		this.ccomarca = ccomarca;
	}
	public String getCmatmot() {
		return cmatmot;
	}
	public void setCmatmot(String cmatmot) {
		this.cmatmot = cmatmot;
	}
	public String getXcfmunic() {
		return xcfmunic;
	}
	public void setXcfmunic(String xcfmunic) {
		this.xcfmunic = xcfmunic;
	}
	public Date getWbdtimst() {
		return wbdtimst;
	}
	public void setWbdtimst(Date wbdtimst) {
		this.wbdtimst = wbdtimst;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
			
	
	

}
