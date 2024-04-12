package repositories;

import org.forafox.domain.Message;
import org.forafox.domain.Role;
import org.forafox.domain.Topic;
import org.forafox.domain.User;
import org.forafox.repository.MessageRepository;
import org.forafox.repository.TopicRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class TopicRepositoryTest {
    @Mock
    private TopicRepository topicRepository;

    @BeforeEach
    public void setUp() {
        Set<Role> roles = new HashSet<>();
        roles.add(Role.USER);
        User user = new User(1L, "Andrey", "Andrey2004", "12345", roles);
        User user2 = new User(2L, "Dima", "Dima2003", "12345", roles);
        Topic topic = new Topic(1L, "Title", user);
        Topic topic2 = new Topic(2L, "Null title", user);
        List<Topic> topicsOneSize = new ArrayList<>();
        List<Topic> topicsTwoSize = new ArrayList<>();
        // Define mock behavior for TopicRepository_TestFindByTitle_returnExistingTopic
        when(topicRepository.findByTitle("Null title")).thenReturn(Optional.of(topic));

        // Define mock behavior for TopicRepository_testFindAllPageBy_ReturnOneSizePage
        topicsOneSize.add(topic);
        Pageable pageable = PageRequest.of(0, 1); // First page with 2 items
        Page<Topic> topicPage = new PageImpl<>(topicsOneSize, pageable, topicsOneSize.size());
        when(topicRepository.findAllPage(pageable)).thenReturn(topicPage);

        // Define mock behavior for TopicRepository_testFindAllPage_ReturnTwoSizePage
        topicsTwoSize.add(topic);
        topicsTwoSize.add(topic2);
        Pageable pageable2 = PageRequest.of(0, 2); // First page with 1 item
        Page<Topic> topicPageTwoSize = new PageImpl<>(topicsTwoSize, pageable2, topicsTwoSize.size());
        when(topicRepository.findAllPage(pageable2)).thenReturn(topicPageTwoSize);
    }


    @Test
    public void TopicRepository_TestFindByTitle_returnExistingTopic() {
        Optional<Topic> userOptional = topicRepository.findByTitle("Null title");
        assertThat(userOptional).isPresent();
        assertThat(userOptional.get().getId()).isEqualTo(1L);
    }

    @Test
    public void TopicRepository_TestFindByTitle_returnNotExistingTopic() {
        Optional<Topic> userOptional = topicRepository.findByTitle("title tile");
        assertThat(userOptional).isEmpty();
    }

    @Test
    public void TopicRepository_testFindAllPageBy_ReturnOneSizePage() {
        Pageable pageable = PageRequest.of(0, 1);
        Page<Topic> topicPage = topicRepository.findAllPage(pageable);
        assertThat(topicPage.getContent()).hasSize(1);
        assertThat(topicPage.getTotalElements()).isEqualTo(1);
    }

    @Test
    public void TopicRepository_testFindAllPage_ReturnTwoSizePage() {
        Pageable pageable = PageRequest.of(0, 2);
        Page<Topic> topicPage = topicRepository.findAllPage(pageable);
        assertThat(topicPage.getContent()).hasSize(2);
        assertThat(topicPage.getTotalElements()).isEqualTo(2);
    }
}
