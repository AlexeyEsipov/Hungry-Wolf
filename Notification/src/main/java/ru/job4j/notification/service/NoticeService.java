package ru.job4j.notification.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.job4j.notification.model.Notice;
import ru.job4j.notification.repository.NoticeRepository;

@Service
@AllArgsConstructor
public class NoticeService {
    @Value("${messenger}")
    private static final String MESSENGER = "messengers";
    private static final Gson GSON = new GsonBuilder().create();
    private final NoticeRepository noticeRepository;

    @KafkaListener(topics = MESSENGER)
    public void saveNotice(String notice) {
        noticeRepository.save(GSON.fromJson(notice, Notice.class));
    }
}
