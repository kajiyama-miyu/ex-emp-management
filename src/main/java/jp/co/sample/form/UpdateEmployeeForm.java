package jp.co.sample.form;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Range;


public class UpdateEmployeeForm {
	
	private String id;
	
	@NotBlank(message = "扶養人数を入力してください")
	@Range(min = 0, max = 10, message = "0~10までの数値を入力してください")
	private String dependentsCount;
	
	@Override
	public String toString() {
		return "UpdateEmployeeForm [id=" + id + ", dependentsCount=" + dependentsCount + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDependentsCount() {
		return dependentsCount;
	}

	public void setDependentsCount(String dependentsCount) {
		this.dependentsCount = dependentsCount;
	}
	
	
	
	

}
