package es.dipujaen.batch.job;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.FixedLengthTokenizer;
import org.springframework.batch.item.file.transform.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import es.dipujaen.batch.mapper.PseudoViasMapeaFila;
import es.dipujaen.batch.mapper.UpoblacionalMapeaFila;
import es.dipujaen.batch.mapper.ViasMapeaFila;
import es.dipujaen.batch.models.PseudoVia;
import es.dipujaen.batch.models.Upoblacional;
import es.dipujaen.batch.models.UpoblacionalTXT;
import es.dipujaen.batch.models.Via;
import es.dipujaen.batch.processor.PseudoViaProcessor;
import es.dipujaen.batch.processor.UpoblacionalProcessor;
import es.dipujaen.batch.processor.ViaProcessor;
import es.dipujaen.batch.writer.UpoblacionalWriter;

@Configuration
public class BatchCallejeroIneJob {

	private JobBuilderFactory jobBuilderFactory;
	private StepBuilderFactory stepBuilderFactory;
	private UpoblacionalProcessor upoblacionalprocessor;
	private DataSource dataSource;
	private ViaProcessor viasprocessor;
	private PseudoViaProcessor pseudoviasprocessor;
	@Autowired
	private JobExecutionListener listener;
	

	Logger log = LoggerFactory.getLogger(BatchCallejeroIneJob.class);

	@Autowired
	public BatchCallejeroIneJob(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory,
			UpoblacionalProcessor upoblacionalprocessor, ViaProcessor viasprocessor, PseudoViaProcessor pseudoviasprocessor, DataSource dataSource) {
		super();
		this.jobBuilderFactory = jobBuilderFactory;
		this.stepBuilderFactory = stepBuilderFactory;
		this.upoblacionalprocessor = upoblacionalprocessor;
		this.dataSource = dataSource;
		this.viasprocessor = viasprocessor;
		this.pseudoviasprocessor = pseudoviasprocessor;				
	}

	/* JOB: Generamos el batch */
	@Bean
	public Job CargaFicherosIneJob() throws Exception {

		return jobBuilderFactory.get("CargaFicherosIneJob")
				//.incrementer(new RunIdIncrementer())			
				.start(CargaFicheroUpIneStep())
				.listener(listener)
				.next(CargaFicheroViasIneStep())
				.next(CargaFicheroPseudoViasIneStep())				
				.build();
	}

	/* STEP: Carga el fichero de Unidades poblacionales */
	@Bean
	public Step CargaFicheroUpIneStep() throws Exception {

		
		/* Damos nombre al Step */
		return stepBuilderFactory.get("CargaFicheroUpIneStep")
				/* Definimos el numero de elementos de la transaccion */
				.<UpoblacionalTXT, Upoblacional>chunk(10)
				/* Definimos la funcion que lee los datos */
				.reader(UpoblacionalReader())
				.processor(upoblacionalprocessor)
				.writer(UpoblacionalWriter())									
				.build();
	}

	/* STEP: Carga el fichero de Vias */
	@Bean
	public Step CargaFicheroViasIneStep() throws Exception {

		/* Damos nombre al Step */
		return stepBuilderFactory.get("CargaFicheroViasIneStep")
				/* Definimos el numero de elementos de la transaccion */
				.<Via, Via>chunk(10)
				/* Definimos la funcion que lee los datos */
				.reader(ViaReader()).processor(viasprocessor).writer(ViaWriter()).build();
	}

	/* STEP: Carga el fichero de PseudoVias */
	@Bean
	public Step CargaFicheroPseudoViasIneStep() throws Exception {

		/* Damos nombre al Step */
		return stepBuilderFactory.get("CargaFicheroPseudoViasIneStep")
				/* Definimos el numero de elementos de la transaccion */
				.<PseudoVia, PseudoVia>chunk(10)
				/* Definimos la funcion que lee los datos */
				.reader(PseudoViaReader()).processor(pseudoviasprocessor).writer(PseudoViaWriter()).build();
	}

	@Bean(name = "upoblacional")
	@StepScope
	/* Inyecta la ruta del fichero en fileName */
	Resource inputFileUpResource(@Value("#{jobParameters[fileNameUp]}") final String fileNameUp) throws Exception {

		return new ClassPathResource(fileNameUp);
	}

	@Bean(name = "vias")
	@StepScope
	/* Inyecta la ruta del fichero en fileName */
	Resource inputFileViaResource(@Value("#{jobParameters[fileNameVia]}") final String fileNameVia) throws Exception {

		
		return new ClassPathResource(fileNameVia);
	}
	
