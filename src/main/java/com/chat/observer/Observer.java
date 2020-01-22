package com.chat.observer;

import com.chat.domain.Conversation;

public interface Observer {
    void update(Conversation conversation);
}
