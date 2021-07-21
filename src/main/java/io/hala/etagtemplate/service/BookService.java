package io.hala.etagtemplate.service;

import io.hala.etagtemplate.controller.dto.BookReqDto;
import io.hala.etagtemplate.controller.dto.BookResDto;
import io.hala.etagtemplate.domain.Book;
import io.hala.etagtemplate.domain.BookRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class BookService {

  private final BookRepository bookRepository;

  @Transactional
  public BookResDto registBook(BookReqDto bookReqDto) {
    Book book = bookRepository.save(bookReqDto.toEntity());
    return BookResDto.of(book);
  }

  public List<BookResDto> findAll() {
    List<Book> books = bookRepository.findAll();
    return books.stream()
        .map(book -> BookResDto.of(book))
        .collect(Collectors.toList());
  }

  @Transactional
  public void update(long id, BookReqDto bookReqDto) {
    Book book = bookRepository.findById(id)
        .orElseThrow(() -> new RuntimeException());
    book.update(bookReqDto.toEntity());
  }

}
