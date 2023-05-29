package com.bank.application.dto.recipient;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecipientBankAccountRequest {

    @NotNull(message = "Routing number cannot be null")
    @JsonProperty("routing_number")
    private String routingNumber;
    @NotNull(message = "Account number cannot be null")
    @JsonProperty("account_number")
    private String accountNumber;

    @NotEmpty(message = "Bank name cannot be empty")
    @JsonProperty("bank_name")
    private String bankName;

    @NotEmpty(message = "Holder first name cannot be empty")
    @JsonProperty("holder_first_name")
    private String holderFirstName;

    @NotEmpty(message = "Holder last name cannot be empty")
    @JsonProperty("holder_last_name")
    private String holderLastName;

    @NotNull(message = "Holder national identification number cannot be null")
    @JsonProperty("holder_national_identification_number")
    private Long holderNationalIdentificationNumber;

    @NotNull(message = "Currency cannot be null")
    private String currency;

}
