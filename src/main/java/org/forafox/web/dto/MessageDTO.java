package org.forafox.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.forafox.domain.Topic;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "DTO for message")
public class MessageDTO {
    @Schema(description = "Unique identifier of the message", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Schema(description = "Title of the topic associated with the message")
    private String topicTitle;

    @Schema(description = "Author of the message")
    private String author;

    @Schema(description = "Text content of the message")
    private String text;

    @Schema(description = "Date and time when the message was created", accessMode = Schema.AccessMode.READ_ONLY)
    private Date createdAt;
}