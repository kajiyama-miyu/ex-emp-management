package jp.co.sample.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.sample.domain.Administrator;
/**
 * 
 * @author kajiyamamiyu
 *
 */

@Repository
public class AdministratorRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;
	
	private static final RowMapper<Administrator> ADMINISTRATOR_ROW_MAPPER
		= (rs,i) -> {
			Administrator administrator = new Administrator();
			administrator.setId(rs.getInt("id"));
			administrator.setName(rs.getString("name"));
			administrator.setMailAddress(rs.getString("mail_address"));
			administrator.setPassword(rs.getString("password"));
			return administrator;		
		};
		
		
		
		public void insert(Administrator administrator) {
			SqlParameterSource param = new BeanPropertySqlParameterSource(administrator);
			String sql = "INSERT INTO administrators(name, mail_address, password) VALUES (:name, :mail_address, :password)";
			
			template.update(sql, param);
		}
		
		
		public List<Administrator> findByMailAddressAndPassword(String mailAdress, String password) {
			String sql;
			
			if(!(mailAdress==null && password==null)) {
			sql= "SELECT mail_address, password FROM administrators";
			
			} else {
				return null;
			}
			
			List<Administrator> administratorList = template.query(sql, ADMINISTRATOR_ROW_MAPPER);
		
			/**
			 * @return mail_addressとpasswordが入ったリストを返す
			 */
			return administratorList;
			
			
		}
		
}
