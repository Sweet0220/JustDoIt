package com.dormsrl.justdoit.dto;

import com.dormsrl.justdoit.entity.enums.ListCategory;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.dormsrl.justdoit.entity.List}
 */
@Value
public class ListDto implements Serializable {
    Long id;
    String name;
    ListCategory category;
    int priority;
}