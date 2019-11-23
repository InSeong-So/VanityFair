package web.common.core.crypto;

public class AES128 implements Algorithm
{
    public byte[] encrypt(byte[] plainData, byte[] key, byte[] iv) throws Exception
    {
        Exception e;
        if (plainData == null || plainData.length < 1)
        {
            throw new Exception("No plaintext.");
        }
        else if (key.length != 16)
        {
            throw new Exception("Key length is not 128 bits.");
        }
        else
        {
            try
            {
                CuAES aes = new CuAES(key, iv);
                try
                {
                    return aes.doEncrypt(plainData, 0, plainData.length);
                }
                catch (Exception e2)
                {
                    e = e2;
                    e.printStackTrace();
                    throw e;
                }
            }
            catch (Exception e3)
            {
                e = e3;
                e.printStackTrace();
                throw e;
            }
        }
    }
    
    public String encrypt_b64eOut(byte[] plainData, byte[] key, byte[] iv) throws Exception
    {
        byte[] encData = encrypt(plainData, key, iv);
        if (encData == null)
        {
            return null;
        }
        return CryptoImpl.doEncode(encData);
    }
    
    public byte[] decrypt(byte[] cipherData, byte[] key, byte[] iv) throws Exception
    {
        Exception e;
        if (cipherData == null || cipherData.length < 1)
        {
            throw new Exception("No ciphertext.");
        }
        else if (key.length != 16)
        {
            throw new Exception("Key length is not 128 bits.");
        }
        else
        {
            try
            {
                CuAES aes = new CuAES(key, iv);
                try
                {
                    return aes.doDecrypt(cipherData);
                }
                catch (Exception e2)
                {
                    e = e2;
                    e.printStackTrace();
                    throw e;
                }
            }
            catch (Exception e3)
            {
                e = e3;
                e.printStackTrace();
                throw e;
            }
        }
    }
    
    public byte[] decrypt_b64eIn(String cipherData, byte[] key, byte[] iv) throws Exception
    {
        if (cipherData == null || cipherData.length() < 1)
        {
            return null;
        }
        return decrypt(CryptoImpl.doDecode(cipherData), key, iv);
    }
}