package group;

import user.User;

public class Group {
    private String name;
    private User[] members; // пустой массив на 10 участников
    private int memberCount;
    private String[] chatMessages; // массив сообщений чата
    private int messageCount;

    public Group(String name) {
        this.name = name;
        this.members = new User[10]; // пустой массив на 10 человек
        this.memberCount = 0;
        this.chatMessages = new String[100]; // массив чата на 100 сообщений
        this.messageCount = 0;
    }

    // Метод для добавления user в группу
    public void addUser(User user) {
        if (memberCount < members.length) {
            members[memberCount] = user;
            memberCount++;
            System.out.println(user.getName() + " добавлен в группу '" + name + "'");
        } else {
            System.out.println("Группа '" + name + "' переполнена! Нельзя добавить больше участников");
        }
    }

    // Метод для добавления сообщения в чат (будет вызываться из другого класса)
    public void addChatMessage(String message) {
        if (messageCount < chatMessages.length) {
            chatMessages[messageCount] = message;
            messageCount++;
        } else {
            System.out.println("Чат группы '" + name + "' переполнен!");
        }
    }

    // Показать всех участников группы
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

    // Показать историю чата
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

    // Геттеры
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
