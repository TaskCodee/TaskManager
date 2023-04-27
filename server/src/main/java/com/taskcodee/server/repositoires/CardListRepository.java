package com.taskcodee.server.repositoires;

import com.taskcodee.server.entities.CardList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardListRepository extends JpaRepository<CardList, Long> {
}
