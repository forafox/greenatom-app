package org.forafox.web.requestRecord;

import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record MessageUpdateRequest (Long id, String text, String author, Date created){
}
