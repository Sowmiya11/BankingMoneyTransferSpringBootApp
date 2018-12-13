package com.bank.service.inter;

import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import com.bank.form.SendMoneyForm;

public interface BankAccountServiceInter {
	
	public ModelAndView showBankAccounts(Model model);
	public ModelAndView viewSendMoneyPage(Model model);
	public ModelAndView processSendMoney(Model model, SendMoneyForm sendMoneyForm);
}
