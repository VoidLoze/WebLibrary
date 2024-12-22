package WebLibrary.WebLibrary.servises.impl;

import WebLibrary.WebLibrary.domain.Author;
import WebLibrary.WebLibrary.dto.AuthorDTO;
import WebLibrary.WebLibrary.repository.AuthorRepository;
import WebLibrary.WebLibrary.servises.AuthorService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@EnableCaching
public class AuthorServiceImpl implements AuthorService {
    @Autowired
    private final AuthorRepository authorRepository;

    @Autowired
    private final ModelMapper modelMapper;

    public AuthorServiceImpl(AuthorRepository authorRepository, ModelMapper modelMapper) {
        this.authorRepository = authorRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public AuthorDTO find(int id) {
        Optional<Author> author = authorRepository.findById(id);
        return modelMapper.map(author, AuthorDTO.class);
    }

    @Override
    @CacheEvict(cacheNames = "author", allEntries = true)
    public int create(String firstName, String lastName, String dateOfBorn) {
        Author author = new Author(firstName,lastName,dateOfBorn);
        Author createdAuthor = authorRepository.save(author);
        return createdAuthor.getId();    }

    @Override
    @Cacheable(value = "author", key = "'allAuthors'")
    public List<AuthorDTO> findAll() {
        return authorRepository.findAll().stream().map((author) -> modelMapper.map(author, AuthorDTO.class)).toList();
    }
}
