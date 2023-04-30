package com.taskcodee.server.mappers;

import com.taskcodee.server.dto.lists.BoardListDTO;
import com.taskcodee.server.entities.BoardList;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface BoardListMapper {

    BoardListDTO mapToBoardListDTO(BoardList boardList);
}
