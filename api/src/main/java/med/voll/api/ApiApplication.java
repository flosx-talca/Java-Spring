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

}
