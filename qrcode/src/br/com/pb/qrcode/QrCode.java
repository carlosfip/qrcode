package br.com.pb.qrcode;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Writer;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;

public class QrCode
{
  public boolean gerarQrCode(String conteudoQRCode, String path)
    throws Exception
  {
    Charset charset = Charset.forName("ISO-8859-1");
    CharsetEncoder encoder = charset.newEncoder();
    byte[] b = null;
    try
    {
      ByteBuffer bbuf = encoder.encode(
        CharBuffer.wrap(conteudoQRCode));
      b = bbuf.array();

      String data = null;
      data = new String(b, "ISO-8859-1");

      BitMatrix matrix = null;
      int h = 100;
      int w = 100;
      Writer writer = new QRCodeWriter();
      matrix = writer.encode(data, 
        BarcodeFormat.QR_CODE, w, h);
      File file = new File(path);
      MatrixToImageWriter.writeToFile(matrix, "JPG", file);
    } catch (IOException e) {
      throw e;
    }
    return true;
  }
}