package jp.co.sample.service;

import org.springframework.transaction.annotation.Transactional;

import jp.co.sample.domain.Employee;
import jp.co.sample.repository.EmployeeRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	/**
	 * 従業員情報を全件取得する。
	 * @return　全従業員情報
	 */
	public List<Employee> showList(){
		return employeeRepository.findAll();
	}
	/**
	 * 従業員情報を取得。
	 * @param id
	 * @return 従業員情報
	 */
	
	public Employee showDetail(Integer id) {
		return employeeRepository.load(id);
	}
	
	/**
	 * 従業員情報を更新する。
	 * @param employee
	 */
	public void update(Employee employee) {
		employeeRepository.update(employee);
	}
}
