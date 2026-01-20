package my.study.opencode.repository;

import java.util.List;
import my.study.opencode.dto.ProductDto;
import my.study.opencode.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, Long> {

  @Query("SELECT new my.study.opencode.dto.ProductDto(p.id, p.name, p.price) FROM Product p")
  List<ProductDto> findProductDtos();
}
