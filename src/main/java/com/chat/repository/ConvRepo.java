package com.chat.repository;

import com.chat.domain.Conversation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConvRepo extends CrudRepository <Conversation, Long >{
    @Override
    Conversation save(Conversation conv);

    @Override
    Optional<Conversation> findById(Long id);

    @Override
    void deleteById(Long id);
}
