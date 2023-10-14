package me.lenglet;

import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class ApplicationReadyEventListener {

    @Autowired
    private EntityManager entityManager;

    @Transactional
    @EventListener(ApplicationReadyEvent.class)
    public void doSomethingAfterStartup() {
        final var clients = this.entityManager.createQuery("""
                        select c from Client c
                        """, Client.class)
                .getResultList();
    }

}
