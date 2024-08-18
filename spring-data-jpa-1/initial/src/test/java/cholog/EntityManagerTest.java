package cholog;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class EntityManagerTest {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 비영속 -> 영속
     */
    @Test
    void persist() {
        Customer customer = new Customer("Jack", "Bauer");
        entityManager.persist(customer); // JPA에서 데이터를 데이터베이스에 영구적으로 저장

        assertThat(entityManager.find(Customer.class, 1L)).isNotNull();
    }

    /**
     * 영속 -> DB
     */
    @Test
    void flush() {
        String sqlForSelectCustomer = "select * from customer where id = 1";

        Customer customer = new Customer("Jack", "Bauer");
        entityManager.persist(customer);
        customer.updateFirstName("Danial");

        Customer savedCustomer = jdbcTemplate.query(sqlForSelectCustomer, rs -> {
            rs.next();
            return new Customer(
                    rs.getLong("id"),
                    rs.getString("first_name"),
                    rs.getString("last_name"));
        });
        assertThat(savedCustomer.getFirstName()).isEqualTo("Jack");

        entityManager.flush();

        Customer updatedCustomer = jdbcTemplate.query(sqlForSelectCustomer, rs -> {
            rs.next();
            return new Customer(
                    rs.getLong("id"),
                    rs.getString("first_name"),
                    rs.getString("last_name"));
        });

        assertThat(updatedCustomer.getFirstName()).isEqualTo("Danial");
    }
}
