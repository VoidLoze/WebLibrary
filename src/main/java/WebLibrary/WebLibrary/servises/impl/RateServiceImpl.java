package WebLibrary.WebLibrary.servises.impl;

import WebLibrary.WebLibrary.domain.Books;
import WebLibrary.WebLibrary.domain.Rate;
import WebLibrary.WebLibrary.dto.RateDTO;
import WebLibrary.WebLibrary.repository.RateRepository;
import WebLibrary.WebLibrary.repository.UserRepository;
import WebLibrary.WebLibrary.servises.RateService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RateServiceImpl implements RateService {
    @Autowired
    private final RateRepository rateRepository;
    @Autowired
    private final ModelMapper modelMapper;

    public RateServiceImpl(RateRepository rateRepository, ModelMapper modelMapper, UserRepository userRepository) {
        this.rateRepository = rateRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public RateDTO find(int id) {
        Optional<Rate> rate = rateRepository.findById(id);
        return modelMapper.map(rate, RateDTO.class);
    }

    @Override
    public int create(int rate, int bookId) {
        Books book = new Books();
        book.setId(bookId);

        Rate ratee = new Rate(rate,book);
        Rate createdRate = rateRepository.save(ratee);
        return createdRate.getId();
    }

    @Override
    public List<RateDTO> findAll() {
        return null;
    }
}
