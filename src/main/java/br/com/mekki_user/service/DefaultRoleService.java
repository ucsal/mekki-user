package br.com.mekki_user.service;

import br.com.mekki_user.entity.Role;
import br.com.mekki_user.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DefaultRoleService  implements  RoleService{


    private static final Integer ROLE_USER = 1;

    @Autowired
    private RoleRepository repository;

    @Override
    public Role register() {
        return repository.findById(ROLE_USER).get();
    }

}
