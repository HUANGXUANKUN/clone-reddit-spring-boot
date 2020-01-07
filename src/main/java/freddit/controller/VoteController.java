package freddit.controller;

import freddit.model.Link;
import freddit.model.Vote;
import freddit.repository.LinkRepository;
import freddit.repository.VoteRepository;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class VoteController {

    private VoteRepository voteRepository;
    private LinkRepository linkRepository;

    public VoteController(VoteRepository voteRepository, LinkRepository linkRepository) {
        this.voteRepository = voteRepository;
        this.linkRepository = linkRepository;
    }

    @Secured({"ROLE_USER"})
    //http://localhost:8080/vote/link/direction/-1/votecount/5
    @GetMapping("/vote/link/{linkID}/direction/{direction}/votecount/{voteCount}")
    public int vote(@PathVariable Long linkID, @PathVariable Short direction, @PathVariable int voteCount){
        Optional<Link> optionalLink = linkRepository.findById(linkID);
        if(optionalLink.isPresent()){
            Link link = optionalLink.get();
            Vote vote = new Vote(direction, link);
            voteRepository.save(vote);

            int updatedVoteCount = voteCount + direction;
            link.setVoteCount(updatedVoteCount);
            linkRepository.save(link);
            return updatedVoteCount;
        }

        // if not found, return voteCount
        return voteCount;
    }
}
