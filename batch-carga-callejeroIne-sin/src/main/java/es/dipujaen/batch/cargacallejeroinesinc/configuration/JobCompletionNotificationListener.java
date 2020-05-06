package es.dipujaen.batch.cargacallejeroinesinc.configuration;

import java.util.Iterator;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.batch.core.launch.NoSuchJobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JobCompletionNotificationListener implements JobExecutionListener {
    private static final Logger log = LoggerFactory.getLogger(JobCompletionNotificationListener.class);
    
    @Autowired
    JobOperator jobOperator;

    @Override
    public void beforeJob(JobExecution jobExecution) {  
    	
        
        log.info("^^^^^^^^^^^^^^^^^^^^^^^ INICIO PETICION BATCH ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
		log.info("ID-{} ", jobExecution.getId());
		log.info("INSTANCIA-{}", jobExecution.getJobInstance());
		log.info("NOMBRE-{} ", jobExecution.getJobInstance().getJobName());
		log.info("F.CREACION-{} ", jobExecution.getCreateTime());
		log.info("ESTADO-{} ", jobExecution.getStatus());
		log.info("^^^^^^^^^^^^^^^^^^^^^^^ PARAMETROS ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
		log.info("{} ", jobExecution.getJobParameters().toString());
		log.info("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");

    }

    @Override
    public void afterJob(JobExecution jobExecution) {
    	
    	log.info("^^^^^^^^^^^^^^^^^^^^^^^ RESULTADO BATCH ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
		log.info("ID-{} ", jobExecution.getId());
		log.info("INSTANCIA-{}", jobExecution.getJobInstance());
		log.info("NOMBRE-{} ", jobExecution.getJobInstance().getJobName());
		log.info("F.CREACION-{} ", jobExecution.getCreateTime());
		log.info("F.FIN {} ", jobExecution.getEndTime());
		log.info("ESTADO-{} ", jobExecution.getStatus());
		log.info("ESTADO FINAL-{} ", jobExecution.getExitStatus());
		log.info("^^^^^^^^^^^^^^^^^^^^^^^ ESTADISTICAS ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
		try {
			
			Map<Long, String> steps_resumen = jobOperator.getStepExecutionSummaries(jobExecution.getId()); 
			
			steps_resumen.forEach(
					(k,v)-> {
						log.info("Id Step-> {} ",k);	
						log.info("Id Resumen-> {} ",v);
					});
			
		} catch (NoSuchJobExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		log.info("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
        

    }
    


}
