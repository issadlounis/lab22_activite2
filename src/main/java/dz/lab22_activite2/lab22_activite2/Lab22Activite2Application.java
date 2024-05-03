package dz.lab22_activite2.lab22_activite2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@SpringBootApplication
public class Lab22Activite2Application {

	public static void main(String[] args) {
		SpringApplication.run(Lab22Activite2Application.class, args);
	}

	@GetMapping("/admin")
	public String index(Model model) {
		model.addAttribute("message", "Console d’administration");
		return "authentification";
	}
	@GetMapping("/portail")
	public String index2(Model model){
		model.addAttribute("message", "Portail utilisateur");
		return "authentification";

	}

}
