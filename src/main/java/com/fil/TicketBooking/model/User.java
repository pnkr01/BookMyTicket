package com.fil.TicketBooking.model;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fil.TicketBooking.enums.UserRole;
import com.fil.TicketBooking.enums.UserStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "user")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "userId")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    @NotEmpty(message = "Name cannot be empty")
    private String name;
    @Email(message = "Invalid email format")
    @NotEmpty(message = "Email is required")
    private String email;
    @NotEmpty(message = "Phone number is required")
    @Size(min = 10,max = 10, message = "Invalid phone length")
    private String phone;
    @NotNull(message = "Password cannot be null")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    private String password;
    @NotNull(message= "Role cannot be null")
    @Enumerated(EnumType.STRING)
    private UserRole role;
    @Enumerated(EnumType.STRING)
    private UserStatus status;
    @NotNull(message= "Address cannot be null")
    private String address;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private String profilePicture;

    @PrePersist
    public void prePersist() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        createdAt = timestamp;
         updatedAt = timestamp;
    }

    @PreUpdate
    public void preUpdate(){
        updatedAt = new Timestamp(System.currentTimeMillis());
    }

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<TicketBooking> ticketBookings;

    @OneToMany(mappedBy = "addedBy")
    private List<Event> events;

    @OneToMany(mappedBy = "addedBy")
    private List<Location> locations;
}
