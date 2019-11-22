package web.common.core.crypto;

import java.io.InputStream;
import java.io.OutputStream;

public class CryptoImpl
{
    private int cipherAlgorithm = 0;
    
    private int[] workingSEEDEncKey = null;
    
    private int[] workingSEEDDecKey = null;
    
    private byte[] workingARIAEncKey = null;
    
    private byte[] workingARIADecKey = null;
    
    private int[] workingDES3EncKey1 = null;
    
    private int[] workingDES3EncKey2 = null;
    
    private int[] workingDES3DecKey1 = null;
    
    private int[] workingDES3DecKey2 = null;
    
    private byte[] workingIV = null;
    
    public CryptoImpl(String alg, byte[] sessionKey, byte[] iv)
    {
        if (alg.equalsIgnoreCase("SEED"))
        {
            this.cipherAlgorithm = 1;
            this.workingSEEDEncKey = CuSEED.generateWorkingKey(true, sessionKey);
            this.workingSEEDDecKey = CuSEED.generateWorkingKey(false, sessionKey);
        }
        else if (alg.equalsIgnoreCase("ARIA"))
        {
            this.workingARIAEncKey = new byte[272];
            this.workingARIADecKey = new byte[272];
            this.cipherAlgorithm = 2;
            CuARIA.EncKeyGen(sessionKey, this.workingARIAEncKey);
            CuARIA.DecKeyGen(sessionKey, this.workingARIADecKey);
        }
        else if (alg.equalsIgnoreCase("3DES"))
        {
            byte[] key1 = new byte[8], key2 = new byte[8];
            System.arraycopy(sessionKey, 0, key1, 0, key1.length);
            System.arraycopy(sessionKey, 8, key2, 0, key2.length);
            this.cipherAlgorithm = 3;
            this.workingDES3EncKey1 = CuDES3.generateWorkingKey(true, key1);
            this.workingDES3EncKey2 = CuDES3.generateWorkingKey(false, key2);
            this.workingDES3DecKey1 = CuDES3.generateWorkingKey(false, key1);
            this.workingDES3DecKey2 = CuDES3.generateWorkingKey(true, key2);
        }
        
        this.workingIV = new byte[iv.length];
        System.arraycopy(iv, 0, this.workingIV, 0, iv.length);
    }
    
    public byte[] writePlainToCipher(byte[] plainText)
    {
        try
        {
            byte[] cipherText = null;
            switch (this.cipherAlgorithm)
            {
                case 1:
                    cipherText = CuSEED.doEncrypt(plainText, this.workingSEEDEncKey, this.workingIV, 0, plainText.length);
                    break;
                
                case 2:
                    cipherText = CuARIA.doEncrypt(plainText, this.workingARIAEncKey, this.workingIV, 0, plainText.length);
                    break;
                
                case 3:
                    cipherText = CuDES3.doEncrypt(plainText, this.workingDES3EncKey1, this.workingDES3EncKey2, this.workingIV, 0, plainText.length);
                    break;
            }
            
            return cipherText;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
    
    public byte[] readCipherToPlain(byte[] cipherText)
    {
        try
        {
            byte[] plainText = null;
            switch (this.cipherAlgorithm)
            {
                case 1:
                    plainText = CuSEED.doDecrypt(cipherText, this.workingSEEDDecKey, this.workingIV);
                    break;
                
                case 2:
                    plainText = CuARIA.doDecrypt(cipherText, this.workingARIADecKey, this.workingIV);
                    break;
                
                case 3:
                    plainText = CuDES3.doDecrypt(cipherText, this.workingDES3DecKey1, this.workingDES3DecKey2, this.workingIV);
                    break;
            }
            
            return plainText;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
    
    public byte[] writePlainToCipher(byte[] plainText, int startlen, int plainlen)
    {
        try
        {
            byte[] cipherText = null;
            switch (this.cipherAlgorithm)
            {
                case 1:
                    cipherText = CuSEED.doEncrypt(plainText, this.workingSEEDEncKey, this.workingIV, startlen, plainlen);
                    break;
                
                case 2:
                    cipherText = CuARIA.doEncrypt(plainText, this.workingARIAEncKey, this.workingIV, startlen, plainlen);
                    break;
                
                case 3:
                    cipherText = CuDES3.doEncrypt(plainText, this.workingDES3EncKey1, this.workingDES3EncKey2, this.workingIV, startlen, plainlen);
                    break;
            }
            
            return cipherText;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
    
    public byte[] readCipherToPlain(byte[] cipherText, int startlen, int cipherlen)
    {
        try
        {
            byte[] tmpCipherText = new byte[cipherlen];
            System.arraycopy(cipherText, startlen, tmpCipherText, 0, cipherlen);
            return CuSEED.doDecrypt(tmpCipherText, this.workingSEEDDecKey, this.workingIV);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
    
    public int writePlainToCipher(byte[] plainText, OutputStream out)
    {
        try
        {
            byte[] cipherText = CuSEED.doEncrypt(plainText, this.workingSEEDEncKey, this.workingIV, 0, plainText.length);
            
            out.write(cipherText, 0, cipherText.length);
            return cipherText.length;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return -1;
        }
    }
    
    public byte[] readCipherToPlain(InputStream in, int length)
    {
        try
        {
            byte[] temp = new byte[length];
            if (in.read(temp, 0, temp.length) <= 0)
                return null;
            return CuSEED.doDecrypt(temp, this.workingSEEDDecKey, this.workingIV);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
    
    public static String doEncode(byte[] plainText)
    {
        try
        {
            return new String(CuBASE64.encode(plainText));
        }
        catch (Exception ex)
        {
            return null;
        }
    }
    
    public static byte[] doDecode(String encodedText)
    {
        try
        {
            return CuBASE64.decode(encodedText.getBytes());
        }
        catch (Exception ex)
        {
            return null;
        }
    }
    
    public static byte[] doDecode(byte[] encodedText)
    {
        try
        {
            return CuBASE64.decode(encodedText);
        }
        catch (Exception ex)
        {
            return null;
        }
    }
    
    public static String doDigestEncode(byte[] plainText)
    {
        try
        {
            CuSHA1 s = new CuSHA1();
            s.init();
            s.update(plainText);
            s.finish();
            return new String(CuBASE64.encode(s.getDigest()));
        }
        catch (Exception e)
        {
            System.out.println("doDigest Error : " + e.getMessage());
            return null;
        }
    }
    
    public static byte[] doDigest(byte[] plainText)
    {
        try
        {
            CuSHA1 s = new CuSHA1();
            s.init();
            s.update(plainText);
            s.finish();
            return s.getDigest();
        }
        catch (Exception e)
        {
            System.out.println("doDigest Error : " + e.getMessage());
            return null;
        }
    }
    
    public static byte[] doHMACDigest(byte[] plainText, byte[] key) throws Exception
    {
        try
        {
            CuHMAC s = new CuHMAC();
            s.init(key);
            s.update(plainText);
            s.finish();
            return s.getDigest();
        }
        catch (Exception e)
        {
            throw new Exception("hmac digest error '" + e.getMessage() + "'");
        }
    }
}
