package com.wow.libre.infrastructure.repositories.mail;

import com.wow.libre.infrastructure.entities.GmTicketEntity;
import com.wow.libre.infrastructure.entities.MailEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


public interface MailRepository extends CrudRepository<MailEntity, Long> {
    List<MailEntity> findByReceiverGuidId(Long guidId);
}
