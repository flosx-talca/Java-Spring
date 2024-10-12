package com.aluracursos.screenmatch;

import com.aluracursos.screenmatch.service.ConsumoApi;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScreenmatchApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ScreenmatchApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		var ConsumoApi = new ConsumoApi();
		var json = ConsumoApi.obtenerDatos("https://www.omdbapi.com/?t=Game+of+thrones&apikey=5e5aa900");
		//var json = ConsumoApi.obtenerDatos("https://coffee.alexflipnote.dev/random.json");
		System.out.println(json);
	}
}
