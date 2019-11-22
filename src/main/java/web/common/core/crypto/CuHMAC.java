package web.common.core.crypto;

public class CuHMAC
{
    private byte[] inputPAD = new byte[64];
    
    private byte[] outputPAD = new byte[64];
    
    private CuSHA1 sha1_in = new CuSHA1();
    
    private CuSHA1 sha1_out = new CuSHA1();
    
    private byte[] innerKey;
    
    public void init(byte[] key)
    {
        if (key.length > 64)
        {
            CuSHA1 sha1_tmp = new CuSHA1();
            sha1_tmp.init();
            sha1_tmp.update(key);
            sha1_tmp.finish();
            this.innerKey = new byte[20];
            System.arraycopy(sha1_tmp.getDigest(), 0, this.innerKey, 0, 20);
        }
        else
        {
            this.innerKey = new byte[key.length];
            System.arraycopy(key, 0, this.innerKey, 0, key.length);
        }
        int i;
        for (i = 0; i < 64; i++)
            this.inputPAD[i] = 0;
        System.arraycopy(this.innerKey, 0, this.inputPAD, 0, this.innerKey.length);
        for (i = 0; i < 64; i++)
        {
            this.inputPAD[i] = (byte) (this.inputPAD[i] ^ 0x36);
        }
        this.sha1_in.init();
        this.sha1_in.update(this.inputPAD);
        
        for (i = 0; i < 64; i++)
            this.outputPAD[i] = 0;
        System.arraycopy(this.innerKey, 0, this.outputPAD, 0, this.innerKey.length);
        for (i = 0; i < 64; i++)
        {
            this.outputPAD[i] = (byte) (this.outputPAD[i] ^ 0x5C);
        }
        this.sha1_out.init();
        this.sha1_out.update(this.outputPAD);
    }
    
    public void update(byte[] input)
    {
        this.sha1_in.update(input);
    }
    
    public void finish()
    {
        this.sha1_in.finish();
        
        this.sha1_out.update(this.sha1_in.getDigest());
        this.sha1_out.finish();
    }
    
    public String getAlg()
    {
        return "HMAC-SHA1";
    }
    
    public byte[] getDigest()
    {
        return this.sha1_out.getDigest();
    }
    
    public String digout()
    {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 20; i++)
        {
            
            byte[] digestBits = getDigest();
            
            char c1 = (char) (digestBits[i] >>> 4 & 0xF);
            char c2 = (char) (digestBits[i] & 0xF);
            c1 = (char) ((c1 > '\t') ? ('a' + c1 - '\n') : ('0' + c1));
            c2 = (char) ((c2 > '\t') ? ('a' + c2 - '\n') : ('0' + c2));
            sb.append(c1);
            sb.append(c2);
        }
        
        return sb.toString();
    }
}
