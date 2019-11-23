package web.common.core.crypto;

public interface Algorithm
{
    byte[] encrypt(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, byte[] paramArrayOfByte3) throws Exception;
    
    String encrypt_b64eOut(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, byte[] paramArrayOfByte3) throws Exception;
    
    byte[] decrypt(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, byte[] paramArrayOfByte3) throws Exception;
    
    byte[] decrypt_b64eIn(String paramString, byte[] paramArrayOfByte1, byte[] paramArrayOfByte2) throws Exception;
}
