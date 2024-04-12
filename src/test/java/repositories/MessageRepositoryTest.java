package repositories;

import org.forafox.domain.Message;
import org.forafox.domain.Role;
import org.forafox.domain.Topic;
import org.forafox.domain.User;
import org.forafox.repository.MessageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class MessageRepositoryTest {

    @Mock
    private MessageRepository messageRepository;

    @BeforeEach
    public void setUp() {
        // Define mock behavior for findByTopicId
        List<Message> messages = createSampleMessages();
        List<Message> messagesWithOneItem = new ArrayList<>();
        messagesWithOneItem.add(messages.get(0));

        when(messageRepository.findByTopicId(1L)).thenReturn(Optional.of(messages));

        // Define mock behavior for findAllPageByTopicId
        Pageable pageable = PageRequest.of(0, 2); // First page with 2 items
        Page<Message> messagePage = new PageImpl<>(messages, pageable, messages.size());
        when(messageRepository.findAllPageByTopicId(1L, pageable)).thenReturn(messagePage);

        Pageable pageable2 = PageRequest.of(0, 1); // First page with 1 item
        Page<Message> messagePageWithOnePageSize = new PageImpl<>(messagesWithOneItem, pageable2, messagesWithOneItem.size());
        when(messageRepository.findAllPageByTopicId(1L, pageable2)).thenReturn(messagePageWithOnePageSize);
    }

    private List<Message> createSampleMessages() {
        List<Message> messages = new ArrayList<>();
        Set<Role> roles = new HashSet<>();
        roles.add(Role.USER);
        User user = new User(1L, "Andrey", "Andrey2004", "12345", roles);
        User user2 = new User(2L, "Dima", "Dima2003", "12345", roles);
        Topic topic = new Topic(1L,"Title",user);
        messages.add(new Message(1L, topic, user, user.getName(), "text1", new Date()));
        messages.add(new Message(2L, topic, user2, user2.getName(), "text2", new Date()));
        return messages;
    }

    @Test
    public void MessageRepository_TestFindByTopicId_ReturnTwoSizeList() {
        Optional<List<Message>> messages = messageRepository.findByTopicId(1L);
        assertThat(messages).isPresent();
        assertThat(messages.get()).hasSize(2);
    }

    @Test
    public void MessageRepository_testFindAllPageByTopicId_ReturnOneSizePage() {
        Pageable pageable = PageRequest.of(0, 1);
        Page<Message> messagePage = messageRepository.findAllPageByTopicId(1L, pageable);
        assertThat(messagePage.getContent()).hasSize(1);
        assertThat(messagePage.getTotalElements()).isEqualTo(1);
    }

    @Test
    public void MessageRepository_testFindAllPageByTopicId_ReturnTwoSizePage() {
        Pageable pageable = PageRequest.of(0, 2);
        Page<Message> messagePage = messageRepository.findAllPageByTopicId(1L, pageable);
        assertThat(messagePage.getContent()).hasSize(2);
        assertThat(messagePage.getTotalElements()).isEqualTo(2);
    }
}
