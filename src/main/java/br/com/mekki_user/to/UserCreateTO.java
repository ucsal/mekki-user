package br.com.mekki_user.to;

import lombok.Data;

@Data
public class UserCreateTO {

    private String Username;

    private String email;

    private String Password;

    private String cpf;

    private String school;
}
