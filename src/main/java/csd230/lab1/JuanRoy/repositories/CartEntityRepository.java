package csd230.lab1.JuanRoy.repositories;

import csd230.lab1.JuanRoy.entities.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartEntityRepository extends JpaRepository<CartEntity, Long> {
    void removeById(Long id);
}
