package com.pragma.bootcamp.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Capability {
    private String id;
    private String name;
    private List<Technology> technologies;
}