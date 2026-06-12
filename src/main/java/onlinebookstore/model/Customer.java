package onlinebookstore.model;

public class Customer {

    private Long id;
    private String email;

    public Customer(Long id, String email) {
        this.id = id;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}
