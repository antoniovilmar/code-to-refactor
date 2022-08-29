package com.example.demo.application;

import com.example.demo.api.dto.AccountOpeningDto;
import com.example.demo.domain.Account;
import com.example.demo.domain.Dependent;
import com.example.demo.domain.AccountType;
import com.example.demo.domain.impl.CheckingAccount;
import com.example.demo.infrastructure.AccountRepository;
import com.example.demo.infrastructure.external.CustomerAccountNumberGenerator;
import com.example.demo.infrastructure.external.CostumerInvestmentAccountNumberGenerator;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    private AccountRepository accountRepository;
    private CustomerAccountNumberGenerator customerAccountNumberGenerator;
    private CostumerInvestmentAccountNumberGenerator costumerInvestmentAccountNumberGenerator;

    public AccountService(AccountRepository accountRepository,
                          CustomerAccountNumberGenerator customerAccountNumberGenerator,
                          CostumerInvestmentAccountNumberGenerator costumerInvestmentAccountNumberGenerator) {
        this.accountRepository = accountRepository;
        this.customerAccountNumberGenerator = customerAccountNumberGenerator;
        this.costumerInvestmentAccountNumberGenerator = costumerInvestmentAccountNumberGenerator;
    }


    public long openAccount(final AccountOpeningDto accountOpeningDto) {
        AccountType accountType = accountOpeningDto.getAccountType();

        //todo pensar melhor nessa parte
        long accountNumber = customerAccountNumberGenerator.generate();
        if (accountOpeningDto.getAccountType().INVESTMENT.equals(accountType)) {
            accountNumber = costumerInvestmentAccountNumberGenerator
                    .generate(accountOpeningDto.getHolderCPF());
        }

        Account newAccount = new CheckingAccount(accountNumber,
                accountOpeningDto.getAgency(),
                accountOpeningDto.getHolderCPF());

        return  accountRepository.save(newAccount).getNumber();
    }

    public void withdraw(final long accountNumber, final double value) {
        Account account = accountRepository.getById(accountNumber);
        account.withdraw(value);
        accountRepository.save(account);
    }

    public void deposit(final long accountNumber, final double value) {
        Account account = accountRepository.getById(accountNumber);
        account.deposit(value);
        accountRepository.save(account);
    }

    public void includeDependent(final Dependent dependent, final long accountNumber) {
        Account account = accountRepository.getById(accountNumber);
        account.includeDependent(dependent);

    }
}
