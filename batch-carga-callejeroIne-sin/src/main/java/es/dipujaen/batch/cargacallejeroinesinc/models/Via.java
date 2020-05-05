package es.dipujaen.batch.cargacallejeroinesinc.models;

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
@Table(name = "vias")
public class Via {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int Id;	
	
	private int CodProvincia;
	
	public int CodMunicipio;
	
	public int CodVia;
	
	public String TipoInf;
	
	public String CausaDev;
	/* FVAR */
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	public Date FechaVar;
	/* CVAR */
	public String Codvariacion;
	
	public int CodViaNuevo;
	
	public String TipoViaNuevo;
	
	public int PosTipoVia;
	
	public String NombreVia;
	
	public String NombreViaCorto;

}
