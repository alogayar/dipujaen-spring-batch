package es.dipujaen.batch.writer;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import es.dipujaen.batch.models.Upoblacional;
import es.dipujaen.batch.persistencia.dao.IUpoblacionalDao;
import lombok.Data;

@Data
public class UpoblacionalWriter implements ItemWriter<Upoblacional>{
	
	@Autowired
	IUpoblacionalDao iupoblacionalDao;    

	@Override
	public void write(List<? extends Upoblacional> items) throws Exception {
		
		iupoblacionalDao.saveAll(items);	
		
	}		
}
