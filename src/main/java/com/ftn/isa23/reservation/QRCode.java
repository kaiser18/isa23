package com.ftn.isa23.reservation;

import com.google.zxing.*;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class QRCode {

    //static function that creates QR Code
    public static void generateQrcode(String data, String path, String charset, Map map, int h, int w) throws WriterException, IOException
    {
//the BitMatrix class represents the 2D matrix of bits
//MultiFormatWriter is a factory class that finds the appropriate Writer subclass for the BarcodeFormat requested and encodes the barcode with the supplied contents.
        BitMatrix matrix = new MultiFormatWriter().encode(new String(data.getBytes(charset), charset), BarcodeFormat.QR_CODE, w, h);
        MatrixToImageWriter.writeToFile(matrix, path.substring(path.lastIndexOf('.') + 1), new File(path));
    }
    public static void generateQrCodeReservation(String text, String id)throws WriterException, IOException, NotFoundException
    {
//data that we want to store in the QR code
        String str= "THE HABIT OF PERSISTENCE IS THE HABIT OF VICTORY.";
//path where we want to get QR Code
        String paths = "D:\\Faks\\\\isa qr\\" + id + ".png";
//Encoding charset to be used
        String charset = "UTF-8";
        Map<EncodeHintType, ErrorCorrectionLevel> hashMap = new HashMap<EncodeHintType, ErrorCorrectionLevel>();
//generates QR code with Low level(L) error correction capability
        hashMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
//invoking the user-defined method that creates the QR code
        generateQrcode(text, paths, charset, hashMap, 200, 200);//increase or decrease height and width accordingly
    }
    //main() method
}