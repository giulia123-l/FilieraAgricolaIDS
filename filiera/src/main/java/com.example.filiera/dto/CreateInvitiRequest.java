package com.example.filiera.dto;

import jakarta.validation.constraints.NotNull;
import java.util.List;

public class CreateInvitiRequest {
    @NotNull
    private Long eventoId;
    @NotNull
    private Long animatoreId;
    @NotNull
    private List<Long> invitatiUserIds;

    public Long getEventoId() { return eventoId; }
    public void setEventoId(Long eventoId) { this.eventoId = eventoId; }
    public Long getAnimatoreId() { return animatoreId; }
    public void setAnimatoreId(Long animatoreId) { this.animatoreId = animatoreId; }
    public List<Long> getInvitatiUserIds() { return invitatiUserIds; }
    public void setInvitatiUserIds(List<Long> invitatiUserIds) { this.invitatiUserIds = invitatiUserIds; }
}
