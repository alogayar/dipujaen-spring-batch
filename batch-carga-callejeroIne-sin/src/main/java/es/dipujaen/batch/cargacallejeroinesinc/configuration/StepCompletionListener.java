package es.dipujaen.batch.cargacallejeroinesinc.configuration;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.AfterStep;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class StepCompletionListener{

	
	@AfterStep
	public ExitStatus afterStep(StepExecution stepExecution) {
		
		log.info("REGISTROS LEIDOS " + stepExecution.getReadCount());
		return stepExecution.getExitStatus();
		
	}

}
