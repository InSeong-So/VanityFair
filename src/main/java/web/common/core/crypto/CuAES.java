package web.common.core.crypto;

import java.security.InvalidKeyException;

public class CuAES
{
    protected int currentBlockSize;
    
    protected Object currentKey;
    
    protected byte[] iv;
    
    private static final String SS = "捼睻濅、末ﻗꭶ쪂쥽繁䟰귔ꊯ鲤狀럽錦㘿㒥燘ㄕӇ⏃ᢖ֚ܒ胢뉵ঃⰚ᭮媠刻횳⧣⾄发í⃼녛櫋븹䩌壏탯꫻䍍㎅䗹ɿ值龨冣䂏銝㣵벶?ჿ촌Ꮼ得䐗쒧總摝ᥳ悁俜∪邈䛮렔?௛㨊䤆⑜싓걢醕㝭跕亩汖敺금멸┮Ღ듆琟䮽變瀾땦䠃愵垹蛁ᶞ頑槙躔鬞蟩칕⣟財褍뿦䉨䆙ⴏ끔묖";
    
    private static final byte[] S = new byte[256];
    
    private static final byte[] Si = new byte[256];
    
    private static final int[] T1 = new int[256];
    
    private static final int[] T2 = new int[256];
    
    private static final int[] T3 = new int[256];
    
    private static final int[] T4 = new int[256];
    
    private static final int[] T5 = new int[256];
    
    private static final int[] T6 = new int[256];
    
    private static final int[] T7 = new int[256];
    
    private static final int[] T8 = new int[256];
    
    private static final int[] U1 = new int[256];
    
    private static final int[] U2 = new int[256];
    
    private static final int[] U3 = new int[256];
    
    private static final int[] U4 = new int[256];
    
    private static final byte[] rcon = new byte[30];
    
    private static final int[][][] shifts = { { { 0, 0 }, { 1, 3 }, { 2, 2 }, { 3, 1 } }, { { 0, 0 }, { 1, 5 }, { 2, 4 }, { 3, 3 } }, { { 0, 0 }, { 1, 7 }, { 3, 5 }, { 4, 4 } } };
    
    static
    {
        long time = System.currentTimeMillis();
        
        int ROOT = 283;
        int i = 0;
        
        for (i = 0; i < 256; i++)
        {
            char c = SS.charAt(i >>> 1);
            S[i] = (byte) ((i & 1) == 0 ? c >>> 8 : c & 255);
            int s = S[i] & 255;
            Si[s] = (byte) i;
            int s2 = s << 1;
            if (s2 >= 256)
            {
                s2 ^= 283;
            }
            int s3 = s2 ^ s;
            int i2 = i << 1;
            if (i2 >= 256)
            {
                i2 ^= 283;
            }
            int i4 = i2 << 1;
            if (i4 >= 256)
            {
                i4 ^= 283;
            }
            int i8 = i4 << 1;
            if (i8 >= 256)
            {
                i8 ^= 283;
            }
            int i9 = i8 ^ i;
            int ib = i9 ^ i2;
            int id = i9 ^ i4;
            int ie = (i8 ^ i4) ^ i2;
            int t = (((s2 << 24) | (s << 16)) | (s << 8)) | s3;
            T1[i] = t;
            T2[i] = (t >>> 8) | (t << 24);
            T3[i] = (t >>> 16) | (t << 16);
            T4[i] = (t >>> 24) | (t << 8);
            int[] iArr2 = T5;
            t = (((ie << 24) | (i9 << 16)) | (id << 8)) | ib;
            U1[i] = t;
            iArr2[s] = t;
            iArr2 = T6;
            int i3 = (t >>> 8) | (t << 24);
            U2[i] = i3;
            iArr2[s] = i3;
            iArr2 = T7;
            i3 = (t >>> 16) | (t << 16);
            U3[i] = i3;
            iArr2[s] = i3;
            iArr2 = T8;
            i3 = (t >>> 24) | (t << 8);
            U4[i] = i3;
            iArr2[s] = i3;
        }
        
        int r = 1;
        rcon[0] = 1;
        for (i = 1; i < 30; i++)
        {
            r <<= 1;
            if (r >= 256)
            {
                r ^= ROOT;
            }
            rcon[i] = (byte) r;
        }
        
        time = System.currentTimeMillis() - time;
    }
    
