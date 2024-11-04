package com.fil.TicketBooking.service;
import com.fil.TicketBooking.model.QRCode;
import com.fil.TicketBooking.model.TicketBooking;

import java.util.List;

public interface QRCodeService {
    QRCode createQRCode(QRCode qrCode);
    QRCode updateQRCode(Long id, QRCode qrCode);
    void deleteQRCode(Long id);
    QRCode getQRCodeById(Long id);
    List<QRCode> getAllQRCodes();
}
