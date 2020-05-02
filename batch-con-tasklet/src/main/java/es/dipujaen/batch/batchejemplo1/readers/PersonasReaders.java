package es.dipujaen.batch.batchejemplo1.readers;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Component;

import es.dipujaen.batch.batchejemplo1.models.Persona;

@Component
@StepScope
public class PersonasReaders implements ItemReader<Persona> {

    static int counter=0;

    @Value("#{jobParameters['P2']}")
    public Long param2;

    @Override
    public Persona read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        

        final Persona persona = new Persona("id"+param2,"nombre"+param2);

        param2++;

        if (param2>20) {
            return null;            
        }

        System.out.println(" PARAMETRO READER " + param2);

        return persona;        
    }


}