package service;

import user.User;
import user.UserToUserMessage;

public class DataBase {
    private UserToUserMessage[] userMessages;
    private int messageCount;

    public DataBase() {
        this.userMessages = new UserToUserMessage[1000];
        this.messageCount = 0;
    }

    public void addMessage(UserToUserMessage message) {
        if (messageCount < userMessages.length) {
            userMessages[messageCount] = message;
            messageCount++;
        }
    }

    public void showUserMessages(User user) {
        System.out.println("\n=== СООБЩЕНИЯ ДЛЯ " + user.getName() + " ===");
        boolean found = false;

        for (int i = 0; i < messageCount; i++) {
            UserToUserMessage msg = userMessages[i];
            if (msg.getFromUser().getLogin().equals(user.getLogin()) ||
                    msg.getToUser().getLogin().equals(user.getLogin())) {
                msg.printMessage();
                found = true;
            }
        }

        if (!found) {
            System.out.println("Сообщений нет");
        }
    }
}