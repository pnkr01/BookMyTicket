package com.fil.TicketBooking.model;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fil.TicketBooking.enums.BookingStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "ticket_booking")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "ticketId")
public class TicketBooking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ticketId;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @ManyToOne
    @JoinColumn(name = "place_id", nullable = false)
    private Event place;
    @NotNull
    private Date bookingDate;
    @NonNull
    private String ticketDetails;
    @NotNull
    private double totalPrice;
    @Enumerated(EnumType.STRING)
    @NotNull
    private BookingStatus status;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private int totalMember;

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
