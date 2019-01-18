package br.com.comercial.repositorio;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.comercial.model.User;

import org.springframework.data.domain.Page;


public interface UserRepository extends CrudRepository<User, Long>, PagingAndSortingRepository<User, Long> {
	
	//List<User> findByNameLike(String username);
	

}
