package tts.ttsexample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import tts.ttsexample.domain.CustomerRepository;
import tts.ttsexample.domain.Customer;

@SpringBootApplication
public class TtsexampleApplication {
    private static final Logger log = LoggerFactory.getLogger(TtsexampleApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(TtsexampleApplication.class, args); }

        @Bean
        CommandLineRunner runner(CustomerRepository repository) {
        return args -> {
            //save a few customers
            repository.save( new Customer("Fah", "Pariyavuth"));
            repository.save( new Customer("Jimmy", "Dang"));
            repository.save( new Customer("Ger", "Vue"));
            repository.save( new Customer("James", "Kingdon"));

            //read all customers
            log.info("Customer found with findAll():");
            log.info("------------------------------");
            for (Customer customer : repository.findAll()){
                log.info(customer.toString());
            }
            log.info("");

            //read an individual customer by ID
            repository.findById(1L)
                .ifPresent(customer -> {
                    log.info("Customer found with findAllById(1L):");
                    log.info("------------------------------------");
                    log.info(customer.toString());
                    log.info("");
                    });


            //read customer by last name
            log.info("Customer found with findByLastName('Pariyavuth'):");
            log.info("------------------------------");
            repository.findByLastName("Kingdon").forEach(bauer -> {
                log.info(bauer.toString());
            });
                log.info("");
        };
    }
}

