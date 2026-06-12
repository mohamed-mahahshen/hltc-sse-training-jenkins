package onlinebookstore.service;

import onlinebookstore.model.Book;
import onlinebookstore.model.Customer;
import onlinebookstore.model.Order;
import onlinebookstore.notification.NotificationService;
import onlinebookstore.payment.PaymentGateway;
import onlinebookstore.repository.BookRepository;
import onlinebookstore.repository.FakeBookRepository;
import onlinebookstore.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrderServiceUnitTest {

    private static final Customer CUSTOMER = new Customer(101L, "cust1@gmail.com");

    @Mock
    private BookRepository bookRepositoryMock;

    @Mock
    private OrderRepository orderRepositoryMock;

    @Mock
    private PaymentGateway paymentGatewayMock;

    @Mock
    private NotificationService notificationServiceMock;

    @InjectMocks
    private OrderService classUnderTest;

    @Test
    void shouldReturnTrue_whenPlaceOrder_givenExistingBookIdAndCustomer() {
        //given
        ArgumentCaptor<Order> captor = ArgumentCaptor.forClass(Order.class);
        Long givenBookId = 1L;
        Book book = new Book(1L, "Clean Code", 500);
        when(bookRepositoryMock.findById(any())).thenReturn(Optional.of(book));
        when(paymentGatewayMock.processPayment(anyDouble())).thenReturn(true);

        //when
        boolean actualResult = classUnderTest.placeOrder(givenBookId, CUSTOMER);

        //then
        verify(bookRepositoryMock).findById(givenBookId);
        verify(paymentGatewayMock).processPayment(500);
        verify(orderRepositoryMock).save(captor.capture());
        verify(notificationServiceMock).sendEmail("cust1@gmail.com", "Order placed successfully");
        assertTrue(true);
    }

    @Test
    void shouldThrowBookNotFound_whenPlaceOrder_givenNonExistingBook() {
        //given
        Long givenBookId = 1L;

        //when
        RuntimeException actualException = assertThrows(RuntimeException.class, () -> classUnderTest.placeOrder(givenBookId, CUSTOMER));

        //then
        verify(bookRepositoryMock).findById(givenBookId);
        verify(paymentGatewayMock, never()).processPayment(anyDouble());
        verify(orderRepositoryMock, never()).save(any());
        verify(notificationServiceMock, never()).sendEmail(any(), any());
        assertEquals("Book not found", actualException.getMessage());
    }

    @Test
    void shouldReturnFalse_whenPlaceOrder_givenExistingBookIdAndPaymentStatusAsFalse() {
        //given
        ArgumentCaptor<Order> captor = ArgumentCaptor.forClass(Order.class);
        Long givenBookId = 1L;
        Book book = new Book(1L, "Clean Code", 500);
        when(bookRepositoryMock.findById(any())).thenReturn(Optional.of(book));

        //when
        boolean actualResult = classUnderTest.placeOrder(givenBookId, CUSTOMER);

        //then
        verify(bookRepositoryMock).findById(givenBookId);
        verify(paymentGatewayMock).processPayment(500);
        verify(orderRepositoryMock, never()).save(any());
        assertFalse(false);
    }

    @Test
    void shouldReturnTrue_whenPlaceOrder_givenExistingBookIdCustomerFakeBookRepo() {
        //given
        ArgumentCaptor<Order> captor = ArgumentCaptor.forClass(Order.class);
        Long givenBookId = 1L;
        Book book = new Book(1L, "Clean Code", 500);
        FakeBookRepository fakeRepo = new FakeBookRepository();
        fakeRepo.addBook(new Book(1L, "Java", 500));

        when(bookRepositoryMock.findById(any())).thenReturn(Optional.of(book));
        when(paymentGatewayMock.processPayment(anyDouble())).thenReturn(true);

        //when
        boolean actualResult = classUnderTest.placeOrder(givenBookId, CUSTOMER);

        //then
        verify(fakeRepo).findById(givenBookId);
        verify(paymentGatewayMock).processPayment(500);
        verify(orderRepositoryMock).save(captor.capture());
        verify(notificationServiceMock).sendEmail("cust1@gmail.com", "Order placed successfully");
        assertTrue(true);
    }

   /* @Test
    void testPlaceOrder_dummyObject() {
        //given
        Long givenBookId = 1L;
        Customer givenCustomer = new Customer(101L, "cust1@gmail.com");

        //when
        //then
        assertThrows(RuntimeException.class, () -> classUnderTest.placeOrder(givenBookId, givenCustomer));
    }

    @Test
    void testPlaceOrder_stub() {
        //given
        Long givenBookId = 1L;
        Customer givenCustomer = new Customer(101L, "cust1@gmail.com");
        when(bookRepositoryMock.findById(any())).thenReturn(Optional.of(new Book(1L, "Clean Code", 500)));

        //when
        boolean actualResult = classUnderTest.placeOrder(givenBookId, givenCustomer);

        //then
        verify(paymentGatewayMock).processPayment(500);
    }

    @Test
    void testPlaceOrder_Mock() {
        //given
        Long givenBookId = 1L;
        Customer givenCustomer = new Customer(101L, "cust1@gmail.com");
        when(bookRepositoryMock.findById(any())).thenReturn(Optional.of(new Book(1L, "Clean Code", 500)));
        when(paymentGatewayMock.processPayment(anyDouble())).thenReturn(true);

        //when
        boolean actualResult = classUnderTest.placeOrder(givenBookId, givenCustomer);

        //then
        verify(paymentGatewayMock).processPayment(500);
        verify(notificationServiceMock).sendEmail("cust1@gmail.com", "Order placed successfully");
    }*/


}
