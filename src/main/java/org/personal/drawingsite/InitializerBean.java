package org.personal.drawingsite;

import org.personal.drawingsite.drawRequest.DrawRequest;
import org.personal.drawingsite.drawRequest.DrawRequestRepository;
import org.personal.drawingsite.image.Image;
import org.personal.drawingsite.image.ImageRepository;
import org.personal.drawingsite.security.role.Role;
import org.personal.drawingsite.security.role.RoleRepository;
import org.personal.drawingsite.user.User;
import org.personal.drawingsite.user.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.HashSet;

@Component
public class InitializerBean {

    public static final Logger LOGGER = LoggerFactory.getLogger(InitializerBean.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private DrawRequestRepository drawRequestRepository;

    @Autowired
    private ImageRepository imageRepository;

    @PostConstruct
    public void init() {
        roleRepository.save(new Role("USER"));
        roleRepository.save(new Role("ADMIN"));
        Role adminRole = roleRepository.findByRole("ADMIN");
        Role userRole = roleRepository.findByRole("USER");
        User simpleUser = new User();

        simpleUser.setUsername("fricho");
        simpleUser.setPassword("$2a$10$iP7tPsRclhoQPoWqwFENj.CiS8HGXW6NnJuGiBbnIdPc26aXJmsMe");
        simpleUser.setEmail("user@usermail.com");
        simpleUser.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        userRepository.save(simpleUser);

        User adminUser = new User();

        adminUser.setUsername("theartist");
        adminUser.setPassword("$2a$10$lv7QGyOy3q6zuAdLV.tKzuntXiYAgl28qCEuc.Q9GtAMZC52GXLia");
        adminUser.setEmail("admin@adminmail.ad");
        adminUser.setRoles(new HashSet<Role>(Arrays.asList(adminRole)));
        userRepository.save(adminUser);

        //Test draw request
        DrawRequest dr = new DrawRequest();
        dr.setRequestName("Vayne from League");
        dr.setRequestDescription("I'd like to request Vayne's spash art from league of legends");
        dr.setUsername("HiImGosu");
        drawRequestRepository.save(dr);

        DrawRequest dr2 = new DrawRequest();
        dr2.setRequestName("Rebellion from Devil may Cry");
        dr2.setRequestDescription("I'd like to have a drawing of the Rebellion from Devil May Cry in my hands. Picture of me: (url)");
        dr2.setUsername("Vergil");
        drawRequestRepository.save(dr2);

        DrawRequest dr3 = new DrawRequest();
        dr3.setRequestName("A portrait of me");
        dr3.setRequestDescription("I want a portrait of myself!!! (url)");
        dr3.setUsername("DravenNumberOne");
        drawRequestRepository.save(dr3);

        DrawRequest dr4 = new DrawRequest();
        dr4.setRequestName("Uganda Knuckles meme");
        dr4.setRequestDescription("I'd like a fully covered image of the do you kno da wei meme");
        dr4.setUsername("nopi");
        drawRequestRepository.save(dr4);

        Image image1 = new Image();
        image1.setLocation("/img/image_1.jpg");
        image1.setName("A portrait");
        image1.setComplexity(3);
        image1.setHours(10);

        Image image2 = new Image();
        image2.setLocation("/img/image_2.jpg");
        image2.setName("Goddess of candles");
        image2.setComplexity(7);
        image2.setHours(22);

        Image image3 = new Image();
        image3.setLocation("/img/image_3.jpg");
        image3.setName("My nightmare");
        image3.setComplexity(4);
        image3.setHours(10);

        Image image4 = new Image();
        image4.setLocation("/img/image_4.jpg");
        image4.setName("Gomez & Morticia Addams");
        image4.setComplexity(10);
        image4.setHours(25);

        Image image5 = new Image();
        image5.setLocation("/img/image_5.jpg");
        image5.setName("Main car from Death Race");
        image5.setComplexity(6);
        image5.setHours(15);

        Image image6 = new Image();
        image6.setLocation("/img/image_6.jpg");
        image6.setName("1967 Chevrolet Impala");
        image6.setComplexity(3);
        image6.setHours(9);

        Image image7 = new Image();
        image7.setLocation("/img/image_7.jpg");
        image7.setName("Jinx poster from League of Legends");
        image7.setComplexity(6);
        image7.setHours(14);

        imageRepository.save(image1);
        imageRepository.save(image2);
        imageRepository.save(image3);
        imageRepository.save(image4);
        imageRepository.save(image5);
        imageRepository.save(image6);
        imageRepository.save(image7);
    }
}
