package br.com.mekki_user.repository;

import br.com.mekki_user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    User findByEmail(String email);

    User findByCpf(String cpf);
}

