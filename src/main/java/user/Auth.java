package user;

import java.util.Scanner;

public class Auth {
    private int countUsers;
    private User[] dataBase;

    public Auth(){
        dataBase = new User[100];
        dataBase[0] = new User("Askar11", "1234", "123456", "askar");
        dataBase[1] = new User("Ilnurka", "1234", "1234789", "ilnur");
        dataBase[2] = new User("Alba","1234","13141","albert");
        dataBase[3] = new User("Tema","1243","653","artem");

        countUsers = 4;

    public User signIn() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите логин: ");
        String login = scanner.nextLine();
        System.out.println("Введите пароль: ");
        String password = scanner.nextLine();
        User user = checkUser(login, password);
        if (user == null) {
            System.out.println("Неверный логин или пароль!");
        }
        else{
            System.out.println("Вы успешно вошли");
        }
        return user;
    }

    public User checkUser(String login, String password){
        for (int i = 0; i < countUsers; i ++){
            if (login.equals(dataBase[i].getLogin()) && password.equals(dataBase[i].getPassword())){
                return dataBase[i];
            }
        }
        return null;
    }

    public User getUserByID(String id) {
        for (int i = 0; i < countUsers; i++){
            if (id.equals(dataBase[i].getId())){
                return dataBase[i];
            }
        }
        return null;
    }

    public void showAllUsers(){
        System.out.println("\n=== ВСЕ ПОЛЬЗОВАТЕЛИ ===");
        for(int i = 0; i < countUsers; i++){
            System.out.println((i+1) + ". " + dataBase[i].getName() +
                    " (логин: " + dataBase[i].getLogin() + ")");
        }
        System.out.println("Всего пользователей: " + countUsers);
    }

    public void showAllUsersSimple(){
        System.out.println("\n=== ПОЛЬЗОВАТЕЛИ ===");
        for(int i = 0; i < countUsers; i++){
            System.out.println((i+1) + ". " + dataBase[i].getName());
        }
    }


    public void showAllUsersFormatted(){
        System.out.println("\n┌─────────────────────────────┐");
        System.out.println("│        СПИСОК УЧАСТНИКОВ     │");
        System.out.println("├─────────────────────────────┤");
        for(int i = 0; i < countUsers; i++){
            System.out.printf("│ %-2d. %-15s (%-10s) │\n",
                    (i+1), dataBase[i].getName(), dataBase[i].getLogin());
        }
        System.out.println("└─────────────────────────────┘");
    }

    public User getUserByLogin(String login) {
        for (int i = 0; i < countUsers; i++) {
            if (login.equals(dataBase[i].getLogin())) {
                return dataBase[i];
            }
        }
        return null;
    }
}