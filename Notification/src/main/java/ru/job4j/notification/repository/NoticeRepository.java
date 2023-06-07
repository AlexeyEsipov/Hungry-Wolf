package ru.job4j.notification.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.notification.model.Notice;

public interface NoticeRepository extends CrudRepository<Notice, Integer> {
}
