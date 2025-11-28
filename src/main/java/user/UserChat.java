package user;

public class UserChat {
    private User fromUser;
    private User toUser;
    private String message;

    public UserChat(User fromUser, User toUser, String message) {
        this.fromUser = fromUser;
        this.toUser = toUser;
        this.message = message;
    }

    public User getFromUser() {
        return fromUser;
    }

    public User getToUser() {
        return toUser;
    }

    public String getMessage() {
        return message;
    }


    public void printMessage() {
        System.out.println(fromUser.getName() + " -> " + toUser.getName() + ": " + message);
    }
}