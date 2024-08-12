package cholog;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class UpdatingDAO {
	private JdbcTemplate jdbcTemplate;

	public UpdatingDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

    /*
    private final RowMapper<Customer> actorRowMapper = (resultSet, rowNum) -> {
        Customer customer = new Customer(
                resultSet.getLong("id"),
                resultSet.getString("first_name"),
                resultSet.getString("last_name")
        );
        return customer;
    };
    추후 rowMapper에 대해 학습해보고 이용해보기
    */

	/**
	 * public int update(String sql, @Nullable Object... args)
	 */
	public void insert(Customer customer) {
		int affectedRow = jdbcTemplate.update("INSERT INTO customers VALUES(?, ?)",
			customer.getFirstName(),
			customer.getLastName());
	}

	/**
	 * public int update(String sql, @Nullable Object... args)
	 */
	public int delete(Long id) {
		int affectedRow = jdbcTemplate.update("DELETE FROM customers WHERE id = ?", id);

		return affectedRow;
	}

	/**
	 * public int update(final PreparedStatementCreator psc, final KeyHolder generatedKeyHolder)
	 */
	public Long insertWithKeyHolder(Customer customer) {
		String sql = "insert into customers (first_name, last_name) values (?, ?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();

		//todo : keyHolder에 대해 학습하고, Customer를 저장후 저장된 Customer의 id를 반환하기

		Long id = keyHolder.getKey().longValue();

		return keyHolder.getKey().longValue();
	}
}
