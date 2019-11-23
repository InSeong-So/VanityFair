package web.common.core.crypto;

public class HMAC implements Hash
{
    public String hash(String data)
    {
        return null;
    }
    
    public String hash(String data, byte[] key) throws Exception
    {
        try
        {
            return new String(CryptoImpl.doEncode(CryptoImpl.doHMACDigest(data.getBytes(), key)));
        }
        catch (Exception e)
        {
            throw e;
        }
    }
    
    public byte[] digest(byte[] data)
    {
        return null;
    }
}