package io.hala.etagtemplate.controller;

import io.hala.etagtemplate.controller.dto.BookReqDto;
import io.hala.etagtemplate.controller.dto.BookResDto;
import io.hala.etagtemplate.service.BookService;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/book")
@RestController
public class BookController {

  private final BookService bookService;

  @PostMapping
  public ResponseEntity<?> registBook(@RequestBody BookReqDto bookReqDto) {
    BookResDto bookResDto = bookService.registBook(bookReqDto);
    return ResponseEntity.created(URI.create("/book/" + bookResDto.getId())).body(bookResDto);
  }

  @GetMapping("/cache")
  public ResponseEntity<?> findAllWithEtag() {
    return ResponseEntity.ok().body(bookService.findAll());
  }

  @GetMapping("/no-cache")
  public ResponseEntity<?> findAllWithoutEtag() {
    return ResponseEntity.ok().body(bookService.findAll());
  }

  @PutMapping("/{bookId}")
  public ResponseEntity<?> updateBook(@PathVariable long bookId,
      @RequestBody BookReqDto bookReqDto) {
    bookService.update(bookId, bookReqDto);
    return ResponseEntity.ok().build();
  }
}
