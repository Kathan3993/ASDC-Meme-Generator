package com.group5.memegenerator;

import com.group5.memegenerator.database.*;
import com.group5.memegenerator.database.factory.DatabaseOperationFactory;
import com.group5.memegenerator.database.factory.MemeDAOFactory;
import com.group5.memegenerator.database.factory.MemeTemplateDAOFactory;
import com.group5.memegenerator.model.*;
import com.group5.memegenerator.model.factory.MemeFactory;
import com.group5.memegenerator.model.factory.TemplateLibraryFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Connection;
import java.util.ArrayList;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@Controller
public class MemegeneratorApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(MemegeneratorApplication.class, args);
		Environment env = ctx.getEnvironment();
		String url = env.getProperty("spring.datasource.url");
		String username = env.getProperty("spring.datasource.username");
		String password = env.getProperty("spring.datasource.password");
		Database.instance().setInitialValues(url, username, password);
	}

	@GetMapping("/")
	public ModelAndView startPage() {
		return new ModelAndView("redirect:/login");
	}

}
