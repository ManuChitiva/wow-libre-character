package com.wow.libre.domain.ports.out.mail;

import com.wow.libre.infrastructure.entities.MailEntity;

import java.util.List;
import java.util.Optional;

public interface ObtainMail {
    List<MailEntity> findByMailGuidId(Long guidId, String transactionId);
}
