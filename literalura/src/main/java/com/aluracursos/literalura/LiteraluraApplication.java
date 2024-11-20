package com.aluracursos.literalura;

import com.aluracursos.literalura.principal.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;




@SpringBootApplication //Injecccion de dependencias
public class LiteraluraApplication implements CommandLineRunner {

	@Autowired //Injeccion de dependencias
	//private SerieRepository repository;


	public static void main(String[] args) {
		SpringApplication.run(LiteraluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal();
		principal.muestraElMenu();




	}
}


/*
@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner {

	//@Autowired //permite la inyeccion de dependencias por parte de spring

	@Autowired
	private LibroRepository libroRepository;
	@Autowired
	private AutorRepository autorRepository;
	public static void main(String[] args) {

		SpringApplication.run(LiteraluraApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception {
		//Principal principal = new Principal();


		Principal principal = new Principal(autorRepository, libroRepository);
		principal.muestraElMenu();
	}

}*/