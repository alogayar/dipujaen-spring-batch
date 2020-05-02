package es.dipujaen.batch.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class BatchListener implements JobExecutionListener {
	
	
	JobExecution jobExecution;
	  

	private static final Logger log = LoggerFactory.getLogger(BatchListener.class);

	@Override
	public void beforeJob(JobExecution jobExecution) {
		
		this.jobExecution = jobExecution;

		log.info("^^^^^^^^^^^^^^^^^^^^^^^ DATOS PETICION BATCH ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
		log.info("ID-{} ", jobExecution.getId());
		log.info("INSTANCIA-{}", jobExecution.getJobInstance());
		log.info("NOMBRE-{} ", jobExecution.getJobInstance().getJobName());
		log.info("F.CREACION-{} ", jobExecution.getCreateTime());
		log.info("ESTADO-{} ", jobExecution.getStatus());
		log.info("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");

	}

	@Override
	public void afterJob(JobExecution jobExecution) {
		log.info("^^^^^^^^^^^^^^^^^^^^^^^^RESULTADO DE LA EJECUCION ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
		log.info("ID-{} ", jobExecution.getId());
		log.info("INSTANCIA-{}", jobExecution.getJobInstance());
		log.info("NOMBRE-{} ", jobExecution.getJobInstance().getJobName());
		log.info("F.FIN {} ", jobExecution.getEndTime());
		log.info("ESTADO-{} ", jobExecution.getStatus());
		log.info("ESTADO FINAL-{} ", jobExecution.getExitStatus());
		log.info("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");

	}
	
	@Bean
	public JobExecution getJobExecution() {
		return this.jobExecution;				
	}
	
	

}
