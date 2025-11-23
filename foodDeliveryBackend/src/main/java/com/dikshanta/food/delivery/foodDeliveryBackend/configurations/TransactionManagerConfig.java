package com.dikshanta.food.delivery.foodDeliveryBackend.configurations;

import jakarta.persistence.EntityManagerFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.support.DefaultTransactionStatus;

@Configuration
@Slf4j
public class TransactionManagerConfig {
    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
        return new JpaTransactionManager(emf) {
            @Override
            protected void doBegin(Object transaction, TransactionDefinition definition) {
                String name = definition.getName() != null ? definition.getName() : "Unnamed";
                log.info("Transaction started: name='{}', propagation={}", name, definition.getPropagationBehavior());
                super.doBegin(transaction, definition);
            }

            @Override
            protected void doCommit(DefaultTransactionStatus status) {
                log.info("Transaction committed");
                super.doCommit(status);
            }

            @Override
            protected void doRollback(DefaultTransactionStatus status) {
                log.info("Transaction rolled back");
                super.doRollback(status);
            }
        };
    }
}
