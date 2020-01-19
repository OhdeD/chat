package com.chat.mapper;

import com.chat.domain.ChatUser;
import com.chat.domain.Conversation;
import com.chat.domain.DTO.ChatUserDto;
import com.chat.domain.DTO.ConversationDto;
import com.chat.domain.DTO.FriendsListDto;
import com.chat.domain.DTO.MessageDto;
import com.chat.domain.FriendsList;
import com.chat.domain.Message;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class ChatMapper {

    public ChatUser mapToChatUser(ChatUserDto chatUserDto) {
        return new ChatUser(chatUserDto.getId(),
                chatUserDto.getName(),
                chatUserDto.getSurname(),
                chatUserDto.getMail(),
                chatUserDto.getPassword(),
                chatUserDto.getCity(),
                false,
                mapToFriendsList(chatUserDto.getFriendsListDto()));
    }
    public ChatUser mapToNEWChatUser(ChatUserDto chatUserDto) {
        return new ChatUser(chatUserDto.getName(),
                chatUserDto.getSurname(),
                chatUserDto.getMail(),
                chatUserDto.getPassword(),
                chatUserDto.getCity(),
                false);
    }

    public ChatUserDto mapToChatUserDto(ChatUser chatUser) {
        return ChatUserDto.builder()
                .name(chatUser.getName())
                .surname(chatUser.getSurname())
                .mail(chatUser.getMail())
                .password(chatUser.getPassword())
                .city(chatUser.getCity())
                .logged(chatUser.isLogged())
                .friendsListDto(mapToFriendsListDto(chatUser.getFriendsList())).build();
    }


    public FriendsListDto mapToFriendsListDto(FriendsList friendsList) {
        return new FriendsListDto(friendsList.getId(), friendsList.getFriends().stream()
                .map(a -> ChatUserDto.builder()
                        .name(a.getName())
                        .surname(a.getSurname())
                        .mail(a.getMail())
                        .password(a.getPassword())
                        .city(a.getCity())
                        .logged(a.isLogged()).build())
                .collect(Collectors.toList()));
    }

    public FriendsList mapToFriendsList(FriendsListDto friendsListDto) {
        return new FriendsList(friendsListDto.getId(), friendsListDto.getFriends().stream()
                .map(a -> new ChatUser(a.getId(), a.getName(), a.getSurname(), a.getMail(), a.getPassword(), a.getCity(), a.isLogged()))
                .collect(Collectors.toList()));
    }

    public Message mapToMessage(MessageDto messageDto) {
        return new Message(messageDto.getSenderId(), messageDto.getSenderId(), messageDto.getRecieverId(), messageDto.getMessage(), messageDto.getSendingDate(), messageDto.getConversationId());
    }

    public MessageDto mapToMessageDto(Message message) {
        return MessageDto.builder()
                .id(message.getId())
                .senderId(message.getSenderId())
                .recieverId(message.getRecieverId())
                .message(message.getMessage())
                .sendingDate(message.getSendingDate())
                .conversationId(message.getConversationId()).build();
    }

    public Conversation mapToConversation(ConversationDto conv) {
        return new Conversation(conv.getId(), conv.getParticipanst(), conv.getMessages().stream()
                .map(this::mapToMessage)
                .collect(Collectors.toList()));
    }

    public ConversationDto mapToConversationDto(Conversation conv) {
        return ConversationDto.builder()
                .id(conv.getId())
                .participanst(conv.getParticipants())
                .messages(conv.getMessages().stream()
                        .map(this::mapToMessageDto)
                        .collect(Collectors.toList())).build();
    }

}
