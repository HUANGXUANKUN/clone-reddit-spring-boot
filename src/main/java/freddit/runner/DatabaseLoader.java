package freddit.runner;

import freddit.model.Comment;
import freddit.model.Link;
import freddit.model.Role;
import freddit.model.User;
import freddit.repository.CommentRepository;
import freddit.repository.LinkRepository;
import freddit.repository.RoleRepository;
import freddit.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.lang.reflect.Array;
import java.util.*;

@Component
public class DatabaseLoader implements CommandLineRunner {

    private LinkRepository linkRepository;
    private CommentRepository commentRepository;
    private UserRepository userRepository;
    private RoleRepository roleRepository;

    private Map<String,User> users = new HashMap<>();

    public DatabaseLoader(LinkRepository linkRepository, CommentRepository commentRepository, UserRepository userRepository, RoleRepository roleRepository) {
        this.linkRepository = linkRepository;
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) {
        addUsersAndRoles();
        addLinks();
    }

    private void addLinks(){
        List<List<String>> links = new ArrayList<List<String>>();
        links.add(Arrays.asList("How I build a reddit with Spring boot?","https://github.com/HUANGXUANKUN/clone-reddit-spring-boot"));
        links.add(Arrays.asList("A short demo of my 2113 Project","https://www.youtube.com/watch?v=GdsyMKeBYHg"));
        links.add(Arrays.asList("How I crawl Leetcode problems using Selenium and Requests","https://github.com/HUANGXUANKUN/leetcode-summary-generator"));
        links.add(Arrays.asList("How I crawl top rated movie using Scrapy?","https://github.com/HUANGXUANKUN/douban-movie-crawler"));
        links.add(Arrays.asList("Building a maze game with Xuan Kun","https://youtu.be/OkyE1ssDXnQ"));
        links.add(Arrays.asList("How I build a maplestory-like game with Pygame","https://www.youtube.com/watch?v=GdsyMKeBYHg"));
        links.add(Arrays.asList("Building a simple shooting game with Pygame","https://github.com/HUANGXUANKUN/more-than-shooting"));

        List<String> comments = new ArrayList<>();
        comments.add("Thank you Xuan Kun. I love it, great post!");
        comments.add("OMG Xuan Kun you are awesome");
        comments.add("Cool");
        comments.add("Nobody says no words");
        comments.add("Wow Amazing");
        comments.add("Buggy");
        comments.add("So many Bug");
        comments.add("Lame");
        comments.add("Lalala");
        comments.add("Omg XK");
        comments.add("No Bug");
        for (List<String> list : links) {
            String k = list.get(0);
            String v = list.get(1);;
            User u1 = users.get("user@gmail.com");
            User u2 = users.get("super@gmail.com");
            Link link = new Link(k, v);
            if (k.startsWith("Building")) {
                link.setUser(u1);
            } else link.setUser(u2);
            linkRepository.save(link);

            // Generate random comments for each
            Random random = new Random();
            int min = 2;
            int randomNumber = random.nextInt(comments.size() - min) + min;
            for (int i = 0; i < randomNumber; i++) {
                Comment comment = new Comment(comments.get(i), link);
                comment.setUser(u1);
                commentRepository.save(comment);
                link.addComment(comment);
            }
        }

        long linkCount = linkRepository.count();
        System.out.println("Number of links in the database: " + linkCount );
    }

    private void addUsersAndRoles() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String secret = "{bcrypt}" + encoder.encode("password");

        Role userRole = new Role("ROLE_USER");
        roleRepository.save(userRole);
        Role adminRole = new Role("ROLE_ADMIN");
        roleRepository.save(adminRole);

        User user = new User("user@gmail.com",secret,true,"nobody","User","Xuan Kun");
        user.addRole(userRole);
        user.setConfirmPassword(secret);
        userRepository.save(user);
        users.put("user@gmail.com",user);

        User admin = new User("admin@gmail.com",secret,true,"nobody","Admin","John");
        admin.setAlias("joeadmin");
        admin.addRole(adminRole);
        admin.setConfirmPassword(secret);
        userRepository.save(admin);
        users.put("admin@gmail.com",admin);

        User master = new User("super@gmail.com",secret,true,"nobody","Super","Average Joe");
        master.addRoles(new HashSet<>(Arrays.asList(userRole,adminRole)));
        master.setConfirmPassword(secret);
        userRepository.save(master);
        users.put("super@gmail.com",master);
    }

}
