package com.fil.TicketBooking.serviceimpl;
import com.fil.TicketBooking.dto.QRCodeDTO;
import com.fil.TicketBooking.model.QRCode;
import com.fil.TicketBooking.repository.QRCodeRepository;
import com.fil.TicketBooking.service.QRCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QRCodeServiceImpl implements QRCodeService {

    @Autowired
    private final QRCodeRepository qrCodeRepository;

    @Autowired
    public QRCodeServiceImpl(QRCodeRepository qrCodeRepository) {
        this.qrCodeRepository = qrCodeRepository;
    }

    @Override
    public String createQRCode(QRCode qrCode) {
        String qrCodeUrl = String.valueOf("Total Pass is"+qrCode.getNoOfPass()+"and TicketId is"+qrCode.getTicket().getTicketId());
        qrCode.setQrCode(qrCodeUrl);
        qrCodeRepository.save(qrCode);
        return qrCodeUrl;
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
    public QRCodeDTO getQRCodeById(Long id) throws Exception {
        return null;
    }

    @Override
    public QRCodeDTO findByTicketId(Long ticketId) throws Exception {
        Optional<QRCode> qrCode = qrCodeRepository.findByTicketTicketId(ticketId);

        // Check if QR code exists
        if (qrCode.isPresent()) {
            QRCode code = qrCode.get();
            return new QRCodeDTO(
                    code.getQrCodeId(),
                    code.getQrCode(),
                    code.getNoOfPass(),
                    code.getStatus()
            );
        } else {
            // Handle the case where the QR code is not found
            throw new Exception("QR Code with ticketId " + ticketId + " not found");
        }
    }

//    @Override
//    public QRCodeDTO getQRCodeById(Long id) {
//        Optional<QRCode> qrCode = qrCodeRepository.findById(id);
//        System.out.println(qrCode);
//        return new QRCodeDTO(
//                qrCode.get().getQrCodeId(),
//                qrCode.get().getQrCode(),
//                qrCode.get().getNoOfPass(),
//                qrCode.get().getStatus()
//        );
//
////        return qrCodeDTO;
//
////                qrCodeRepository.findById(id).orElse(null);
//    }
//@Override
//public QRCodeDTO getQRCodeById(Long id) throws Exception {
//    Optional<QRCode> qrCode = qrCodeRepository.findById(id);
//
//    // Check if QR code exists
//    if (qrCode.isPresent()) {
//        QRCode code = qrCode.get();
//        return new QRCodeDTO(
//                code.getQrCodeId(),
//                code.getQrCode(),
//                code.getNoOfPass(),
//                code.getStatus()
//        );
//    } else {
//        // Handle the case where the QR code is not found
//        throw new Exception("QR Code with id " + id + " not found");
//    }
//}


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

