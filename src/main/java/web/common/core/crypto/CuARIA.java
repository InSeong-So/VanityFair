package web.common.core.crypto;

public class CuARIA
{
    public static final int ENCALGID_ARIA_R12 = 12;
    
    public static final int ENCALGID_ARIA_R14 = 14;
    
    public static final int ENCALGID_ARIA_R16 = 16;
    
    public static final int ENCMOD_ECB = 0;
    
    public static final int ENCMOD_CBC = 1;
    
    public static final int ENCMOD_CFB = 2;
    
    public static final int ENCMOD_OFB = 3;
    
    public static final int ENCMOD_NO = 9;
    
    private static int nARIAENCALGIDRound = 12;
    
    private static byte[][] S = {
            { 99, 124, 119, 123, -14, 107, 111, -59, 48, 1, 103, 43, -2, -41, -85, 118, -54, -126, -55, 125, -6, 89, 71, -16, -83, -44, -94, -81, -100, -92, 114, -64, -73, -3, -109, 38, 54, 63, -9, -52, 52, -91, -27, -15, 113, -40, 49, 21, 4, -57, 35, -61, 24, -106, 5, -102, 7, 18, Byte.MIN_VALUE,
                    -30, -21, 39, -78, 117, 9, -125, 44, 26, 27, 110, 90, -96, 82, 59, -42, -77, 41, -29, 47, -124, 83, -47, 0, -19, 32, -4, -79, 91, 106, -53, -66, 57, 74, 76, 88, -49, -48, -17, -86, -5, 67, 77, 51, -123, 69, -7, 2, Byte.MAX_VALUE, 80, 60, -97, -88, 81, -93, 64, -113, -110, -99,
                    56, -11, -68, -74, -38, 33, 16, -1, -13, -46, -51, 12, 19, -20, 95, -105, 68, 23, -60, -89, 126, 61, 100, 93, 25, 115, 96, -127, 79, -36, 34, 42, -112, -120, 70, -18, -72, 20, -34, 94, 11, -37, -32, 50, 58, 10, 73, 6, 36, 92, -62, -45, -84, 98, -111, -107, -28, 121, -25, -56, 55,
                    109, -115, -43, 78, -87, 108, 86, -12, -22, 101, 122, -82, 8, -70, 120, 37, 46, 28, -90, -76, -58, -24, -35, 116, 31, 75, -67, -117, -118, 112, 62, -75, 102, 72, 3, -10, 14, 97, 53, 87, -71, -122, -63, 29, -98, -31, -8, -104, 17, 105, -39, -114, -108, -101, 30, -121, -23, -50,
                    85, 40, -33, -116, -95, -119, 13, -65, -26, 66, 104, 65, -103, 45, 15, -80, 84, -69, 22 },
            { -30, 78, 84, -4, -108, -62, 74, -52, 98, 13, 106, 70, 60, 77, -117, -47, 94, -6, 100, -53, -76, -105, -66, 43, -68, 119, 46, 3, -45, 25, 89, -63, 29, 6, 65, 107, 85, -16, -103, 105, -22, -100, 24, -82, 99, -33, -25, -69, 0, 115, 102, -5, -106, 76, -123, -28, 58, 9, 69, -86, 15, -18,
                    16, -21, 45, Byte.MAX_VALUE, -12, 41, -84, -49, -83, -111, -115, 120, -56, -107, -7, 47, -50, -51, 8, 122, -120, 56, 92, -125, 42, 40, 71, -37, -72, -57, -109, -92, 18, 83, -1, -121, 14, 49, 54, 33, 88, 72, 1, -114, 55, 116, 50, -54, -23, -79, -73, -85, 12, -41, -60, 86, 66, 38,
                    7, -104, 96, -39, -74, -71, 17, 64, -20, 32, -116, -67, -96, -55, -124, 4, 73, 35, -15, 79, 80, 31, 19, -36, -40, -64, -98, 87, -29, -61, 123, 101, 59, 2, -113, 62, -24, 37, -110, -27, 21, -35, -3, 23, -87, -65, -44, -102, 126, -59, 57, 103, -2, 118, -99, 67, -89, -31, -48, -11,
                    104, -14, 27, 52, 112, 5, -93, -118, -43, 121, -122, -88, 48, -58, 81, 75, 30, -90, 39, -10, 53, -46, 110, 36, 22, -126, 95, -38, -26, 117, -94, -17, 44, -78, 28, -97, 93, 111, Byte.MIN_VALUE, 10, 114, 68, -101, 108, -112, 11, 91, 51, 125, 90, 82, -13, 97, -95, -9, -80, -42, 63,
                    124, 109, -19, 20, -32, -91, 61, 34, -77, -8, -119, -34, 113, 26, -81, -70, -75, -127 },
            { 82, 9, 106, -43, 48, 54, -91, 56, -65, 64, -93, -98, -127, -13, -41, -5, 124, -29, 57, -126, -101, 47, -1, -121, 52, -114, 67, 68, -60, -34, -23, -53, 84, 123, -108, 50, -90, -62, 35, 61, -18, 76, -107, 11, 66, -6, -61, 78, 8, 46, -95, 102, 40, -39, 36, -78, 118, 91, -94, 73, 109,
                    -117, -47, 37, 114, -8, -10, 100, -122, 104, -104, 22, -44, -92, 92, -52, 93, 101, -74, -110, 108, 112, 72, 80, -3, -19, -71, -38, 94, 21, 70, 87, -89, -115, -99, -124, -112, -40, -85, 0, -116, -68, -45, 10, -9, -28, 88, 5, -72, -77, 69, 6, -48, 44, 30, -113, -54, 63, 15, 2, -63,
                    -81, -67, 3, 1, 19, -118, 107, 58, -111, 17, 65, 79, 103, -36, -22, -105, -14, -49, -50, -16, -76, -26, 115, -106, -84, 116, 34, -25, -83, 53, -123, -30, -7, 55, -24, 28, 117, -33, 110, 71, -15, 26, 113, 29, 41, -59, -119, 111, -73, 98, 14, -86, 24, -66, 27, -4, 86, 62, 75, -58,
                    -46, 121, 32, -102, -37, -64, -2, 120, -51, 90, -12, 31, -35, -88, 51, -120, 7, -57, 49, -79, 18, 16, 89, 39, Byte.MIN_VALUE, -20, 95, 96, 81, Byte.MAX_VALUE, -87, 25, -75, 74, 13, 45, -27, 122, -97, -109, -55, -100, -17, -96, -32, 59, 77, -82, 42, -11, -80, -56, -21, -69, 60,
                    -125, 83, -103, 97, 23, 43, 4, 126, -70, 119, -42, 38, -31, 105, 20, 99, 85, 33, 12, 125 },
            { 48, 104, -103, 27, -121, -71, 33, 120, 80, 57, -37, -31, 114, 9, 98, 60, 62, 126, 94, -114, -15, -96, -52, -93, 42, 29, -5, -74, -42, 32, -60, -115, -127, 101, -11, -119, -53, -99, 119, -58, 87, 67, 86, 23, -44, 64, 26, 77, -64, 99, 108, -29, -73, -56, 100, 106, 83, -86, 56, -104, 12,
                    -12, -101, -19, Byte.MAX_VALUE, 34, 118, -81, -35, 58, 11, 88, 103, -120, 6, -61, 53, 13, 1, -117, -116, -62, -26, 95, 2, 36, 117, -109, 102, 30, -27, -30, 84, -40, 16, -50, 122, -24, 8, 44, 18, -105, 50, -85, -76, 39, 10, 35, -33, -17, -54, -39, -72, -6, -36, 49, 107, -47, -83,
                    25, 73, -67, 81, -106, -18, -28, -88, 65, -38, -1, -51, 85, -122, 54, -66, 97, 82, -8, -69, 14, -126, 72, 105, -102, -32, 71, -98, 92, 4, 75, 52, 21, 121, 38, -89, -34, 41, -82, -110, -41, -124, -23, -46, -70, 93, -13, -59, -80, -65, -92, 59, 113, 68, 70, 43, -4, -21, 111, -43,
                    -10, 20, -2, 124, 112, 90, 125, -3, 47, 24, -125, 22, -91, -111, 31, 5, -107, 116, -87, -63, 91, 74, -123, 109, 19, 7, 79, 78, 69, -78, 15, -55, 28, -90, -68, -20, 115, -112, 123, -49, 89, -113, -95, -7, 45, -14, -79, 0, -108, 55, -97, -48, 46, -100, 110, 40, 63, Byte.MIN_VALUE,
                    -16, 61, -45, 37, -118, -75, -25, 66, -77, -57, -22, -9, 76, 17, 51, 3, -94, -84, 96 } };
    
