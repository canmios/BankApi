package com.bank.application.dto.fee;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FeeResponse {

    @JsonProperty("initial_amount")
    private Double initialAmount;
    @JsonProperty("transaction_fee")
    private Double transactionFee;
    @JsonProperty("final_amount")
    private Double finalAmount;
}
