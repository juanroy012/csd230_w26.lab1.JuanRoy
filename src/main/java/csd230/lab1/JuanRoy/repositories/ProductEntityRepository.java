package csd230.lab1.JuanRoy.repositories;

import csd230.lab1.JuanRoy.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductEntityRepository extends JpaRepository<ProductEntity, Long> {
}
