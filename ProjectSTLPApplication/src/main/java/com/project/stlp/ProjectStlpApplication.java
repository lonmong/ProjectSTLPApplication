package com.project.stlp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.project.stlp.entity.Login;
import com.project.stlp.repository.LoginRepository;
import com.project.stlp.util.PasswordUtil;

@SpringBootApplication
public class ProjectStlpApplication implements CommandLineRunner {
	
	@Autowired
	LoginRepository loginRepository;
	
	private static String SALT = "123456";
	private String password = "stlpappadmin";
	
	public static void main(String[] args) {
		SpringApplication.run(ProjectStlpApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Login login = new Login();
		login.setUsername("adminmirrow");
		login.setPassword(PasswordUtil.getInstance().createPassword(password, SALT));
		login.setType(0);
		loginRepository.save(login);
	}
}
