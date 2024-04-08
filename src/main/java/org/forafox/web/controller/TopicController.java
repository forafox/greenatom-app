package org.forafox.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
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
import org.forafox.web.responseRecord.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public TopicResponse createTopic(@Valid @RequestBody final TopicCreateRequest topicRequest) {
        var message = new MessageDTO(null, null, topicRequest.message().author(), topicRequest.message().text(), null);
        return dtoToResponse(topicService.createTopicEntity(new TopicDTO(null, topicRequest.title(), null), message));
    }

    @PutMapping
    @Operation(description = "Update an existing topic by Id", operationId = "updateTopic", tags = "Client API")
    public TopicResponse updateTopic(@Valid @RequestBody final TopicUpdateRequest topicRequest) {
        return dtoToResponse(topicMapper.toDto(topicService.updateTopicById(new TopicDTO(topicRequest.id(), topicRequest.title(), null))));
    }

    @GetMapping("/{topic_id}")
    @Operation(description = "Shows all messages in topic", operationId = "ListTopicMessages", tags = "Client API")
    public TopicWithMessagesResponse getTopicByID(@PathVariable @Min(0) Long topic_id) {
        var topics = topicService.getTopicByID(topic_id);
        var messagesDTO = messageMapper.toDtos(messageService.getAllMessagesByTopicId(topic_id));
        return dtoToResponseWithMessages(topicMapper.toDtoWithMessages(topics, messagesDTO));
    }

    @DeleteMapping("/{topic_id}")
    @Operation(description = "Delete an existing topic by Id", operationId = "deleteTopic", tags = "Client API")
    public ResponseEntity<String> deleteTopic(@PathVariable  @Min(0) Long topic_id) {
        topicService.deleteTopicById(topic_id);
        return new ResponseEntity<>("Successful operation", HttpStatus.NO_CONTENT);
    }

    @GetMapping
    @Operation(description = "View all topics", operationId = "listAllTopics", tags = "Client API")
    public TopicListResponse listAllTopics() {
        return dtoListToResponse(topicMapper.toDtos(topicService.getAllTopics()));
    }

    @PostMapping("/{topicId}/message")
    @Operation(description = "Create a new message in topic", operationId = "CreateMessage", tags = "Client API")
    public MessageResponse createMessageInTopic(@PathVariable @Min(0) Long topicId,@Valid @RequestBody final MessageCreateRequest messageRequest) {
        var topic = topicService.getTopicByID(topicId);
        return messageDtoToResponse(messageService.createMessage(new MessageDTO(null, topic, messageRequest.author(), messageRequest.text(), null)));
    }

    @PutMapping("/{topicId}/message")
    @Operation(description = "Update an existing message by Id", operationId = "updateMessage", tags = "Client API")
    public MessageResponse updateMessageInTopic(@PathVariable @Min(0) Long topicId, @Valid @RequestBody final MessageUpdateRequest messageRequest) {
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

    private TopicWithMessagesResponse dtoToResponseWithMessages(TopicDTO dto) {
        return new TopicWithMessagesResponse(dto.getId(), dto.getTitle(), messageDtoToMessageInTopicResponse(dto.getMessages()));
    }

    private TopicResponse dtoToResponse(TopicDTO dto) {
        return new TopicResponse(dto.getId(), dto.getTitle());
    }

    private MessageResponse messageDtoToResponse(MessageDTO messageDTO) {
        return new MessageResponse(messageDTO.getId(), messageDTO.getAuthor(), messageDTO.getText(), messageDTO.getCreatedAt());
    }

    private List<MessageResponse> messageDtoToMessageInTopicResponse(List<MessageDTO> messageDTO) {
        return messageDTO.stream().
                map(dto -> new MessageResponse(dto.getId(), dto.getAuthor(), dto.getText(), dto.getCreatedAt()))
                .collect(Collectors.toList());
    }

}
