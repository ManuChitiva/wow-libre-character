package com.wow.libre.infrastructure.repositories.mail;

import com.wow.libre.domain.ports.out.gm_ticket.ObtainGmTicket;
import com.wow.libre.domain.ports.out.mail.ObtainMail;
import com.wow.libre.infrastructure.entities.MailEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class JpaMailAdapter implements ObtainMail {

    private final MailRepository mailRepository;

    public JpaMailAdapter(MailRepository mailRepository) {
        this.mailRepository = mailRepository;
    }


    @Override
    public List<MailEntity> findByMailGuidId(Long guidId, String transactionId) {
        return mailRepository.findByReceiverGuidId(guidId);
    }
}
