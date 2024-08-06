package com.wow.libre.infrastructure.repositories.gm_ticket;

import com.wow.libre.domain.mapper.MapToModel;
import com.wow.libre.domain.model.GmTicket;
import com.wow.libre.domain.ports.out.gm_ticket.ObtainGmTicket;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class JpaGmTicketAdapter implements ObtainGmTicket {
    private static final long UNRESOLVED_TICKET = 0L;

    private final GmTicketRepository gmTicketRepository;

    public JpaGmTicketAdapter(GmTicketRepository gmTicketRepository) {
        this.gmTicketRepository = gmTicketRepository;
    }

    @Override
    public List<GmTicket> findByTicket(Long characterId, String transactionId) {
        return gmTicketRepository
                .findByPlayerGuidAndResolvedAndClosedByOrderByIdDesc(characterId, UNRESOLVED_TICKET, UNRESOLVED_TICKET)
                .stream().filter(Objects::nonNull).map(MapToModel::ticket).collect(Collectors.toList());
    }


}
