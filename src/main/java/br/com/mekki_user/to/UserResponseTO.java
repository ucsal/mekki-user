package br.com.mekki_user.to;

import lombok.Data;

import java.util.List;

@Data
public class UserResponseTO {

    private Integer id;

    private String Username;

    private String email;

    private String school;

    private List<String> roles;
}
