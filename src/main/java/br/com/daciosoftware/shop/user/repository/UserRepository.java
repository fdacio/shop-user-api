package br.com.daciosoftware.shop.user.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.daciosoftware.shop.user.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
	User findByCpf(String cpf);
	
	List<User> findByNomeContaining(String nome);
}
