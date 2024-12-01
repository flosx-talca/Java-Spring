package med.voll.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiApplication {


	public static void main(String[] args) {

		SpringApplication.run(ApiApplication.class, args);

	}

	/* {
"nombre": "Orlando ROzas",
"email": "orozas@live.cl",
"documento": "123456",
"especialidad":"ORTOPEDIA",
"direccion":{
	"calle": "Calle 1",
	"distrito": "distrito 1",
	"ciudad": "Talca",
	"numero": "1",
	"complemento": "a"
	}
}
	*/

	// delete from flyway_schema_history where success = 0;  cuando no se detiene el proyecto al ahacer un migration
	// http://localhost:8080/medico?size=10&page=0&sort=nombre
}
