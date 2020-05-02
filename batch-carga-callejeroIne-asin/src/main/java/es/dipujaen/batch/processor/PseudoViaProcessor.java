package es.dipujaen.batch.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import es.dipujaen.batch.models.PseudoVia;

@Component
public class PseudoViaProcessor implements ItemProcessor<PseudoVia, PseudoVia>{
	
	private static final Logger logger = LoggerFactory.getLogger(PseudoViaProcessor .class);

	@Override
	public PseudoVia process(PseudoVia item) throws Exception {
		
		PseudoVia pseudovia = new PseudoVia();
		if (item == null) return null;
		
		pseudovia.setCodMunicipio(item.getCodMunicipio());
		pseudovia.setCodProvincia(item.getCodProvincia());

		pseudovia.setCodPseudoVia(item.getCodPseudoVia());
		pseudovia.setTipoInf(item.getTipoInf());
		pseudovia.setCausaDev(item.getCausaDev());
		pseudovia.setFechaVar(item.getFechaVar());
		pseudovia.setCodvariacion(item.getCodvariacion());
		pseudovia.setCodPseudoViaNuevo(item.getCodPseudoViaNuevo());		
		pseudovia.setNombrePseudoVia(item.getNombrePseudoVia());
		
		
		logger.info("** PROCESADO PSEUDOVIA->" + pseudovia.toString());
		return pseudovia;
		
		
	}

	
}
