package com.jobportal.jobportal.model;
import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonProperty;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    
    private String phone;
    private String skills;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
private String password;
}

// public class Student {
    
// }
