package com.api;

import com.api.interfaces.IRoleService;
import com.api.interfaces.IUserService;
import com.api.models.Comment;
import com.api.models.Role;
import com.api.models.ThreadEntity;
import com.api.models.UserEntity;
import com.api.repository.ICommentRepository;
import com.api.repository.IRoleRepository;
import com.api.repository.IThreadRepository;
import com.api.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

@Component
public class Seed implements CommandLineRunner {


    private final IUserRepository userRepository;

    private final IRoleService roleService;
    private final IThreadRepository threadRepository;
    private final ICommentRepository commentRepository;

    private final IRoleRepository roleRepository;

    @Autowired
    public Seed(IUserRepository userRepository, IRoleService roleService, IThreadRepository threadRepository, ICommentRepository commentRepository, IRoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.threadRepository = threadRepository;
        this.commentRepository = commentRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    @Transactional
    public void run(String... args){seedData();}


    private void seedData(){
        if(userRepository.count() == 0){
            List<ThreadEntity> threads = new ArrayList<>();
            List<Comment> comments = new ArrayList<>();

            //------------------------------------------------------------------------------------------//
            List<Role> roles = new ArrayList<>();

            Role admin = new Role("ADMIN");
            roles.add(admin);
            Role user = new Role("USER");
            roles.add(user);

            List<UserEntity> users = new ArrayList<>();

            UserEntity mamiyaTakuji = new UserEntity("mamiya", "riruru", "Mamiya Takuji");
            roleService.assignUserRoles(mamiyaTakuji,admin);
            users.add(mamiyaTakuji);

            UserEntity yuki = new UserEntity("YAKIUCHI", "SIZUGATEKE", "Shibata Katsuie");
            roleService.assignUserRoles(yuki,user);
            users.add(yuki);

            UserEntity takashimaZakuro = new UserEntity("zakuro", "cheshirecat", "Takashima Zakuro");
            roleService.assignUserRoles(takashimaZakuro,user);
            users.add(takashimaZakuro);

            UserEntity akasakaMegu = new UserEntity("megu", "megu123", "Megu");
            roleService.assignUserRoles(akasakaMegu,user);
            users.add(akasakaMegu);

            UserEntity kitamiSatoko = new UserEntity("satokon", "satoko123", "Satoko");
            roleService.assignUserRoles(kitamiSatoko,user);
            users.add(kitamiSatoko);

            UserEntity anonymousStudentKAERA = new UserEntity("KAERA", "password123");
            roleService.assignUserRoles(anonymousStudentKAERA,user);
            users.add(anonymousStudentKAERA);

            UserEntity anonymousStudentInkin = new UserEntity("inkin", "password123");
            roleService.assignUserRoles(anonymousStudentInkin,user);
            users.add(anonymousStudentInkin);

            UserEntity anonymousStudentADAMO = new UserEntity("ADAMO", "password123");
            roleService.assignUserRoles(anonymousStudentADAMO,user);
            users.add(anonymousStudentADAMO);

            //-----------------------------------------------------------------------------------------//
            ThreadEntity threadSatoko = new ThreadEntity("Megu and Satoko's friendship thread.",
                    "anyway, i made this thread",
                    LocalDateTime.of(2011, 8, 15, 1, 21, 25),
                    users.get(5));
            threads.add(threadSatoko);

            ThreadEntity threadZakuro = new ThreadEntity("Bick Hazard is coming.",
                    "The sealed nevus uses a Specialized Physics Bug (i.e. an Outer Magic Dark Summons Bug) to plant negative thoughts in the human race.\n" +
                            "That is the true nature of the battle between the dictators and tyrants of the world (Hitler, Genghis Khan, Marie Antoinette, etc.) and " +
                            "us (the peasants, the revolutionaries, the proletariat).\n" +
                            "Shiroyama Tsubasa was infected by the Specialized Physics Bug.\n" +
                            "The nevus used him to degrade me (the phenomenon of inducing a low energy state with a negative blast).\n" +
                            "And the reason is because the nevus will son awaken. Bick Hazard is coming.\n\n" +
                            "A great disaste will occur!",
                    LocalDateTime.of(2012, 7, 11, 22, 2, 13),
                    takashimaZakuro);
            threads.add(threadZakuro);

            ThreadEntity threadMamiya = new ThreadEntity("Who shall be saved and who shall not.",
                    "Many of you have confirmed it.\n" +
                            "Many of you have seen it.\n" +
                            "Takashima's message predicted my awakening.\n" +
                            "I am standing here now.\n\n" +
                            "As the savior.\n" +
                            "Now it is time to determine who will be saved and who will not.",
                    LocalDateTime.of(2012, 7, 15, 11, 23, 12),
                    mamiyaTakuji);
            threads.add(threadMamiya);

            //-----------------------------------------------------------------------------------------//
            Comment commentSatokoSatoko1 = new Comment("anyway, i made this thread",
                    LocalDateTime.of(2011, 8, 15, 1, 21, 25),
                    kitamiSatoko,
                    threadSatoko);
            comments.add(commentSatokoSatoko1);

            Comment commentMeguSatoko2 = new Comment("you sure did.\n" +
                    "are you sure no one else can see this board?",
                    LocalDateTime.of(2011, 8, 15, 22, 24, 5),
                    akasakaMegu,
                    threadSatoko);
            comments.add(commentMeguSatoko2);

            Comment commentSatokoSatoko3 = new Comment("apparently.",
                    LocalDateTime.of(2011, 8, 15, 23, 21, 22),
                    kitamiSatoko,
                    threadSatoko);
            comments.add(commentSatokoSatoko3);

            Comment commentAnonymousZakuro1 = new Comment("Stay away from me, Bick Hazard",
                    LocalDateTime.of(2012, 7, 11, 22, 10, 15),
                    anonymousStudentKAERA,
                    threadZakuro);
            comments.add(commentAnonymousZakuro1);

            Comment commentAnonymousZakuro2 = new Comment("lol",
                    LocalDateTime.of(2012, 7, 11, 22, 16, 25),
                    anonymousStudentKAERA,
                    threadZakuro);
            comments.add(commentAnonymousZakuro2);

            Comment commentAnonymousZakuro3 = new Comment("Pls stay away from me kthx",
                    LocalDateTime.of(2012, 7, 11, 22, 17, 23),
                    anonymousStudentKAERA,
                    threadZakuro);
            comments.add(commentAnonymousZakuro3);

            Comment commentZakuroZakuro4 = new Comment("Don't joke around!",
                    LocalDateTime.of(2012, 7, 11, 22, 21, 13),
                    takashimaZakuro,
                    threadZakuro);
            comments.add(commentZakuroZakuro4);

            Comment commentAnonymousMamiya1 = new Comment("Another one right after Takashima?\n" +
                    "Knock it off with that crap.",
                    LocalDateTime.of(2012, 7, 15, 11, 26, 15),
                    anonymousStudentKAERA,
                    threadMamiya);
            comments.add(commentAnonymousMamiya1);

            Comment commentAnonymousMamiya2 = new Comment("I reported you to the police.",
                    LocalDateTime.of(2012, 7, 15, 11, 30, 20),
                    anonymousStudentInkin,
                    threadMamiya);
            comments.add(commentAnonymousMamiya2);

            Comment commentAnonymousMamiya3 = new Comment("lol they still haven't caught you yet?",
                    LocalDateTime.of(2012, 7, 15, 11, 35, 40),
                    anonymousStudentADAMO,
                    threadMamiya);
            comments.add(commentAnonymousMamiya3);

            roleRepository.saveAll(roles);
            userRepository.saveAll(users);
            threadRepository.saveAll(threads);
            commentRepository.saveAll(comments);

        }
    }
}
