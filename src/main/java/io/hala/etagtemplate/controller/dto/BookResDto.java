package io.hala.etagtemplate.controller.dto;

import io.hala.etagtemplate.domain.Book;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class BookResDto {

  private Long id;
  private String title;
  private String description;
  private String author;

  public static BookResDto of(Book book) {
    return BookResDto.builder()
        .id(book.getId())
        .title(book.getTitle())
        .description(book.getDescription())
        .author(book.getAuthor())
        .build();
  }
}
