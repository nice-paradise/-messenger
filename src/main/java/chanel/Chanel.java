package chanel;

import user.User;
import user.Message;

public class Chanel {
    private String name;
    private User owner;
    private User[] subscribers = new User[25];
    private int subscriberCount = 0;

    private Message[] messages = new Message[100];
    private int messageCount = 0;

    public Chanel(String name, User owner) {
        this.name = name;
        this.owner = owner;
    }

    public String getName() {
        return name;
    }

    public void subscribe(User user) {
        if (subscriberCount < subscribers.length) {
            subscribers[subscriberCount++] = user;
            System.out.println(user.getUsername() + " подписался на канал " + name);
        } else {
            System.out.println("Невозможно подписаться!");
        }
    }

    public void unsubscribe(User user) {
        for (int i = 0; i < subscriberCount; i++) {
            if (subscribers[i].equals(user)) {
                for (int j = i; j < subscriberCount - 1; j++) {
                    subscribers[j] = subscribers[j + 1];
                }
                subscribers[--subscriberCount] = null;
                System.out.println(user.getUsername() + " отписался от канала " + name);
                return;
            }
        }
        System.out.println(user.getUsername() + " не был подписан");
    }

    public void postMessage(User from, String text) {
        if (!from.equals(owner)) {
            System.out.println("Только владелец может писать!");
            return;
        }

        if (messageCount < messages.length) {
            messages[messageCount++] = new Message(from, null, text);
            System.out.println("Сообщение отправлено в '" + name + "': " + text);
        } else {
            System.out.println("Канал переполнен");
        }
    }

    public void showMessages(User user) {
        boolean hasAccess = user.equals(owner);

        for (int i = 0; i < subscriberCount; i++) {
            if (subscribers[i].equals(user)) hasAccess = true;
        }

        if (!hasAccess) {
            System.out.println("Нет доступа к сообщениям канала '" + name + "'");
            return;
        }

        if (messageCount == 0) {
            System.out.println("Сообщений нет");
            return;
        }

        System.out.println("Сообщения канала '" + name + "':");
        for (int i = 0; i < messageCount; i++) {
            System.out.println(messages[i].getFrom().getUsername() + ": " + messages[i].getText());
        }
    }
}

