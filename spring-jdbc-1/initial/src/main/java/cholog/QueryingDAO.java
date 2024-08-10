package cholog;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class QueryingDAO {
    private JdbcTemplate jdbcTemplate;

    public QueryingDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Customer> actorRowMapper = (resultSet, rowNum) -> {
        Customer customer = new Customer(
                resultSet.getLong("id"),
                resultSet.getString("first_name"),
                resultSet.getString("last_name")
        );
        return customer;
    };
    /*
    추후 rowMapper에 대해 학습해보고 이용해보기
    */

    /**
     * public <T> T queryForObject(String sql, Class<T> requiredType)
     */
    public int count() {
        int rowCount = jdbcTemplate.queryForObject("select COUNT(*) from customers", Integer.class);
        return rowCount;
    }

    /**
     * public <T> T queryForObject(String sql, Class<T> requiredType, @Nullable Object... args)
     */
    public String getLastName(Long id) {
        String lastName = jdbcTemplate.queryForObject("select last_name from customers where id = ?", String.class, id);
        return lastName;
    }

    /**
     * public <T> T queryForObject(String sql, RowMapper<T> rowMapper, @Nullable Object... args)
     */
    public Customer findCustomerById(Long id) {
        String sql = "select id, first_name, last_name from customers where id = ?";
        Customer customer = jdbcTemplate.queryForObject(sql,
                (resultSet, rowNum) -> {
                    Customer customer1 = new Customer(
                            resultSet.getLong("id"),
                            resultSet.getString("first_name"),
                            resultSet.getString("last_name")
                    );
                    return customer1;
                }, id);
        return customer;
    }

    public void findAllCustomersWithAllAttributes() {
        String sql = "select * from customers";
        List<Map<String, Object>> customers = jdbcTemplate.queryForList(sql);

        for(Map<String, Object> customer : customers) {
            System.out.println(customer);
        }
    }

    /**
     * public <T> List<T> query(String sql, RowMapper<T> rowMapper)
     */
    public List<Customer> findAllCustomers() {
        String sql = "select id, first_name, last_name from customers";

//        RowMapper rowMapper = (resultSet, rowNum) -> {
//            Customer customer = new Customer(
//                    resultSet.getLong("id"),
//                    resultSet.getString("first_name"),
//                    resultSet.getString("last_name")
//            );
//            return customer;
//        };

        List<Customer> customerList = jdbcTemplate.query(sql, actorRowMapper);
        return customerList;
    }

    /**
     * public <T> List<T> query(String sql, RowMapper<T> rowMapper, @Nullable Object... args)
     */
    public List<Customer> findCustomerByFirstName(String firstName) {
        String sql = "select id, first_name, last_name from customers where first_name = ?";
        List<Customer> customerList = jdbcTemplate.query(sql, actorRowMapper, firstName);
        return customerList;
    }
}
