    package com.project.fitnova.model;

    import com.fasterxml.jackson.annotation.JsonIgnore;
    import jakarta.persistence.*;
    import lombok.*;
    import org.hibernate.annotations.JdbcTypeCode;
    import org.hibernate.type.SqlTypes;

    import java.time.LocalDateTime;
    import java.util.ArrayList;
    import java.util.List;
    import java.util.Map;
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    @Entity
    public class Activity {
        @Id
        @GeneratedValue(strategy = GenerationType.UUID)
        private String id;
        @Enumerated(EnumType.STRING)
        private ActivityType type;


        @JdbcTypeCode(SqlTypes.JSON)
        @Column(columnDefinition = "json")
        private Map<String, Object> additionalMetrics;
        private Integer duration;
        private Integer caloriesBurned;

        private LocalDateTime startedAt;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name="users_id",nullable = false ,foreignKey = @ForeignKey(name="fk_activity_user"))
        @JsonIgnore
        private User user;

        @OneToMany(mappedBy = "activity",cascade=CascadeType.ALL, orphanRemoval = true )
        private List<Recommendation> recommendations= new ArrayList<>();
    }
