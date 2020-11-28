package ru.itis;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.itis.context.AppContext;
import ru.itis.dispatcher.RequestDispatcher;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppContext.class);
        RequestDispatcher dispatcher = context.getBean(RequestDispatcher.class);
        try(BufferedReader in = new BufferedReader(new InputStreamReader(System.in))) {
            while(true) {
                dispatcher.doResolve(in.readLine());
            }
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
}
