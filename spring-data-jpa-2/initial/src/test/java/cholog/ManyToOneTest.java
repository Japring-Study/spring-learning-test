package cholog;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class ManyToOneTest {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private PublisherRepository publisherRepository;

    @Test
    void uniDirection() {
        Publisher publisher = new Publisher("출판사");
        entityManager.persist(publisher);

        Book book = new Book("책", publisher);
        entityManager.persist(book);

        Book persistBook = entityManager.find(Book.class, book.getId());

        assertThat(persistBook).isNotNull();
        assertThat(persistBook.getPublisher()).isNotNull();
    }

    @Test
    void biDirection() {
        Publisher publisher = new Publisher("출판사");
        entityManager.persist(publisher);

        Book book = new Book("책", publisher);
        entityManager.persist(book);
        publisher.addBook(book);

        entityManager.flush(); // 영속성 컨텍스트에 있는 publisher와 book 엔티티의 상태(추가 및 연관관계 설정)가 데이터베이스에 반영됩니다. 즉, Publisher와 Book 객체가 실제 데이터베이스에 저장
        entityManager.clear(); // 영속성 컨텍스트를 초기화하여 현재 관리되고 있는 모든 엔티티를 비영속 상태로 만듭니다. 이후 Publisher 객체를 조회할 때, 영속성 컨텍스트가 아닌 데이터베이스에서 해당 엔티티를 다시 가져오게 됩니다

        Publisher persistPublisher = entityManager.find(Publisher.class, publisher.getId());
        assertThat(persistPublisher).isNotNull();
        assertThat(persistPublisher.getBooks()).isNotEmpty();
    }

    @Test
    void findByIdForBook() {
        Publisher publisher = new Publisher("출판사");
        entityManager.persist(publisher);

        Book book = new Book("책", publisher);
        entityManager.persist(book);

        entityManager.flush();
        entityManager.clear();

        Optional<Book> persistBook = bookRepository.findById(book.getId());
        assertThat(persistBook).isPresent();
        assertThat(persistBook.get().getPublisher()).isNotNull();
    }

    @Test
    void findByIdForPublisher() {
        Publisher publisher = new Publisher("출판사");
        entityManager.persist(publisher);

        Book book = new Book("책", publisher);
        entityManager.persist(book);

        entityManager.flush();
        entityManager.clear();

        Optional<Publisher> persistPublisher = publisherRepository.findById(publisher.getId());
        assertThat(persistPublisher).isPresent();
        assertThat(persistPublisher.get().getBooks()).isNotNull();
    }
}
