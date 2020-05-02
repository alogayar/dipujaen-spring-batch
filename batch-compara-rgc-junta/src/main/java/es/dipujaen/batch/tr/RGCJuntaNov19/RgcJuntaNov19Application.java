package es.dipujaen.batch.tr.RGCJuntaNov19;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableBatchProcessing
public class RgcJuntaNov19Application {

	public static void main(String[] args) {
		SpringApplication.run(RgcJuntaNov19Application.class, args);
	}

}
