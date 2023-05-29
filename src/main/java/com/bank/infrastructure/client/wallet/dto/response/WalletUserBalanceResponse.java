package com.bank.infrastructure.client.wallet.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class WalletUserBalanceResponse implements Serializable {

    private Double balance;
    @JsonProperty("user_id")
    private Long userId;

}
