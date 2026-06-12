package onlinebookstore.repository;

import onlinebookstore.model.Order;

public interface OrderRepository {

    void save(Order order);
}
