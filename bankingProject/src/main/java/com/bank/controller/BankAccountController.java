package com.bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.bank.form.SendMoneyForm;
import com.bank.service.inter.BankAccountServiceInter;

@RestController
public class BankAccountController {
	
	
	@Autowired
	BankAccountServiceInter bankAccountService;
 
    @GetMapping("/")
    public ModelAndView showBankAccounts(Model model) {
    	return bankAccountService.showBankAccounts(model);
    }
 
    @GetMapping("/sendMoney")
    public ModelAndView viewSendMoneyPage(Model model) {
        return bankAccountService.viewSendMoneyPage(model);
    }
 
  
    @PostMapping("/sendMoney")
    public ModelAndView processSendMoney(Model model, SendMoneyForm sendMoneyForm) {
 
        System.out.println("Send Money: " + sendMoneyForm.getAmount());
        return bankAccountService.processSendMoney(model, sendMoneyForm);
        
    }


}
