package es.dipujaen.batch.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.sun.istack.NotNull;

import lombok.Data;


@Table(name = "upoblacional")
@Data
@Entity
public class Upoblacional implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int Id;	
	/* CPRO */
	public int CodProvincia;
	/* CMUN */
	public int CodMunicipio;
	/* CUN */
	public int CodUnidadPoblacional;
	/* TIPOINF */
	public String TipoInf;
	/* CDEV */
	@NotNull
	public String CausaDev;
	/* FVAR */
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	public Date FechaVar;
	/* CVAR */
	public String Codvariacion;
	/* NMUN */
	public String NombreMunicipio;
	/* DMUN50 */
	public String NombreMunicipio50;
	/* NMUNC */
	public String NombreMunicipioCorto;
	/* NENTCO */
	public String NombreEntidadColectiva;
	/* NENTCO50 */
	public String NombreEntCol50;
	/* NENTCOC */
	public String NombreEntColCorto;
	/* NENTSI */
	public String NombreEntidadSingular;
	/* NENTSI50 */
	public String NombreEntSin50;
	/* NENTSIC */
	public String NombreEntSingCorto;
	/* NNUCLE */
	public String NombreNucleoDise;
	/* NNUCLE50 */
	public String NombreNucleoDise50;
	/* NNUCLEC */
	public String NombreNucleoDiseCorto;

}
