package com.bank.dao.inter;

import java.util.List;

import com.bank.entity.BankAccount;
import com.bank.exception.BankTransactionException;
import com.bank.model.BankAccountInfo;

public interface BankAccountDAOInter {
	
	public BankAccount findById(Long id);
	public List<BankAccountInfo> listBankAccountInfo();
	public void addAmount(Long id, double amount) throws BankTransactionException;
	public void sendMoney(Long fromAccountId, Long toAccountId, double amount) throws BankTransactionException;

}
