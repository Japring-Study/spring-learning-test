package cholog;

import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface CustomerRepository extends CrudRepository<Customer,Long> {
    List<Customer> findByLastName(String lastName); //다음 부분이 이해가 잘 되지 않습니다..ㅠ
}