    private static byte[] KRK_R12 = { 81, 124, -63, -73, 39, 34, 10, -108, -2, 19, -85, -24, -6, -102, 110, -32, 109, -79, 74, -52, -98, 33, -56, 32, -1, 40, -79, -43, -17, 93, -30, -80, -37, -110, 55, 29, 33, 38, -23, 112, 3, 36, -105, 117, 4, -24, -55, 14 };
    
    private static byte[] KRK_R14 = { 109, -79, 74, -52, -98, 33, -56, 32, -1, 40, -79, -43, -17, 93, -30, -80, -37, -110, 55, 29, 33, 38, -23, 112, 3, 36, -105, 117, 4, -24, -55, 14, 81, 124, -63, -73, 39, 34, 10, -108, -2, 19, -85, -24, -6, -102, 110, -32 };
    
    private static byte[] KRK_R16 = { -37, -110, 55, 29, 33, 38, -23, 112, 3, 36, -105, 117, 4, -24, -55, 14, 81, 124, -63, -73, 39, 34, 10, -108, -2, 19, -85, -24, -6, -102, 110, -32, 109, -79, 74, -52, -98, 33, -56, 32, -1, 40, -79, -43, -17, 93, -30, -80 };
    
    private static void DL(byte[] i, int ipos, byte[] o, int opos)
    {
        byte T = (byte) (i[ipos + 3] ^ i[ipos + 4] ^ i[ipos + 9] ^ i[ipos + 14]);
        o[opos + 0] = (byte) (i[ipos + 6] ^ i[ipos + 8] ^ i[ipos + 13] ^ T);
        o[opos + 5] = (byte) (i[ipos + 1] ^ i[ipos + 10] ^ i[ipos + 15] ^ T);
        o[opos + 11] = (byte) (i[ipos + 2] ^ i[ipos + 7] ^ i[ipos + 12] ^ T);
        o[opos + 14] = (byte) (i[ipos + 0] ^ i[ipos + 5] ^ i[ipos + 11] ^ T);
        T = (byte) (i[ipos + 2] ^ i[ipos + 5] ^ i[ipos + 8] ^ i[ipos + 15]);
        o[opos + 1] = (byte) (i[ipos + 7] ^ i[ipos + 9] ^ i[ipos + 12] ^ T);
        o[opos + 4] = (byte) (i[ipos + 0] ^ i[ipos + 11] ^ i[ipos + 14] ^ T);
        o[opos + 10] = (byte) (i[ipos + 3] ^ i[ipos + 6] ^ i[ipos + 13] ^ T);
        o[opos + 15] = (byte) (i[ipos + 1] ^ i[ipos + 4] ^ i[ipos + 10] ^ T);
        T = (byte) (i[ipos + 1] ^ i[ipos + 6] ^ i[ipos + 11] ^ i[ipos + 12]);
        o[opos + 2] = (byte) (i[ipos + 4] ^ i[ipos + 10] ^ i[ipos + 15] ^ T);
        o[opos + 7] = (byte) (i[ipos + 3] ^ i[ipos + 8] ^ i[ipos + 13] ^ T);
        o[opos + 9] = (byte) (i[ipos + 0] ^ i[ipos + 5] ^ i[ipos + 14] ^ T);
        o[opos + 12] = (byte) (i[ipos + 2] ^ i[ipos + 7] ^ i[ipos + 9] ^ T);
        T = (byte) (i[ipos + 0] ^ i[ipos + 7] ^ i[ipos + 10] ^ i[ipos + 13]);
        o[opos + 3] = (byte) (i[ipos + 5] ^ i[ipos + 11] ^ i[ipos + 14] ^ T);
        o[opos + 6] = (byte) (i[ipos + 2] ^ i[ipos + 9] ^ i[ipos + 12] ^ T);
        o[opos + 8] = (byte) (i[ipos + 1] ^ i[ipos + 4] ^ i[ipos + 15] ^ T);
        o[opos + 13] = (byte) (i[ipos + 3] ^ i[ipos + 6] ^ i[ipos + 8] ^ T);
    }
    
