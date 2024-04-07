package org.forafox.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.forafox.service.TopicService;
import org.forafox.service.impl.MessageServiceImpl;
import org.forafox.web.dto.MessageDTO;
import org.forafox.web.dto.TopicDTO;
import org.forafox.web.dto.TopicInListDTO;
import org.forafox.web.dto.UserDto;
import org.forafox.web.dto.auth.JwtRequest;
import org.forafox.web.dto.auth.JwtResponse;
import org.forafox.web.mapper.MessageMapper;
import org.forafox.web.mapper.TopicMapper;
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
        return dtoToResponse(topicService.createTopicEntity(new TopicDTO(null, topicRequest.title, null)));
    }

    @PutMapping
    @Operation(description = "Update an existing topic by Id", operationId = "updateTopic", tags = "Client API")
    public TopicResponse updateTopic(@RequestBody final TopicWithDataRequest topicRequest) {
        return dtoToResponse(topicMapper.toDto(topicService.updateTopicById(new TopicDTO(topicRequest.id, topicRequest.title, null))));
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


    record TopicResponse(@NotNull Long id, @NotNull String title) {
    }

    record TopicResponseWithMessages(@NotNull Long id, @NotNull String title, List<MessageDTO> messageDTOList) {
    }

    record TopicListResponse(List<TopicInListDTO> listAllTopics) {
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

    record TopicCreateRequest(@NotNull String title) {

    }

    record TopicWithDataRequest(@NotNull Long id, String title) {

    }

}
