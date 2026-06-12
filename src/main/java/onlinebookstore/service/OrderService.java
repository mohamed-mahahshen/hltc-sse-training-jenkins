package onlinebookstore.service;

import onlinebookstore.model.Book;
import onlinebookstore.model.Customer;
import onlinebookstore.model.Order;
import onlinebookstore.notification.NotificationService;
import onlinebookstore.payment.PaymentGateway;
import onlinebookstore.repository.BookRepository;
import onlinebookstore.repository.OrderRepository;

public class OrderService {

    private BookRepository bookRepository;
    private OrderRepository orderRepository;
    private PaymentGateway paymentGateway;
    private NotificationService notificationService;

    public OrderService(BookRepository bookRepository, OrderRepository orderRepository, PaymentGateway paymentGateway, NotificationService notificationService) {
        this.bookRepository = bookRepository;
        this.orderRepository = orderRepository;
        this.paymentGateway = paymentGateway;
        this.notificationService = notificationService;
    }

    public boolean placeOrder(Long bookId, Customer customer) {
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new RuntimeException("Book not found"));

        boolean paymentStatus = paymentGateway.processPayment(book.getPrice());
        if (!paymentStatus) {
            return false;
        }

        Order order = new Order(customer, book, book.getPrice());
        orderRepository.save(order);

        notificationService.sendEmail(customer.getEmail(), "Order placed successfully");

        return true;
    }
}