    public CuAES(byte[] key, byte[] iv) throws InvalidKeyException
    {
        this.currentBlockSize = 16;
        this.currentKey = makeKey(key, this.currentBlockSize);
        this.iv = iv;
    }
    
    private Object makeKey(byte[] k, int bs) throws InvalidKeyException
    {
        if (k == null)
        {
            throw new InvalidKeyException("Empty key");
        }
        if (k.length != 16 && k.length != 24 && k.length != 32)
        {
            throw new InvalidKeyException("Incorrect key length");
        }
        if (bs != 16 && bs != 24 && bs != 32)
        {
            throw new IllegalArgumentException();
        }
        
        int ROUNDS = getRounds(k.length, bs);
        int BC = bs / 4;
        int[][] Ke = new int[ROUNDS + 1][BC];
        int[][] Kd = new int[ROUNDS + 1][BC];
        int ROUND_KEY_COUNT = (ROUNDS + 1) * BC;
        int KC = k.length / 4;
        int[] tk = new int[KC];
        
        int i, j;
        
        for (i = 0, j = 0; i < KC;)
        {
            tk[i++] = k[j++] << 24 | (k[j++] & 0xFF) << 16 | (k[j++] & 0xFF) << 8 | k[j++] & 0xFF;
        }
        
        int t = 0;
        for (j = 0; j < KC && t < ROUND_KEY_COUNT; j++, t++)
        {
            Ke[t / BC][t % BC] = tk[j];
            Kd[ROUNDS - t / BC][t % BC] = tk[j];
        }
        int rconpointer = 0;
        while (t < ROUND_KEY_COUNT)
        {
            
            int tt = tk[KC - 1];
            tk[0] = tk[0] ^ (S[tt >>> 16 & 0xFF] & 0xFF) << 24 ^ (S[tt >>> 8 & 0xFF] & 0xFF) << 16 ^ (S[tt & 0xFF] & 0xFF) << 8 ^ S[tt >>> 24] & 0xFF ^ rcon[rconpointer++] << 24;
            
            if (KC != 8)
            {
                for (i = 1, j = 0; i < KC;)
                {
                    tk[i++] = tk[i++] ^ tk[j++];
                }
            }
            else
            {
                for (i = 1, j = 0; i < KC / 2;)
                {
                    tk[i++] = tk[i++] ^ tk[j++];
                }
                tt = tk[KC / 2 - 1];
                tk[KC / 2] = tk[KC / 2] ^ S[tt & 0xFF] & 0xFF ^ (S[tt >>> 8 & 0xFF] & 0xFF) << 8 ^ (S[tt >>> 16 & 0xFF] & 0xFF) << 16 ^ S[tt >>> 24 & 0xFF] << 24;
                
                for (j = KC / 2, i = j + 1; i < KC;)
                {
                    tk[i++] = tk[i++] ^ tk[j++];
                }
            }
            
            for (j = 0; j < KC && t < ROUND_KEY_COUNT; j++, t++)
            {
                Ke[t / BC][t % BC] = tk[j];
                Kd[ROUNDS - t / BC][t % BC] = tk[j];
            }
        }
        for (int r = 1; r < ROUNDS; r++)
        {
            for (j = 0; j < BC; j++)
            {
                int tt = Kd[r][j];
                Kd[r][j] = U1[tt >>> 24] ^ U2[tt >>> 16 & 0xFF] ^ U3[tt >>> 8 & 0xFF] ^ U4[tt & 0xFF];
            }
        }
        
        return new Object[] { Ke, Kd };
    }
    
    public static int getRounds(int ks, int bs)
    {
        switch (ks)
        {
            case 16:
                return (bs == 16) ? 10 : ((bs == 24) ? 12 : 14);
            case 24:
                return (bs != 32) ? 12 : 14;
        }
        return 14;
    }
    
