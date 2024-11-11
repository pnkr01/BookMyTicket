package com.fil.TicketBooking.dto;
import com.fil.TicketBooking.enums.QRCodeStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QRCodeDTO {
    private Long qrCodeId;
    private String qrCode;
    private int noOfPass;
    private QRCodeStatus status; // active, expired
}