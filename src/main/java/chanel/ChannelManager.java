package chanel;

import user.User;

public class ChannelManager {
    private Channel[] channels = new Channel[10];
    private int channelCount = 0;

    public Channel createChannel(String name, User owner) {
        if (channelCount < channels.length) {
            Channel channel = new Channel(name, owner);
            channels[channelCount++] = channel;
            System.out.println("Канал '" + name + "' создан!");
            return channel;
        } else {
            System.out.println("Достигнут лимит каналов!");
            return null;
        }
    }

    public Channel findChannel(String name) {
        for (int i = 0; i < channelCount; i++) {
            if (channels[i].getName().equals(name)) {
                return channels[i];
            }
        }
        return null;
    }

    public void showAllChannels() {
        System.out.println("\n=== ВСЕ КАНАЛЫ ===");
        if (channelCount == 0) {
            System.out.println("Каналов нет");
        } else {
            for (int i = 0; i < channelCount; i++) {
                Channel channel = channels[i];
                System.out.println((i + 1) + ". " + channel.getName() +
                        " (владелец: " + channel.getOwner().getName() +
                        ", подписчиков: " + getSubscriberCount(channel) + ")");
            }
        }
    }

    public void showUserChannels(User user) {
        System.out.println("\n=== МОИ КАНАЛЫ ===");
        boolean foundOwned = false;
        boolean foundSubscribed = false;

        // Каналы, где пользователь владелец
        for (int i = 0; i < channelCount; i++) {
            Channel channel = channels[i];
            if (channel.getOwner().equals(user)) {
                if (!foundOwned) {
                    System.out.println("Созданные каналы:");
                    foundOwned = true;
                }
                System.out.println("- " + channel.getName() + " (подписчиков: " +
                        getSubscriberCount(channel) + ")");
            }
        }

        // Каналы, где пользователь подписчик
        for (int i = 0; i < channelCount; i++) {
            Channel channel = channels[i];
            if (channel.isSubscriber(user) && !channel.getOwner().equals(user)) {
                if (!foundSubscribed) {
                    System.out.println("Подписки:");
                    foundSubscribed = true;
                }
                System.out.println("- " + channel.getName() + " (владелец: " +
                        channel.getOwner().getName() + ")");
            }
        }

        if (!foundOwned && !foundSubscribed) {
            System.out.println("Вы не создали каналов и не подписаны ни на один канал");
        }
    }

    private int getSubscriberCount(Channel channel) {
        // Временный метод, пока не добавим счетчик подписчиков в Channel
        java.lang.reflect.Field field;
        try {
            field = Channel.class.getDeclaredField("subscriberCount");
            field.setAccessible(true);
            return (int) field.get(channel);
        } catch (Exception e) {
            return 0;
        }
    }
}