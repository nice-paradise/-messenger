package service;

import user.User;
import user.Auth;
import user.UserChat;
import group.Group;
import group.GroupManager;
import java.util.Scanner;

public class Menu {
    private static Auth auth;

    public static void showMenu(User currentUser, Auth authInstance, DataBase dataBase) {
        auth = authInstance;
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("\n=== ГЛАВНОЕ МЕНЮ ===");
            System.out.println("Добро пожаловать, " + currentUser.getName() + "!");
            System.out.println("1. Написать личное сообщение");
            System.out.println("2. Посмотреть мои сообщения");
            System.out.println("3. Группы");
            System.out.println("0. Выйти");
            System.out.print("Выберите: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    sendMessage(currentUser, dataBase, scanner);
                    break;
                case 2:
                    dataBase.showUserMessages(currentUser);
                    break;
                case 3:
                    showGroupMenu(currentUser, dataBase, scanner);
                    break;
                case 0:
                    exit = true;
                    System.out.println("До свидания!");
                    break;
                default:
                    System.out.println("Неверный выбор!");
            }
        }
    }

    private static void showGroupMenu(User currentUser, DataBase dataBase, Scanner scanner) {
        GroupManager groupManager = new GroupManager();
        boolean back = false;

        while (!back) {
            System.out.println("\n=== МЕНЮ ГРУПП ===");
            System.out.println("1. Создать группу");
            System.out.println("2. Добавить пользователя в группу");
            System.out.println("3. Написать в группу");
            System.out.println("4. Просмотреть группу");
            System.out.println("5. Мои группы");
            System.out.println("6. Все группы");
            System.out.println("0. Назад в главное меню");
            System.out.print("Выберите: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    createGroup(currentUser, groupManager, scanner);
                    break;
                case 2:
                    addUserToGroup(groupManager, scanner);
                    break;
                case 3:
                    writeToGroup(currentUser, groupManager, scanner);
                    break;
                case 4:
                    viewGroup(groupManager, scanner);
                    break;
                case 5:
                    groupManager.showUserGroups(currentUser);
                    break;
                case 6:
                    groupManager.showAllGroups();
                    break;
                case 0:
                    back = true;
                    break;
                default:
                    System.out.println("Неверный выбор!");
            }
        }
    }

    private static void createGroup(User currentUser, GroupManager groupManager, Scanner scanner) {
        System.out.print("Введите название группы: ");
        String name = scanner.nextLine();
        groupManager.createGroup(name, currentUser);
    }

    private static void addUserToGroup(GroupManager groupManager, Scanner scanner) {
        groupManager.showAllGroups();
        System.out.print("Введите название группы: ");
        String groupName = scanner.nextLine();

        Group group = groupManager.findGroup(groupName);
        if (group != null) {
            auth.showAllUsers();
            System.out.print("Введите логин пользователя для добавления: ");
            String userLogin = scanner.nextLine();

            User userToAdd = auth.getUserByLogin(userLogin);
            if (userToAdd != null) {
                group.addUser(userToAdd);
            } else {
                System.out.println("Пользователь не найден!");
            }
        } else {
            System.out.println("Группа не найдена!");
        }
    }

    private static void writeToGroup(User currentUser, GroupManager groupManager, Scanner scanner) {
        groupManager.showAllGroups();
        System.out.print("Введите название группы: ");
        String groupName = scanner.nextLine();

        Group group = groupManager.findGroup(groupName);
        if (group != null) {
            System.out.print("Введите сообщение для группы: ");
            String message = scanner.nextLine();

            String formattedMessage = currentUser.getName() + ": " + message;
            group.addChatMessage(formattedMessage);
            System.out.println("Сообщение отправлено в группу '" + groupName + "'");
        } else {
            System.out.println("Группа не найдена!");
        }
    }

    private static void viewGroup(GroupManager groupManager, Scanner scanner) {
        groupManager.showAllGroups();
        System.out.print("Введите название группы: ");
        String groupName = scanner.nextLine();

        Group group = groupManager.findGroup(groupName);
        if (group != null) {
            System.out.println("\n=== ИНФОРМАЦИЯ О ГРУППЕ '" + groupName + "' ===");
            group.showAllMembers();
            group.showChatHistory();
        } else {
            System.out.println("Группа не найдена!");
        }
    }

    private static void sendMessage(User currentUser, DataBase dataBase, Scanner scanner) {
        System.out.println("\n=== НАПИСАТЬ ЛИЧНОЕ СООБЩЕНИЕ ===");
        auth.showAllUsers();
        System.out.print("Введите логин получателя: ");
        String login = scanner.nextLine();

        User recipient = auth.getUserByLogin(login);
        if (recipient != null && !login.equals(currentUser.getLogin())) {
            System.out.print("Введите сообщение: ");
            String message = scanner.nextLine();
            UserChat userMessage = new UserChat(currentUser, recipient, message);
            dataBase.addMessage(userMessage);
            System.out.println("Сообщение отправлено!");
        } else if (login.equals(currentUser.getLogin())) {
            System.out.println("Нельзя отправить себе!");
        } else {
            System.out.println("Пользователь не найден!");
        }
    }
}