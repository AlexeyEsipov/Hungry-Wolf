package ru.job4j.kitchen.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.job4j.kitchen.dto.OrderDto;
import ru.job4j.kitchen.dto.NoticeOrder;
import ru.job4j.kitchen.mapper.KitchenOrderToDto;
import ru.job4j.kitchen.mapper.OrderToNotice;
import ru.job4j.kitchen.model.KitchenOrder;
import ru.job4j.kitchen.model.OrderStatus;
import ru.job4j.kitchen.repository.KitchenOrderRepository;

import javax.transaction.Transactional;

@Slf4j
@Service
@Transactional
@AllArgsConstructor
public class KitchenOrderService {
    @Value("${order-to-kitchen}")
    private static final String TO_KITCHEN = "cooked_order";

    @Value("${kitchen-to-order}")
    private static final String TO_ORDER = "notice_kitchen";
    private static final Gson GSON = new GsonBuilder().create();
    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final OrderToNotice orderToNotice;
    private final KitchenOrderRepository repository;
    private final KitchenOrderToDto kitchenOrderMapper;

    public OrderDto save(OrderDto orderDto) {
        KitchenOrder entity = kitchenOrderMapper.toEntity(orderDto);
        return kitchenOrderMapper.toDto(repository.save(entity));
    }

    public void deleteById(int id) {
        repository.deleteById(id);
    }

    public OrderDto findById(int id) {
        return kitchenOrderMapper.toDto(repository.findById(id).orElseThrow());
    }



    public OrderDto update(OrderStatus status, int id) {
        OrderDto data = findById(id);
        KitchenOrder entity = kitchenOrderMapper.toEntity(data);
        entity.setOrderStatus(status);
        return kitchenOrderMapper.toDto(repository.save(entity));
    }

    @KafkaListener(topics = TO_KITCHEN)
    public void receiver(String order) {
        OrderDto dto = GSON.fromJson(order, OrderDto.class);
        process(dto);
    }

    private void process(OrderDto dto) {
        OrderDto orderDto = save(dto);
        OrderStatus status = cooking(orderDto);
        update(status, orderDto.getId());
        NoticeOrder notice = orderToNotice.toNotice(dto, status);
        String sendNotice = GSON.toJson(notice);
        sender(TO_ORDER, sendNotice);
    }
    /* В методе cooking имитация процесса приготовления с различным результатом*/
    private OrderStatus cooking(OrderDto dto) {
        boolean process = (dto.getId() + dto.getOrderId() + dto.getDishes().size()) % 2 == 1;
        return process ? OrderStatus.READY : OrderStatus.CANCELED;
    }

    public void sender(String topic, String message) {
        kafkaTemplate.send(topic, message);
    }
}