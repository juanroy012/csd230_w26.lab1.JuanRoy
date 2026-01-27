package csd230.lab1.JuanRoy.repositories;

import csd230.lab1.JuanRoy.entities.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderEntityRepository extends JpaRepository<OrderEntity, Long> {
    OrderEntity getOrderEntitiesById(Long id);
}
