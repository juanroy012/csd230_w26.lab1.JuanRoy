package csd230.lab1.JuanRoy.repositories;

import csd230.lab1.JuanRoy.entities.DiscMagEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiscMagEntityRepository extends JpaRepository<DiscMagEntity, Long> {
    DiscMagEntity findById(long id);
}
