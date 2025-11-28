package service;

import user.User;
import user.UserToUserMessage;

public class DataBase {

    private UserToUserMessage[] messages = new UserToUserMessage[200];
    private int messageCount = 0;

    public void addMessage(UserToUserMessage msg) {
        if (messageCount < messages.length) {
            messages[messageCount++] = msg;
        } else {
            System.out.println("База сообщений переполнена!");
        }
    }

    public void showUserMessages(User user) {
        System.out.println("\n=== Ваши сообщения ===");
        boolean found = false;

        for (int i = 0; i < messageCount; i++) {
            if (messages[i].getToUser().equals(user)) {
                messages[i].printMessage();
                found = true;
            }
        }

        if (!found) {
            System.out.println("Пока нет сообщений");
        }
    }
}
