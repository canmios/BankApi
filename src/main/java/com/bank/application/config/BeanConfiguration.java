package com.bank.application.config;

import com.bank.infrastructure.repository.wallet.WalletTransactionRepository;
import com.bank.infrastructure.client.wallet.WalletClient;
import com.bank.infrastructure.repository.payment.PaymentRepository;
import com.bank.infrastructure.repository.recipient.RecipientBankAccountRepository;
import com.bank.infrastructure.repository.wallet.WalletBankAccountRepository;
import com.bank.service.fee.FeeServiceImpl;
import com.bank.service.fee.FeeService;
import com.bank.service.payment.PaymentServiceImpl;
import com.bank.service.provider.PaymentProviderServiceImpl;
import com.bank.service.wallet.WalletService;
import com.bank.service.wallet.WalletServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public PaymentServiceImpl paymentService(RecipientBankAccountRepository recipientBankAccountRepository,
                                             WalletService walletService, PaymentRepository paymentRepository,
                                             PaymentProviderServiceImpl paymentProviderClient, FeeService feeService) {
        return new PaymentServiceImpl(recipientBankAccountRepository, paymentRepository, walletService,
                paymentProviderClient, feeService);
    }

    @Bean
    public WalletServiceImpl walletService(WalletClient walletClient,
                                           WalletTransactionRepository walletTransactionRepository,
                                           WalletBankAccountRepository walletBankAccountRepository) {
        return new WalletServiceImpl(walletClient, walletTransactionRepository, walletBankAccountRepository);
    }

    @Bean
    public FeeService feeService() {
        return new FeeServiceImpl();
    }

}
