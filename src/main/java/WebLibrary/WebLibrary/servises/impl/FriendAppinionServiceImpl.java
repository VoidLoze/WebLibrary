package WebLibrary.WebLibrary.servises.impl;

import WebLibrary.WebLibrary.domain.FriendAppinion;
import WebLibrary.WebLibrary.dto.FriendAppinionDTO;
import WebLibrary.WebLibrary.repository.FriendAppinionRepository;
import WebLibrary.WebLibrary.servises.FriendAppinionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FriendAppinionServiceImpl implements FriendAppinionService {
    @Autowired
    private final FriendAppinionRepository friendAppinionRepository;
    @Autowired
    private final ModelMapper modelMapper;

    public FriendAppinionServiceImpl(FriendAppinionRepository friendAppinionRepository, ModelMapper modelMapper) {
        this.friendAppinionRepository = friendAppinionRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public FriendAppinionDTO find(int id) {
        Optional<FriendAppinion> friendAppinion = friendAppinionRepository.findById(id);
        return modelMapper.map(friendAppinion, FriendAppinionDTO.class);
    }
}
