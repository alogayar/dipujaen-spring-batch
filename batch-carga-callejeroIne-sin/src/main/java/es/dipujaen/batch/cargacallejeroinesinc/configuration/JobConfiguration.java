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
        .writer(writer())
//        .processor(new ItemProcessor<Persona, Persona>() {
//
//                    @Override
//                    public Persona process(final Persona item) throws Exception {
//                        final Persona persona = new Persona(item.getId().toUpperCase(), item.getNombre().toUpperCase());
//                        
//						return persona;
//                    }})
//                .writer(writer())
               
                .build();
    }

        
   
    @Bean
    public UpoblacionalWriter writer(){
        //System.out.println("PARAMETROS1 WRITER" + param1);
       //System.out.println("PARAMETROS2 WRITER" + param2.toString());
        return new UpoblacionalWriter(); 
    }   
    
    
    
    
    
    
    
    
    







}