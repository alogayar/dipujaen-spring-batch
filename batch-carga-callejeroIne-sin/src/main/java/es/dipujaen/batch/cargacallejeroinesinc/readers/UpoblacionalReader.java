package es.dipujaen.batch.cargacallejeroinesinc.readers;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ItemReadListener;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.batch.item.file.transform.FixedLengthTokenizer;
import org.springframework.batch.item.file.transform.Range;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;

import es.dipujaen.batch.cargacallejeroinesinc.models.Upoblacional;



@Component
public class UpoblacionalReader  {
	
	private static final Logger log = LoggerFactory.getLogger(UpoblacionalReader.class);
	static int counter = 0;
	

	@Bean	
	@StepScope
	public FlatFileItemReader<Upoblacional> UpoblacionalItemReader(@Value("#{jobParameters['FilePath']}") String path) {	
		
		FileSystemResource filename = new FileSystemResource(path);	
		
		FlatFileItemReader<Upoblacional> upreader = new FlatFileItemReader<Upoblacional>();
		upreader.setResource(filename);
		  
		
		
		// upreader.setLinesToSkip(1);
		LineMapper<Upoblacional> upMapeaLinea = createupMapeaLinea();
		upreader.setLineMapper(upMapeaLinea);
		return upreader;

	}

	private LineMapper<Upoblacional> createupMapeaLinea() {

		DefaultLineMapper<Upoblacional> mapeador = new DefaultLineMapper<Upoblacional>();

		/*---------------------------------------------------------------*/
		FixedLengthTokenizer columnas = new FixedLengthTokenizer();

		columnas.setColumns(new Range[] { new Range(1, 2), // CPRO
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

		columnas.setNames(new String[] { "Cpro", "Cmun", "Cun", "TipoInf", "Cdev", "Fvar", "Cvar", "Nmun", "Dmun50",
				"Nmunc", "Nentco", "Nentco50", "Nentcoc", "Nentsi", "Nentsi50", "Nentsic", "Nnucle", "Nnucle50",
				"Nnuclec" });

		columnas.setStrict(false);

		/*---------------------------------------------------------------*/

		mapeador.setLineTokenizer(columnas);
		mapeador.setFieldSetMapper(new FieldSetMapper<Upoblacional>() {

			@Override
			public Upoblacional mapFieldSet(FieldSet fieldSet) throws BindException {
				Upoblacional upoblacional = new Upoblacional();

				upoblacional.setCodProvincia(fieldSet.readInt("Cpro"));
				upoblacional.setCodMunicipio(fieldSet.readInt("Cmun"));
				upoblacional.setCodUnidadPoblacional(fieldSet.readInt("Cun"));
				upoblacional.setTipoInf(fieldSet.readString("TipoInf"));
				upoblacional.setCausaDev(fieldSet.readString("Cdev"));
				
				try {
					upoblacional.setFechaVar(new SimpleDateFormat("yyyyMMdd").parse(fieldSet.readString("Fvar")));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				upoblacional.setCodvariacion(fieldSet.readString("Cvar"));
				upoblacional.setNombreMunicipio(fieldSet.readString("Nmun"));
				upoblacional.setCodvariacion(fieldSet.readString("Cvar"));
				upoblacional.setNombreMunicipio50(fieldSet.readString("Dmun50"));
				upoblacional.setNombreMunicipioCorto(fieldSet.readString("Nmunc"));
				upoblacional.setNombreEntidadColectiva(fieldSet.readString("Nentco"));
				upoblacional.setNombreEntCol50(fieldSet.readString("Nentco50"));
				upoblacional.setNombreEntColCorto(fieldSet.readString("Nentcoc"));
				upoblacional.setNombreEntidadSingular(fieldSet.readString("Nentsi"));
				upoblacional.setNombreEntSin50(fieldSet.readString("Nentsi50"));
				upoblacional.setNombreEntSingCorto(fieldSet.readString("Nentsic"));
				upoblacional.setNombreNucleoDise(fieldSet.readString("Nnucle"));
				upoblacional.setNombreNucleoDise50(fieldSet.readString("Nnucle50"));
				upoblacional.setNombreNucleoDiseCorto(fieldSet.readString("Nnuclec"));

				 log.info("PROCESANDO..... U.POBLACIONAL: {} ", fieldSet.readString("Dmun50"));
				 

				return upoblacional;
			}
		});

		return mapeador;
	}

}