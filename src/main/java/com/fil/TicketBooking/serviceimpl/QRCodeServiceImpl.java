package com.fil.TicketBooking.serviceimpl;
import com.fil.TicketBooking.enums.QRCodeStatus;
import com.fil.TicketBooking.model.Payment;
import com.fil.TicketBooking.model.QRCode;
import com.fil.TicketBooking.model.TicketBooking;
import com.fil.TicketBooking.repository.QRCodeRepository;
import com.fil.TicketBooking.service.QRCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QRCodeServiceImpl implements QRCodeService {

    private final QRCodeRepository qrCodeRepository;

    @Autowired
    public QRCodeServiceImpl(QRCodeRepository qrCodeRepository) {
        this.qrCodeRepository = qrCodeRepository;
    }

    @Override
    public QRCode createQRCode(QRCode qrCode) {
        qrCodeRepository.save(qrCode);
        return qrCode;
    }

    @Override
    public QRCode updateQRCode(Long id, QRCode qrCode) {
        Optional<QRCode> existingQRCode = qrCodeRepository.findById(id);
        if (existingQRCode.isPresent()) {
            qrCode.setQrCodeId(id);
            return qrCodeRepository.save(qrCode);
        }
        return null; // or throw an exception
    }

    @Override
    public void deleteQRCode(Long id) {
        qrCodeRepository.deleteById(id);
    }

    @Override
    public QRCode getQRCodeById(Long id) {
        return qrCodeRepository.findById(id).orElse(null);
    }

    @Override
    public List<QRCode> getAllQRCodes() {
        return qrCodeRepository.findAll();
    }

    @Lookup
    protected QRCode createQRCodeInstance() {
        // Spring will override this method to return a new QRCode instance
        return null; // This will be overridden by Spring
    }
}

