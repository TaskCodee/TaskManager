package com.taskcodee.server.repositoires;

import com.taskcodee.server.entities.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {

    @Query("SELECT COUNT(b1) FROM Board b1 INNER JOIN BoardMember b2 WHERE b1.title = :title OR b2.user.id = :userId")
    Long countBoards(@Param("title") String title,
                     @Param("userId") Long userId);
}
