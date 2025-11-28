package user;

public class UserChat {




    private String message;
    private User user;

    public UserChat(User user, String message) {
        this.user = user;
        this.message = message;
    }


    public String getMessage() {
        return message;
    }

    public User getUser() {
        return user;
    }

    // Вывод сообщения
    public void printMessage() {
        System.out.println(user.getName() + ": " + message);
    }





}
