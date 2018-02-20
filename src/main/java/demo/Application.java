package demo;

import org.slf4j.*;
import org.springframework.amqp.rabbit.core.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cloud.client.discovery.*;
import org.springframework.context.annotation.*;

import demo.model.*;
import demo.model.repo.*;
import springfox.documentation.builders.*;
import springfox.documentation.spi.*;
import springfox.documentation.spring.web.plugins.*;
import springfox.documentation.swagger2.annotations.*;

@SpringBootApplication
//@EnableDiscoveryClient
@EnableSwagger2
public class Application {

	private static final Logger log = LoggerFactory.getLogger(Application.class);

	@Autowired
	LeaveRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	// @Autowired
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any()).build();
	}

	@Bean
	public CommandLineRunner demo(LeaveRepository repository) {
		return (args) -> {
			
//			Address addr = new Address("My address", "A1B2C3");
//			Customer cust = new Customer("Abigail", "Smith", addr);

			LeaveRecord leave = new LeaveRecord("Abigail Smith","Vacation","01-May-2018", "01-May-2018", "Approved","Demo");
			//leave.setCustomer(cust);
			
			repository.save(leave);  
			
			//repository.save(order);
			
			log.info("Leaves found with findAll():");
			log.info("-------------------------------");
			for (LeaveRecord lv : repository.findAll()) {
				System.out.println(lv.getType());
			}
			log.info("");
		};
	}
}
