package br.com.mekki_user.service;

import br.com.mekki_user.entity.Role;
import br.com.mekki_user.entity.User;
import br.com.mekki_user.to.LoginTO;
import br.com.mekki_user.to.UserCreateTO;
import br.com.mekki_user.to.UserResponseTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    UserResponseTO save(UserCreateTO user, Role role);
    List<UserResponseTO> findAll();
    UserResponseTO authenticate(LoginTO loginDto);

    User findUserByEmail(String email);

    void updateUser(String email, Role role);
}
