package onlinebookstore.model;

public class Book {

    private Long id;
    private String title;
    private double price;

    public Book(Long id, String title, double price) {
        this.id = id;
        this.title = title;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public Long getId() {
        return id;
    }
}

