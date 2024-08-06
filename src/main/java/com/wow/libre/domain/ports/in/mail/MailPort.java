package com.wow.libre.domain.ports.in.mail;

import com.wow.libre.domain.dto.MailsDto;

public interface MailPort {

    MailsDto getMails(Long guid, Long accountWebId, String transactionId);
}
