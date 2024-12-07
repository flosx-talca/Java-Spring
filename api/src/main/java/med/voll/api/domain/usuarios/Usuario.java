package med.voll.api.domain.usuarios;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name="usuarios")
@Entity(name = "Usuario")
@NoArgsConstructor //genera constructor default lombok
@AllArgsConstructor //genera constructor con todos los argumentos
@Getter // genera getters
@EqualsAndHashCode(of ="id") //usa parametro id para la comparacion, en este caso de usuario
public class Usuario {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;
    private String login;
    private String clave;

}
