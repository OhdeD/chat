package com.chat.repository;

import com.chat.domain.ChatUser;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface ChatUserRepo extends CrudRepository<ChatUser, Long> {
    @Override
    Optional<ChatUser> findById(Long id);

    @Override
    List<ChatUser> findAll();

    @Override
    long count();

    @Override
    void deleteById(Long id);

    @Override
    ChatUser save(final ChatUser chatUser);

    Optional<List<ChatUser>> findAllByName(String name);

    @Modifying
    @Query(value = "SELECT * FROM CHAT_USER ;", nativeQuery = true)
    List<ChatUser> getAllUsers();
}



