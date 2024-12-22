package WebLibrary.WebLibrary.servises.impl;

import WebLibrary.WebLibrary.domain.Reader;
import WebLibrary.WebLibrary.dto.ReaderDTO;
import WebLibrary.WebLibrary.repository.ReaderRepository;
import WebLibrary.WebLibrary.repository.UserRepository;
import WebLibrary.WebLibrary.servises.ReaderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@EnableCaching
public class ReaderServiceImpl implements ReaderService{
    @Autowired
    private final ReaderRepository readerRepository;

    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final ModelMapper modelMapper;

    public ReaderServiceImpl(ReaderRepository readerRepository, UserRepository userRepository, ModelMapper modelMapper) {
        this.readerRepository = readerRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ReaderDTO find(int id) {
        Optional<Reader> reader = readerRepository.findById(id);
        return modelMapper.map(reader, ReaderDTO.class);
    }

    @Override
    public Reader findByEmailReader(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Reader not found with email: " + email));
    }

//    @Override
//    public Reader getCurrentUser(Principal principal) {
//        // Получаем имя пользователя из principal (например, email или username)
//        String email = principal.getEmail();
//
//        // Используем репозиторий для поиска объекта Reader по имени пользователя (например, по email)
//        return userRepository.findByEmail(email);
//    }
    @Override
    public List<Reader> findAllReadersExcept(int loggedInReaderId) {
        // Fetch all readers from the database except the logged-in reader
        return readerRepository.findAll().stream()
                .filter(reader -> reader.getId() != loggedInReaderId)
                .collect(Collectors.toList());
    }


    @Override
    public Optional<Reader> findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    @Override
    @CacheEvict(cacheNames = "reader", allEntries = true)
    public int create(String firstName, String lastName, int age, String phoneNumber) {
        Reader reader = new Reader(firstName, lastName, age, phoneNumber);
        Reader createdReader = readerRepository.save(reader);
        return createdReader.getId();
    }

    @Override
    @Cacheable(value = "reader", key = "'allReader'")
    public List<ReaderDTO> findAll() {
        return readerRepository.findAll().stream().map((reader) -> modelMapper.map(reader, ReaderDTO.class)).toList();

    }

    @Override
    public void editReader(int id,String name, String lastName, int age, String phoneNumber) {
        var reader = readerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Читатель не найден"));
        reader.setId(id);
        reader.setFirstName(name);
        reader.setLastName(lastName);
        reader.setAge(age);
        reader.setPhoneNumber(phoneNumber);
    }

    @Override
//    @Cacheable(value = "reader", key = "'profile'")
    public ReaderDTO getUserProfile(String username) {
        Optional<Reader> optionalReader = userRepository.findByEmail(username);

        return optionalReader.map(reader -> new ReaderDTO(
                        reader.getId(),
                        reader.getFirstName(),
                        reader.getLastName(),
                        reader.getAge(),
                        reader.getPhoneNumber(),
                        reader.getEmail()
                ))
                .orElse(null);
    }

    private ReaderDTO convertToDto(Reader reader) {
        ReaderDTO dto = new ReaderDTO();
        dto.setId(reader.getId());
        dto.setEmail(reader.getEmail());
        dto.setFirstName(reader.getFirstName());
        return dto;
    }

    private ReaderDTO convertToUserDto(Reader reader) {
        ReaderDTO dto = new ReaderDTO();
        dto.setId(reader.getId());
        dto.setEmail(reader.getEmail());
        dto.setFirstName(reader.getFirstName());
        return dto;
    }

    //    @Override
//    public ReaderDTO updateReader(ReaderDTO reader) {
//        Optional<Reader> readerToUpdate = readerRepository.findById(reader.getId());
//
//        if (!readerToUpdate.isPresent()){
//            throw new ReaderNotFoundException();
//        }
//        readerToUpdate.get().getFirstName();
//        readerToUpdate.get().getLastName();
//        readerToUpdate.get().getAge();
//        readerToUpdate.get().getPhoneNumber();
//        readerToUpdate.get().getFriend();
//
//        readerRepository.update(readerToUpdate.get());
//
//        return modelMapper.map(readerToUpdate, ReaderDTO.class);
//    }
}
