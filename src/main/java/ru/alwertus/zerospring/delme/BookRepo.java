package ru.alwertus.zerospring.delme;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepo extends JpaRepository<Book, Long> {
    Optional<Book> findByName(String name);
    List<Book> findAllByAccessRole(String accessRole);
}
