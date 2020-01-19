package com.chat.domain.DTO;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class ConversationDto {
    private Long id;
    private String participanst;
    @Builder.Default
    private List<MessageDto> messages =new ArrayList<>();
}
