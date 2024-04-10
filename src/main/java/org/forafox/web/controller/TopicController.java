package org.forafox.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.forafox.service.TopicService;
import org.forafox.service.impl.MessageServiceImpl;
import org.forafox.web.dto.*;
import org.forafox.web.mapper.MessageMapper;
import org.forafox.web.mapper.TopicMapper;
import org.forafox.web.requestRecord.MessageCreateRequest;
import org.forafox.web.requestRecord.MessageUpdateRequest;
import org.forafox.web.requestRecord.TopicCreateRequest;
import org.forafox.web.requestRecord.TopicUpdateRequest;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/topic")
@SecurityRequirement(name = "JWT")
@RequiredArgsConstructor
@Validated
@Tag(name = "Topics", description = "Endpoints for managing topics")
public class TopicController {
    private final TopicService topicService;
    private final TopicMapper topicMapper;
    private final MessageServiceImpl messageService;
    private final MessageMapper messageMapper;

    @PostMapping
    @Operation(summary = "Create topic",
            description = "Create new topic to store messages",
            operationId = "createTopic")
    public TopicDTO createTopic(@Valid @RequestBody final TopicCreateRequest topicRequest) {
        var message = new MessageDTO(null, null, topicRequest.message().author(), topicRequest.message().text(), null);
        return topicService.createTopicEntity(new TopicDTO(null, topicRequest.title()), message);
    }

    @PutMapping
    @Operation(summary = "Update topic",
            description = "Update existing topic by its ID",
            operationId = "updateTopic")
    public TopicDTO updateTopic(@Valid @RequestBody final TopicUpdateRequest topicRequest) {
        return topicMapper.toDto(topicService.updateTopicById(new TopicDTO(topicRequest.id(), topicRequest.title())));
    }

    @GetMapping("/{topic_id}")
    @Operation(summary = "Show all messages in topic",
            description = "Retrieves all messages in topic by its ID",
            operationId = "listTopicMessages")
    public List<MessageDTO> getMessageByID(
            @PathVariable
            @Min(value = 0, message = "Topic ID must be greater than or equal to 0")
            @Parameter(description = "ID of the topic to retrieve messages from", required = true) Long topic_id) {
        return messageMapper.toDtos(messageService.getAllMessagesByTopicId(topic_id));
    }

    @GetMapping("/{topic_id}/{page_offset}/{page_limit}")
    @Operation(summary = "Show messages page in topic",
            description = "Retrieves a page of messages within topic, given topic ID, page offset, and page limit",
            operationId = "pageListTopicMessages")
    public MessagePageDTO getPageListByID(
            @PathVariable @Min(0) Long topic_id,
            @PathVariable @Min(0) int page_offset,
            @PathVariable @Min(1) @Max(100) int page_limit) {
        return messageService.getMessagePageDTOByTopicId(topic_id, page_offset, page_limit);
    }

    @DeleteMapping("/{topic_id}")
    @Operation(summary = "Delete existing topic",
            description = "Delete existing topic by its ID",
            operationId = "deleteTopic")
    public ResponseEntity<String> deleteTopic(
            @PathVariable
            @Min(value = 0, message = "Topic ID must be greater than or equal to 0")
            @Parameter(description = "ID of the topic to delete", required = true) Long topic_id) {
        topicService.deleteTopicById(topic_id);
        return new ResponseEntity<>("Successful operation", HttpStatus.NO_CONTENT);
    }

    @GetMapping("")
    @Operation(summary = "View all topics",
            description = "Retrieves all topics",
            operationId = "listAllTopics")
    public List<TopicDTO> listAllTopics() {
        return topicMapper.toDtos(topicService.getAllTopics());
    }

    @GetMapping("/{page_offset}/{page_limit}")
    @Operation(summary = "Show topics page",
            description = "Retrieves a page of topics with given size and sequence number of topics",
            operationId = "pageListTopics")
    public TopicPageDTO pageListTopics(
            @PathVariable @Min(0) int page_offset,
            @PathVariable @Min(0) int page_limit) {
        return topicService.getAllPageTopicsDTO(page_offset, page_limit);
    }

    @PostMapping("/{topicId}/message")
    @Operation(
            summary = "Create message in topic",
            description = "Create new message in topic by topic ID",
            operationId = "createMessage")
    public MessageDTO createMessageInTopic(
            @PathVariable
            @Min(value = 0, message = "Topic ID must be greater than or equal to 0")
            @Parameter(description = "ID of the topic to create the message in", required = true) Long topicId,
            @Valid @RequestBody final MessageCreateRequest messageRequest) {
        var topic = topicService.getTopicByID(topicId);
        return messageService.createMessage(new MessageDTO(null, topic.getTitle(), messageRequest.author(), messageRequest.text(), null), topic);
    }

    @PutMapping("/{topicId}/message")
    @Operation(summary = "Update message in topic",
            description = "Update existing message by topic ID",
            operationId = "updateMessage")
    public MessageDTO updateMessageInTopic(
            @PathVariable
            @Min(value = 0, message = "Topic ID must be greater than or equal to 0")
            @Parameter(description = "ID of the topic to update the message in", required = true) Long topicId,
            @Valid @RequestBody final MessageUpdateRequest messageRequest) {
        var topic = topicService.getTopicByID(topicId);
        var messageDTO = new MessageDTO(messageRequest.id(), topic.getTitle(), messageRequest.author(), messageRequest.text(), messageRequest.created());
        return messageMapper.toDto(messageService.updateMessageById(messageDTO));
    }


}
