package com.wow.libre.domain.dto;

import com.wow.libre.domain.model.MailModel;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
@Data
@AllArgsConstructor
public class MailsDto {
    private List<MailModel> mails;
    private int size;
}
