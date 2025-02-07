package com.example.internportfoliotask2.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "education")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Education {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String institution;
    private String degree;
    private Double gpa;
    private LocalDate startDate;
    private LocalDate endDate;
    private String academicStatus;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
