package freddit.service;

import freddit.model.User;
import freddit.repository.UserRepository;
import org.hibernate.cache.spi.entry.StructuredCacheEntry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class UserService {

    private final Logger logger = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User register(User user){
        return user;
    }

    public User save(User user){
        return userRepository.save(user);
    }

    @Transactional
    public void saveUsers(User... users){
        // begin transaction
        for(User user: users){
            logger.info("Saving User: "+user.getEmail());
            userRepository.save(user);
        }
        // commit if transaction is success
    }
}
