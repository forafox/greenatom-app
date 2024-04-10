package org.forafox.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.forafox.service.TopicService;
import org.forafox.service.impl.MessageServiceImpl;
import org.forafox.web.dto.*;
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
    public TopicDTO createTopic(@Valid @RequestBody final TopicCreateRequest topicRequest) {
        var message = new MessageDTO(null, null, topicRequest.message().author(), topicRequest.message().text(), null);
        return topicService.createTopicEntity(new TopicDTO(null, topicRequest.title()), message);
    }

    @PutMapping
    @Operation(description = "Update an existing topic by Id", operationId = "updateTopic", tags = "Client API")
    public TopicDTO updateTopic(@Valid @RequestBody final TopicUpdateRequest topicRequest) {
        return topicMapper.toDto(topicService.updateTopicById(new TopicDTO(topicRequest.id(), topicRequest.title())));
    }

    @GetMapping("/{topic_id}")
    @Operation(description = "Shows all messages in topic", operationId = "ListTopicMessages", tags = "Client API")
    public List<MessageDTO> getMessageByID(@PathVariable @Min(0) Long topic_id) {
        return messageMapper.toDtos(messageService.getAllMessagesByTopicId(topic_id));
    }

    @GetMapping("/{topic_id}/{page_offset}/{page_limit}")
    @Operation(description = "Shows all messages in topic2", operationId = "ListTopicMessages2", tags = "Client API")
    public MessagePageDTO getPageListByID(@PathVariable @Min(0) Long topic_id,
                                          @PathVariable @Min(0) int page_offset,
                                          @PathVariable @Min(1) @Max(100) int page_limit) {
        return messageService.getMessagePageDTOByTopicId(topic_id, page_offset, page_limit);
    }

    @DeleteMapping("/{topic_id}")
    @Operation(description = "Delete an existing topic by Id", operationId = "deleteTopic", tags = "Client API")
    public ResponseEntity<String> deleteTopic(@PathVariable @Min(0) Long topic_id) {
        topicService.deleteTopicById(topic_id);
        return new ResponseEntity<>("Successful operation", HttpStatus.NO_CONTENT);
    }

    @GetMapping("")
    @Operation(description = "View all topics", operationId = "listAllTopics", tags = "Client API")
    public List<TopicDTO> listAllTopics() {
        return topicMapper.toDtos(topicService.getAllTopics());
    }

    @GetMapping("/{page_offset}/{page_limit}")
    @Operation(description = "View all topics2", operationId = "listAllTopics2", tags = "Client API2")
    public TopicPageDTO pageListTopics(@PathVariable @Min(0) int page_offset,
                                       @PathVariable @Min(0) int page_limit) {
        return topicService.getAllPageTopicsDTO(page_offset, page_limit);
    }

    @PostMapping("/{topicId}/message")
    @Operation(description = "Create a new message in topic", operationId = "CreateMessage", tags = "Client API")
    public MessageDTO createMessageInTopic(@PathVariable @Min(0) Long topicId, @Valid @RequestBody final MessageCreateRequest messageRequest) {
        var topic = topicService.getTopicByID(topicId);
        return messageService.createMessage(new MessageDTO(null, topic.getTitle(), messageRequest.author(), messageRequest.text(), null),topic);
    }

    @PutMapping("/{topicId}/message")
    @Operation(description = "Update an existing message by Id", operationId = "updateMessage", tags = "Client API")
    public MessageDTO updateMessageInTopic(@PathVariable @Min(0) Long topicId, @Valid @RequestBody final MessageUpdateRequest messageRequest) {
        var topic = topicService.getTopicByID(topicId);
        var messageDTO = new MessageDTO(messageRequest.id(), topic.getTitle(), messageRequest.author(), messageRequest.text(), messageRequest.created());
        return messageMapper.toDto(messageService.updateMessageById(messageDTO));
    }



}
