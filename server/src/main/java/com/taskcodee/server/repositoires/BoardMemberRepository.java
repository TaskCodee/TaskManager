package com.taskcodee.server.repositoires;

import com.taskcodee.server.entities.BoardMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardMemberRepository extends JpaRepository<BoardMember, Long> {
}
