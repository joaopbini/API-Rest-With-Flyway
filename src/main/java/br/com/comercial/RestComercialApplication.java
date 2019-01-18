package br.com.comercial;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import br.com.comercial.model.User;
import br.com.comercial.repositorio.UserRepository;

@SpringBootApplication
@RestController
@RequestMapping(value = "/api")
public class RestComercialApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestComercialApplication.class, args);
	}

	@Autowired
	private UserRepository usuarioRepositorio;

	/*
	 * Método para inserir usuários no banco de dados
	 * 
	 * @param name, email
	 * 
	 * @return user
	 */
	@RequestMapping(value = "/criar", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public User save(@RequestBody User u) {
		return usuarioRepositorio.save(u);
	}

	/*
	 * Método para buscar determinado usuário pelo seu id
	 * 
	 * @param id
	 * 
	 * @return user http://127.0.0.1:8080/api/usuario?id=6
	 */
	@RequestMapping(value = "/usuario", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Optional<User> get_user(@RequestParam(value = "id") long id) {
		// System.out.println(id);
		return usuarioRepositorio.findById(id);
	}

	/*
	 * Método para buscar todos os usuários
	 * 
	 * @page Page you want to retrieve, 0 indexed and defaults to 0.
	 * 
	 * @size Size of the page you want to retrieve, defaults to 20.
	 * 
	 * @sort=firstname&sort=lastname,asc
	 */
	@RequestMapping(value = "/usuarios", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Page<User> get_users(User user, Pageable pageable) {
		return usuarioRepositorio.findAll(pageable);
		// return "users";
	}

	/*
	 * Método para deletar um usuário
	 * 
	 * @param id
	 * 
	 * @return user http://127.0.0.1:8080/api/deletar?id=1
	 */
	@RequestMapping(value = "/deletar", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String delete_user(@RequestParam(value = "id") long id) {
		usuarioRepositorio.deleteById(id);
		return "Usuário deletado com sucesso";
	}

	/*
	 * Método para atualizar determinado cliente
	 * 
	 * @param id
	 * 
	 * @return user
	 */
	@RequestMapping(value = "/atualizar", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String update_user(@RequestBody User user) {

		usuarioRepositorio.findById(user.getId());
		// user.toString();

		if(usuarioRepositorio.existsById(user.getId())) {
			user.setUserName(user.getUserName());
			user.setFirstName(user.getFirstName());
			user.setLastName(user.getLastName());
			usuarioRepositorio.save(user);

			return "Atualizado com sucesso";
		}
		
		return "Usuário não existe";
		

	}

}
