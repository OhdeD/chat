package com.chat.repository;

import com.chat.domain.Message;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MessegeRepo extends CrudRepository <Message, Long> {
    @Override
    Message  save(Message message);

    @Override
    Optional<Message> findById(Long id);

    @Override
    List<Message> findAll();

    @Override
    void deleteById(Long id);
}
