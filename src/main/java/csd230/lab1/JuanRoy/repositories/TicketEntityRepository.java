package csd230.lab1.JuanRoy.repositories;

import csd230.lab1.JuanRoy.entities.TicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketEntityRepository extends JpaRepository<TicketEntity, Long> {
}

