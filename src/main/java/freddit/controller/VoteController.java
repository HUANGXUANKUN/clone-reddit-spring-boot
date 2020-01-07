package freddit.controller;

import freddit.model.Link;
import freddit.model.Vote;
import freddit.repository.LinkRepository;
import freddit.repository.VoteRepository;
import freddit.service.LinkService;
import freddit.service.VoteService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class VoteController {

    private LinkService linkService;
    private VoteService voteService;

    public VoteController(LinkService linkService, VoteService voteService) {
        this.linkService = linkService;
        this.voteService = voteService;
    }

    @Secured({"ROLE_USER"})
    //http://localhost:8080/vote/link/direction/-1/votecount/5
    @GetMapping("/vote/link/{linkID}/direction/{direction}/votecount/{voteCount}")
    public int vote(@PathVariable Long linkID, @PathVariable Short direction, @PathVariable int voteCount){
        Optional<Link> optionalLink = linkService.findById(linkID);
        if(optionalLink.isPresent()){
            Link link = optionalLink.get();
            Vote vote = new Vote(direction, link);
            voteService.save(vote);

            int updatedVoteCount = voteCount + direction;
            link.setVoteCount(updatedVoteCount);
            linkService.save(link);
            return updatedVoteCount;
        }

        // if not found, return voteCount
        return voteCount;
    }
}
