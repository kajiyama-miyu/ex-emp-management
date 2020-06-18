package jp.co.sample.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sample.domain.Administrator;
import jp.co.sample.form.InsertAdiministratorForm;
import jp.co.sample.form.LoginForm;
import jp.co.sample.service.AdministratorService;

@Controller
@RequestMapping("/")
public class AdministratorController {

	@Autowired
	private HttpSession administratorSession;
	
	@Autowired
	private AdministratorService administratorService;
	
	@ModelAttribute
	public InsertAdiministratorForm setUpInsertAdministratorForm() {
		return new InsertAdiministratorForm();
	}
	
	@ModelAttribute
	public LoginForm setUpLoginForm() {
		return new LoginForm();
	}
	
	@RequestMapping("/toInsert")
	public String toInsert() {
		return "administrator/insert";
	}
	/**
	 * 
	 * 管理者情報の登録。
	 * @param insertAdiministratorForm
	 * @param model
	 * @return
	 */
	@RequestMapping("/insert")
	public String insert(@Validated InsertAdiministratorForm insertAdiministratorForm, BindingResult result) {
		
		if(result.hasErrors()) {
			return toInsert();
		}
		Administrator administrator = new Administrator();
		administrator.setName(insertAdiministratorForm.getName());
		administrator.setMailAddress(insertAdiministratorForm.getMailAddress());
		administrator.setPassword(insertAdiministratorForm.getPassword());
		
		administratorService.insert(administrator);
		
		return "redirect:/";
	}
	
	@RequestMapping("/")
	public String toLogin() {
		return "administrator/login";
	}
	/**
	 * ログインする。
	 * @param loginForm
	 * @param model
	 * @return
	 */
	@RequestMapping("/login")
	public String login(LoginForm loginForm, Model model) {
		Administrator administrator = administratorService.login(loginForm.getMailAddress(), loginForm.getPassword());
		if(administrator == null) {
			model.addAttribute("failure", "メールアドレスまたはパスワードが不正です。");
			return toLogin();
		} else {
			administratorSession.setAttribute("administratorName", administrator.getName());
			return "forward:/employee/showList";
	
		}
		
	}
}
