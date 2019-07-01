package utn.tacs.grupo3.spring;

import java.time.LocalDate;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import utn.tacs.grupo3.service.helper.TodayHelper;

@Configuration
@ComponentScan(basePackages = {"utn.tacs.grupo3"})
public class SpringControllerConfiguration {
	
	@Bean
	public TodayHelper todayHelper() {
		return ()-> LocalDate.now();		
	}
}
