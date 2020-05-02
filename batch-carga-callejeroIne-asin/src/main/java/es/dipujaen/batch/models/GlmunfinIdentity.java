package es.dipujaen.batch.models;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class GlmunfinIdentity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private short cpais = (short) 0;
	private short cprovinc = (short) 0;
	private short cmunicip = (short) 0;
	public short getCpais() {
		return cpais;
	}
	public void setCpais(short cpais) {
		this.cpais = cpais;
	}
	public short getCprovinc() {
		return cprovinc;
	}
	public void setCprovinc(short cprovinc) {
		this.cprovinc = cprovinc;
	}
	public short getCmunicip() {
		return cmunicip;
	}
	public void setCmunicip(short cmunicip) {
		this.cmunicip = cmunicip;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cmunicip;
		result = prime * result + cpais;
		result = prime * result + cprovinc;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GlmunfinIdentity other = (GlmunfinIdentity) obj;
		if (cmunicip != other.cmunicip)
			return false;
		if (cpais != other.cpais)
			return false;
		if (cprovinc != other.cprovinc)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "GlmunfinIdentity [cpais=" + cpais + ", cprovinc=" + cprovinc + ", cmunicip=" + cmunicip + "]";
	}
	
	

}
