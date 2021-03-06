package jp.co.sample.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sample.domain.Employee;
import jp.co.sample.form.UpdateEmployeeForm;
import jp.co.sample.service.EmployeeService;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
	
	@Autowired
	private HttpSession administratorSession;
	
	@Autowired
	private EmployeeService employeeService;
	
	@ModelAttribute
	public UpdateEmployeeForm setUpUpdateEmployeeForm() {
		return new UpdateEmployeeForm();
	}
	
	@RequestMapping("/showList")
	public String showList(Model model) {
		List<Employee> employeeList = employeeService.showList();
		model.addAttribute("employeeList", employeeList);
		return "employee/list";
	}
	

	@RequestMapping("/showDetail")
	public String showDeail(String id, Model model) {
		Employee employee = employeeService.showDetail(Integer.parseInt(id));
		model.addAttribute("employee", employee);
		
		return "employee/detail";
	}
	
	/**
	 *  扶養人数の更新。
	 * @param updateEmployeeForm
	 * @return 
	 */
	
	@RequestMapping("/update")
	public String update(@Validated UpdateEmployeeForm updateEmployeeForm,BindingResult result, Model model,String id) {
		
		if(result.hasErrors()) {
			return showDeail(id, model);
		}
		
		
			Employee employee = new Employee();
			employee.setId(Integer.parseInt(updateEmployeeForm.getId()));
			employee.setDependentsCount(Integer.parseInt(updateEmployeeForm.getDependentsCount()));
			employeeService.update(employee);
			
			return "redirect:/employee/showList";

		
	}
	
	@RequestMapping("/logout")
	public String logout() {
		administratorSession.invalidate();
		return "forward://";
	}
	
}
