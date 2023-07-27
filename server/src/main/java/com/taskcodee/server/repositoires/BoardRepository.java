package com.taskcodee.server.repositoires;

import com.taskcodee.server.entities.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {

    @Query("SELECT COUNT(b1) FROM Board b1 INNER JOIN BoardMember b2 WHERE b1.title = :title AND b2.user.id = :userId AND b2.role = 'OWNER'")
    Long countBoards(@Param("title") String title,
                     @Param("userId") Long userId);

    //Найти максимальную позицию листа в определенной доске
    @Query("SELECT MAX(l.pos) FROM Board b INNER JOIN BoardList l WHERE b.id = :id")
    Optional<Double> maxListPositionFromBoardById(@Param("id") Long id);
}
