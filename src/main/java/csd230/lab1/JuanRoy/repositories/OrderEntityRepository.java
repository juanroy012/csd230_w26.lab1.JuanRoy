package csd230.lab1.JuanRoy.repositories;

import csd230.lab1.JuanRoy.entities.OrderEntity;
import csd230.lab1.JuanRoy.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderEntityRepository extends JpaRepository<OrderEntity, Long> {
    OrderEntity getOrderEntitiesById(Long id);

    List<OrderEntity> findAllByUser(UserEntity user);
}
