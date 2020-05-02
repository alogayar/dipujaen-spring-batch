package es.dipujaen.batch.batchejemplo1.configuration;

import org.springframework.batch.core.Job;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import es.dipujaen.batch.batchejemplo1.models.Persona;
import es.dipujaen.batch.batchejemplo1.readers.PersonasReaders;
import es.dipujaen.batch.batchejemplo1.writers.PersonaWriter;

@Configuration
@EnableBatchProcessing
public class JobConfiguration {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    public PersonasReaders reader;
       
    @Autowired
    public es.dipujaen.batch.batchejemplo1.configuration.tasklet1 tasklet1;

    @Bean
    public Job job(final JobCompletionNotificationListener listener) {
        return jobBuilderFactory.get("job")
        .incrementer(new RunIdIncrementer())
        .listener(listener)
        .start(step1())
        .build();
    }

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
        .<Persona, Persona>chunk(1).reader(reader)
        .processor(new ItemProcessor<Persona, Persona>() {

                    @Override
                    public Persona process(final Persona item) throws Exception {
                        final Persona persona = new Persona(item.getId().toUpperCase(), item.getNombre().toUpperCase());
                        
						return persona;
                    }})
                .writer(writer())
               
                .build();
    }

        
   
    @Bean
    public PersonaWriter writer(){
        //System.out.println("PARAMETROS1 WRITER" + param1);
       //System.out.println("PARAMETROS2 WRITER" + param2.toString());
        return new PersonaWriter();
    }








}