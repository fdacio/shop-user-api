package br.com.daciosoftware.shop.user.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import br.com.daciosoftware.shop.product.dto.CategoryDTO;
import br.com.daciosoftware.shop.user.entity.User;
import br.com.daciosoftware.shop.user.validator.CPF;
import br.com.daciosoftware.shop.user.validator.CPFUserExists;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@CPFUserExists
public class UserDTO {

	private Long id;
	
	@NotBlank(message="Informe o nome")
	@Size(message="Nome tem que ter no máximo 80 caracteres", max = 80)
	private String nome;
	
	@NotBlank(message="Informe o CPF")
	@Size(message="CPF tem que ter no máximo 11 caracteres", max = 11)
	@CPF
	private String cpf;
	
	@NotBlank(message="Informe o endereço")
	@Size(message="Endereço tem que ter no máximo 100 caracteres", max = 100)
	private String endereco;
	
	@NotBlank(message="Informe o email")
	@Size(message="Email tem que ter no máximo 100 caracteres", max = 100)
	private String email;
	
	@NotBlank(message="Informe o telefone")
	@Size(message="Telefone tem que ter no máximo 20 caracteres", max = 20)
	private String telefone;
	
	private String key;
	private LocalDateTime dataCadastro;
	
	private List<CategoryDTO> interesses;
	
	public static UserDTO convert(User user) {		
		UserDTO userDTO = new UserDTO();
		userDTO.setId(user.getId());
		userDTO.setNome(user.getNome());
		userDTO.setCpf(user.getCpf());
		userDTO.setEndereco(user.getEndereco());
		userDTO.setEmail(user.getEmail());
		userDTO.setTelefone(user.getTelefone());
		userDTO.setKey(user.getKey());
		userDTO.setDataCadastro(user.getDataCadastro());
		if (user.getInteresses() != null)
			userDTO.setInteresses(user.getInteresses().stream().map(CategoryDTO::convert).collect(Collectors.toList()));
		return userDTO;
	}

	@Override
	public String toString() {
		return "UserDTO [id=" + id + ", nome=" + nome + ", cpf=" + cpf + ", endereco=" + endereco + ", email=" + email
				+ ", telefone=" + telefone + ", dataCadastro=" + dataCadastro + "]";
	}
	
}
