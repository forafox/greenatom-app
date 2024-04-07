package org.forafox.web.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.forafox.service.TopicService;
import org.forafox.web.dto.MessageDTO;
import org.forafox.web.dto.TopicDTO;
import org.forafox.web.dto.UserDto;
import org.forafox.web.dto.auth.JwtRequest;
import org.forafox.web.dto.auth.JwtResponse;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/data/v1")
@RequiredArgsConstructor
@Validated
@Tag(name = "Topic controller", description = "Topic API")
public class TopicController {
    private final TopicService topicService;

    @PostMapping("/topic")
    public TopicResponse login(@Validated @RequestBody final TopicRequest topicRequest) {
        return dtoToResponse(topicService.createTopicEntity(new TopicDTO(null, topicRequest.title, null)));
    }

    record TopicResponse(@NotNull Long id, @NotNull String title, List<MessageDTO> messageDTOList) {
    }

    private TopicResponse dtoToResponse(TopicDTO dto) {
        return new TopicResponse(dto.getId(), dto.getTitle(), dto.getMessages());
    }

    record TopicRequest(@NotNull String title) {

    }

}
