package org.forafox.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "DTO for topic page", accessMode = Schema.AccessMode.READ_ONLY)
public class TopicPageDTO {
    @Schema(description = "List of topics in the current page")
    private List<TopicDTO> topicsList;

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