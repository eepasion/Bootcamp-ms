package com.pragma.bootcamp.domain.model;

import java.util.List;

public record BootcampWithCapabilities(String id, String name, String description, List<Capability> capabilities) {
}
