package cholog;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class BookAuthor {
    @Id @GeneratedValue
    Long id;

    @ManyToOne
    Book book;

    public BookAuthor(){

    }

    @ManyToOne
    Author author;
    public BookAuthor(Book book, Author author) {
        this.book = book;
        this.author = author;

    }
}
