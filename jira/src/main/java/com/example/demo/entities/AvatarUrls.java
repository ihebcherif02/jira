package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AvatarUrls {

        @JsonProperty("48x48")
        private String fortyEightX48;
        @JsonProperty("24x24")
        private String twentyFourX24;
        @JsonProperty("16x16")
        private String sixteenX16;
        @JsonProperty("32x32")
        private String thirtyTwoX32;
}
