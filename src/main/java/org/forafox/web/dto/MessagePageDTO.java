package org.forafox.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.forafox.domain.Message;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "DTO for message page", accessMode = Schema.AccessMode.READ_ONLY)
public class MessagePageDTO {
    @Schema(description = "List of messages in the current page")
    private List<MessageDTO> messagesList;

    @Schema(description = "Indicates whether this page is the first page")
    private boolean isFirstPage;

    @Schema(description = "Indicates whether this page is the last page")
    private boolean isLastPage;

    @Schema(description = "Indicates whether the page is empty")
    private boolean isEmpty;

    @Schema(description = "Total number of pages")
    private int totalPages;

    @Schema(description = "Total number of elements across all pages")
    private long totalElements;

    @Schema(description = "Number of elements in the current page")
    private int pageSize;

    @Schema(description = "Current page number")
    private int pageNumber;
}