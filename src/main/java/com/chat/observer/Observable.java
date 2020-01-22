package com.chat.observer;

import com.chat.domain.Message;
import com.chat.exception.ChatUserNotFoundException;

public interface Observable {

    void specifyReciever(Observer observer);
    void notifyReciever();
}
