package es.dipujaen.batch.runner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.annotation.AfterJob;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class CallejeroIneJobRunnerConfig {

	private static final Logger logger = LoggerFactory.getLogger(CallejeroIneJobRunnerConfig.class);

	@Autowired
	JobLauncher jobLauncher;

	@Autowired
	Job CargaCallejeroIne;
	

	/* AOC: Ejecución del Batch con los Parametros de forma Asíncrona */
	@Async
	public void runBatchJob(JobParametersBuilder jobParametersBuilder) {

		logger.info("PARAMETROS" + jobParametersBuilder.toJobParameters().toString());
		runJob(CargaCallejeroIne, jobParametersBuilder.toJobParameters());
	}

	
	public void runJob(Job job, JobParameters parameters) {		
		
		try {
			
			jobLauncher.run(job, parameters);			

		} catch (JobExecutionAlreadyRunningException e) {
			logger.info("Job with parametros={} is already running.", parameters.getParameters());
		} catch (JobRestartException e) {
			logger.info("Job with parametros={} was not restarted.", parameters.getParameters());
		} catch (JobInstanceAlreadyCompleteException e) {
			logger.info("Job with parametros={} already completed.", parameters.getParameters());
		} catch (JobParametersInvalidException e) {
			logger.info("Invalid job parameters.", parameters.getParameters());
		}
		
		
		
	}


	

}
