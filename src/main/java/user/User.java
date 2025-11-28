package user;

public class User {
    private String login;
    private String password;
    private String name;
    private String id;

    public User(String login, String password, String id, String name){
        this.login = login;
        this.password = password;
        this.name = name;
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public String getUsername() {
        return login; // используется в Channel
    }

    public String getPassword() {
        return password;
    }

    public String getName(){
        return name;
    }

    public String getId(){
        return id;
    }

    @Override
    public String toString() {
        return "логин: " + login + ", пароль: " + password + ", id: " + id + ", имя: " + name;
    }
}