    private static void RotXOR(byte[] s, int n, byte[] t, int pos)
    {
        int q = n / 8;
        n %= 8;
        for (int i = 0; i < 16; i++)
        {
            int tmp = s[i];
            if (tmp < 0)
                tmp = 256 + tmp;
            t[pos + (q + i) % 16] = (byte) (t[pos + (q + i) % 16] ^ (byte) (tmp >> n));
            if (n != 0)
            {
                t[pos + (q + i + 1) % 16] = (byte) (t[pos + (q + i + 1) % 16] ^ (byte) (tmp << 8 - n));
            }
        }
    }
    
    public static void EncKeyGen(byte[] w0, byte[] e)
    {
        byte[] t = new byte[16], w1 = new byte[16], w2 = new byte[16], w3 = new byte[16], pKRK = null;
        
        switch (nARIAENCALGIDRound)
        {
            case 12:
                pKRK = KRK_R12;
                break;
            case 14:
                pKRK = KRK_R14;
                break;
            case 16:
                pKRK = KRK_R16;
                break;
            default:
                return;
        }
        int i;
        for (i = 0; i < 16; i++)
        {
            int tmppos = pKRK[i] ^ w0[i];
            if (tmppos < 0)
                tmppos = 256 + tmppos;
            t[i] = S[i % 4][tmppos];
        }
        DL(t, 0, w1, 0);
        
        if (nARIAENCALGIDRound == 14)
        {
            for (i = 0; i < 8; i++)
                w1[i] = (byte) (w1[i] ^ w0[16 + i]);
        }
        else if (nARIAENCALGIDRound == 16)
        {
            for (i = 0; i < 16; i++)
                w1[i] = (byte) (w1[i] ^ w0[16 + i]);
        }
        for (i = 0; i < 16; i++)
        {
            int tmppos = pKRK[i + 16] ^ w1[i];
            if (tmppos < 0)
                tmppos = 256 + tmppos;
            t[i] = S[(2 + i) % 4][tmppos];
        }
        DL(t, 0, w2, 0);
        for (i = 0; i < 16; i++)
        {
            w2[i] = (byte) (w2[i] ^ w0[i]);
        }
        for (i = 0; i < 16; i++)
        {
            int tmppos = pKRK[i + 32] ^ w2[i];
            if (tmppos < 0)
                tmppos = 256 + tmppos;
            t[i] = S[i % 4][tmppos];
        }
        DL(t, 0, w3, 0);
        for (i = 0; i < 16; i++)
        {
            w3[i] = (byte) (w3[i] ^ w1[i]);
        }
        for (i = 0; i < 16 * (nARIAENCALGIDRound + 1); i++)
        {
            e[i] = 0;
        }
        RotXOR(w0, 0, e, 0);
        RotXOR(w1, 19, e, 0);
        RotXOR(w1, 0, e, 16);
        RotXOR(w2, 19, e, 16);
        RotXOR(w2, 0, e, 32);
        RotXOR(w3, 19, e, 32);
        RotXOR(w3, 0, e, 48);
        RotXOR(w0, 19, e, 48);
        RotXOR(w0, 0, e, 64);
        RotXOR(w1, 31, e, 64);
        RotXOR(w1, 0, e, 80);
        RotXOR(w2, 31, e, 80);
        RotXOR(w2, 0, e, 96);
        RotXOR(w3, 31, e, 96);
        RotXOR(w3, 0, e, 112);
        RotXOR(w0, 31, e, 112);
        RotXOR(w0, 0, e, 128);
        RotXOR(w1, 67, e, 128);
        RotXOR(w1, 0, e, 144);
        RotXOR(w2, 67, e, 144);
        RotXOR(w2, 0, e, 160);
        RotXOR(w3, 67, e, 160);
        RotXOR(w3, 0, e, 176);
        RotXOR(w0, 67, e, 176);
        RotXOR(w0, 0, e, 192);
        RotXOR(w1, 97, e, 192);
        
        if (nARIAENCALGIDRound > 12)
        {
            RotXOR(w1, 0, e, 208);
            RotXOR(w2, 97, e, 208);
            RotXOR(w2, 0, e, 224);
            RotXOR(w3, 97, e, 224);
            if (nARIAENCALGIDRound > 14)
            {
                RotXOR(w3, 0, e, 240);
                RotXOR(w0, 97, e, 240);
                RotXOR(w0, 0, e, 256);
                RotXOR(w1, 109, e, 256);
            }
        }
    }
    
