package com.wow.libre.infrastructure.controller;

import com.wow.libre.domain.model.BenefitModel;
import com.wow.libre.domain.ports.in.benefit.BenefitPort;
import com.wow.libre.domain.shared.GenericResponse;
import com.wow.libre.domain.shared.GenericResponseBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.wow.libre.domain.model.Constants.HEADER_TRANSACTION_ID;

@RestController
@RequestMapping("/api/benefits")
public class BenefitsController {
    private final BenefitPort benefitPort;

    public BenefitsController(BenefitPort benefitPort) {
        this.benefitPort = benefitPort;
    }


    @GetMapping
    public ResponseEntity<GenericResponse<List<BenefitModel>>> benefits(
            @RequestHeader(name = HEADER_TRANSACTION_ID, required = false) final String transactionId) {

        List<BenefitModel> benefits = benefitPort.benefits(transactionId);

        if (benefits == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new GenericResponseBuilder<List<BenefitModel>>(transactionId).ok(benefits).build());
    }

}
