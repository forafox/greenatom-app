package org.forafox.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TopicDTO {
    private Long id;
    private String title;
    private List<MessageDTO> messages;
}
