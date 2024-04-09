package org.forafox.repository;

import org.forafox.domain.Topic;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface TopicRepository extends JpaRepository<Topic, Long> {
    Optional<Topic> findByTitle(String title);

    @Query("SELECT p FROM Topic p")
    Slice<Topic> findAllSlice(Pageable pageable);
}
