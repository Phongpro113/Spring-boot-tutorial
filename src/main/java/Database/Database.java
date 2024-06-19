package Database;

import Repositories.ProductRepository;
import com.tutorial.apidemo.Spring.boot.tutorial.Models.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Database {
    private static final Logger logger = LoggerFactory.getLogger(Database.class);
    @Bean
    CommandLineRunner initDatabase(ProductRepository productRepository) {
        // logger
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                Product productA = new Product("Macbook pro 16", 2020, 2400.0, "urllll");
                Product productB = new Product("Windows pro 16", 2020, 2400.0, "urllll");
                logger.info("insert data: "+ productRepository.save(productA));
                logger.info("insert data: "+ productRepository.save(productB));
            }
        };
    }
}
