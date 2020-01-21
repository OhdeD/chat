package com.chat.repository;

import com.chat.domain.ChatUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
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
}
