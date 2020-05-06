package es.dipujaen.batch.cargacallejeroinesinc.configuration;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import es.dipujaen.batch.cargacallejeroinesinc.models.Upoblacional;
import es.dipujaen.batch.cargacallejeroinesinc.writers.UpoblacionalWriter;

@Configuration
public class JobConfiguration {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;
    
    @Autowired
    public FlatFileItemReader<Upoblacional> reader;
    
    @Autowired
    public StepCompletionListener steplistener;
    

    @Bean
    public Job job(final JobCompletionNotificationListener listener) {
        return jobBuilderFactory.get("Batch-CargaCallejeroIne")
        .incrementer(new RunIdIncrementer())
        .listener(listener)
        .start(step1())
        .build();
    }

    @Bean    
    public Step step1() {
        return stepBuilderFactory.get("step1")
        .<Upoblacional, Upoblacional>chunk(1)
        .reader(reader)
        .listener(steplistener)
        .writer(writer())                       
        .build();
    }        
   
    @Bean
    public UpoblacionalWriter writer(){        
        return new UpoblacionalWriter(); 
    }   
    
    
    
    
    
    
    
    
    







}