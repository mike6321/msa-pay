package com.example.banking.application.port.out;

import com.example.banking.adapter.out.external.bank.GetBankAccountRequest;
import com.example.banking.adapter.out.external.bank.BankAccount;

public interface RequestBankAccountInfoPort {

    BankAccount getBankAccountInfo(GetBankAccountRequest request);

}
