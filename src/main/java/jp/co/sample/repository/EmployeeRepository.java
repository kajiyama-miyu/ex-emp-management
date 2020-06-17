package jp.co.sample.repository;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.sample.domain.Employee;

/**
 * 
 * @author kajiyamamiyu
 *
 */
@Repository
public class EmployeeRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;
	
	/**
	 * フィールドの値にResultSetに入っている値をセット
	 */
	
	private static final RowMapper<Employee> EMPLOYEE_ROW_MAPPER
	= (rs,i) -> {
		Employee employee = new Employee();
		employee.setId(rs.getInt("id"));
		employee.setName(rs.getString("name"));
		employee.setImage(rs.getString("image"));
		employee.setGender(rs.getString("gender"));
		employee.setHireDate(rs.getTimestamp("hire_date"));
		employee.setMailAddress(rs.getString("mail_address"));
		employee.setZipCode(rs.getString("zip_code"));
		employee.setAddress(rs.getString("address"));
		employee.setTelephone(rs.getString("telephone"));
		employee.setSalary(rs.getInt("salary"));
		employee.setCharacteristics(rs.getString("characteristics"));
		employee.setDependentsCount(rs.getInt("dependents_count"));
		
		return employee;
	};
	
	/**
	 * 
	 * @return 従業員情報が入ったリスト
	 */
	public List<Employee> findAll(){
	
		String sql = "SELECT id, name, image, gender, hire_date, mail_address, zip_code, address, telephone, salary, characteristics, dependents_count "
				+ "FROM employees ORDER BY hire_date";
		List<Employee> employeeList = template.query(sql, EMPLOYEE_ROW_MAPPER);
		
		if(employeeList.isEmpty()) {
			return Collections.emptyList();
		}else {
			return employeeList;
		}
		
	}
	
	/**
	 * 
	 * @param id
	 * @return 
	 */
	public Employee load(Integer id) {
		String sql = "SELECT id, name, image, gender, hire_date, mail_address, zip_code, address, telephone, salary, characteristics, dependents_count "
				+ "FROM employees WHERE id = :id";
		
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		
		Employee employee = template.queryForObject(sql, param, EMPLOYEE_ROW_MAPPER);
		
		return employee;
				
	}
	/**
	 * 
	 * @param employee
	 */
	public void update(Employee employee) {
		SqlParameterSource param =  new BeanPropertySqlParameterSource(employee);
		String sql = "UPDATE employees SET dependents_count=:dependents_count WHERE id=:id";
		
		template.update(sql, param);
	}
	
}
