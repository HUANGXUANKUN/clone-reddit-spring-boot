package freddit.controller;

import freddit.model.Link;
import freddit.model.ServiceResponse;
import freddit.model.Vote;
import freddit.repository.LinkRepository;
import freddit.repository.VoteRepository;
import freddit.service.LinkService;
import freddit.service.VoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class VoteController {

    private LinkService linkService;
    private VoteService voteService;
    private Logger logger = LoggerFactory.getLogger(VoteController.class);

    public VoteController(LinkService linkService, VoteService voteService) {
        this.linkService = linkService;
        this.voteService = voteService;
    }

    @Secured({"ROLE_USER"})
    @GetMapping("/vote/link/{linkID}/direction/{direction}/votecount/{voteCount}")
    public int vote(@PathVariable Long linkID, @PathVariable Short direction, @PathVariable int voteCount){
        logger.debug("optionalLink is not present");
        Optional<Link> optionalLink = linkService.findById(linkID);
        if(optionalLink.isPresent()){
            Link link = optionalLink.get();
//            Vote vote = new Vote(direction, link);
//            logger.debug(vote.toString());
//            voteService.save(vote);
            int updatedVoteCount = voteCount + direction;
            link.setVoteCount(updatedVoteCount);
            linkService.save(link);
            return updatedVoteCount;
        }
        logger.debug("optionalLink is not present");

        // if not found, return voteCount
        return voteCount;
    }

    @Secured({"ROLE_USER"})
    @GetMapping("/link/{linkID}/upvote")
    public ResponseEntity<Object> upvote(@PathVariable Long linkID){
        logger.debug("linkID: "+Long.toString(linkID));
        Optional<Link> optionalLink = linkService.findById(linkID);
        if(optionalLink.isPresent()){
            Link link = optionalLink.get();
            int updatedVoteCount = voteService.upvote(link);
//            int voteCount = link.getVoteCount();
//            int updatedVoteCount = voteCount + 1;
//            link.setVoteCount(updatedVoteCount);
//            linkService.save(link);
            ServiceResponse<Integer> response = new ServiceResponse<>("success", updatedVoteCount);
            return new ResponseEntity<Object>(response, HttpStatus.OK);
        }
        ServiceResponse<Integer> response = new ServiceResponse<>("fail", -999);
        return new ResponseEntity<Object>(response, HttpStatus.OK);
    }

    @Secured({"ROLE_USER"})
    @GetMapping("/link/{linkID}/downvote")
    public ResponseEntity<Object> downvote(@PathVariable Long linkID){
        logger.debug("linkID: "+Long.toString(linkID));
        Optional<Link> optionalLink = linkService.findById(linkID);
        if(optionalLink.isPresent()){
            Link link = optionalLink.get();
            int updatedVoteCount = voteService.downvote(link);
            ServiceResponse<Integer> response = new ServiceResponse<>("success", updatedVoteCount);
            return new ResponseEntity<Object>(response, HttpStatus.OK);
        }
        ServiceResponse<Integer> response = new ServiceResponse<>("fail", -999);
        return new ResponseEntity<Object>(response, HttpStatus.OK);
    }
}
