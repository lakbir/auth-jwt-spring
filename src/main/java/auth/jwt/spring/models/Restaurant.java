package auth.jwt.spring.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Restaurant implements Serializable {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    
    @NotBlank
    @Size(min = 3, max = 50)
	private String name;
    
    @NotBlank
    @Size(min = 10, max = 200)
	private String description;
    
    @NotBlank
    @Size(min = 10, max = 13)
	private String tel;
	
    @NotBlank
    @Size(max = 50)
    @Email
	private String email;
    
    @NotBlank
    @Size(min = 3, max = 50)
	private String adresse;
    
    @NotBlank
    @Size(min = 3, max = 6)
	private String codePostale;
    
    @NotBlank
    @Size(min = 3, max = 20)
	private String ville;
    
    private Long idUser;
}
