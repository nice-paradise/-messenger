package service;

import user.User;
import user.UserChat;

public class DataBase {
    private UserChat[] userMessages;  // ИЗМЕНИЛ UserToUserMessage на UserChat
    private int messageCount;

    public DataBase() {
        this.userMessages = new UserChat[1000];  // ИЗМЕНИЛ UserToUserMessage на UserChat
        this.messageCount = 0;
    }

    public void addMessage(UserChat message) {  // Оставляем UserChat
        if (messageCount < userMessages.length) {
            userMessages[messageCount] = message;
            messageCount++;
        }
    }

    public void showUserMessages(User user) {
        System.out.println("\n=== СООБЩЕНИЯ ДЛЯ " + user.getName() + " ===");
        boolean found = false;

        for (int i = 0; i < messageCount; i++) {
            UserChat msg = userMessages[i];  // ИЗМЕНИЛ UserToUserMessage на UserChat
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