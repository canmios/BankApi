package com.ontop.bank.service.recipient;

import com.ontop.bank.infrastructure.repository.recipient.RecipientBankAccountEntityRepository;
import com.ontop.bank.infrastructure.repository.recipient.entity.RecipientBankAccountEntity;
import com.ontop.bank.service.exception.InvalidBankAccountException;
import com.ontop.bank.service.model.recipient.RecipientBankAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RecipientBankAccountServiceImplTest {

    @InjectMocks
    private RecipientBankAccountServiceImpl service;

    @Mock
    private RecipientBankAccountEntityRepository repository;

    private RecipientBankAccount sampleBankAccount;

    private RecipientBankAccountEntity sampleBankAccountEntity;

    @BeforeEach
    public void setUp() {
        sampleBankAccount = new RecipientBankAccount(1L, "123456789", "987654321", "Bank Name",
                "First", "Last", 123456789L, 1L, "USD");
        sampleBankAccountEntity = new RecipientBankAccountEntity(1L, "123456789", "987654321", "Bank Name",
                "First", "Last", 123456789L, 1L, "USD");
    }

    @Test
    public void testCreateBankAccount() {
        when(repository.findByUserId(sampleBankAccount.getUserId())).thenReturn(Optional.empty());
        when(repository.save(any())).thenReturn(sampleBankAccountEntity);

        RecipientBankAccount result = service.createBankAccount(sampleBankAccount);

        assertEquals(sampleBankAccount, result);
        verify(repository).findByUserId(sampleBankAccount.getUserId());
        verify(repository).save(sampleBankAccountEntity);

    }

    @Test
    public void testCreateBankAccountWithNullData() {
        assertThrows(InvalidBankAccountException.class, () -> service.createBankAccount(null));
    }

}
