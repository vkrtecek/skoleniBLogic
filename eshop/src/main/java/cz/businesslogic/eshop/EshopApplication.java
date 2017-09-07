package cz.businesslogic.eshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

@SpringBootApplication
@EnableAsync
@EnableScheduling
@EnableCaching
@ImportResource("classpath:security.xml")
public class EshopApplication {

	@Bean
	public MapperFacade mapper() {
		MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
		return mapperFactory.getMapperFacade();
	}

	public static void main(String[] args) {
		SpringApplication.run(EshopApplication.class, args);
		/*
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String str = encoder.encode("ahoj");
		System.out.println(str);
		*/
	}
}
