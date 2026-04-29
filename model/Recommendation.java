package com.project.fitnova.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "recommendation")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Recommendation {

    @Id
    @UuidGenerator
    private String id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "activity_id", nullable = false)
    private Activity activity;

    @Column(columnDefinition = "TEXT")
    private String recommendation;

    @ElementCollection
    @CollectionTable(
            name = "recommendation_improvements",
            joinColumns = @JoinColumn(name = "recommendation_id")
    )
    @Column(name = "improvement", columnDefinition = "TEXT")
    private List<String> improvements;

    @ElementCollection
    @CollectionTable(
            name = "recommendation_suggestions",
            joinColumns = @JoinColumn(name = "recommendation_id")
    )
    @Column(name = "suggestion", columnDefinition = "TEXT")
    private List<String> suggestions;

    @ElementCollection
    @CollectionTable(
            name = "recommendation_safety",
            joinColumns = @JoinColumn(name = "recommendation_id")
    )
    @Column(name = "safety_item", columnDefinition = "TEXT")
    private List<String> safety;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @PrePersist
    public void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}