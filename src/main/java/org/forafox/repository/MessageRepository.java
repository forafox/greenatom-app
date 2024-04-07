package org.forafox.repository;

import org.forafox.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {

}
