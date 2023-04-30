package com.taskcodee.server.dto.cards;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CardCreationDTO {

    private Long cardListId;

    private String title;

    private String description;

}
