package com.fil.TicketBooking.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "location")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long locationId;
    @NotEmpty(message = "location name cannot be empty")
    private String locationName;
    @NotEmpty(message = "description cannot be empty")
    private String description;
    @ManyToOne
    @JoinColumn(name = "added_by", nullable = false)
    private User addedBy;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    @OneToMany(mappedBy = "location")
    private List<Event> events;
    @NotNull
    private String locationCity;

    // Getters and Setters

    @PrePersist
    public void prePersist() {
        createdAt = new Timestamp(System.currentTimeMillis());
        updatedAt = new Timestamp(System.currentTimeMillis());
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = new Timestamp(System.currentTimeMillis());
    }
}
