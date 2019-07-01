package utn.tacs.grupo3.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import utn.tacs.grupo3.model.Role;
import utn.tacs.grupo3.model.User;
import utn.tacs.grupo3.repository.mongo.UserRepository;
import utn.tacs.grupo3.repository.mongo.UserRepositoryImpl;

@SpringBootApplication
@ComponentScan(basePackages = {"utn.tacs.grupo3"})
public class Grupo3Application {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(Grupo3Application.class, args);
		createDefaultAdminIfNotExists(context);
				
	}
	
	private static void createDefaultAdminIfNotExists(ApplicationContext context) {
		UserRepository userRepo = context.getBean(UserRepositoryImpl.class);
		User defaultAdmin = new User("Juan", "Perez","JPerez1","$2a$10$drCbdd8tk2Hs.rFZScoBguX4U/SsuzjdCIZIWuQm/dEhO2/KydXca", Role.ADMIN);
		
		if (!userRepo.userExists(defaultAdmin.getUsername())) {
			userRepo.save(defaultAdmin);
		}
	}

}
