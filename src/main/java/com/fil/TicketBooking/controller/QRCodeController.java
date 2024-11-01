package com.fil.TicketBooking.controller;
import com.fil.TicketBooking.model.QRCode;
import com.fil.TicketBooking.model.TicketBooking;
import com.fil.TicketBooking.service.QRCodeService;
import com.fil.TicketBooking.serviceimpl.QRCodeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/qrcodes")
public class QRCodeController {
    @Autowired
    private QRCodeServiceImpl qrCodeService;

    @GetMapping("/get-all-qrcode")
    public ResponseEntity<List<QRCode>> getAllQRCodes() {
        List<QRCode> qrCodes = qrCodeService.getAllQRCodes();
        return new ResponseEntity<>(qrCodes, HttpStatus.OK);
    }

    @GetMapping("/get-qrcode-by-id/{id}")
    public ResponseEntity<QRCode> getQRCodeById(@PathVariable Long id) {
        QRCode qrCode = qrCodeService.getQRCodeById(id);
        return new ResponseEntity<>(qrCode, HttpStatus.OK);
    }

    @PostMapping("/create-qr-code")
    public ResponseEntity<QRCode> createQRCode(@RequestBody QRCode qrCode) {
        QRCode createdQRCode = qrCodeService.createQRCode(qrCode);
        return new ResponseEntity<>(createdQRCode, HttpStatus.CREATED);
    }

    @PutMapping("/update-qr-code-by-id/{id}")
    public ResponseEntity<QRCode> updateQRCode(@PathVariable Long id, @RequestBody QRCode qrCode) {
        QRCode updatedQRCode = qrCodeService.updateQRCode(id, qrCode);
        return new ResponseEntity<>(updatedQRCode, HttpStatus.OK);
    }

    @DeleteMapping("/delete-qr-code-by-id/{id}")
    public ResponseEntity<Void> deleteQRCode(@PathVariable Long id) {
        qrCodeService.deleteQRCode(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
