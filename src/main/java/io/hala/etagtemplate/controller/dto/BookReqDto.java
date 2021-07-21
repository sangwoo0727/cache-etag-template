package io.hala.etagtemplate.controller.dto;

import io.hala.etagtemplate.domain.Book;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class BookReqDto {

  private String title;
  private String description;
  private String author;

  public Book toEntity() {
    return Book.builder()
        .title(title)
        .description(description)
        .author(author)
        .build();
  }
}
