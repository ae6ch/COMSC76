import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * File I/O Assignment
 * 
 * 
 * 
 * 9/6/2023
 * 
 * @author Steve Rubin
 */
public class Program2 {
   public static void main(String[] args) throws Exception {
      BitOutputStream output = new BitOutputStream(new File("testOutput.dat"));
      String testString = "01000010 01000010 01101";
      // System.out.println(testString);
      output.writeBit(testString);
      // output.flush();
      // output.writeBit("1111");
      // output.writeBit("0101");
      output.close();

      try (FileInputStream input = new FileInputStream("testOutput.dat")) {
         while (input.available() > 0) {
            printBits((byte) input.read());
            System.out.print(" ");
         }
         System.out.println();
      }

   }

   /**
    * Print the bits in a byte as 8 bits
    * 
    * @param c
    */
   public static void printBits(byte c) {
      for (int i = 7; i >= 0; i--) { // There is a static method in Integer that does almost the same thing, but it
                                     // "helpfully" removes leading zeros.
         System.out.print((c >> i) & 1);
      }
      //String s = String.format("%8s", Integer.toBinaryString( c & 0xFF)).replace(' ', '0');
      //System.out.println(s);

   }
}

class BitOutputStream {
   private FileOutputStream output;
   private byte byteBuffer = 0; // Current buffer contents
   private byte bytePosition = 0; // Current byte position in the buffer

   // Constructor
   public BitOutputStream(File file) throws IOException {
      // Program statements for this method
      output = new FileOutputStream(file);
   }

   /**
    * Write a bit string to the output stream
    * 
    * @param bitString a string of bits '0' or '1'
    * @throws IOException
    */
   public void writeBit(String bitString) throws IOException {
      for (int i = 0; i < bitString.length(); i++)
         if (bitString.charAt(i) != ' ') // Ignore spaces, but not other characters
            writeBit(bitString.charAt(i));
   }

   /**
    * Write a bit to the output stream
    * 
    * @param bit '0' or '1'
    * @throws IOException
    */
   public void writeBit(char bit) throws IOException {
      // Program statements for this method

      byteBuffer = (byte) (byteBuffer << 1);
      if (bit == '1') {
         byteBuffer = (byte) (byteBuffer | 1);
      } else if (bit != '0') {
         System.out.println("bits are 0 or 1 only ");
         return;
      }
      bytePosition++;
      if (bytePosition == 8) { // If the buffer has a full byte store it to the file
         flush();
      }
   }

   /**
    * Write the last byte to the output file, if the last byte is not full, pad it
    * 
    * @throws IOException
    */
   public void flush() throws IOException {
      if (bytePosition != 0) {
         byteBuffer = (byte) (byteBuffer << (8 - bytePosition)); // the last byte is not full, pad it
         output.write(byteBuffer);
         byteBuffer = 0;
         bytePosition = 0;
      }

   }

   /**
    * Close the stream after flushing the buffer
    * 
    * @throws IOException
    */
   public void close() throws IOException {

      flush(); // Write the last byte to the output file
      output.close();
   }

}
