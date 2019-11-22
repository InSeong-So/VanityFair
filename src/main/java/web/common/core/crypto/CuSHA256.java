package web.common.core.crypto;

public class CuSHA256
{
    static final int[] K = { 1116352408, 1899447441, -1245643825, -373957723, 961987163, 1508970993, -1841331548, -1424204075, -670586216, 310598401, 607225278, 1426881987, 1925078388, -2132889090, -1680079193, -1046744716, -459576895, -272742522, 264347078, 604807628, 770255983, 1249150122,
            1555081692, 1996064986, -1740746414, -1473132947, -1341970488, -1084653625, -958395405, -710438585, 113926993, 338241895, 666307205, 773529912, 1294757372, 1396182291, 1695183700, 1986661051, -2117940946, -1838011259, -1564481375, -1474664885, -1035236496, -949202525, -778901479,
            -694614492, -200395387, 275423344, 430227734, 506948616, 659060556, 883997877, 958139571, 1322822218, 1537002063, 1747873779, 1955562222, 2024104815, -2067236844, -1933114872, -1866530822, -1538233109, -1090935817, -965641998 };
    
    private int H1;
    
    private int H2;
    
    private int H3;
    
    private int H4;
    
    private int H5;
    
    private int H6;
    
    public CuSHA256()
    {
        this.H1 = 0;
        this.H2 = 0;
        this.H3 = 0;
        this.H4 = 0;
        this.H5 = 0;
        this.H6 = 0;
        this.H7 = 0;
        this.H8 = 0;
        this.X = new int[64];
        this.xOff = 0;
        this.xBuf = null;
        this.xBufOff = 0;
        this.byteCount = 0L;
        
        this.xBuf = new byte[4];
        this.xBufOff = 0;
        
        reset();
    }
    
    private int H7;
    
    private int H8;
    
    private int[] X;
    
    public byte[] hash(byte[] data)
    {
        byte[] digestBytes = null;
        
        try
        {
            reset();
            update(data, 0, data.length);
            digestBytes = new byte[32];
            doFinal(digestBytes, 0);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
        return digestBytes;
    }
    
    private int xOff;
    
    private byte[] xBuf;
    
    private int xBufOff;
    
    private long byteCount;
    
    private int doFinal(byte[] out, int outOff)
    {
        finish();
        
        unpackWord(this.H1, out, outOff);
        unpackWord(this.H2, out, outOff + 4);
        unpackWord(this.H3, out, outOff + 8);
        unpackWord(this.H4, out, outOff + 12);
        unpackWord(this.H5, out, outOff + 16);
        unpackWord(this.H6, out, outOff + 20);
        unpackWord(this.H7, out, outOff + 24);
        unpackWord(this.H8, out, outOff + 28);
        
        reset();
        return 32;
    }
    
    private void processLength(long bitLength)
    {
        if (this.xOff > 14)
        {
            processBlock();
        }
        
        this.X[14] = (int) (bitLength >>> 32);
        this.X[15] = (int) (bitLength & 0xFFFFFFFFFFFFFFFFL);
    }
    
    private void processWord(byte[] in, int inOff)
    {
        this.X[this.xOff++] = (in[inOff] & 0xFF) << 24 | (in[inOff + 1] & 0xFF) << 16 | (in[inOff + 2] & 0xFF) << 8 | in[inOff + 3] & 0xFF;
        
        if (this.xOff == 16)
        {
            processBlock();
        }
    }
    
    private void unpackWord(int word, byte[] out, int outOff)
    {
        out[outOff] = (byte) (word >>> 24);
        out[outOff + 1] = (byte) (word >>> 16);
        out[outOff + 2] = (byte) (word >>> 8);
        out[outOff + 3] = (byte) word;
    }
    
    private void processBlock()
    {
        int t;
        for (t = 16; t <= 63; t++)
        {
            this.X[t] = Theta1(this.X[t - 2]) + this.X[t - 7] + Theta0(this.X[t - 15]) + this.X[t - 16];
        }
        
        int a = this.H1;
        int b = this.H2;
        int c = this.H3;
        int d = this.H4;
        int e = this.H5;
        int f = this.H6;
        int g = this.H7;
        int h = this.H8;
        
        for (t = 0; t <= 63; t++)
        {
            
            int T1 = h + Sum1(e) + Ch(e, f, g) + K[t] + this.X[t];
            int T2 = Sum0(a) + Maj(a, b, c);
            h = g;
            g = f;
            f = e;
            e = d + T1;
            d = c;
            c = b;
            b = a;
            a = T1 + T2;
        }
        
        this.H1 += a;
        this.H2 += b;
        this.H3 += c;
        this.H4 += d;
        this.H5 += e;
        this.H6 += f;
        this.H7 += g;
        this.H8 += h;
        
        this.xOff = 0;
        for (int i = 0; i != this.X.length; i++)
        {
            this.X[i] = 0;
        }
    }
    
    private int rotateRight(int x, int n)
    {
        return x >>> n | x << 32 - n;
    }
    
    private int Ch(int x, int y, int z)
    {
        return x & y ^ (x ^ 0xFFFFFFFF) & z;
    }
    
    private int Maj(int x, int y, int z)
    {
        return x & y ^ x & z ^ y & z;
    }
    
    private int Sum0(int x)
    {
        return rotateRight(x, 2) ^ rotateRight(x, 13) ^ rotateRight(x, 22);
    }
    
    private int Sum1(int x)
    {
        return rotateRight(x, 6) ^ rotateRight(x, 11) ^ rotateRight(x, 25);
    }
    
    private int Theta0(int x)
    {
        return rotateRight(x, 7) ^ rotateRight(x, 18) ^ x >>> 3;
    }
    
    private int Theta1(int x)
    {
        return rotateRight(x, 17) ^ rotateRight(x, 19) ^ x >>> 10;
    }
    
    private void reset()
    {
        this.byteCount = 0L;
        this.xBufOff = 0;
        int i;
        for (i = 0; i < this.xBuf.length; i++)
        {
            this.xBuf[i] = 0;
        }
        
        this.H1 = 1779033703;
        this.H2 = -1150833019;
        this.H3 = 1013904242;
        this.H4 = -1521486534;
        this.H5 = 1359893119;
        this.H6 = -1694144372;
        this.H7 = 528734635;
        this.H8 = 1541459225;
        
        this.xOff = 0;
        for (i = 0; i != this.X.length; i++)
        {
            this.X[i] = 0;
        }
    }
    
    private void update(byte in)
    {
        this.xBuf[this.xBufOff++] = in;
        
        if (this.xBufOff == this.xBuf.length)
        {
            processWord(this.xBuf, 0);
            this.xBufOff = 0;
        }
        
        this.byteCount++;
    }
    
    private void update(byte[] in, int inOff, int len)
    {
        while (this.xBufOff != 0 && len > 0)
        {
            update(in[inOff]);
            
            inOff++;
            len--;
        }
        
        while (len > this.xBuf.length)
        {
            processWord(in, inOff);
            
            inOff += this.xBuf.length;
            len -= this.xBuf.length;
            this.byteCount += this.xBuf.length;
        }
        
        while (len > 0)
        {
            update(in[inOff]);
            
            inOff++;
            len--;
        }
    }
    
    private void finish()
    {
        long bitLength = this.byteCount << 3;
        
        update(Byte.MIN_VALUE);
        
        while (this.xBufOff != 0)
        {
            update((byte) 0);
        }
        
        processLength(bitLength);
        
        processBlock();
    }
}