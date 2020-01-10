package freddit.service;

import freddit.model.Link;
import freddit.model.Vote;
import freddit.repository.VoteRepository;
import org.springframework.stereotype.Service;

@Service
public class VoteService {

    private final VoteRepository voteRepository;
    private final LinkService linkService;

    public VoteService(VoteRepository voteRepository, LinkService linkService) {
        this.voteRepository = voteRepository;
        this.linkService = linkService;
    }

    public Vote save(Vote vote){
        return voteRepository.save(vote);
    }

    public Integer upvote(Link link){
        int voteCount = link.getVoteCount();
        int updatedVoteCount = voteCount + 1;
        link.setVoteCount(updatedVoteCount);
        linkService.save(link);
        return updatedVoteCount;
    }

    public Integer downvote(Link link){
        int voteCount = link.getVoteCount();
        int updatedVoteCount = voteCount - 1;
        link.setVoteCount(updatedVoteCount);
        linkService.save(link);
        return updatedVoteCount;
    }
}
