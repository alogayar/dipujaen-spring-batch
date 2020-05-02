package es.dipujaen.batch.tr.RGCJuntaNov19.config;

import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.step.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.adapter.ItemWriterAdapter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import es.dipujaen.batch.tr.RGCJuntaNov19.models.gtt_cargos;

@Configuration
public class BatchConfig {

    private static final Logger logger = LoggerFactory.getLogger(BatchConfig.class);

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job JobRGCJunta(final Step step) {
        return jobBuilderFactory.get("JobProcesarRGC").start(step).build();
    }

    @Bean
    public Step step() {
        return stepBuilderFactory.get("StepCargaRGC").<gtt_cargos, gtt_cargos>chunk(1).reader(StepCargaRGC())
                .writer(writer()).build();
    }

    @Bean
    public FlatFileItemReader<gtt_cargos> StepCargaRGC() {

        // Create reader instance
        final FlatFileItemReader<gtt_cargos> reader = new FlatFileItemReader<gtt_cargos>();

        // Set input file location
        reader.setResource(new FileSystemResource("src/main/resources/RGC.csv"));
        // Set number of lines to skips. Use it if file has header rows.
        reader.setLinesToSkip(1);

        // Configure how each line will be parsed and mapped to different values
        reader.setLineMapper(new DefaultLineMapper<gtt_cargos>() {
            {
                // 3 columns in each row
                setLineTokenizer(new DelimitedLineTokenizer() {
                    {
                        setNames(new String[] { "ID_ANCO", "ID_PADRE_IMPO", "ID_FIJU", "REF_EXTERNA", "IMPORTE_ANCO",
                                "ID_JUNTA", "ID_VALOR", "REF_EXT_JUNTA", "NUM_LIQ_R1JU", "REF_EXTERNA_VALO" });
                    }
                    {setDelimiter(";");}
                    
                    
                    
                });
                // Set values in gtt_cargos class
                setFieldSetMapper(new BeanWrapperFieldSetMapper<gtt_cargos>() {
                    {
                        setTargetType(gtt_cargos.class);
                    }
                });

                
            }
        });
        return reader;

    }

    @Bean
    public ItemWriter<gtt_cargos> writer() {
        return new ItemWriter<gtt_cargos>() {

            @Override
            public void write(final List<? extends gtt_cargos> items) throws Exception {

                Iterator<? extends gtt_cargos> lista = items.iterator();
                while (lista.hasNext()) {
                    logger.info("ITEMS: {}", lista.next().getID_ANCO());
                    
                }

            }
        };

    }

}