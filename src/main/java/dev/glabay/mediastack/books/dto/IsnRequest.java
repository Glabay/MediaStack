package dev.glabay.mediastack.books.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

/**
 * @author Glabay | Glabay-Studios
 * @project MediaStack
 * @social Discord: Glabay
 * @since 2026-03-05
 */
public record IsnRequest(
    @NotBlank(message = "ISBN cannot be blank")
    @Pattern(regexp = "\\d{10,13}", message = "ISBN must be 10 or 13 digits")
    String isbn
) {}
