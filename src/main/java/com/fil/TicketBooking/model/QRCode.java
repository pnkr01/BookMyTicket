package com.fil.TicketBooking.model;
import com.fil.TicketBooking.enums.QRCodeStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.sql.Timestamp;

@Entity
@Table(name = "qr_code")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class QRCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long qrCodeId;

    @ManyToOne
    @JoinColumn(name = "ticket_id", nullable = false)
    private TicketBooking ticket;
    private Timestamp generatedAt;
    private String qrCode;
    private int noOfPass;
    @Enumerated(EnumType.STRING)
    private QRCodeStatus status; // active, expired

    @PrePersist
    protected void onCreate() {
        generatedAt = new Timestamp(System.currentTimeMillis());
    }
}
