package es.dipujaen.batch.mapper;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

import es.dipujaen.batch.models.Via;

public class ViasMapeaFila implements FieldSetMapper<Via> {

	Logger log = LoggerFactory.getLogger(UpoblacionalMapeaFila.class);

	@Override
	public Via mapFieldSet(FieldSet fieldSet) throws BindException {

		Via vias = new Via();
		vias.setCodProvincia(fieldSet.readInt("CodProvincia"));
		vias.setCodMunicipio(fieldSet.readInt("CodMunicipio"));
		vias.setCodVia(fieldSet.readInt("CodVia"));
		vias.setTipoInf(fieldSet.readString("TipoInf"));
		vias.setCausaDev(fieldSet.readString("CausaDev"));
		try {
			vias.setFechaVar(new SimpleDateFormat("yyyyMMdd").parse(fieldSet.readString("FechaVar")));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		vias.setCodvariacion(fieldSet.readString("Codvariacion"));
		vias.setCodViaNuevo(fieldSet.readInt("CodViaNuevo"));
		vias.setTipoViaNuevo(fieldSet.readString("TipoViaNuevo"));
		vias.setPosTipoVia(fieldSet.readInt("PosTipoVia"));
		vias.setNombreVia(fieldSet.readString("NombreVia"));
		vias.setNombreViaCorto(fieldSet.readString("NombreViaCorto"));
		
		log.info("** MAPPEANDO  VIA: " + fieldSet);	
		return vias;
		
	}

}
