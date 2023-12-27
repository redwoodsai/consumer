package com.redwoods.consumer.consumerservice.repos;

import com.redwoods.consumer.consumerservice.models.Consumer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsumerRepository extends JpaRepository<Consumer, Long> {

}
