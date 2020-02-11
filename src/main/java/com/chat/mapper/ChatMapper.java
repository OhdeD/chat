package com.chat.mapper;

import com.chat.domain.*;
import com.chat.domain.DTO.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ChatMapper {
    public ChatUser mapToChatUser(ChatUserDto chatUserDto) {
        return new ChatUser(chatUserDto.getId(),
                chatUserDto.getName().toUpperCase(),
                chatUserDto.getSurname().toUpperCase(),
                chatUserDto.getMail(),
                chatUserDto.getPassword(),
                chatUserDto.getCity().toUpperCase(),
                false,
                mapToFriendsList(chatUserDto.getFriendsListDto()));
    }

    public ChatUser mapToNEWChatUser(ChatUserDto chatUserDto) {
        return new ChatUser(chatUserDto.getName().toUpperCase(),
                chatUserDto.getSurname().toUpperCase(),
                chatUserDto.getMail(),
                chatUserDto.getPassword(),
                chatUserDto.getCity().toUpperCase(),
                false);
    }

    public ChatUserDto mapToChatUserDto(ChatUser chatUser) {
        return ChatUserDto.builder()
                .id(chatUser.getId())
                .name(chatUser.getName())
                .surname(chatUser.getSurname())
                .mail(chatUser.getMail())
                .password(chatUser.getPassword())
                .city(chatUser.getCity())
                .logged(chatUser.isLogged())
                .friendsListDto(mapToFriendsListDto(chatUser.getFriendsList())).build();
    }

    public List<ChatUserDto> mapToChatUserDtoList(List<ChatUser> chatUserList) {
        return chatUserList.stream()
                .map(this::mapToChatUserDto)
                .collect(Collectors.toList());
    }

    public FriendsListDto mapToFriendsListDto(FriendsList friendsList) {
        if (friendsList!= null) {
            return new FriendsListDto(friendsList.getId(), friendsList.getFriends().stream()
                    .map(a -> ChatUserDto.builder()
                            .name(a.getName())
                            .surname(a.getSurname())
                            .mail(a.getMail())
                            .password(a.getPassword())
                            .city(a.getCity())
                            .logged(a.isLogged()).build())
                    .collect(Collectors.toList()));
        }else return new FriendsListDto();
    }

    public FriendsList mapToFriendsList(FriendsListDto friendsListDto) {
        return new FriendsList(friendsListDto.getId(), friendsListDto.getFriends().stream()
                .map(a -> new ChatUser(a.getId(), a.getName(), a.getSurname(), a.getMail(), a.getPassword(), a.getCity(), a.isLogged()))
                .collect(Collectors.toList()));
    }

    public Message mapToMessage(MessageDto messageDto) {
        return new Message(messageDto.getSenderId(), messageDto.getSenderId(), messageDto.getReceiverId(), messageDto.getMessage(), messageDto.getSendingDate(), messageDto.getConversationId(), messageDto.isRead());
    }

    public MessageDto mapToMessageDto(Message message) {
        return MessageDto.builder()
                .id(message.getId())
                .senderId(message.getSenderId())
                .receiverId(message.getReceiverId())
                .message(message.getMessage())
                .sendingDate(message.getSendingDate())
                .conversationId(message.getConversationId())
                .read(message.isRead()).build();
    }

    public List<MessageDto> mapToMessageDtoList(List<Message> l) {
        return l.stream()
                .map(this::mapToMessageDto)
                .collect(Collectors.toList());
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

    public RolesDto mapToRolesDto(Roles r){
        return RolesDto.builder()
                .id(r.getId())
                .chatUserdto(mapToChatUserDto(r.getChatUser()))
                .role(r.getRole()).build();
    }

    public Roles mapToRoles (RolesDto r){
        return new Roles(r.getId(), r.getRole(), mapToChatUser(r.getChatUserdto()));
    }

}
