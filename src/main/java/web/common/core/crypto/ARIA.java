package web.common.core.crypto;

public class ARIA implements Algorithm
{
    public byte[] encrypt(byte[] plainData, byte[] key, byte[] iv)
    {
        byte[] encData = null;
        CryptoImpl impl = null;
        
        if (plainData == null || plainData.length < 1)
        {
            return null;
        }
        
        try
        {
            impl = new CryptoImpl("ARIA", key, iv);
            
            encData = impl.writePlainToCipher(plainData);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
        
        return encData;
    }
    
    public String encrypt_b64eOut(byte[] plainData, byte[] key, byte[] iv)
    {
        byte[] encData = null;
        
        encData = encrypt(plainData, key, iv);
        if (encData == null)
        {
            return null;
        }
        
        return CryptoImpl.doEncode(encData);
    }
    
    public byte[] decrypt(byte[] cipherData, byte[] key, byte[] iv)
    {
        byte[] plainData = null;
        CryptoImpl impl = null;
        
        if (cipherData == null || cipherData.length < 1)
        {
            return null;
        }
        
        try
        {
            impl = new CryptoImpl("ARIA", key, iv);
            
            plainData = impl.readCipherToPlain(cipherData);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
        
        return plainData;
    }
    
    public byte[] decrypt_b64eIn(String cipherData, byte[] key, byte[] iv)
    {
        if (cipherData == null || cipherData.length() < 1)
        {
            return null;
        }
        
        return decrypt(CryptoImpl.doDecode(cipherData), key, iv);
    }
}