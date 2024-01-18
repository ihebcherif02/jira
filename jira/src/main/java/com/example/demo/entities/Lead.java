package com.example.demo.entities;

import com.example.demo.entities.AvatarUrls;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Lead {

    private String accountId;
    private String expand;
    private AvatarUrls avatarUrls;
    private String displayName;
    private String accountType;
    private String self;
    private boolean active;

}
