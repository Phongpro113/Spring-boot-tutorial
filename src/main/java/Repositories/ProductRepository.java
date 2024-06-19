package Repositories;

import com.tutorial.apidemo.Spring.boot.tutorial.Models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
