package com.taskcodee.server.mappers;

import com.taskcodee.server.dto.cards.CardDTO;
import com.taskcodee.server.entities.BoardCard;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface CardMapper {

    CardDTO mapToCardDTO(BoardCard boardCard);
}
