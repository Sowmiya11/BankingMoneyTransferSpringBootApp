package com.bank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import com.bank.dao.BankAccountDAO;
import com.bank.exception.BankTransactionException;
import com.bank.form.SendMoneyForm;
import com.bank.model.BankAccountInfo;
import com.bank.service.inter.BankAccountServiceInter;

@Service
public class BankAccountService implements BankAccountServiceInter {
	
	@Autowired
	BankAccountDAO bankAccountDAO;
	
	@Override
	public ModelAndView showBankAccounts(Model model) {
        List<BankAccountInfo> list = bankAccountDAO.listBankAccountInfo();
 
        model.addAttribute("accountInfos", list);
 
        return new ModelAndView("accountsPage");
    }
	
	@Override
	public ModelAndView viewSendMoneyPage(Model model) {
		 
        SendMoneyForm form = new SendMoneyForm(1L, 2L, 700d);
 
        model.addAttribute("sendMoneyForm", form);
 
        return new ModelAndView("sendMoneyPage");
    }
	
	@Override
	public ModelAndView processSendMoney(Model model, SendMoneyForm sendMoneyForm) {
		 
        System.out.println("Send Money: " + sendMoneyForm.getAmount());
 
        try {
            bankAccountDAO.sendMoney(sendMoneyForm.getFromAccountId(), //
                    sendMoneyForm.getToAccountId(), //
                    sendMoneyForm.getAmount());
        } catch (BankTransactionException e) {
            model.addAttribute("errorMessage", "Error: " + e.getMessage());
            return new ModelAndView("/sendMoneyPage");
        }
        return new ModelAndView("redirect:/");
    }

}
