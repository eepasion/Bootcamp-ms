package com.pragma.bootcamp.domain.model;

import java.util.List;

public record Bootcamp(String id, String name, String description, List<String> capabilities) {
}
