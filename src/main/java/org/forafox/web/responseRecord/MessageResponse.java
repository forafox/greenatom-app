package org.forafox.web.responseRecord;

import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record MessageResponse(Long id, String text, String author, Date created) {
}
