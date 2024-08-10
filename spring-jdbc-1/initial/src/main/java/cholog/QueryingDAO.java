package cholog;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class QueryingDAO {
	private final RowMapper<Customer> actorRowMapper = (resultSet, rowNum) -> {
		Customer customer = new Customer(
			resultSet.getLong("id"),
			resultSet.getString("first_name"),
			resultSet.getString("last_name")
		);
		return customer;
	};
	private JdbcTemplate jdbcTemplate;

	public QueryingDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	/**
	 * public <T> T queryForObject(String sql, Class<T> requiredType)
	 */
	public int count() {
		int count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM customers", Integer.class);

		return count;
	}

	/**
	 * public <T> T queryForObject(String sql, Class<T> requiredType, @Nullable Object... args)
	 */
	public String getLastName(Long id) {
		String lastName = jdbcTemplate.queryForObject("SELECT last_name FROM customers WHERE id=?", String.class, id);

		return lastName;
	}

	/**
	 * public <T> T queryForObject(String sql, RowMapper<T> rowMapper, @Nullable Object... args)
	 */
	public Customer findCustomerById(Long id) {
		String sql = "select id, first_name, last_name from customers where id = ?";
		Customer customer = jdbcTemplate.queryForObject(sql, actorRowMapper, id);

		return customer;
	}

	/**
	 * public <T> List<T> query(String sql, RowMapper<T> rowMapper)
	 */
	public List<Customer> findAllCustomers() {
		String sql = "select id, first_name, last_name from customers";
		List<Customer> customers = jdbcTemplate.query(sql, actorRowMapper);

		return customers;
	}

	/**
	 * public <T> List<T> query(String sql, RowMapper<T> rowMapper, @Nullable Object... args)
	 */
	public List<Customer> findCustomerByFirstName(String firstName) {
		String sql = "select id, first_name, last_name from customers where first_name = ?";
		//TODO : firstName을 기준으로 customer를 list형태로 반환
		return null;
	}
}