    private static void rijndaelEncrypt(byte[] in, int inOffset, byte[] out, int outOffset, Object sessionKey, int bs)
    {
        Object[] sKey = (Object[]) sessionKey;
        int[][] Ke = (int[][]) sKey[0];
        
        int BC = bs / 4;
        int ROUNDS = Ke.length - 1;
        int SC = (BC == 4) ? 0 : ((BC == 6) ? 1 : 2);
        int s1 = shifts[SC][1][0];
        int s2 = shifts[SC][2][0];
        int s3 = shifts[SC][3][0];
        int[] a = new int[BC];
        int[] t = new int[BC];
        
        int i;
        for (i = 0; i < BC; i++)
        {
            t[i] = (in[inOffset++] << 24 | (in[inOffset++] & 0xFF) << 16 | (in[inOffset++] & 0xFF) << 8 | in[inOffset++] & 0xFF) ^ Ke[0][i];
        }
        
        for (int r = 1; r < ROUNDS; r++)
        {
            for (i = 0; i < BC; i++)
            {
                a[i] = T1[t[i] >>> 24] ^ T2[t[(i + s1) % BC] >>> 16 & 0xFF] ^ T3[t[(i + s2) % BC] >>> 8 & 0xFF] ^ T4[t[(i + s3) % BC] & 0xFF] ^ Ke[r][i];
            }
            
            System.arraycopy(a, 0, t, 0, BC);
        }
        
        for (i = 0; i < BC; i++)
        {
            int tt = Ke[ROUNDS][i];
            out[outOffset++] = (byte) (S[t[i] >>> 24] ^ tt >>> 24);
            out[outOffset++] = (byte) (S[t[(i + s1) % BC] >>> 16 & 0xFF] ^ tt >>> 16);
            out[outOffset++] = (byte) (S[t[(i + s2) % BC] >>> 8 & 0xFF] ^ tt >>> 8);
            out[outOffset++] = (byte) (S[t[(i + s3) % BC] & 0xFF] ^ tt);
        }
    }
    
    private static void rijndaelDecrypt(byte[] in, int inOffset, byte[] out, int outOffset, Object sessionKey, int bs)
    {
        Object[] sKey = (Object[]) sessionKey;
        int[][] Kd = (int[][]) sKey[1];
        
        int BC = bs / 4;
        int ROUNDS = Kd.length - 1;
        int SC = (BC == 4) ? 0 : ((BC == 6) ? 1 : 2);
        int s1 = shifts[SC][1][1];
        int s2 = shifts[SC][2][1];
        int s3 = shifts[SC][3][1];
        int[] a = new int[BC];
        int[] t = new int[BC];
        
        int i;
        for (i = 0; i < BC; i++)
        {
            t[i] = (in[inOffset++] << 24 | (in[inOffset++] & 0xFF) << 16 | (in[inOffset++] & 0xFF) << 8 | in[inOffset++] & 0xFF) ^ Kd[0][i];
        }
        
        for (int r = 1; r < ROUNDS; r++)
        {
            for (i = 0; i < BC; i++)
            {
                a[i] = T5[t[i] >>> 24] ^ T6[t[(i + s1) % BC] >>> 16 & 0xFF] ^ T7[t[(i + s2) % BC] >>> 8 & 0xFF] ^ T8[t[(i + s3) % BC] & 0xFF] ^ Kd[r][i];
            }
            
            System.arraycopy(a, 0, t, 0, BC);
        }
        
        for (i = 0; i < BC; i++)
        {
            int tt = Kd[ROUNDS][i];
            out[outOffset++] = (byte) (Si[t[i] >>> 24] ^ tt >>> 24);
            out[outOffset++] = (byte) (Si[t[(i + s1) % BC] >>> 16 & 0xFF] ^ tt >>> 16);
            out[outOffset++] = (byte) (Si[t[(i + s2) % BC] >>> 8 & 0xFF] ^ tt >>> 8);
            out[outOffset++] = (byte) (Si[t[(i + s3) % BC] & 0xFF] ^ tt);
        }
    }
    
