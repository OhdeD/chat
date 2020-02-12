package com.chat.repository;

import com.chat.domain.ChatUser;
import com.chat.domain.Roles;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface RolesRepo extends CrudRepository<Roles, Long> {
    @Override
    Roles save(Roles r);

    @Override
    Optional<Roles> findById(Long id);

    Optional<Roles> findByChatUser(ChatUser u);
    Optional<List<Roles>> findByRole(String role);
}
