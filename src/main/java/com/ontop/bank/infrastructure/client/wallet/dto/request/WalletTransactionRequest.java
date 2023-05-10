package com.ontop.bank.infrastructure.client.wallet.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class WalletTransactionRequest  implements Serializable {

    @JsonProperty("user_id")
    private Long userId;

    private Double amount;

}
