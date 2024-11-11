package com.example.Memorizar.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Reviewhistory")
public class ReviewHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_history_id")
    private Long id;

    @Column(name = "hard_date")
    private Date hard;

    @Column(name = "medium_date")
    private Date medium;

    @Column(name = "easy_date")
    private Date easy;

    @Column(name = "next_review_date")
    private Date nextReviewDate;

    @ManyToOne
    @JoinColumn(name = "id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "flashcard_id", nullable = false)
    private Flashcard flashcard;

    @Column(name = "current_interval")
    private int interval;  // Current interval for the next review


}
