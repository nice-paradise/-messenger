package user;

import java.util.Scanner;

public class Auth {
    private int countUsers;
    private User[] dataBase;



    public Auth(){
        dataBase = new User[100];
        dataBase[0] = new User("Askar11", "1234", "123456", "askar");

        dataBase[1] = new User("Ilnurka", "1234", "1234789", "ilnur");



        countUsers = 2;

    }
    public void showMenu(){

        System.out.println("Меню: ");
        System.out.println(" написать человеку - 1\n написать в группу - 2\n посмотреть канал - 3 ");
        Scanner scanner = new Scanner(System.in);
        int move = scanner.nextInt();

        switch (move){
            case 1:
                System.out.println("кому вы хотите написать? ");
                break;
            case 2:
                System.out.println("выберите группу");
                break;
            case 3:
                System.out.println("выберите канал");
                break;
        }
    }


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
}
