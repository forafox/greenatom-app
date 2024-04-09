package org.forafox.repository;

import org.forafox.domain.Message;
import org.forafox.domain.Topic;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MessageRepository extends JpaRepository<Message, Long> {
    Optional<List<Message>> findByTopicId(Long topic_id);

    @Query("SELECT p FROM Message p " +
            "WHERE p.topic.id = :topic_id")
    Slice<Message> findAllSliceByTopicId(@Param("topic_id") Long topic_id, Pageable pageable);
}
