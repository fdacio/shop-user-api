package br.com.daciosoftware.shop.user.dto;

import java.time.LocalDateTime;

import br.com.daciosoftware.shop.user.entity.User;
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

	private Long id;
	@NotBlank(message="Informe o nome")
	private String nome;
	@NotBlank(message="Informe o CPF")
	private String cpf;
	private String endereco;
	@NotBlank(message="Informe o email")
	private String email;
	private String telefone;
	private LocalDateTime dataCadastro;  
	
	public static UserDTO convert(User user) {		
		UserDTO userDTO = new UserDTO();
		userDTO.setId(user.getId());
		userDTO.setNome(user.getNome());
		userDTO.setCpf(user.getCpf());
		userDTO.setEndereco(user.getEndereco());
		userDTO.setEmail(user.getEmail());
		userDTO.setTelefone(user.getTelefone());
		userDTO.setDataCadastro(user.getDataCadastro());		
		return userDTO;
	}

	@Override
	public String toString() {
		return "UserDTO [id=" + id + ", nome=" + nome + ", cpf=" + cpf + ", endereco=" + endereco + ", email=" + email
				+ ", telefone=" + telefone + ", dataCadastro=" + dataCadastro + "]";
	}
	
}
