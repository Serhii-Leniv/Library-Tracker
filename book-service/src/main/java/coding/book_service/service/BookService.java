package coding.book_service.service;

import coding.book_service.dto.BookDto;
import coding.book_service.model.Book;
import coding.book_service.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<BookDto> getAllBooks() {

        return bookRepository.findAll().stream().map(this::toDto).toList();
    }

    public BookDto getBookById(Long id) {
        return bookRepository.findById(id)
                .map(this::toDto)
                .orElseThrow(() -> new RuntimeException("Book not found with id: " + id));
    }

    public BookDto createBook(BookDto bookbto){
        Book book = bookRepository.save(toEntity(bookbto));
        return toDto(book);
    }

    public void deleteBook(Long id){
        try {
            bookRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Error deleting book with id: " + id, e);
        }
    }

    public BookDto updateBook(Long id, BookDto bookDto) {
        if (!bookRepository.existsById(id)) {
            throw new RuntimeException("Book not found with id: " + id);
        }
        bookDto.setId(id);
        Book book = bookRepository.save(toEntity(bookDto));
        return toDto(book);
    }



    private BookDto toDto(Book book) {
        return new BookDto(book.getId(), book.getTitle(), book.getAuthor());
    }

    private Book toEntity(BookDto dto) {
        return new Book(dto.getId(), dto.getTitle(), dto.getAuthor());
    }

}