package org.example;
import chanel.Chanel;
import user.Auth;
import user.User;
import service.Menu;
import service.DataBase;
import group.Group;
import chanel.Chanel;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // Инициализация
        Auth auth = new Auth();
        DataBase dataBase = new DataBase();

        System.out.println("=== Добро пожаловать в систему ===");

        // Авторизация
        User currentUser = null;
        while (currentUser == null) {
            currentUser = auth.signIn();
        }

        System.out.println("\nДобро пожаловать, " + currentUser.getName() + "!");

        // Создаём одну тестовую группу и канал
        Group myGroup = new Group("Java Developers");
        Chanel myChannel = new Chanel("Объявления", currentUser);

        boolean exit = false;

        while (!exit) {
            System.out.println("\n=== ГЛАВНОЕ МЕНЮ ===");
            System.out.println("1. Личные сообщения");
            System.out.println("2. Группа: Java Developers");
            System.out.println("3. Канал: Объявления");
            System.out.println("4. Выйти");
            System.out.print("Выберите: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    // Личные сообщения — использует твой Menu.class
                    Menu.showMenu(currentUser, auth, dataBase);
                    break;

                case 2:
                    groupMenu(scanner, myGroup, currentUser);
                    break;

                case 3:
                    channelMenu(scanner, myChannel, currentUser);
                    break;

                case 4:
                    exit = true;
                    System.out.println("Завершение работы...");
                    break;

                default:
                    System.out.println("Неверный ввод!");
            }
        }
    }


    // === МЕНЮ ГРУППЫ ===
    private static void groupMenu(Scanner scanner, Group group, User currentUser) {

        boolean back = false;

        while (!back) {
            System.out.println("\n=== Групповой чат: " + group.getName() + " ===");
            System.out.println("1. Вступить в группу");
            System.out.println("2. Показать участников");
            System.out.println("3. Написать сообщение в групповой чат");
            System.out.println("4. История чата");
            System.out.println("5. Назад");
            System.out.print("Выберите: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    group.addUser(currentUser);
                    break;

                case 2:
                    group.showAllMembers();
                    break;

                case 3:
                    System.out.print("Введите сообщение: ");
                    String text = scanner.nextLine();
                    group.addChatMessage(currentUser.getName() + ": " + text);
                    System.out.println("Отправлено!");
                    break;

                case 4:
                    group.showChatHistory();
                    break;

                case 5:
                    back = true;
                    break;

                default:
                    System.out.println("Ошибка: неверный выбор!");
            }
        }
    }


    // === МЕНЮ КАНАЛА ===
    private static void channelMenu(Scanner scanner, Chanel channel, User currentUser) {

        boolean back = false;

        while (!back) {
            System.out.println("\n=== Канал: " + channel.getName() + " ===");
            System.out.println("1. Подписаться");
            System.out.println("2. Отписаться");
            System.out.println("3. Написать сообщение (только владелец)");
            System.out.println("4. Посмотреть сообщения");
            System.out.println("5. Назад");
            System.out.print("Выберите: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    channel.subscribe(currentUser);
                    break;

                case 2:
                    channel.unsubscribe(currentUser);
                    break;

                case 3:
                    System.out.print("Введите текст поста: ");
                    String post = scanner.nextLine();
                    channel.postMessage(currentUser, post);
                    break;

                case 4:
                    channel.showMessages(currentUser);
                    break;

                case 5:
                    back = true;
                    break;

                default:
                    System.out.println("Ошибка: неверный выбор!");
            }
        }
    }
}
