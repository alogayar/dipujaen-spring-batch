package es.dipujaen.batch.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import es.dipujaen.batch.models.Via;
import es.dipujaen.batch.utils.Constants;

@Component
public class ViaProcessor implements ItemProcessor<Via, Via> {

	Logger logger = LoggerFactory.getLogger(ViaProcessor.class);
	@Override
	public Via process(Via item) throws Exception {

		Via vias = new Via();

		if (item == null)
			return null;

		vias.setCodMunicipio(item.getCodMunicipio());
		vias.setCodProvincia(item.getCodProvincia());

		vias.setCodVia(item.getCodVia());
		vias.setTipoInf(item.getTipoInf());
		vias.setCausaDev(item.getCausaDev());
		vias.setFechaVar(item.getFechaVar());

		vias.setCodvariacion(item.getCodvariacion());
		vias.setCodViaNuevo(item.getCodViaNuevo());			

		if (Constants.TIPOSVIA.contains(item.getTipoViaNuevo().toUpperCase()))
			vias.setTipoViaNuevo(Constants.CALLE);
		else
			vias.setTipoViaNuevo(item.getTipoViaNuevo());

		vias.setPosTipoVia(item.getPosTipoVia());
		vias.setNombreVia(item.getNombreVia());
		vias.setNombreViaCorto(item.getNombreViaCorto());
		
		logger.info("** PROCESADO VIA->" + vias.toString());

		return vias;
	}

}
