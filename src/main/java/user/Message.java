package user;

public class Message {
    private User from;
    private User to;
    private String text;

    public Message(User from, User to, String text) {
        this.from = from;
        this.to = to;
        this.text = text;
    }

    public User getFrom() {
        return from;
    }

    public User getTo() {
        return to;
    }

    public String getText() {
        return text;
    }
}