    public static void DecKeyGen(byte[] w0, byte[] d)
    {
        byte[] t = new byte[16];
        
        EncKeyGen(w0, d);
        int j;
        for (j = 0; j < 16; j++)
        {
            t[j] = d[j];
            d[j] = d[16 * nARIAENCALGIDRound + j];
            d[16 * nARIAENCALGIDRound + j] = t[j];
        }
        for (int i = 1; i <= nARIAENCALGIDRound / 2; i++)
        {
            DL(d, i * 16, t, 0);
            DL(d, (nARIAENCALGIDRound - i) * 16, d, i * 16);
            for (j = 0; j < 16; j++)
            {
                d[(nARIAENCALGIDRound - i) * 16 + j] = t[j];
            }
        }
    }
    
    public static void Crypt(byte[] p, byte[] e, byte[] c)
    {
        int epos = 0, tmppos = 0;
        byte[] t = new byte[16];
        int j;
        for (j = 0; j < 16; j++)
            c[j] = p[j];
        for (int i = 0; i < nARIAENCALGIDRound / 2; i++)
        {
            for (j = 0; j < 16; j++)
            {
                tmppos = e[epos + j] ^ c[j];
                if (tmppos < 0)
                    tmppos = 256 + tmppos;
                t[j] = S[j % 4][tmppos];
            }
            DL(t, 0, c, 0);
            epos += 16;
            for (j = 0; j < 16; j++)
            {
                tmppos = e[epos + j] ^ c[j];
                if (tmppos < 0)
                    tmppos = 256 + tmppos;
                t[j] = S[(2 + j) % 4][tmppos];
            }
            DL(t, 0, c, 0);
            epos += 16;
        }
        DL(c, 0, t, 0);
        for (j = 0; j < 16; j++)
        {
            c[j] = (byte) (e[epos + j] ^ t[j]);
        }
    }
    
