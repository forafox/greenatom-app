package org.forafox.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.forafox.service.TopicService;
import org.forafox.service.impl.MessageServiceImpl;
import org.forafox.web.dto.MessageDTO;
import org.forafox.web.dto.TopicDTO;
import org.forafox.web.dto.TopicInListDTO;
import org.forafox.web.mapper.MessageMapper;
import org.forafox.web.mapper.TopicMapper;
import org.forafox.web.requestRecord.MessageCreateRequest;
import org.forafox.web.requestRecord.MessageUpdateRequest;
import org.forafox.web.requestRecord.TopicCreateRequest;
import org.forafox.web.requestRecord.TopicUpdateRequest;
import org.forafox.web.responseRecord.MessageResponse;
import org.forafox.web.responseRecord.TopicListResponse;
import org.forafox.web.responseRecord.TopicResponse;
import org.forafox.web.responseRecord.TopicResponseWithMessages;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/topic")
@RequiredArgsConstructor
@Validated
@Tag(name = "Topic controller", description = "Topic API")
public class TopicController {
    private final TopicService topicService;
    private final TopicMapper topicMapper;
    private final MessageServiceImpl messageService;
    private final MessageMapper messageMapper;

    @PostMapping
    @Operation(description = "Create a new topic to the store", operationId = "CreateTopic", tags = "Client API")
    public TopicResponse createTopic(@Validated @RequestBody final TopicCreateRequest topicRequest) {
        return dtoToResponse(topicService.createTopicEntity(new TopicDTO(null, topicRequest.title(), null)));
    }

    @PutMapping
    @Operation(description = "Update an existing topic by Id", operationId = "updateTopic", tags = "Client API")
    public TopicResponse updateTopic(@RequestBody final TopicUpdateRequest topicRequest) {
        return dtoToResponse(topicMapper.toDto(topicService.updateTopicById(new TopicDTO(topicRequest.id(), topicRequest.title(), null))));
    }

    @GetMapping("/{topic_id}")
    @Operation(description = "Shows all messages in topic", operationId = "ListTopicMessages", tags = "Client API")
    public TopicResponseWithMessages getTopicByID(@PathVariable Long topic_id) {
        var topics = topicService.getTopicByID(topic_id);
        var messagesDTO = messageMapper.toDtos(messageService.getAllMessagesByTopicId(topic_id));
        return dtoToResponseWithMessages(topicMapper.toDtoWithMessages(topics, messagesDTO));
    }

    @GetMapping
    @Operation(description = "View all topics", operationId = "listAllTopics", tags = "Client API")
    public TopicListResponse listAllTopics() {
        return dtoListToResponse(topicMapper.toDtos(topicService.getAllTopics()));
    }

    @PostMapping("/{topicId}/message")
    @Operation(description = "Create a new message in topic", operationId = "CreateMessage", tags = "Client API")
    public MessageResponse createMessageInTopic(@PathVariable Long topicId, @Validated @RequestBody final MessageCreateRequest messageRequest) {
        var topic = topicService.getTopicByID(topicId);
        return messageDtoToResponse(messageService.createMessage(new MessageDTO(null, topic, messageRequest.author(), messageRequest.text(), null)));
    }

    @PutMapping("/{topicId}/message")
    @Operation(description = "Update an existing message by Id", operationId = "updateMessage", tags = "Client API")
    public MessageResponse updateMessageInTopic(@PathVariable Long topicId, @RequestBody final MessageUpdateRequest messageRequest) {
        var topic = topicService.getTopicByID(topicId);
        var messageDTO = new MessageDTO(messageRequest.id(), topic, messageRequest.author(), messageRequest.text(), messageRequest.created());
        return messageDtoToResponse(messageMapper.toDto(messageService.updateMessageById(messageDTO)));
    }

    private TopicListResponse dtoListToResponse(List<TopicDTO> topicDTO) {
        List<TopicInListDTO> listAllTopics = topicDTO.stream()
                .map(dto -> new TopicInListDTO(dto.getId(), dto.getTitle()))
                .collect(Collectors.toList());

        return new TopicListResponse(listAllTopics);
    }

    private TopicResponseWithMessages dtoToResponseWithMessages(TopicDTO dto) {
        return new TopicResponseWithMessages(dto.getId(), dto.getTitle(), dto.getMessages());
    }

    private TopicResponse dtoToResponse(TopicDTO dto) {
        return new TopicResponse(dto.getId(), dto.getTitle());
    }

    private MessageResponse messageDtoToResponse(MessageDTO messageDTO) {
        return new MessageResponse(messageDTO.getId(), messageDTO.getText(), messageDTO.getAuthor(), messageDTO.getCreatedAt());
    }

}
