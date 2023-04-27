package com.taskcodee.server.repositoires;

import com.taskcodee.server.entities.BoardList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardListRepository extends JpaRepository<BoardList, Long> {
}
