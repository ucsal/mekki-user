package br.com.mekki_user.service;

import br.com.mekki_user.entity.Role;
import org.springframework.stereotype.Service;

@Service
public interface RoleService {
    Role register();
}
