package org.forafox.web.controller.admin;

import io.swagger.v3.oas.annotations.Operation;
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
import org.forafox.web.responseRecord.MessageResponse;
import org.forafox.web.responseRecord.ResponseService;
import org.forafox.web.responseRecord.TopicResponse;
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
    private final ResponseService responseService;


    @PutMapping
    public TopicResponse updateTopic(@Valid @RequestBody final TopicUpdateRequest topicRequest) {
        return responseService.dtoToResponse(topicMapper.toDto(topicService.updateTopicById(new TopicDTO(topicRequest.id(), topicRequest.title(), null))));
    }

    @DeleteMapping("/{topic_id}")
    public ResponseEntity<String> deleteTopic(@PathVariable @Min(0) Long topic_id) {
        topicService.deleteTopicById(topic_id);
        return new ResponseEntity<>("Successful operation", HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{topicId}/message")
    public MessageResponse updateMessageInTopic(@PathVariable @Min(0) Long topicId, @Valid @RequestBody final MessageUpdateRequest messageRequest) {
        var topic = topicService.getTopicByID(topicId);
        var messageDTO = new MessageDTO(messageRequest.id(), topic, messageRequest.author(), messageRequest.text(), messageRequest.created());
        return responseService.messageDtoToResponse(messageMapper.toDto(messageService.updateMessageById(messageDTO)));
    }


}
