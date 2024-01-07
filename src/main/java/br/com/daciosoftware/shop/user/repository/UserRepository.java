package br.com.daciosoftware.shop.user.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.daciosoftware.shop.modelos.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
	User findByCpf(String cpf);
	
	List<User> findByNomeContainingIgnoreCase(String nome);
	
	Optional<User> findByIdAndKey(Long id, String key);
}
