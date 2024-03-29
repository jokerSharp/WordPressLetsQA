package ui.model.api;

public class UserResp {
    private int id;
    private String username;
    private String name;
    private String first_name;
    private String last_name;
    private String email;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserResp(int id, String username, String name, String first_name, String last_name, String email) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
    }

    public UserResp() {
    }
}
