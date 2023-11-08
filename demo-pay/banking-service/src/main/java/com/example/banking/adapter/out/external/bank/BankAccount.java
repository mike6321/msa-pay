package com.example.banking.adapter.out.external.bank;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class BankAccount {

    private String bankName;
    private String bankAccountNumber;
    private boolean isValid;

}
