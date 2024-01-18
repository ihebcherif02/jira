package com.example.demo.entities;

import com.example.demo.entities.AvatarUrls;
import com.example.demo.entities.Lead;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Project {

    @JsonProperty("avatarUrls")
    private AvatarUrls avatarUrls;
    private String description;
    private String entityId;
    @JsonProperty("isPrivate")
    private boolean isPrivate;
    private String uuid;
    private Lead lead;
    private String expand;
    private boolean simplified;
    private String name;
    private String self;
    private String style;
    private Long id;
    private String projectTypeKey;
    private String key;
    private Object properties;
}
