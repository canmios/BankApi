package com.bank.infrastructure.client.wallet.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class WalletTransactionResponse  implements Serializable {

    @JsonProperty("wallet_transaction_id")
    private Long walletTransactionId;

    @JsonProperty("user_id")
    private Long userId;

    private Double amount;

}
