package net.thetechstack.projectmybookcollection.collections;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.thetechstack.projectmybookcollection.books.Book;
import net.thetechstack.projectmybookcollection.readers.Reader;
import org.hibernate.annotations.Immutable;

import javax.persistence.*;
import java.io.Serializable;

@Entity @NoArgsConstructor @Immutable @Getter
public class Collection {
    @Embeddable @NoArgsConstructor @AllArgsConstructor
    public static class Id implements Serializable {
        @Column(name = "reader_id")
        protected String readerId;
        @Column(name = "book_id")
        protected Integer bookId;
    }
    public Collection(Reader reader, Book book) {
        this.reader = reader;
        this.book = book;
        this.id.bookId = book.getBookId();
        this.id.readerId = reader.getId();
        reader.getBookCollection().add(this);
        book.getCollectedBooks().add(this);
    }
    @EmbeddedId
    private Id id = new Id();
    
    @ManyToOne @JoinColumn(name="reader_id", insertable = false, updatable = false)
    private Reader reader;
    
    @ManyToOne @JoinColumn(name="book_id", insertable = false, updatable = false)
    private Book book;
    
}
