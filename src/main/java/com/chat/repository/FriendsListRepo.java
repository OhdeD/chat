package com.chat.repository;

import com.chat.domain.FriendsList;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
@Repository
@Transactional
public interface FriendsListRepo extends CrudRepository<FriendsList, Long> {
    @Override
    FriendsList save(final FriendsList friendsList);

    @Override
    Optional<FriendsList> findById(Long id);

    @Override
    void deleteById(Long id);



}
