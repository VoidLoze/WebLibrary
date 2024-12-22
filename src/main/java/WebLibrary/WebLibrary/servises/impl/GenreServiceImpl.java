package WebLibrary.WebLibrary.servises.impl;

import WebLibrary.WebLibrary.domain.Genre;
import WebLibrary.WebLibrary.dto.GenreDTO;
import WebLibrary.WebLibrary.repository.GenreRepository;
import WebLibrary.WebLibrary.servises.GenreService;
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
public class GenreServiceImpl implements GenreService {
    @Autowired
    private final GenreRepository genreRepository;
    @Autowired
    private final ModelMapper modelMapper;

    public GenreServiceImpl(GenreRepository genreRepository, ModelMapper modelMapper) {
        this.genreRepository = genreRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public GenreDTO find(int id) {
        Optional<Genre> genre = genreRepository.findById(id);
        return modelMapper.map(genre, GenreDTO.class);
    }

    @Override
    @CacheEvict(cacheNames = "genre", allEntries = true)
    public int create(String nameOfGenre, String description) {

        Genre genre = new Genre(nameOfGenre, description);
        Genre createdGenre = genreRepository.save(genre);
        return createdGenre.getId();    }

    @Override
    @Cacheable(value = "genre", key = "'allGenre'")
    public List<GenreDTO> findAll() {
        return genreRepository.findAll().stream().map((genre) -> modelMapper.map(genre, GenreDTO.class)).toList();
    }
}
