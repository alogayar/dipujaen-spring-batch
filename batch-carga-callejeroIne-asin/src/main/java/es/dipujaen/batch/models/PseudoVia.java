package es.dipujaen.batch.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Entity
@Table(name = "pseudovias")
public class PseudoVia {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Id;
	private int CodProvincia;
	
	private int CodMunicipio;
	private int CodPseudoVia;
	private String TipoInf;
	
	private String CausaDev;
	/* FVAR */
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date FechaVar;
	/* CVAR */
	private String Codvariacion;
	private String CodPseudoViaNuevo;
	private String NombrePseudoVia;
	
}
