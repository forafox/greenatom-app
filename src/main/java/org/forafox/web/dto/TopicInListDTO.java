package org.forafox.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "DTO for topic in list")
public class TopicInListDTO {
    @Schema(description = "Unique identifier of the topic")
    private Long id;

    @Schema(description = "Title of the topic")
    private String title;
}
