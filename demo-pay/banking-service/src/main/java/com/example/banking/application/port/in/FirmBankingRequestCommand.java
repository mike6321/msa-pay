package com.example.banking.application.port.in;

import com.example.common.SelfValidating;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Builder
@Data
@EqualsAndHashCode(callSuper = false)
public class FirmBankingRequestCommand extends SelfValidating<FirmBankingRequestCommand> {

    @NotNull
    private final String fromBankName;
    @NotNull
    private final String fromBankAccountNumber;
    @NotNull
    private final String toBankName;
    @NotNull
    @NotBlank
    private final String toBankAccountNumber;

    private final int moneyAmount; // only won


    public FirmBankingRequestCommand(String fromBankName,
                                     String fromBankAccountNumber,
                                     String toBankName,
                                     String toBankAccountNumber,
                                     int moneyAmount) {
        this.fromBankName = fromBankName;
        this.fromBankAccountNumber = fromBankAccountNumber;
        this.toBankName = toBankName;
        this.toBankAccountNumber = toBankAccountNumber;
        this.moneyAmount = moneyAmount;

        this.validateSelf();
    }

}
