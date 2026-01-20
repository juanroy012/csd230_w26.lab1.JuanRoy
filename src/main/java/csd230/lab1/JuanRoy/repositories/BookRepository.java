package csd230.lab1.JuanRoy.repositories;

import csd230.lab1.JuanRoy.entities.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<BookEntity, Long> {
    List<BookEntity> findByAuthor(String isbn);
    BookEntity findById(long id);
}
