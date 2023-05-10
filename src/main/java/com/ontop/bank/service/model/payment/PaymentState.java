package com.ontop.bank.service.model.payment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PaymentState implements Serializable {
    private Long id;
    private Long paymentId;
    private PaymentStatus status;
    private LocalDateTime created;
}
