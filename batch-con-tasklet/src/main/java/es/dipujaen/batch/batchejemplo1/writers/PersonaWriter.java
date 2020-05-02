package es.dipujaen.batch.batchejemplo1.writers;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import es.dipujaen.batch.batchejemplo1.models.Persona;

@Component
public class PersonaWriter implements ItemWriter<Persona> {

    @Override
    public void write(List<? extends Persona> items) throws Exception {
       
        System.out.println("PERSONA WRITER " + items);

    }
    
}