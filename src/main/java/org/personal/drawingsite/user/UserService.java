package org.personal.drawingsite.user;

import org.springframework.beans.factory.annotation.Autowired;

public class UserService {

    @Autowired
    UserRepository userRepository;

    public UserService() {
    }
}
