package com.taskcodee.server.repositoires;

import com.taskcodee.server.entities.BoardList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface BoardListRepository extends JpaRepository<BoardList, Long> {

    //Найти максимальную позицию карточки в определенном листе
    @Query("SELECT MAX(c.pos) FROM BoardList l INNER JOIN BoardCard c WHERE l.id = :id")
    Optional<Double> maxCardPositionFromBoardListById(@Param("id") Long id);
}
