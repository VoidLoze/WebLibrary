package WebLibrary.WebLibrary.servises.impl;

import WebLibrary.WebLibrary.domain.Friend;
import WebLibrary.WebLibrary.domain.Reader;
import WebLibrary.WebLibrary.dto.FriendDTO;
import WebLibrary.WebLibrary.repository.FriendRepository;
import WebLibrary.WebLibrary.repository.ReaderRepository;
import WebLibrary.WebLibrary.repository.UserRepository;
import WebLibrary.WebLibrary.servises.FriendService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@EnableCaching
public class FriendServiceImpl implements FriendService {
    @Autowired
    private final ReaderRepository readerRepository;
    @Autowired
    private final FriendRepository friendRepository;
    @Autowired
    private final ModelMapper modelMapper;

    @Autowired
    private final UserRepository userRepository;

    public FriendServiceImpl(FriendRepository friendRepository, ModelMapper modelMapper, ReaderRepository readerRepository, UserRepository userRepository) {
        this.friendRepository = friendRepository;
        this.modelMapper = modelMapper;
        this.readerRepository = readerRepository;
        this.userRepository = userRepository;
    }

    @Override
    public FriendDTO find(int id) {
        Optional<Friend> friend = friendRepository.findById(id);
        return modelMapper.map(friend, FriendDTO.class);
    }

//    @Override
//    public void addFriend(int id,String firstName,String lastName) {
//        readerRepository.findAll().stream().map((reader) -> modelMapper.map(reader, ReaderDTO.class)).toList();
//
//
//        Friend friend = new Friend(firstName,lastName);
//        Friend createdFriend = friendRepository.save(friend);
//        createdFriend.getId();
//    }


    @Override
    public void deleteFriend(Integer id) {
        friendRepository.deleteById(id);
    }

    @Override
    public int addFriend(int readerId, int friendReaderId) {
        // Проверка на добавление самого себя в друзья
        if (readerId == friendReaderId) {
            throw new IllegalArgumentException("You cannot add yourself as a friend.");
        }

        // Получение читателя и потенциального друга
        Reader reader = readerRepository.findById(readerId)
                .orElseThrow(() -> new RuntimeException("Reader not found"));

        Reader friend = readerRepository.findById(friendReaderId)
                .orElseThrow(() -> new RuntimeException("Friend not found"));

        // Проверка, являются ли они уже друзьями
        if (friendRepository.existsByReaderIdAndFriendReaderId(readerId, friendReaderId)) {
            throw new IllegalArgumentException("Friend already added.");
        }

        // Проверка текущего количества друзей
        int currentFriendCount = friendRepository.countByReaderId(readerId);
        if (currentFriendCount >= 10) {
            throw new IllegalArgumentException("You cannot have more than 10 friends.");
        }

        // Создание нового объекта друга
        Friend newFriend = new Friend(reader, friend.getLastName(), friend.getFirstName(), friend);

        // Сохранение нового друга в репозитории
        Friend createdFriend = friendRepository.save(newFriend);

        return createdFriend.getId();
    }




//    @Override
//    public int addFriend(int id) {
//        // Получаем список всех читателей
//        List<ReaderDTO> readers = readerRepository.findAll()
//                .stream()
//                .map(reader -> modelMapper.map(reader, ReaderDTO.class))
//                .collect(Collectors.toList());
//
//        Reader readerId = new Reader();
//        readerId.setId(id);
//
////        if (friendRepository.existsByReaderId(readerId)) {
////            throw new IllegalArgumentException("Friend already exists.");
////        }
//
//
//        // Находим читателя по id (или любому другому критерию)
//        ReaderDTO selectedReader = readers.stream()
//                .filter(reader -> reader.getId() == id)
//                .findFirst()
//                .orElseThrow(() -> new RuntimeException("Reader not found"));
//
//        // Создаем друга с именем и фамилией выбранного читателя
//        Friend friend = new Friend(selectedReader.getFirstName(), selectedReader.getLastName());
//
//        // Сохраняем друга в базе данных
//        Friend createdFriend = friendRepository.save(friend);
//
//
//        // Возвращаем ID созданного друга (если нужно)
//        return createdFriend.getId();
//    }


    @Override
//    @Cacheable(value = "friends", key = "'allFriendsByReader'")
    public List<FriendDTO> findAllFriendsByReader(int readerId) {
        // Fetch the reader by id
        Reader reader = readerRepository.findById(readerId)
                .orElseThrow(() -> new RuntimeException("Reader not found"));

        // Get the list of friends added by the reader
        List<Friend> friends = friendRepository.findAllFriendsByReader(reader.getId());

        // Convert the friends to DTOs for better presentation
        return friends.stream()
                .map(friend -> modelMapper.map(friend, FriendDTO.class))
                .collect(Collectors.toList());
    }


    @Override
//    @Cacheable(value = "friends", key = "'allFriends'")
    public List<FriendDTO> findAllFriend() {
        return friendRepository.findAll().stream().map((friendList) -> modelMapper.map(friendList, FriendDTO.class)).toList();
    }

}
