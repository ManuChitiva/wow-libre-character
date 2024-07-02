package com.wow.libre.application.service.mail;

import com.wow.libre.domain.model.CharacterDetail;
import com.wow.libre.domain.model.MailModel;
import com.wow.libre.domain.ports.in.characters.CharactersPort;
import com.wow.libre.domain.ports.in.mail.MailPort;
import com.wow.libre.domain.ports.out.mail.ObtainMail;
import com.wow.libre.infrastructure.entities.MailEntity;
import com.wow.libre.domain.dto.MailsDto;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class MailService implements MailPort {

    private final ObtainMail obtainMail;
    private final CharactersPort charactersPort;

    public MailService(ObtainMail obtainMail, CharactersPort charactersPort) {
        this.obtainMail = obtainMail;
        this.charactersPort = charactersPort;
    }

    @Override
    public MailsDto getMails(Long guid, Long accountWebId, String transactionId) {
        List<MailModel> mails = obtainMail.findByMailGuidId(guid, transactionId).stream()
                .map(mail -> mapToModel(mail, transactionId)).toList();

        return new MailsDto(mails, mails.size());
    }

    private MailModel mapToModel(MailEntity mail, String transactionId) {
        Date deliverTime = Date.from(Instant.ofEpochMilli(mail.getDeliverTime() * 1000));
        Date expirationMail = Date.from(Instant.ofEpochMilli(mail.getExpireTime() * 1000));
        CharacterDetail characterSender = charactersPort.getCharacter(mail.getSenderGuidId(), transactionId);

        return new MailModel(mail.getId(), mail.getMessageType(), mail.getSenderGuidId(),
                Optional.ofNullable(characterSender).map(CharacterDetail::getName).orElse("Wow Libre"),
                mail.getSubject(), mail.getBody(), mail.isHasItems(), expirationMail, deliverTime,
                mail.getMoney());
    }
}
