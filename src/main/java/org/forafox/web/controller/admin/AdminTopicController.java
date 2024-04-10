package org.forafox.web.controller.admin;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.forafox.service.TopicService;
import org.forafox.service.impl.MessageServiceImpl;
import org.forafox.web.dto.MessageDTO;
import org.forafox.web.dto.TopicDTO;
import org.forafox.web.mapper.MessageMapper;
import org.forafox.web.mapper.TopicMapper;
import org.forafox.web.requestRecord.MessageUpdateRequest;
import org.forafox.web.requestRecord.TopicUpdateRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/topic")
@RequiredArgsConstructor
@Validated
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminTopicController {
    private final TopicService topicService;
    private final TopicMapper topicMapper;
    private final MessageServiceImpl messageService;
    private final MessageMapper messageMapper;


    @PutMapping
    public TopicDTO updateTopic(@Valid @RequestBody final TopicUpdateRequest topicRequest) {
        return topicMapper.toDto(topicService.updateTopicById(new TopicDTO(topicRequest.id(), topicRequest.title())));
    }

    @DeleteMapping("/{topic_id}")
    public ResponseEntity<String> deleteTopic(@PathVariable @Min(0) Long topic_id) {
        topicService.deleteTopicById(topic_id);
        return new ResponseEntity<>("Successful operation", HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{topicId}/message")
    public MessageDTO updateMessageInTopic(@PathVariable @Min(0) Long topicId, @Valid @RequestBody final MessageUpdateRequest messageRequest) {
        var topic = topicService.getTopicByID(topicId);
        var messageDTO = new MessageDTO(messageRequest.id(), topic.getTitle(), messageRequest.author(), messageRequest.text(), messageRequest.created());
        return messageMapper.toDto(messageService.updateMessageById(messageDTO));
    }


}
