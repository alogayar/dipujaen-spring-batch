package es.dipujaen.batch.cargacallejeroinesinc.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

@Component
public class JobCompletionNotificationListener implements JobExecutionListener {
    private static final Logger log = LoggerFactory.getLogger(JobCompletionNotificationListener.class);

    @Override
    public void beforeJob(JobExecution jobExecution) {
        log.info("beforeJob >>> {} ",jobExecution);

    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        log.info("afterJob >>> {} ",jobExecution);

    }
    


}