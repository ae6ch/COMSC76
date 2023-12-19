Implement a class named BitOutputStream for writing a stream of bits to a file as follows:

+BitOutputStream(file: File)    // Creates a BitOutputStream to write bits to the file.

+writeBit(char bit): void             // Writes a bit '0' or '1' to the output stream

+writeBit(String bitString): void         // Writes a string of bits to the output stream

+close(): void                                    // This method must be invoked to close the stream

The writeBit(char bit) method stores the bit in a byte variable. When you create a BitOutputStream, the byte is empty. After invoking writeBit('1') the byte becomes becomes 00000001. After invoking writeBit("0101"), the byte becomes 00010101. The first three bits are not filled yet. When a byte is full, it is sent to the output stream. Now the byte is reset to empty. You must close the stream by invoking the close() method. If a byte is neither empty nor full, the close() method first fills in zeros to make a full 8 bits in the byte, and then closes the stream. Hint: It might help to look at Exercise 5.44, on page 202, as well as Appendix G on Bitwise operations on page 1169 of the text. Write a program that sends the bits 010000100100001001101 to a file called testOutput.dat.

Then you need to open the file and print out the contents of the file in bits. So if you are sending the bits 010000100100001001101 to that file, the output will look something like this: 
01000010 01000010 01101000


 

One possible approach to this program is the following outline:

public class Program2 {
    public static void main(String[] args) throws Exception {
    BitOutputStream output = new BitOutputStream(new File("testOutput.dat"));
    output.writeBit("010000100100001001101");
    output.close();

    // Use Scanner to read the file again and then write it back out   

    }

    public static class BitOutputStream {
        private FileOutputStream output;
        //  programs statements

    // Constructor
    public BitOutputStream(File file) throws IOException {
    // one statement will do the job
    }

     public void writeBit(String bitString) throws IOException {
          for (int i = 0; i < bitString.length(); i++)
              writeBit(bitString.charAt(i));
      }

     public void writeBit(char bit) throws IOException {
          // Program statements for this method
     

      }

     /** Write the last byte and close the stream. If the last byte is not full, right-shfit with zeros         */
     public void close( ) throws IOException {
         // Program statements for this method


          output.close();  // This makes use of the close() method for a FileOutputStream object
       }
   }
}
