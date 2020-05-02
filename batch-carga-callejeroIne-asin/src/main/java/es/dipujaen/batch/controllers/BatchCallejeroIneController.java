package es.dipujaen.batch.controllers;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.batch.core.launch.NoSuchJobInstanceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.dipujaen.batch.listener.BatchListener;
import es.dipujaen.batch.runner.CallejeroIneJobRunnerConfig;
import es.dipujaen.batch.utils.Constants;

@RestController
@RequestMapping("/callejero")
public class BatchCallejeroIneController {

	@Autowired
	private CallejeroIneJobRunnerConfig jobRunner;

	@Value(value = "${PathDirectory}")
	private String pathDirectory;

	@Autowired
	JobOperator jobOperator;	
	
	@RequestMapping(value = "/batchs")
	public String index() {
		return "index";
	}

	@RequestMapping(value = { "/cargarIne", "/cargarIne/{pathdir}" })
	public String runJob(@PathVariable(required = false) String pathdir) throws NoSuchJobInstanceException {

		if (StringUtils.isEmpty(pathdir)) {
			pathdir = pathDirectory;
		}

		String[] files = Constants.getFiles(pathdir);

		int tama = files.length;
		if (tama == 0) {
			return String.format("La ruta %s no contiene ficheros", pathdir);
		}

		JobParametersBuilder jobParametersBuilder = new JobParametersBuilder();

		for (int i = 0; i < tama; i++) {
			// Fichero Unidades Poblacionales
			if (files[i].contains("UP")) {
				jobParametersBuilder.addString(Constants.FILE_NAME_UPOBLACIONAL_KEY, files[i].toString());
			}
			if (files[i].contains("VIAS")) {
				jobParametersBuilder.addString(Constants.FILE_NAME_VIAS_KEY, files[i].toString());
			}
			if (files[i].contains("PSEU")) {
				jobParametersBuilder.addString(Constants.FILE_NAME_PSEUDOVIAS_KEY, files[i].toString());
			}
		}

		jobParametersBuilder.addDate("date", new Date());

//		JobParametersBuilder jobParametersBuilder = new JobParametersBuilder();
//
//		jobParametersBuilder.addString(Constants.FILE_NAME_UPOBLACIONAL_KEY, "UP-NAL.F190630");
//		jobParametersBuilder.addString(Constants.FILE_NAME_VIAS_KEY, "VIAS-NAL.F190630");
//		jobParametersBuilder.addString(Constants.FILE_NAME_PSEUDOVIAS_KEY, "PSEU-NAL.F190630");
//		jobParametersBuilder.addDate("date", new Date(), true);

		jobRunner.runBatchJob(jobParametersBuilder);		
		return String.format("Procesos batch lanzado con exito.");

	}

}
