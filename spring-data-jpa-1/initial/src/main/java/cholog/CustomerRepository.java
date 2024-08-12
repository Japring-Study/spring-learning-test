package cholog;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findByLastName(String lastName);
    List<Customer> findByLastNameIgnoreCase(String lastName);
    List<Customer> findByLastNameOrderByFirstNameDesc(String lastName);
}
