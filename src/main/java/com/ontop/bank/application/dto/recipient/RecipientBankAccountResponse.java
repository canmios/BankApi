package com.ontop.bank.application.dto.recipient;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecipientBankAccountResponse {

    private Long id;
    @JsonProperty("routing_number")
    private String routingNumber;
    @JsonProperty("account_number")
    private String accountNumber;

    @JsonProperty("bank_name")
    private String bankName;

    @JsonProperty("holder_first_name")
    private String holderFirstName;

    @JsonProperty("holder_last_name")
    private String holderLastName;

    @JsonProperty("holder_national_identification_number")
    private Long holderNationalIdentificationNumber;

    @JsonProperty("user_id")
    private Long userId;

    private String currency;

}
