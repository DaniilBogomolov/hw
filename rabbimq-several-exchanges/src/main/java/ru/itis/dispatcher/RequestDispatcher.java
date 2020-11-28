package ru.itis.dispatcher;

import org.springframework.stereotype.Component;

public interface RequestDispatcher {

    void doResolve(String command);
}
