package com.bank.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bank.dao.inter.BankAccountDAOInter;
import com.bank.entity.BankAccount;
import com.bank.exception.BankTransactionException;
import com.bank.model.BankAccountInfo;
 
@Repository
public class BankAccountDAO implements BankAccountDAOInter{
 
    @Autowired
    private EntityManager entityManager;
      
 
    public BankAccountDAO() {
    }
    
    @Override
    public BankAccount findById(Long id) { 
        return this.entityManager.find(BankAccount.class, id);
    }
 
    @Override
    public List<BankAccountInfo> listBankAccountInfo() {
        String sql = "Select new " + BankAccountInfo.class.getName() //
                + "(e.id,e.fullName,e.balance) " //
                + " from " + BankAccount.class.getName() + " e ";
        Query query = entityManager.createQuery(sql, BankAccountInfo.class);
        return query.getResultList();
    }
 
    @Override
    @Transactional(propagation = Propagation.MANDATORY )
    public void addAmount(Long id, double amount) throws BankTransactionException {
        BankAccount account = this.findById(id);
        if (account == null) {
            throw new BankTransactionException("Account not found " + id);
        }
        double newBalance = account.getBalance() + amount;
        if (account.getBalance() + amount < 0) {
            throw new BankTransactionException(
                    "The money in the account '" + id + "' is not enough (" + account.getBalance() + ")");
        }
        account.setBalance(newBalance);
    }
 
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, 
                        rollbackFor = BankTransactionException.class)
    public void sendMoney(Long fromAccountId, Long toAccountId, double amount) throws BankTransactionException {
 
        addAmount(toAccountId, amount);
        addAmount(fromAccountId, -amount);
    }
 
}