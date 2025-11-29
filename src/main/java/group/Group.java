package group;

import user.User;

public class Group {
    public User[] members;
    private String name;
    private int memberCount;
    private String[] chatMessages;
    private int messageCount;

    public Group(String name) {
        this.name = name;
        this.members = new User[10];
        this.memberCount = 0;
        this.chatMessages = new String[100];
        this.messageCount = 0;
    }

    public void addUser(User user) {
        if (memberCount < members.length) {
            members[memberCount] = user;
            memberCount++;
            System.out.println(user.getName() + " добавлен в группу '" + name + "'");
        } else {
            System.out.println("Группа '" + name + "' переполнена! Нельзя добавить больше участников");
        }
    }

    public void addChatMessage(String message) {
        if (messageCount < chatMessages.length) {
            chatMessages[messageCount] = message;
            messageCount++;
        } else {
            System.out.println("Чат группы '" + name + "' переполнен!");
        }
    }

    public void showAllMembers() {
        System.out.println("\n=== Участники группы '" + name + "' ===");
        if (memberCount == 0) {
            System.out.println("В группе нет участников");
        } else {
            for (int i = 0; i < memberCount; i++) {
                System.out.println((i + 1) + ". " + members[i].getName() +
                        " (логин: " + members[i].getLogin() + ")");
            }
        }
        System.out.println("Свободных мест: " + (members.length - memberCount));
    }

    public void showChatHistory() {
        System.out.println("\n=== История чата группы '" + name + "' ===");
        if (messageCount == 0) {
            System.out.println("Сообщений пока нет");
        } else {
            for (int i = 0; i < messageCount; i++) {
                System.out.println(chatMessages[i]);
            }
        }
    }

    public String getName() {
        return name;
    }

    public int getMemberCount() {
        return memberCount;
    }

    public int getMessageCount() {
        return messageCount;
    }

    public int getMaxMembers() {
        return members.length;
    }
}