	@Bean(name = "pseudovias")
	@StepScope
	/* Inyecta la ruta del fichero en fileName */
	Resource inputFilePseudoviaResource(@Value("#{jobParameters[fileNamePseudoVia]}") final String fileNamePseudoVia) throws Exception {	
		return new ClassPathResource(fileNamePseudoVia);
	}


	/***************************************************************************************************************/
	// READERS
	/***************************************************************************************************************/

	/* READER : Funcion que lee los datos del fichero de UP */
	@Bean
	public FlatFileItemReader<UpoblacionalTXT> UpoblacionalReader() throws Exception {

		FlatFileItemReader<UpoblacionalTXT> reader = new FlatFileItemReader<UpoblacionalTXT>();
		/* Definimos el fichero de entrada en esta funcion */
		reader.setResource(inputFileUpResource(null));
		reader.setLineMapper(new DefaultLineMapper<UpoblacionalTXT>() {
			{
				setLineTokenizer(new FixedLengthTokenizer() {
					{
						setColumns(new Range[] { new Range(1, 2), // CPRO
								new Range(3, 5), // CMUM
								new Range(6, 12), // CUN
								new Range(13, 13), // TIPOINF
								new Range(14, 15), // CDEV
								new Range(16, 23), // FVAR
								new Range(24, 24), // CVAR
								new Range(25, 94), // NMUN*/
								new Range(95, 144), // DMUN50*/
								new Range(145, 169), // NMUNC*/
								new Range(170, 239), // NENTCO*/
								new Range(240, 289), // NENTCO50*/
								new Range(290, 314), // NENTCOC*/
								new Range(315, 384), // NENTSI*/
								new Range(385, 434), // NENTSI50*/
								new Range(435, 459), // NENTSIC*/
								new Range(460, 529), // NNUCLE*/
								new Range(530, 579), // NNUCLE50*/
								new Range(580, 604) // NNUCLEC*/

						});
						setNames(new String[] { "Cpro", "Cmun", "Cun", "TipoInf", "Cdev", "Fvar", "Cvar", "Nmun",
								"Dmun50", "Nmunc", "Nentco", "Nentco50", "Nentcoc", "Nentsi", "Nentsi50", "Nentsic",
								"Nnucle", "Nnucle50", "Nnuclec" });
						setStrict(false);
					}
				});

				setFieldSetMapper(new UpoblacionalMapeaFila());

			}
		});

		return reader;

	}

	/* READER : Funcion que lee los datos del fichero */
	@Bean
	public FlatFileItemReader<Via> ViaReader() throws Exception {

		FlatFileItemReader<Via> reader = new FlatFileItemReader<Via>();
		/* Definimos el fichero de entrada en esta funcion */
		reader.setResource(inputFileViaResource(null));
		reader.setLineMapper(new DefaultLineMapper<Via>() {
			{
				setLineTokenizer(new FixedLengthTokenizer() {
					{
						setColumns(new Range[] { new Range(1, 2), // CPRO
								new Range(3, 5), // CMUM
								new Range(6, 10), // CUN
								new Range(11, 11), // TIPOINF
								new Range(12, 13), // CDEV
								new Range(14, 21), // FVAR
								new Range(22, 22), // CVAR
								new Range(23, 27), new Range(28, 32), new Range(33, 33), new Range(34, 83),
								new Range(84, 108)

						});
						setNames(new String[] { "CodProvincia", "CodMunicipio", "CodVia", "TipoInf", "CausaDev",
								"FechaVar", "Codvariacion", "CodViaNuevo", "TipoViaNuevo", "PosTipoVia", "NombreVia",
								"NombreViaCorto" });
						setStrict(false);
					}
				});

				setFieldSetMapper(new ViasMapeaFila());
			}
		});

		return reader;

	}