    private static void aesEncrypt(byte[] in, int i, byte[] out, int j, Object key)
    {
        int[][] Ke = (int[][]) ((Object[]) key)[0];
        
        int ROUNDS = Ke.length - 1;
        int[] Ker = Ke[0];
        
        int t0 = (in[i++] << 24 | (in[i++] & 0xFF) << 16 | (in[i++] & 0xFF) << 8 | in[i++] & 0xFF) ^ Ker[0];
        
        int t1 = (in[i++] << 24 | (in[i++] & 0xFF) << 16 | (in[i++] & 0xFF) << 8 | in[i++] & 0xFF) ^ Ker[1];
        
        int t2 = (in[i++] << 24 | (in[i++] & 0xFF) << 16 | (in[i++] & 0xFF) << 8 | in[i++] & 0xFF) ^ Ker[2];
        
        int t3 = (in[i++] << 24 | (in[i++] & 0xFF) << 16 | (in[i++] & 0xFF) << 8 | in[i++] & 0xFF) ^ Ker[3];
        
        for (int r = 1; r < ROUNDS; r++)
        {
            Ker = Ke[r];
            int a0 = T1[t0 >>> 24] ^ T2[t1 >>> 16 & 0xFF] ^ T3[t2 >>> 8 & 0xFF] ^ T4[t3 & 0xFF] ^ Ker[0];
            
            int a1 = T1[t1 >>> 24] ^ T2[t2 >>> 16 & 0xFF] ^ T3[t3 >>> 8 & 0xFF] ^ T4[t0 & 0xFF] ^ Ker[1];
            
            int a2 = T1[t2 >>> 24] ^ T2[t3 >>> 16 & 0xFF] ^ T3[t0 >>> 8 & 0xFF] ^ T4[t1 & 0xFF] ^ Ker[2];
            
            int a3 = T1[t3 >>> 24] ^ T2[t0 >>> 16 & 0xFF] ^ T3[t1 >>> 8 & 0xFF] ^ T4[t2 & 0xFF] ^ Ker[3];
            
            t0 = a0;
            t1 = a1;
            t2 = a2;
            t3 = a3;
        }
        
        Ker = Ke[ROUNDS];
        int tt = Ker[0];
        out[j++] = (byte) (S[t0 >>> 24] ^ tt >>> 24);
        out[j++] = (byte) (S[t1 >>> 16 & 0xFF] ^ tt >>> 16);
        out[j++] = (byte) (S[t2 >>> 8 & 0xFF] ^ tt >>> 8);
        out[j++] = (byte) (S[t3 & 0xFF] ^ tt);
        tt = Ker[1];
        out[j++] = (byte) (S[t1 >>> 24] ^ tt >>> 24);
        out[j++] = (byte) (S[t2 >>> 16 & 0xFF] ^ tt >>> 16);
        out[j++] = (byte) (S[t3 >>> 8 & 0xFF] ^ tt >>> 8);
        out[j++] = (byte) (S[t0 & 0xFF] ^ tt);
        tt = Ker[2];
        out[j++] = (byte) (S[t2 >>> 24] ^ tt >>> 24);
        out[j++] = (byte) (S[t3 >>> 16 & 0xFF] ^ tt >>> 16);
        out[j++] = (byte) (S[t0 >>> 8 & 0xFF] ^ tt >>> 8);
        out[j++] = (byte) (S[t1 & 0xFF] ^ tt);
        tt = Ker[3];
        out[j++] = (byte) (S[t3 >>> 24] ^ tt >>> 24);
        out[j++] = (byte) (S[t0 >>> 16 & 0xFF] ^ tt >>> 16);
        out[j++] = (byte) (S[t1 >>> 8 & 0xFF] ^ tt >>> 8);
        out[j++] = (byte) (S[t2 & 0xFF] ^ tt);
    }
    
