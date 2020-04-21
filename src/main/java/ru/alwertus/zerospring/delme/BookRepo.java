package ru.alwertus.zerospring.delme;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepo extends JpaRepository<Book, Long> {

    List<Book> findAllByAccessRole(String accessRole);
}