	/* READER : Funcion que lee los datos del fichero */
	@Bean
	public FlatFileItemReader<PseudoVia> PseudoViaReader() throws Exception {

		FlatFileItemReader<PseudoVia> reader = new FlatFileItemReader<PseudoVia>();
		/* Definimos el fichero de entrada en esta funcion */
		reader.setResource(inputFilePseudoviaResource(null));
		reader.setLineMapper(new DefaultLineMapper<PseudoVia>() {
			{
				setLineTokenizer(new FixedLengthTokenizer() {
					{
						setColumns(new Range[] { new Range(1, 2), // CPRO
								new Range(3, 5), // CMUM
								new Range(6, 10), // CUN
								new Range(11, 11), // TIPOINF
								new Range(12, 13), // CDEV
								new Range(14, 21), // FVAR
								new Range(22, 22), // CVAR
								new Range(23, 27), new Range(28, 77)

						});
						setNames(new String[] { "CodProvincia", "CodMunicipio", "CodPseudoVia", "TipoInf", "CausaDev",
								"FechaVar", "Codvariacion", "CodPseudoViaNuevo", "NombrePseudoVia" });
						setStrict(false);
					}
				});

				setFieldSetMapper(new PseudoViasMapeaFila());
			}
		});

		return reader;

	}

	/***************************************************************************************************************/
	// WRITERS
	/***************************************************************************************************************/

	/* WRITER : Funcion que persiste los datos en la BD */

	@Bean
	public JdbcBatchItemWriter<Upoblacional> UpoblacionalWriter() throws Exception {

		log.info("** INICIANDO WRITER U.POBLACIONALES EN BASE DE DATOS **");
		JdbcBatchItemWriter<Upoblacional> writer = new JdbcBatchItemWriter<Upoblacional>();
		writer.setDataSource(dataSource);

		writer.setSql(
				"insert into upoblacional(cod_provincia,cod_municipio,cod_unidad_poblacional,tipo_inf, causa_dev, fecha_var, codvariacion, nombre_municipio, nombre_municipio50, nombre_municipio_corto, "
						+ "nombre_entidad_colectiva, nombre_ent_col50, nombre_ent_col_corto, nombre_entidad_singular, nombre_ent_sin50, nombre_ent_sing_corto, nombre_nucleo_dise, nombre_nucleo_dise50, nombre_nucleo_dise_corto) values "
						+ "(:CodProvincia, :CodMunicipio, :CodUnidadPoblacional, :TipoInf, :CausaDev, :FechaVar, :Codvariacion, :NombreMunicipio, :NombreMunicipio50, :NombreMunicipioCorto, "
						+ ":NombreEntidadColectiva, :NombreEntCol50, :NombreEntColCorto, :NombreEntidadSingular, :NombreEntSin50, :NombreEntSingCorto, :NombreNucleoDise, :NombreNucleoDise50, :NombreNucleoDiseCorto )");
		writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<Upoblacional>());

		

		return writer;

	}

	/* WRITER : Funcion que persiste los datos en la BD */

	@Bean
	public JdbcBatchItemWriter<Via> ViaWriter() throws Exception {

		log.info("** INICIANDO WRITER VIAS EN BASE DE DATOS **");
		JdbcBatchItemWriter<Via> writer = new JdbcBatchItemWriter<Via>();
		writer.setDataSource(dataSource);

		writer.setSql(
				"insert into vias(cod_provincia, cod_municipio, cod_via, tipo_inf, causa_dev, fecha_var, codvariacion, cod_via_nuevo, tipo_via_nuevo, pos_tipo_via, "
						+ "nombre_via, nombre_via_corto) values "
						+ "(:CodProvincia, :CodMunicipio, :CodVia, :TipoInf, :CausaDev, :FechaVar, :Codvariacion, :CodViaNuevo, :TipoViaNuevo, :PosTipoVia, "
						+ ":NombreVia, :NombreViaCorto)");
		writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<Via>());

		return writer;

	}

	/* WRITER : Funcion que persiste los datos en la BD */

	@Bean
	public JdbcBatchItemWriter<PseudoVia> PseudoViaWriter() throws Exception {

		log.info("** INICIANDO WRITER PSEUDOVIAS EN BASE DE DATOS **");
		JdbcBatchItemWriter<PseudoVia> writer = new JdbcBatchItemWriter<PseudoVia>();
		writer.setDataSource(dataSource);

		writer.setSql(
				"insert into pseudovias(cod_provincia, cod_municipio, cod_pseudo_via, tipo_inf, causa_dev, fecha_var, codvariacion, cod_pseudo_via_nuevo, nombre_pseudo_via) "
						+ "values (:CodProvincia, :CodMunicipio, :CodPseudoVia, :TipoInf, :CausaDev, :FechaVar, :Codvariacion, :CodPseudoViaNuevo, :NombrePseudoVia )");
		writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<PseudoVia>());


		return writer;

	}


	
//	@Bean
//	public UpoblacionalWriter upwriter() {
//		return new UpoblacionalWriter();
//		
//	}

	
}