    private static void aesDecrypt(byte[] in, int i, byte[] out, int j, Object key)
    {
        int[][] Kd = (int[][]) ((Object[]) key)[1];
        
        int ROUNDS = Kd.length - 1;
        int[] Kdr = Kd[0];
        
        int t0 = (in[i++] << 24 | (in[i++] & 0xFF) << 16 | (in[i++] & 0xFF) << 8 | in[i++] & 0xFF) ^ Kdr[0];
        
        int t1 = (in[i++] << 24 | (in[i++] & 0xFF) << 16 | (in[i++] & 0xFF) << 8 | in[i++] & 0xFF) ^ Kdr[1];
        
        int t2 = (in[i++] << 24 | (in[i++] & 0xFF) << 16 | (in[i++] & 0xFF) << 8 | in[i++] & 0xFF) ^ Kdr[2];
        
        int t3 = (in[i++] << 24 | (in[i++] & 0xFF) << 16 | (in[i++] & 0xFF) << 8 | in[i++] & 0xFF) ^ Kdr[3];
        
        for (int r = 1; r < ROUNDS; r++)
        {
            Kdr = Kd[r];
            int a0 = T5[t0 >>> 24] ^ T6[t3 >>> 16 & 0xFF] ^ T7[t2 >>> 8 & 0xFF] ^ T8[t1 & 0xFF] ^ Kdr[0];
            
            int a1 = T5[t1 >>> 24] ^ T6[t0 >>> 16 & 0xFF] ^ T7[t3 >>> 8 & 0xFF] ^ T8[t2 & 0xFF] ^ Kdr[1];
            
            int a2 = T5[t2 >>> 24] ^ T6[t1 >>> 16 & 0xFF] ^ T7[t0 >>> 8 & 0xFF] ^ T8[t3 & 0xFF] ^ Kdr[2];
            
            int a3 = T5[t3 >>> 24] ^ T6[t2 >>> 16 & 0xFF] ^ T7[t1 >>> 8 & 0xFF] ^ T8[t0 & 0xFF] ^ Kdr[3];
            
            t0 = a0;
            t1 = a1;
            t2 = a2;
            t3 = a3;
        }
        
        Kdr = Kd[ROUNDS];
        int tt = Kdr[0];
        out[j++] = (byte) (Si[t0 >>> 24] ^ tt >>> 24);
        out[j++] = (byte) (Si[t3 >>> 16 & 0xFF] ^ tt >>> 16);
        out[j++] = (byte) (Si[t2 >>> 8 & 0xFF] ^ tt >>> 8);
        out[j++] = (byte) (Si[t1 & 0xFF] ^ tt);
        tt = Kdr[1];
        out[j++] = (byte) (Si[t1 >>> 24] ^ tt >>> 24);
        out[j++] = (byte) (Si[t0 >>> 16 & 0xFF] ^ tt >>> 16);
        out[j++] = (byte) (Si[t3 >>> 8 & 0xFF] ^ tt >>> 8);
        out[j++] = (byte) (Si[t2 & 0xFF] ^ tt);
        tt = Kdr[2];
        out[j++] = (byte) (Si[t2 >>> 24] ^ tt >>> 24);
        out[j++] = (byte) (Si[t1 >>> 16 & 0xFF] ^ tt >>> 16);
        out[j++] = (byte) (Si[t0 >>> 8 & 0xFF] ^ tt >>> 8);
        out[j++] = (byte) (Si[t3 & 0xFF] ^ tt);
        tt = Kdr[3];
        out[j++] = (byte) (Si[t3 >>> 24] ^ tt >>> 24);
        out[j++] = (byte) (Si[t2 >>> 16 & 0xFF] ^ tt >>> 16);
        out[j++] = (byte) (Si[t1 >>> 8 & 0xFF] ^ tt >>> 8);
        out[j++] = (byte) (Si[t0 & 0xFF] ^ tt);
    }
    
    private void encrypt(byte[] in, int i, byte[] out, int j, Object k, int bs)
    {
        if (bs != 16 && bs != 24 && bs != 32)
        {
            throw new IllegalArgumentException();
        }
        
        if (bs == this.currentBlockSize)
        {
            aesEncrypt(in, i, out, j, k);
        }
        else
        {
            rijndaelEncrypt(in, i, out, j, k, bs);
        }
    }
    
    private void decrypt(byte[] in, int i, byte[] out, int j, Object k, int bs)
    {
        if (bs != 16 && bs != 24 && bs != 32)
        {
            throw new IllegalArgumentException();
        }
        
        if (bs == this.currentBlockSize)
        {
            aesDecrypt(in, i, out, j, k);
        }
        else
        {
            rijndaelDecrypt(in, i, out, j, k, bs);
        }
    }
    
