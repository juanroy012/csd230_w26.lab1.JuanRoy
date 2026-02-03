package csd230.lab1.JuanRoy.repositories;

import csd230.lab1.JuanRoy.entities.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookEntityRepository extends JpaRepository<BookEntity, Long> {
    BookEntity findById(long id);
}
