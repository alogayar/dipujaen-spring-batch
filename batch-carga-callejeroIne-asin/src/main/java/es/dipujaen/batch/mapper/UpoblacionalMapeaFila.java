package es.dipujaen.batch.mapper;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

import es.dipujaen.batch.models.UpoblacionalTXT;

public class UpoblacionalMapeaFila implements FieldSetMapper<UpoblacionalTXT> {

	Logger log = LoggerFactory.getLogger(UpoblacionalMapeaFila.class);

	@Override
	public UpoblacionalTXT mapFieldSet(FieldSet fieldSet) throws BindException {

		

		UpoblacionalTXT upoblacionaltxt = new UpoblacionalTXT();	
			
		upoblacionaltxt.setCpro(fieldSet.readString("Cpro"));
		upoblacionaltxt.setCmun(fieldSet.readString("Cmun"));
		upoblacionaltxt.setCun(fieldSet.readString("Cun"));
		upoblacionaltxt.setTipoInf(fieldSet.readString("TipoInf"));		
		upoblacionaltxt.setCdev(fieldSet.readString("Cdev"));		
		upoblacionaltxt.setFvar(fieldSet.readString("Fvar"));
		upoblacionaltxt.setCvar(fieldSet.readString("Cvar"));
		upoblacionaltxt.setNmun(fieldSet.readString("Nmun"));
		upoblacionaltxt.setCvar(fieldSet.readString("Cvar"));
		upoblacionaltxt.setDmun50(fieldSet.readString("Dmun50"));
		upoblacionaltxt.setNmunc(fieldSet.readString("Nmunc"));
		upoblacionaltxt.setNentco(fieldSet.readString("Nentco"));
		upoblacionaltxt.setNentco50(fieldSet.readString("Nentco50"));
		upoblacionaltxt.setNentcoc(fieldSet.readString("Nentcoc"));
		upoblacionaltxt.setNentsi(fieldSet.readString("Nentsi"));
		upoblacionaltxt.setNentsi50(fieldSet.readString("Nentsi50"));
		upoblacionaltxt.setNentsic(fieldSet.readString("Nentsic"));
		upoblacionaltxt.setNnucle(fieldSet.readString("Nnucle"));
		upoblacionaltxt.setNnucle50(fieldSet.readString("Nnucle50"));
		upoblacionaltxt.setNnuclec(fieldSet.readString("Nnuclec"));
		
		log.info("** MAPPEANDO  U.POBLACIONAL: " + fieldSet);
		
		return upoblacionaltxt;
	}

}
