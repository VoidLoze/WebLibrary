package WebLibrary.WebLibrary.repository.impl;

import WebLibrary.WebLibrary.domain.FriendAppinion;
import WebLibrary.WebLibrary.repository.BaseRepository;
import WebLibrary.WebLibrary.repository.FriendAppinionRepository;
import org.springframework.stereotype.Repository;

@Repository
public class FriendAppinionRepositoryImpl extends BaseRepository<FriendAppinion> implements FriendAppinionRepository {
    public FriendAppinionRepositoryImpl() {
        super(FriendAppinion.class);
    }
}
