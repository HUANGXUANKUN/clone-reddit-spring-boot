package freddit.service;

import freddit.model.Link;
import freddit.model.User;
import freddit.repository.LinkRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LinkService {

    private final LinkRepository linkRepository;

    public LinkService(LinkRepository linkRepository) {
        this.linkRepository = linkRepository;
    }

    public List<Link> findAll(){
        return linkRepository.findAll();
    }

    public Optional<Link> findById(Long id){
        return linkRepository.findById(id);
    }

    public Link save(Link link){
        //save will return the saved entity(link in this case)
        link.setUser((User)SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return linkRepository.save(link);
    }
}
