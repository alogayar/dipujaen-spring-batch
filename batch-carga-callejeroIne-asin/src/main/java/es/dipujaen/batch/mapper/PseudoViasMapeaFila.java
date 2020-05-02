package es.dipujaen.batch.mapper;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

import es.dipujaen.batch.models.PseudoVia;

public class PseudoViasMapeaFila implements FieldSetMapper<PseudoVia> {
	
	Logger log = LoggerFactory.getLogger(PseudoViasMapeaFila.class);

	@Override
	public PseudoVia mapFieldSet(FieldSet fieldSet) throws BindException {
		PseudoVia pseudovia = new PseudoVia();
		
		
		if (fieldSet == null) return null;
		
		pseudovia.setCodProvincia(fieldSet.readInt("CodProvincia"));
		pseudovia.setCodMunicipio(fieldSet.readInt("CodMunicipio"));
		pseudovia.setCodPseudoVia(fieldSet.readInt("CodPseudoVia"));
		pseudovia.setTipoInf(fieldSet.readString("TipoInf"));
		pseudovia.setCausaDev(fieldSet.readString("CausaDev"));
		try {
			pseudovia.setFechaVar(new SimpleDateFormat("yyyyMMdd").parse(fieldSet.readString("FechaVar")));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pseudovia.setCodvariacion(fieldSet.readString("Codvariacion"));
		pseudovia.setCodPseudoViaNuevo(fieldSet.readString("CodPseudoViaNuevo"));
		pseudovia.setNombrePseudoVia(fieldSet.readString("NombrePseudoVia"));
		
		log.info("** MAPPEANDO  PSEUDOVIA: " + fieldSet);
		return pseudovia;
		
		
		
	}

}
