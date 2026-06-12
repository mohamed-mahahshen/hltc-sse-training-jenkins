package onlinebookstore.model;

public class Order {

    private Customer customer;
    private Book book;
    private double amount;

    public Order(Customer customer,
                 Book book,
                 double amount) {

        this.customer = customer;
        this.book = book;
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }
}

