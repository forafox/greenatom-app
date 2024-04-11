package org.forafox.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "DTO for topic")
public class TopicDTO {
    @Schema(description = "Unique identifier of the topic")
    private Long id;

    @Schema(description = "Title of the topic")
    private String title;
}