    public byte[] doEncrypt(byte[] plainText, int startlen, int plainlen)
    {
        try
        {
            int nMod = 0, nModTmp = 0, i = 0, nOutLen = 0;
            byte[] pTemp = null;
            
            pTemp = new byte[plainlen + 16];
            System.arraycopy(plainText, startlen, pTemp, 0, plainlen);
            
            nMod = plainlen % 16;
            nModTmp = 16 - nMod;
            for (i = 0; i < nModTmp; i++)
                pTemp[plainlen + i] = (byte) nModTmp;
            nOutLen = plainlen + nModTmp;
            
            return doAESCBCEncrypt(pTemp, nOutLen);
        }
        catch (Exception e)
        {
            
            return null;
        }
    }
    
    public byte[] doDecrypt(byte[] encryptedData)
    {
        try
        {
            if (encryptedData == null || this.currentKey == null || this.iv == null)
                return null;
            byte[] tmpPlainText = doAESCBCDecrypt(encryptedData, encryptedData.length);
            
            if (tmpPlainText == null)
                return null;
            int nOutLen = 0, padBit = tmpPlainText[encryptedData.length - 1];
            if (padBit < 1 || padBit > 16)
                return null;
            if (padBit == 16)
            {
                nOutLen = encryptedData.length - 16;
            }
            else
            {
                nOutLen = encryptedData.length - tmpPlainText[encryptedData.length - 1];
            }
            
            byte[] plainText = new byte[nOutLen];
            
            System.arraycopy(tmpPlainText, 0, plainText, 0, nOutLen);
            return plainText;
        }
        catch (Exception e)
        {
            
            return null;
        }
    }
    
    private byte[] doAESCBCEncrypt(byte[] in, long bytes)
    {
        byte[] tin = new byte[16], tout = new byte[16];
        long l = bytes;
        int i = 0, j = 0;
        byte[] out = new byte[(int) bytes];
        
        if (in == null || out == null || this.currentKey == null || bytes <= 0L)
        {
            return null;
        }
        System.arraycopy(this.iv, 0, tout, 0, 16);
        for (l -= 16L; l >= 0L; l -= 16L, j++)
        {
            System.arraycopy(in, j * 16, tin, 0, 16);
            for (i = 0; i < 16; i++)
            {
                tin[i] = (byte) (tin[i] ^ tout[i]);
            }
            encrypt(tin, 0, tout, 0, this.currentKey, 16);
            System.arraycopy(tout, 0, out, j * 16, 16);
        }
        
        if (l != -16L)
        {
            System.arraycopy(in, j * 16, tin, 0, (int) (l + 16L));
            for (i = 0; i < l + 16L; i++)
                tin[i] = (byte) (tin[i] ^ tout[i]);
            for (; i < 16; i++)
            {
                tin[i] = tout[i];
            }
            encrypt(tin, 0, tout, 16, this.currentKey, 16);
            System.arraycopy(tout, 0, out, j * 16, 16);
        }
        return out;
    }
    
    private byte[] doAESCBCDecrypt(byte[] in, long bytes)
    {
        byte[] xor = new byte[16], tin = new byte[16], tout = new byte[16];
        long l = bytes;
        int i = 0, j = 0;
        byte[] out = new byte[(int) bytes];
        
        if (in == null || out == null || this.currentKey == null || bytes <= 0L)
        {
            return null;
        }
        System.arraycopy(this.iv, 0, xor, 0, 16);
        for (l -= 16L; l >= 0L; l -= 16L, j++)
        {
            System.arraycopy(in, j * 16, tin, 0, 16);
            
            decrypt(tin, 0, tout, 0, this.currentKey, 16);
            for (i = 0; i < 16; i++)
            {
                tout[i] = (byte) (tout[i] ^ xor[i]);
                xor[i] = tin[i];
            }
            System.arraycopy(tout, 0, out, j * 16, 16);
        }
        if (l != -16L)
        {
            System.arraycopy(in, j * 16, tin, 0, 16);
            
            decrypt(tin, 0, tout, 0, this.currentKey, 16);
            for (i = 0; i < l + 16L; i++)
                tout[i] = (byte) (tout[i] ^ xor[i]);
            System.arraycopy(tout, 0, out, j * 16, (int) (l + 16L));
        }
        return out;
    }
}