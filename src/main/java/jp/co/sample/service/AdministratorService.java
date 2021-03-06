package jp.co.sample.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.sample.domain.Administrator;
import jp.co.sample.repository.AdministratorRepository;

@Service
@Transactional
public class AdministratorService {

	@Autowired
	private AdministratorRepository addAdministratorRepository;
	
	/**
	 * 管理者情報を挿入する。
	 * @param administrator
	 */
	public void insert(Administrator administrator) {
		addAdministratorRepository.insert(administrator);
	}
	
	/**
	 * ログイン処理する。
	 * @param mailAddress
	 * @param password
	 * @return
	 */
	
	public Administrator login(String mailAddress, String password) {
		return addAdministratorRepository.findByMailAddressAndPassword(mailAddress, password);
	}
	
	
	
}
