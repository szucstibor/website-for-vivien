package org.personal.drawingsite.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public UserService() {
    }

    public void register(Map<String, String> requestParams){
        String username = requestParams.get("regUsername");
        String password = requestParams.get("regPassword");
        String email = requestParams.get("email");
        if (username.length() > 5 && password.length() > 6){
            User newUser = new User(username, password, email);
            userRepository.save(newUser);
        }
    }
}
