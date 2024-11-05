package com.fil.TicketBooking.model;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "event")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "placeId")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long placeId;
    @ManyToOne
    @JoinColumn(name = "location_id", nullable = false)
    private Location location;
    private String placeName;
    private String description;
    @ManyToOne
    @JoinColumn(name = "added_by", nullable = false)
    private User addedBy;
    @OneToMany(mappedBy = "place", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<TicketPricing> ticketPricings;
    @OneToMany(mappedBy = "place",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TicketBooking> ticketBookings;
    @NotEmpty(message = "open timings cannot be null")
    private String openTiming;
    @NotEmpty(message = "open days cannot be null")
    private String openDays;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    @NonNull
    private Long maxTicket;
    @NonNull
    private Long soldTicket;
    @NonNull
    @JoinColumn(name = "ticket_price")
    private Long ticketPrice;
    @NonNull
    @NotEmpty(message = "event from date cannot be null")
    private Date eventFromDate;
    @NonNull
    @NotEmpty(message = "event to date cannot be null")
    private Date eventToDate;

    @PrePersist
    protected void onCreate() {
        createdAt = new Timestamp(System.currentTimeMillis());
        updatedAt = new Timestamp(System.currentTimeMillis());
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = new Timestamp(System.currentTimeMillis());
    }
}
