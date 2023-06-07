package ru.job4j.order.v1.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.order.v1.model.entity.Card;
import ru.job4j.order.v1.model.entity.Customer;
import ru.job4j.order.v1.model.status.CardName;
import ru.job4j.order.v1.repository.CardRepository;
import java.util.List;
@Service
@AllArgsConstructor
public class CardService {
    private final CardRepository repository;

    public Card create(Customer customer) {
        Card card = new Card();
        card.setCustomerId(customer);
        card.setName(CardName.REGULAR);
        return repository.save(card);
    }

    public List<Card> findAll() {
        return (List<Card>) repository.findAll();
    }

    public List<Card> findAllByCustomer(Customer customer) {
        return repository.findByCustomerIdId(customer.getId());
    }

    public void delete(Card card) {
        repository.delete(card);
    }

    public void deleteByCustomer(Customer customer) {
        repository.deleteByCustomerId(customer);
    }

    public void deleteAll() {
        repository.deleteAll();
    }
}
