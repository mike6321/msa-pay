package com.example.money.adapter.out.persistence;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "money_changing_request")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MoneyChangingRequestJpaEntity {

    @Id
    @GeneratedValue
    private Long moneyChangingRequestId;

    private String targetMembershipId;

    private int moneyChangingType;

    private int moneyAmount;

    private int changingMoneyStatus;

    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;

    private UUID uuid;

    public MoneyChangingRequestJpaEntity(String targetMembershipId, int moneyChangingType, int moneyAmount, int changingMoneyStatus, Timestamp timestamp, UUID uuid) {
        this.targetMembershipId = targetMembershipId;
        this.moneyChangingType = moneyChangingType;
        this.moneyAmount = moneyAmount;
        this.changingMoneyStatus = changingMoneyStatus;
        this.timestamp = timestamp;
        this.uuid = uuid;
    }

    @Override
    public String toString() {
        return "MoneyChangingRequestJpaEntity{" +
                "moneyChangingRequestId=" + moneyChangingRequestId +
                ", targetMembershipId='" + targetMembershipId + '\'' +
                ", moneyChangingType=" + moneyChangingType +
                ", moneyAmount=" + moneyAmount +
                ", changingMoneyStatus=" + changingMoneyStatus +
                ", timestamp=" + timestamp +
                ", uuid=" + uuid +
                '}';
    }

}
