package es.dipujaen.batch.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class Constants {
	public static final String FILE_NAME_UPOBLACIONAL_KEY = "fileNameUp";
	public static final String FILE_NAME_VIAS_KEY = "fileNameVia";
	public static final String FILE_NAME_PSEUDOVIAS_KEY = "fileNamePseudoVia";
	
	public static final String CALLE = "CALLE";
	public static final List<String> TIPOSVIA = new ArrayList<String>() ;

	public Constants() {
		TIPOSVIA.add("KALEA");
		TIPOSVIA.add("C/");
		TIPOSVIA.add("C/.");
		TIPOSVIA.add("RUA");
		TIPOSVIA.add("KALE");
	}
	
	/* Obtenemos los ficheros de la carpeta*/
	public static String[] getFiles(String dir_path) {

		String[] arr_res = null;
		File f = new File(dir_path);
		if (f.isDirectory()) {
			List<String> res = new ArrayList<>();
			File[] arr_content = f.listFiles();

			int size = arr_content.length;

			for (int i = 0; i < size; i++) {

				if (arr_content[i].isFile())
					res.add(arr_content[i].getName());
			}
			arr_res = res.toArray(new String[0]);

		} else
			System.err.println("¡ La ruta NO es una carpeta !");

		return arr_res;
	}
	

}
