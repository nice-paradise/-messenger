package сhanel;


import User.User;
import User.Message;

public class Channel {
    private String name;
    private User owner;
    private User[] subscribers = new User[25];
    private int subscriberCount = 0;

    private Message[] messages = new Message[100];
    private int messageCount = 0;

    public Channel(String name, User owner) {
        this.name = name;
        this.owner = owner;
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
        System.out.println(user.getUsername() + " не был подписан на канал");
    }

    public void postMessage(User from, String text) {
        if (!from.equals(owner)) {
            System.out.println("Только владелец канала может отправлять сообщения!");
            return;
        }
        if (messageCount < messages.length) {
            messages[messageCount++] = new Message(from, null, text);
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
            System.out.println(messages[i].getFrom().getUsername() + ": " + messages[i].getText());
        }
    }
}
