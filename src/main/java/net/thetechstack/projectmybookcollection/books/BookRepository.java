package net.thetechstack.projectmybookcollection.books;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {
    List<Book> findByOriginalTitleContainingIgnoreCase(String title);
}
