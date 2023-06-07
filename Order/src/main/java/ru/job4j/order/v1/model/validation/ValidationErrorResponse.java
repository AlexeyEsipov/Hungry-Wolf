package ru.job4j.order.v1.model.validation;

import java.util.List;

public record ValidationErrorResponse(List<Violation> violations) {
}
