package br.com.mekki_user.service;

import br.com.mekki_user.entity.Role;
import br.com.mekki_user.entity.User;
import br.com.mekki_user.repository.UserRepository;
import br.com.mekki_user.to.LoginTO;
import br.com.mekki_user.to.UserCreateTO;
import br.com.mekki_user.to.UserResponseTO;
import br.com.mekki_user.util.Codificar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DefaultUserService implements UserService {


    @Autowired
    private UserRepository repository;

    @Override
    public UserResponseTO save(UserCreateTO userCreateTO, Role role) {

        validateInsert(userCreateTO.getCpf(), userCreateTO.getEmail());

        String prefixPassword = "w.";

        User user = convertToUser(userCreateTO);
        user.setPassword(Codificar.generateHash(prefixPassword + user.getCpf()));
        user.setRoles(new ArrayList<>());
        user.getRoles().add(role);

        user = repository.save(user);

        return getUserResponseTO(user);
    }


    @Override
    public List<UserResponseTO> findAll() {
        List<User> lista = repository.findAll();
        lista.removeIf(user -> user.getRoles().stream().anyMatch(role -> role.getId() == 2));
        return lista.stream().map(this::getUserResponseTO).collect(Collectors.toList());
    }

    @Override
    public UserResponseTO authenticate(LoginTO loginTo) {
        User emailUser = repository.findByEmail(loginTo.getEmail());

        if (emailUser != null) {

            System.out.println("email encontrado");
            loginTo.setPassword(Codificar.generateHash(loginTo.getPassword()));
            if (emailUser.getPassword().equals(loginTo.getPassword())) {
                System.out.println("estranho");
                return getUserResponseTO(emailUser);
            }
        }
        throw new RuntimeException("Credenciais invalidas");
    }

    @Override
    public User findUserByEmail(String email) {
        return repository.findByEmail(email);
    }

    @Override
    public void updateUser(String email, Role role) {
        System.out.println(email);
        User user = repository.findByEmail(email);
        user.getRoles().add(role);
        repository.save(user);
    }


    private User convertToUser(UserCreateTO userCreateTO) {
        User user = new User();
        user.setUsername(userCreateTO.getUsername());
        user.setEmail(userCreateTO.getEmail());
        user.setPassword(userCreateTO.getPassword());
        user.setCpf(userCreateTO.getCpf());
        user.setSchool(userCreateTO.getSchool());
        return user;
    }

    private UserResponseTO getUserResponseTO(User user) {
        UserResponseTO userResponseDTO = new UserResponseTO();
        userResponseDTO.setUsername(user.getUsername());
        userResponseDTO.setEmail(user.getEmail());
        userResponseDTO.setSchool(user.getSchool());
        if (user.getRoles() != null) {
            List<String> roles = user.getRoles().stream()
                    .map(Role::getName)
                    .collect(Collectors.toList());
            userResponseDTO.setRoles(roles);
        }


        return userResponseDTO;
    }

    private boolean validateInsert(String cpf, String email) {
        User emailUser = repository.findByEmail(email);

        if (emailUser != null) {
            throw new RuntimeException("Email já cadastrado");
        }

        User cpfUser = repository.findByCpf(cpf);

        if (cpfUser != null) {
            throw new RuntimeException("cpf já cadastrado");
        }
        return true;

    }

}
