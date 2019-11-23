package web.common.core.crypto;

public class SEED implements Algorithm
{
    public byte[] encrypt(byte[] plainData, byte[] key, byte[] iv)
    {
        Exception e;
        if (plainData == null || plainData.length < 1)
        {
            return null;
        }
        try
        {
            CryptoImpl impl = new CryptoImpl(Crypto.ALGORITHM_SEED, key, iv);
            try
            {
                return impl.writePlainToCipher(plainData);
            }
            catch (Exception e2)
            {
                e = e2;
                e.printStackTrace();
                return null;
            }
        }
        catch (Exception e3)
        {
            e = e3;
            e.printStackTrace();
            return null;
        }
    }
    
    public String encrypt_b64eOut(byte[] plainData, byte[] key, byte[] iv)
    {
        byte[] encData = encrypt(plainData, key, iv);
        if (encData == null)
        {
            return null;
        }
        return CryptoImpl.doEncode(encData);
    }
    
    public byte[] decrypt(byte[] cipherData, byte[] key, byte[] iv)
    {
        Exception e;
        if (cipherData == null || cipherData.length < 1)
        {
            return null;
        }
        try
        {
            CryptoImpl impl = new CryptoImpl(Crypto.ALGORITHM_SEED, key, iv);
            try
            {
                return impl.readCipherToPlain(cipherData);
            }
            catch (Exception e2)
            {
                e = e2;
                e.printStackTrace();
                return null;
            }
        }
        catch (Exception e3)
        {
            e = e3;
            e.printStackTrace();
            return null;
        }
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