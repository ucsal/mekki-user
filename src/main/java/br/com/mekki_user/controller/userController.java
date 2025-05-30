package br.com.mekki_user.controller;


import br.com.mekki_user.entity.Role;
import br.com.mekki_user.entity.User;
import br.com.mekki_user.service.RoleService;
import br.com.mekki_user.service.UserService;
import br.com.mekki_user.to.LoginTO;
import br.com.mekki_user.to.UserCreateTO;
import br.com.mekki_user.to.UserResponseTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/user")
public class userController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;


    @PostMapping("/register")
    @ResponseStatus(CREATED)
    public UserResponseTO save(@RequestBody UserCreateTO user) {
        Role role = roleService.register();
        return userService.save(user,role);
    }


    //    @PreAuthorize("hasRole('ADMINISTRADOR')")
    @GetMapping("/list")
    public List<UserResponseTO> findAll() {
        return userService.findAll();
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(@RequestBody LoginTO loginTo){

        UserResponseTO user = userService.authenticate(loginTo);

        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }

        return ResponseEntity.ok().body(user);
    }


//    @PostMapping("update/user")
//    public ResponseEntity<?> updateUser(@RequestBody UserUpdateRequest userUpdateRequest){
//        userService.updateUser(userUpdateRequest.getEmail());
//        return ResponseEntity.ok().body("deu certo");
//    }
}
