package com.example.Bajaj.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@JsonPropertyOrder({
    "is_success", "user_id", "email", "roll_number",
    "odd_numbers", "even_numbers", "alphabets",
    "special_characters", "sum", "concat_string"
})
public class BfhlResponse {

    @JsonProperty("is_success")
    private Boolean isSuccess;

    @JsonProperty("user_id")
    private String userId;

    private String email;

    @JsonProperty("roll_number")
    private String rollNumber;

    @JsonProperty("odd_numbers")
    private List<String> oddNumbers;

    @JsonProperty("even_numbers")
    private List<String> evenNumbers;

    private List<String> alphabets;

    @JsonProperty("special_characters")
    private List<String> specialCharacters;

    private String sum;

    @JsonProperty("concat_string")
    private String concatString;
}

