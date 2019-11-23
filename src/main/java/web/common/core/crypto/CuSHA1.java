package web.common.core.crypto;

public class CuSHA1
{
    private int[] state;
    
    private long count;
    
    private byte[] digestBits;
    
    private boolean digestValid;
    
    private int[] block;
    
    private int blockIndex;
    
    int[] dd;
    
    public void update(byte[] input, int offset, int len)
    {
        for (int i = 0; i < len; i++)
            update(input[i + offset]);
    }
    
    public void update(byte[] input)
    {
        update(input, 0, input.length);
    }
    
    public void updateASCII(String input)
    {
        int len = input.length();
        for (int i = 0; i < len; i++)
        {
            byte x = (byte) (input.charAt(i) & 0xFF);
            update(x);
        }
    }
    
    public CuSHA1()
    {
        this.state = new int[5];
        
        this.block = new int[16];
        
        this.dd = new int[5];
        this.state = new int[5];
        this.count = 0L;
        if (this.block == null)
            this.block = new int[16];
        this.digestBits = new byte[20];
        this.digestValid = false;
    }
    
    final int rol(int value, int bits)
    {
        return value << bits | value >>> 32 - bits;
    }
    
    final int blk0(int i)
    {
        this.block[i] = rol(this.block[i], 24) & 0xFF00FF00 | rol(this.block[i], 8) & 0xFF00FF;
        return this.block[i];
    }
    
    void transform()
    {
        this.dd[0] = this.state[0];
        this.dd[1] = this.state[1];
        this.dd[2] = this.state[2];
        this.dd[3] = this.state[3];
        this.dd[4] = this.state[4];
        
        R0(this.dd, 0, 1, 2, 3, 4, 0);
        R0(this.dd, 4, 0, 1, 2, 3, 1);
        R0(this.dd, 3, 4, 0, 1, 2, 2);
        R0(this.dd, 2, 3, 4, 0, 1, 3);
        R0(this.dd, 1, 2, 3, 4, 0, 4);
        R0(this.dd, 0, 1, 2, 3, 4, 5);
        R0(this.dd, 4, 0, 1, 2, 3, 6);
        R0(this.dd, 3, 4, 0, 1, 2, 7);
        R0(this.dd, 2, 3, 4, 0, 1, 8);
        R0(this.dd, 1, 2, 3, 4, 0, 9);
        R0(this.dd, 0, 1, 2, 3, 4, 10);
        R0(this.dd, 4, 0, 1, 2, 3, 11);
        R0(this.dd, 3, 4, 0, 1, 2, 12);
        R0(this.dd, 2, 3, 4, 0, 1, 13);
        R0(this.dd, 1, 2, 3, 4, 0, 14);
        R0(this.dd, 0, 1, 2, 3, 4, 15);
        R1(this.dd, 4, 0, 1, 2, 3, 16);
        R1(this.dd, 3, 4, 0, 1, 2, 17);
        R1(this.dd, 2, 3, 4, 0, 1, 18);
        R1(this.dd, 1, 2, 3, 4, 0, 19);
        R2(this.dd, 0, 1, 2, 3, 4, 20);
        R2(this.dd, 4, 0, 1, 2, 3, 21);
        R2(this.dd, 3, 4, 0, 1, 2, 22);
        R2(this.dd, 2, 3, 4, 0, 1, 23);
        R2(this.dd, 1, 2, 3, 4, 0, 24);
        R2(this.dd, 0, 1, 2, 3, 4, 25);
        R2(this.dd, 4, 0, 1, 2, 3, 26);
        R2(this.dd, 3, 4, 0, 1, 2, 27);
        R2(this.dd, 2, 3, 4, 0, 1, 28);
        R2(this.dd, 1, 2, 3, 4, 0, 29);
        R2(this.dd, 0, 1, 2, 3, 4, 30);
        R2(this.dd, 4, 0, 1, 2, 3, 31);
        R2(this.dd, 3, 4, 0, 1, 2, 32);
        R2(this.dd, 2, 3, 4, 0, 1, 33);
        R2(this.dd, 1, 2, 3, 4, 0, 34);
        R2(this.dd, 0, 1, 2, 3, 4, 35);
        R2(this.dd, 4, 0, 1, 2, 3, 36);
        R2(this.dd, 3, 4, 0, 1, 2, 37);
        R2(this.dd, 2, 3, 4, 0, 1, 38);
        R2(this.dd, 1, 2, 3, 4, 0, 39);
        R3(this.dd, 0, 1, 2, 3, 4, 40);
        R3(this.dd, 4, 0, 1, 2, 3, 41);
        R3(this.dd, 3, 4, 0, 1, 2, 42);
        R3(this.dd, 2, 3, 4, 0, 1, 43);
        R3(this.dd, 1, 2, 3, 4, 0, 44);
        R3(this.dd, 0, 1, 2, 3, 4, 45);
        R3(this.dd, 4, 0, 1, 2, 3, 46);
        R3(this.dd, 3, 4, 0, 1, 2, 47);
        R3(this.dd, 2, 3, 4, 0, 1, 48);
        R3(this.dd, 1, 2, 3, 4, 0, 49);
        R3(this.dd, 0, 1, 2, 3, 4, 50);
        R3(this.dd, 4, 0, 1, 2, 3, 51);
        R3(this.dd, 3, 4, 0, 1, 2, 52);
        R3(this.dd, 2, 3, 4, 0, 1, 53);
        R3(this.dd, 1, 2, 3, 4, 0, 54);
        R3(this.dd, 0, 1, 2, 3, 4, 55);
        R3(this.dd, 4, 0, 1, 2, 3, 56);
        R3(this.dd, 3, 4, 0, 1, 2, 57);
        R3(this.dd, 2, 3, 4, 0, 1, 58);
        R3(this.dd, 1, 2, 3, 4, 0, 59);
        R4(this.dd, 0, 1, 2, 3, 4, 60);
        R4(this.dd, 4, 0, 1, 2, 3, 61);
        R4(this.dd, 3, 4, 0, 1, 2, 62);
        R4(this.dd, 2, 3, 4, 0, 1, 63);
        R4(this.dd, 1, 2, 3, 4, 0, 64);
        R4(this.dd, 0, 1, 2, 3, 4, 65);
        R4(this.dd, 4, 0, 1, 2, 3, 66);
        R4(this.dd, 3, 4, 0, 1, 2, 67);
        R4(this.dd, 2, 3, 4, 0, 1, 68);
        R4(this.dd, 1, 2, 3, 4, 0, 69);
        R4(this.dd, 0, 1, 2, 3, 4, 70);
        R4(this.dd, 4, 0, 1, 2, 3, 71);
        R4(this.dd, 3, 4, 0, 1, 2, 72);
        R4(this.dd, 2, 3, 4, 0, 1, 73);
        R4(this.dd, 1, 2, 3, 4, 0, 74);
        R4(this.dd, 0, 1, 2, 3, 4, 75);
        R4(this.dd, 4, 0, 1, 2, 3, 76);
        R4(this.dd, 3, 4, 0, 1, 2, 77);
        R4(this.dd, 2, 3, 4, 0, 1, 78);
        R4(this.dd, 1, 2, 3, 4, 0, 79);
        
        this.state[0] = this.state[0] + this.dd[0];
        this.state[1] = this.state[1] + this.dd[1];
        this.state[2] = this.state[2] + this.dd[2];
        this.state[3] = this.state[3] + this.dd[3];
        this.state[4] = this.state[4] + this.dd[4];
    }
    
