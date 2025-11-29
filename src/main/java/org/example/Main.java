package org.example;

import user.Auth;
import user.User;
import service.DataBase;
import service.Menu;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Auth auth = new Auth();
        DataBase dataBase = new DataBase();

        System.out.println("====================================");
        System.out.println("    ДОБРО ПОГОЖАЛОВАТЬ В ЧАТ!      ");
        System.out.println("====================================");

        while (true) {
            System.out.println("\n--- ГЛАВНОЕ МЕНЮ ---");
            System.out.println("1. Войти в аккаунт");
            System.out.println("0. Выйти из программы");
            System.out.print("Выберите: ");

            String input = scanner.nextLine();

            switch (input) {
                case "1":
                    User currentUser = auth.signIn();
                    if (currentUser != null) {
                        System.out.println("\n Успешный вход! Здравствуйте, " + currentUser.getName() + "!");
                        Menu.showMenu(currentUser, auth, dataBase);
                        System.out.println("\n Вы вышли из аккаунта " + currentUser.getName());
                    }
                    break;
                case "0":
                    System.out.println("Спасибо за использование! До свидания!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Неверный выбор! Попробуйте снова.");
            }
        }
    }
}