package org.forafox.web.responseRecord;

import java.util.Date;

public record MessageResponse(Long id, String author, String text, Date createdAt) {
}
