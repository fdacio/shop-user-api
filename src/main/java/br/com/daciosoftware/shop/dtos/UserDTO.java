package br.com.daciosoftware.shop.dtos;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

	@NotBlank(message="Informe o nome")
	private String nome;
	@NotBlank(message="Informe o CPF")
	private String cpf;
	private String endereco;
	@NotBlank(message="Informe o email")
	private String email;
	private String telefone;
	private LocalDateTime dataCadastro;  
}
