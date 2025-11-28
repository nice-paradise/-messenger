package chanel;

import user.User;
import user.UserChat;

public class Channel {
    private String name;
    private User owner;
    private User[] subscribers = new User[25];
    private int subscriberCount = 0;

    private UserChat[] messages = new UserChat[100];
    private int messageCount = 0;

    public Channel(String name, User owner) {
        this.name = name;
        this.owner = owner;
    }

    public void subscribe(User user) {
        if (subscriberCount < subscribers.length) {
            subscribers[subscriberCount++] = user;
            System.out.println(user.getName() + " подписался на канал " + name);
        } else {
            System.out.println("Невозможно подписаться! Канал переполнен.");
        }
    }

    public void unsubscribe(User user) {
        for (int i = 0; i < subscriberCount; i++) {
            if (subscribers[i].equals(user)) {
                for (int j = i; j < subscriberCount - 1; j++) {
                    subscribers[j] = subscribers[j + 1];
                }
                subscribers[--subscriberCount] = null;
                System.out.println(user.getName() + " отписался от канала " + name);
                return;
            }
        }
        System.out.println(user.getName() + " не был подписан на канал");
    }

    public void postMessage(User from, String text) {
        if (!from.equals(owner)) {
            System.out.println("Только владелец канала может отправлять сообщения!");
            return;
        }
        if (messageCount < messages.length) {
            messages[messageCount++] = new UserChat(from, null, text);
            System.out.println("В канал '" + name + "' отправлено сообщение: " + text);
        } else {
            System.out.println("Канал переполнен, сообщение не отправлено");
        }
    }

    public void showMessages(User user) {
        boolean hasAccess = false;
        if (user.equals(owner)) hasAccess = true;
        for (int i = 0; i < subscriberCount; i++) {
            if (subscribers[i].equals(user)) hasAccess = true;
        }
        if (!hasAccess) {
            System.out.println("У вас нет доступа к сообщениям канала '" + name + "'");
            return;
        }

        if (messageCount == 0) {
            System.out.println("Сообщений пока нет");
            return;
        }

        System.out.println("Сообщения канала '" + name + "':");
        for (int i = 0; i < messageCount; i++) {
            System.out.println(messages[i].getFromUser().getName() + ": " + messages[i].getMessage());
        }
    }

    public String getName() {
        return name;
    }

    public User getOwner() {
        return owner;
    }

    public void showSubscribers() {
        System.out.println("\n=== Подписчики канала '" + name + "' ===");
        if (subscriberCount == 0) {
            System.out.println("Подписчиков пока нет");
        } else {
            for (int i = 0; i < subscriberCount; i++) {
                System.out.println((i + 1) + ". " + subscribers[i].getName() +
                        " (логин: " + subscribers[i].getLogin() + ")");
            }
        }
        System.out.println("Всего подписчиков: " + subscriberCount + "/" + subscribers.length);
    }

    public boolean isSubscriber(User user) {
        for (int i = 0; i < subscriberCount; i++) {
            if (subscribers[i].equals(user)) {
                return true;
            }
        }
        return false;
    }
}