package com.api;

import com.api.interfaces.ICommentService;
import com.api.interfaces.IRoleService;
import com.api.interfaces.IThreadService;
import com.api.interfaces.IUserService;
import com.api.models.CommentEntity;
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

    private final IUserService userService;

    private final IThreadService threadService;

    private final ICommentService commentService;

    private final IRoleService roleService;


    @Autowired
    public Seed(IUserRepository userRepository, IUserService userService, IRoleService roleService, IThreadService threadService, ICommentService commentService) {
        this.userRepository = userRepository;
        this.userService = userService;
        this.roleService = roleService;
        this.threadService = threadService;
        this.commentService = commentService;
    }

    @Override
    @Transactional
    public void run(String... args){seedData();}


    private void seedData(){
        if(userRepository.count() == 0){
            //------------------------------------   ROLES   ---------------------------------------------------//
            Role admin = roleService.createRole("ADMIN");
            Role user = roleService.createRole("USER");
            //-----------------------------------   USERS    ---------------------------------------------------//
            UserEntity mamiyaTakuji = userService.createUser("mamiya", "riruru", "Mamiya Takuji","mamiya@hotmail.com",admin);

            UserEntity yukiMinakami = userService.createUser("YAKIUCHI", "SIZUGATEKE", "Shibata Katsuie","yuki@hotmail.com",user);

            UserEntity takashimaZakuro = userService.createUser("zakuro", "cheshirecat", "Takashima Zakuro","zakuro@hotmail.com",user);

            UserEntity akasakaMegu = userService.createUser("megu", "megu123", "Megu","megu@hotmail.com",user);

            UserEntity kitamiSatoko = userService.createUser("satokon", "satoko123", "Satoko","satoko@hotmail.com",user);

            UserEntity anonymousStudentKAERA = userService.createUser("KAERA", "password123","Anonymous Kita High Student","kaera@hotmail.com",user);

            UserEntity anonymousStudentInkin = userService.createUser("inkin", "password123","Anonymous Kita High Student","inkin@hotmail.com",user);

            UserEntity anonymousStudentADAMO = userService.createUser("ADAMO", "password123","Anonymous Kita High Student","adamo@hotmail.com",user);

            //---------------------------------------   THREADS   --------------------------------------------------//
            ThreadEntity threadSatoko = threadService.createThread("Megu and Satoko's friendship thread.",
                    "anyway, i made this thread",
                    LocalDateTime.of(2011, 8, 15, 1, 21, 25),
                    akasakaMegu);

            ThreadEntity threadZakuro = threadService.createThread("Bick Hazard is coming.",
                    "The sealed nevus uses a Specialized Physics Bug (i.e. an Outer Magic Dark Summons Bug) to plant negative thoughts in the human race.\n" +
                            "That is the true nature of the battle between the dictators and tyrants of the world (Hitler, Genghis Khan, Marie Antoinette, etc.) and " +
                            "us (the peasants, the revolutionaries, the proletariat).\n" +
                            "Shiroyama Tsubasa was infected by the Specialized Physics Bug.\n" +
                            "The nevus used him to degrade me (the phenomenon of inducing a low energy state with a negative blast).\n" +
                            "And the reason is because the nevus will son awaken. Bick Hazard is coming.\n\n" +
                            "A great disaste will occur!",
                    LocalDateTime.of(2012, 7, 11, 22, 2, 13),
                    takashimaZakuro);

            ThreadEntity threadMamiya = threadService.createThread("Who shall be saved and who shall not.",
                    "Many of you have confirmed it.\n" +
                            "Many of you have seen it.\n" +
                            "Takashima's message predicted my awakening.\n" +
                            "I am standing here now.\n\n" +
                            "As the savior.\n" +
                            "Now it is time to determine who will be saved and who will not.",
                    LocalDateTime.of(2012, 7, 15, 11, 23, 12),
                    mamiyaTakuji);

            //---------------------------------------   COMMENTS   --------------------------------------------------//
            commentService.createComment("you sure did.\n" +
                    "are you sure no one else can see this board?",
                    LocalDateTime.of(2011, 8, 15, 22, 24, 5),
                    akasakaMegu,
                    threadSatoko);

            commentService.createComment("apparently.",
                    LocalDateTime.of(2011, 8, 15, 23, 21, 22),
                    kitamiSatoko,
                    threadSatoko);

            commentService.createComment("Stay away from me, Bick Hazard",
                    LocalDateTime.of(2012, 7, 11, 22, 10, 15),
                    anonymousStudentKAERA,
                    threadZakuro);

            commentService.createComment("lol",
                    LocalDateTime.of(2012, 7, 11, 22, 16, 25),
                    anonymousStudentKAERA,
                    threadZakuro);

            commentService.createComment("Pls stay away from me kthx",
                    LocalDateTime.of(2012, 7, 11, 22, 17, 23),
                    anonymousStudentKAERA,
                    threadZakuro);

            commentService.createComment("Don't joke around!",
                    LocalDateTime.of(2012, 7, 11, 22, 21, 13),
                    takashimaZakuro,
                    threadZakuro);

            commentService.createComment("Another one right after Takashima?\n" +
                    "Knock it off with that crap.",
                    LocalDateTime.of(2012, 7, 15, 11, 26, 15),
                    anonymousStudentKAERA,
                    threadMamiya);

            commentService.createComment("I reported you to the police.",
                    LocalDateTime.of(2012, 7, 15, 11, 30, 20),
                    anonymousStudentInkin,
                    threadMamiya);

            commentService.createComment("lol they still haven't caught you yet?",
                    LocalDateTime.of(2012, 7, 15, 11, 35, 40),
                    anonymousStudentADAMO,
                    threadMamiya);

        }
    }
}
