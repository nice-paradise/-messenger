package service;

import user.User;
import user.Auth;
import user.UserToUserMessage;
import java.util.Scanner;

public class Menu {

    public static void showMenu(User currentUser, Auth auth, DataBase dataBase) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("\n=== МЕНЮ ===");
            System.out.println("1. Написать сообщение");
            System.out.println("2. Посмотреть мои сообщения");
            System.out.println("3. Выйти");
            System.out.print("Выберите: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    sendMessage(currentUser, auth, dataBase);
                    break;
                case 2:
                    dataBase.showUserMessages(currentUser);
                    break;
                case 3:
                    exit = true;
                    System.out.println("Выход...");
                    break;
                default:
                    System.out.println("Неверный выбор!");
            }
        }
    }

    private static void sendMessage(User currentUser, Auth auth, DataBase dataBase) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n=== НАПИСАТЬ СООБЩЕНИЕ ===");
        auth.showAllUsers();

        System.out.print("Введите логин получателя: ");
        String login = scanner.nextLine();

        User recipient = auth.getUserByLogin(login);
        if (recipient != null && !login.equals(currentUser.getLogin())) {
            System.out.print("Введите сообщение: ");
            String message = scanner.nextLine();

            UserToUserMessage userMessage = new UserToUserMessage(currentUser, recipient, message);
            dataBase.addMessage(userMessage);

            System.out.println("Сообщение отправлено!");
        } else if (login.equals(currentUser.getLogin())) {
            System.out.println("Нельзя отправить себе!");
        } else {
            System.out.println("Пользователь не найден!");
        }
    }
}