package com.taskcodee.server.mappers;

import com.taskcodee.server.dto.boards.BoardCreationDTO;
import com.taskcodee.server.dto.boards.BoardDTO;
import com.taskcodee.server.entities.Board;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface BoardMapper {

    BoardDTO mapToBoardDTO(Board board);

    Board mapToBoardEntity(BoardCreationDTO boardCreationDTO);
}