    final int blk(int i)
    {
        this.block[i & 0xF] = rol(this.block[i + 13 & 0xF] ^ this.block[i + 8 & 0xF] ^ this.block[i + 2 & 0xF] ^ this.block[i & 0xF], 1);
        return this.block[i & 0xF];
    }
    
    final void R0(int[] data, int v, int w, int x, int y, int z, int i)
    {
        data[z] = data[z] + (data[w] & (data[x] ^ data[y]) ^ data[y]) + blk0(i) + 1518500249 + rol(data[v], 5);
        data[w] = rol(data[w], 30);
    }
    
    final void R1(int[] data, int v, int w, int x, int y, int z, int i)
    {
        data[z] = data[z] + (data[w] & (data[x] ^ data[y]) ^ data[y]) + blk(i) + 1518500249 + rol(data[v], 5);
        data[w] = rol(data[w], 30);
    }
    
    public void init()
    {
        this.state[0] = 1732584193;
        this.state[1] = -271733879;
        this.state[2] = -1732584194;
        this.state[3] = 271733878;
        this.state[4] = -1009589776;
        this.count = 0L;
        this.digestBits = new byte[20];
        this.digestValid = false;
        this.blockIndex = 0;
    }
    
    final void R2(int[] data, int v, int w, int x, int y, int z, int i)
    {
        data[z] = data[z] + (data[w] ^ data[x] ^ data[y]) + blk(i) + 1859775393 + rol(data[v], 5);
        data[w] = rol(data[w], 30);
    }
    
    final void R3(int[] data, int v, int w, int x, int y, int z, int i)
    {
        data[z] = data[z] + ((data[w] | data[x]) & data[y] | data[w] & data[x]) + blk(i) + -1894007588 + rol(data[v], 5);
        data[w] = rol(data[w], 30);
    }
    
    final void R4(int[] data, int v, int w, int x, int y, int z, int i)
    {
        data[z] = data[z] + (data[w] ^ data[x] ^ data[y]) + blk(i) + -899497514 + rol(data[v], 5);
        data[w] = rol(data[w], 30);
    }
    
    public void update(byte b)
    {
        int mask = 8 * (this.blockIndex & 0x3);
        
        this.count += 8L;
        this.block[this.blockIndex >> 2] = this.block[this.blockIndex >> 2] & (255 << mask ^ 0xFFFFFFFF);
        this.block[this.blockIndex >> 2] = this.block[this.blockIndex >> 2] | (b & 0xFF) << mask;
        this.blockIndex++;
        if (this.blockIndex == 64)
        {
            transform();
            this.blockIndex = 0;
        }
    }
    
    public void finish()
    {
        byte[] bits = new byte[8];
        int i = 0;
        
        for (i = 0; i < 8; i++)
        {
            bits[i] = (byte) (int) (this.count >>> (7 - i) * 8 & 0xFFL);
        }
        
        update(Byte.MIN_VALUE);
        while (this.blockIndex != 56)
        {
            update((byte) 0);
        }
        update(bits);
        for (i = 0; i < 20; i++)
        {
            this.digestBits[i] = (byte) (this.state[i >> 2] >> (3 - (i & 0x3)) * 8 & 0xFF);
        }
        this.digestValid = true;
    }
    
    public String getAlg()
    {
        return "SHA1";
    }
    
    public byte[] getDigest()
    {
        if (this.digestValid != true)
        {
            return null;
        }
        return this.digestBits;
    }
    
    public String digout()
    {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 20; i++)
        {
            
            char c1 = (char) (this.digestBits[i] >>> 4 & 0xF);
            char c2 = (char) (this.digestBits[i] & 0xF);
            c1 = (char) ((c1 > '\t') ? ('a' + c1 - '\n') : ('0' + c1));
            c2 = (char) ((c2 > '\t') ? ('a' + c2 - '\n') : ('0' + c2));
            sb.append(c1);
            sb.append(c2);
        }
        
        return sb.toString();
    }
}
