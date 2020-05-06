package es.dipujaen.batch.cargacallejeroinesinc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableBatchProcessing
@SpringBootApplication
public class DemoApplication implements CommandLineRunner{
	private static final Logger log = LoggerFactory.getLogger(DemoApplication.class);
	
	@Autowired
	JobLauncher jobLauncher;
	
	@Autowired
	Job job;	


	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);		
	}

	@Override
	public void run(String... args) throws Exception {

		log.info("-> SE INICIA EL BATCH CON LOS SIGUIENTES PARAMETROS={}}", args[0]);				
		JobParameters params = new JobParametersBuilder().addString("FilePath", args[0]).toJobParameters();
		jobLauncher.run(job, params);

		
	}

}
