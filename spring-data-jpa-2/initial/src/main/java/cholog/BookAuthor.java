package cholog;

import jakarta.persistence.*;

@Entity
public class BookAuthor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    Book book;

    @ManyToOne
    Author author;

    public BookAuthor(Book book, Author author) {
        this.book = book;
        this.author = author;
    }

    public BookAuthor() {

    }
}
