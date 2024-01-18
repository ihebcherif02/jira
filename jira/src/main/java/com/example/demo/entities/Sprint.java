package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Sprint {

    private String id;
    private String self;
    private String state;
    private String name;
    private Date startDate;
    private Date endDate;
    private Date completeDate;
    private String originBoardId;
    private String goal;

}
