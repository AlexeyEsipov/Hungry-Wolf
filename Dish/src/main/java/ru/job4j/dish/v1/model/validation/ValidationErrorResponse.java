package ru.job4j.dish.v1.model.validation;

import java.util.List;

public record ValidationErrorResponse(List<Violation> violations) {
}
