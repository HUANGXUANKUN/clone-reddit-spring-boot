package freddit.service;

import freddit.model.Comment;
import freddit.model.User;
import freddit.repository.CommentRepository;
import freddit.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommentService {
    private static final Logger logger = LoggerFactory.getLogger(CommentService.class);

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    public CommentService(CommentRepository commentRepository, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
    }

    public Comment save(Comment comment){
        if(SecurityContextHolder.getContext().getAuthentication() == null){
            logger.debug("User is not presented");
        }else{
            logger.debug("User is presented");
            comment.setUser((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        }
        return commentRepository.save(comment);
    }
}