    public static byte[] doARIAEncrypt(byte[] in, byte[] rkey, long bytes)
    {
        byte[] tin = new byte[16], tout = new byte[16];
        long l = bytes;
        int i = 0;
        byte[] out = new byte[(int) bytes];
        
        if (in == null || out == null || rkey == null || bytes <= 0L)
            return null;
        for (l -= 16L; l >= 0L; l -= 16L, i++)
        {
            System.arraycopy(in, i * 16, tin, 0, 16);
            Crypt(tin, rkey, tout);
            System.arraycopy(tout, 0, out, i * 16, 16);
        }
        if (l != -16L)
        {
            System.arraycopy(in, i * 16, tin, 0, (int) (l + 16L));
            for (i = (int) (l + 16L); i < 16; i++)
                tin[i] = 0;
            Crypt(tin, rkey, tout);
            System.arraycopy(tout, 0, out, i * 16, 16);
        }
        return out;
    }
    
    public static byte[] doARIADecrypt(byte[] in, byte[] rkey, long bytes)
    {
        byte[] tin = new byte[16], tout = new byte[16];
        long l = bytes;
        int i = 0;
        byte[] out = new byte[(int) bytes];
        
        if (in == null || out == null || rkey == null || bytes <= 0L)
            return null;
        for (l -= 16L; l >= 0L; l -= 16L, i++)
        {
            System.arraycopy(in, i * 16, tin, 0, 16);
            Crypt(tin, rkey, tout);
            System.arraycopy(tout, 0, out, i * 16, 16);
        }
        if (l != -16L)
        {
            System.arraycopy(in, i * 16, tin, 0, 16);
            Crypt(tin, rkey, tout);
            System.arraycopy(tout, 0, out, i * 16, (int) (l + 16L));
        }
        return out;
    }
    
