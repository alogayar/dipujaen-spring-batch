package es.dipujaen.batch.processor;


import java.text.SimpleDateFormat;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import es.dipujaen.batch.models.Upoblacional;
import es.dipujaen.batch.models.UpoblacionalTXT;

@Component
public class UpoblacionalProcessor implements ItemProcessor<UpoblacionalTXT, Upoblacional> {

	Logger logger = LoggerFactory.getLogger(UpoblacionalProcessor.class);

	@Override
	public Upoblacional process(UpoblacionalTXT item) throws Exception {

		Upoblacional up = new Upoblacional();
		
		up.setCodProvincia(Integer.parseInt(item.getCpro()));
		up.setCodMunicipio(Integer.parseInt(item.getCmun()));
		up.setCodUnidadPoblacional(Integer.parseInt(item.getCun()));
		up.setTipoInf(item.getTipoInf());
		
		if (StringUtils.isEmpty(StringUtils.trim(item.getCdev())))
			up.setCausaDev(StringUtils.SPACE);
		else
			up.setCausaDev(item.getCdev());
		
		up.setFechaVar(new SimpleDateFormat("yyyyMMdd").parse(item.getFvar()));
		
		if (StringUtils.isEmpty(StringUtils.trim(item.getCvar())))
			up.setCodvariacion(StringUtils.SPACE);
		else
			up.setCodvariacion(item.getCvar());
		
		if (StringUtils.isEmpty(StringUtils.trim(item.getNmun())))
			up.setNombreMunicipio(StringUtils.SPACE);
		else
			up.setNombreMunicipio(item.getNmun());
		
		if (StringUtils.isEmpty(StringUtils.trim(item.getDmun50())))
			up.setNombreMunicipio50(StringUtils.SPACE);
		else
			up.setNombreMunicipio50(item.getDmun50());
		
		if (StringUtils.isEmpty(StringUtils.trim(item.getNmunc())))
			up.setNombreMunicipioCorto(StringUtils.SPACE);
		else
			up.setNombreMunicipioCorto((item.getNmunc()));		
		
		if (StringUtils.isEmpty(StringUtils.trim(item.getNentco())))
			up.setNombreEntidadColectiva(StringUtils.SPACE);
		else
			up.setNombreEntidadColectiva(item.getNentco());
		
		if (StringUtils.isEmpty(StringUtils.trim(item.getNentco50())))
			up.setNombreEntCol50(StringUtils.SPACE);
		else
			up.setNombreEntCol50(item.getNentco50());
		
		if (StringUtils.isEmpty(StringUtils.trim(item.getNentcoc())))
			up.setNombreEntColCorto(StringUtils.SPACE);
		else
			up.setNombreEntColCorto(item.getNentcoc());	
		
		if (StringUtils.isEmpty(StringUtils.trim(item.getNentsi())))
			up.setNombreEntidadSingular(StringUtils.SPACE);
		else
			up.setNombreEntidadSingular(item.getNentsi());
		
		if (StringUtils.isEmpty(StringUtils.trim(item.getNentsi50())))
			up.setNombreEntSin50(StringUtils.SPACE);
		else
			up.setNombreEntSin50(item.getNentsi50());
		
		if (StringUtils.isEmpty(StringUtils.trim(item.getNentsic())))
			up.setNombreEntSingCorto(StringUtils.SPACE);
		else
			up.setNombreEntSingCorto(item.getNentsic());

		if (StringUtils.isEmpty(StringUtils.trim(item.getNnucle())))
			up.setNombreNucleoDise(StringUtils.SPACE);
		else
			up.setNombreNucleoDise(item.getNnucle());
		
		if (StringUtils.isEmpty(StringUtils.trim(item.getNnucle50())))
			up.setNombreNucleoDise50(StringUtils.SPACE);
		else
			up.setNombreNucleoDise50(item.getNnucle50());
		
		if (StringUtils.isEmpty(StringUtils.trim(item.getNnuclec())))
			up.setNombreNucleoDiseCorto(StringUtils.SPACE);
		else
			up.setNombreNucleoDiseCorto(item.getNnuclec());
			

		//log.info("****** U. POBLACIONAL  ******** PROVINCIA:" + item.getCpro() + " MUNICIPIO: " + item.getDmun50()); 
		logger.info("** PROCESADO U.POBLACIONAL->" + up.toString());		
		

		return up;
	}

}
