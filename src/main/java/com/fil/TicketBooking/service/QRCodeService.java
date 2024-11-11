package com.fil.TicketBooking.service;
import com.fil.TicketBooking.dto.QRCodeDTO;
import com.fil.TicketBooking.model.QRCode;

import java.util.List;

public interface QRCodeService {
    String createQRCode(QRCode qrCode);
    QRCode updateQRCode(Long id, QRCode qrCode);
    void deleteQRCode(Long id);
    QRCodeDTO getQRCodeById(Long id) throws Exception;
    QRCodeDTO findByTicketId(Long ticketId) throws Exception;
    List<QRCode> getAllQRCodes();
}