    public static byte[] doARIACBCEncrypt(byte[] in, byte[] rkey, long bytes, byte[] iv)
    {
        byte[] tin = new byte[16], tout = new byte[16];
        long l = bytes;
        int i = 0, j = 0;
        byte[] out = new byte[(int) bytes];
        
        if (in == null || out == null || rkey == null || bytes <= 0L)
            return null;
        System.arraycopy(iv, 0, tout, 0, 16);
        for (l -= 16L; l >= 0L; l -= 16L, j++)
        {
            System.arraycopy(in, j * 16, tin, 0, 16);
            for (i = 0; i < 16; i++)
                tin[i] = (byte) (tin[i] ^ tout[i]);
            Crypt(tin, rkey, tout);
            System.arraycopy(tout, 0, out, j * 16, 16);
        }
        if (l != -16L)
        {
            System.arraycopy(in, j * 16, tin, 0, (int) (l + 16L));
            for (i = 0; i < l + 16L; i++)
                tin[i] = (byte) (tin[i] ^ tout[i]);
            for (; i < 16; i++)
                tin[i] = tout[i];
            Crypt(tin, rkey, tout);
            System.arraycopy(tout, 0, out, j * 16, 16);
        }
        return out;
    }
    
    public static byte[] doARIACBCDecrypt(byte[] in, byte[] rkey, long bytes, byte[] iv)
    {
        byte[] xor = new byte[16], tin = new byte[16], tout = new byte[16];
        long l = bytes;
        int i = 0, j = 0;
        byte[] out = new byte[(int) bytes];
        
        if (in == null || out == null || rkey == null || bytes <= 0L)
            return null;
        System.arraycopy(iv, 0, xor, 0, 16);
        for (l -= 16L; l >= 0L; l -= 16L, j++)
        {
            System.arraycopy(in, j * 16, tin, 0, 16);
            Crypt(tin, rkey, tout);
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
            Crypt(tin, rkey, tout);
            for (i = 0; i < l + 16L; i++)
                tout[i] = (byte) (tout[i] ^ xor[i]);
            System.arraycopy(tout, 0, out, j * 16, (int) (l + 16L));
        }
        return out;
    }
    
    public static byte[] doEncrypt(byte[] plainText, byte[] workingKey, byte[] byteIv, int startlen, int plainlen)
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
            
            return doARIACBCEncrypt(pTemp, workingKey, nOutLen, byteIv);
        }
        catch (Exception e)
        {
            
            return null;
        }
    }
    
    public static byte[] doDecrypt(byte[] encryptedData, byte[] workingKey, byte[] byteIv)
    {
        try
        {
            if (encryptedData == null || workingKey == null || byteIv == null)
            {
                return null;
            }
            byte[] tmpPlainText = doARIACBCDecrypt(encryptedData, workingKey, encryptedData.length, byteIv);
            
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
}
