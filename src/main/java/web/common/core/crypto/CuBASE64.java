package web.common.core.crypto;

public class CuBASE64
{
    private static byte[] encodingTable = { 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53,
            54, 55, 56, 57, 43, 47 };
    
    public static byte[] encode(byte[] data)
    {
        int d1;
        int d2;
        int d3;
        int b1;
        int b2;
        int b3;
        int b4;
        byte[] bytes;
        if (data.length % 3 == 0)
        {
            bytes = new byte[4 * data.length / 3];
        }
        else
        {
            bytes = new byte[4 * (data.length / 3 + 1)];
        }
        for (int i = 0, j = 0; i < data.length / 3 * 3; i += 3, j += 4)
        {
            
            d1 = data[i] & 0xFF;
            d2 = data[i + 1] & 0xFF;
            d3 = data[i + 2] & 0xFF;
            
            b1 = d1 >>> 2 & 0x3F;
            b2 = (d1 << 4 | d2 >>> 4) & 0x3F;
            b3 = (d2 << 2 | d3 >>> 6) & 0x3F;
            b4 = d3 & 0x3F;
            
            bytes[j] = encodingTable[b1];
            bytes[j + 1] = encodingTable[b2];
            bytes[j + 2] = encodingTable[b3];
            bytes[j + 3] = encodingTable[b4];
        }
        
        switch (data.length % 3)
        {
            
            case 1:
                d1 = data[data.length - 1] & 0xFF;
                b1 = d1 >>> 2 & 0x3F;
                b2 = d1 << 4 & 0x3F;
                
                bytes[bytes.length - 4] = encodingTable[b1];
                bytes[bytes.length - 3] = encodingTable[b2];
                bytes[bytes.length - 2] = 61;
                bytes[bytes.length - 1] = 61;
                break;
            case 2:
                d1 = data[data.length - 2] & 0xFF;
                d2 = data[data.length - 1] & 0xFF;
                
                b1 = d1 >>> 2 & 0x3F;
                b2 = (d1 << 4 | d2 >>> 4) & 0x3F;
                b3 = d2 << 2 & 0x3F;
                
                bytes[bytes.length - 4] = encodingTable[b1];
                bytes[bytes.length - 3] = encodingTable[b2];
                bytes[bytes.length - 2] = encodingTable[b3];
                bytes[bytes.length - 1] = 61;
                break;
        }
        
        return bytes;
    }
    
    private static byte[] decodingTable = new byte[128];
    static
    {
        int i;
        for (i = 65; i <= 90; i++)
            decodingTable[i] = (byte) (i - 65);
        for (i = 97; i <= 122; i++)
            decodingTable[i] = (byte) (i - 97 + 26);
        for (i = 48; i <= 57; i++)
            decodingTable[i] = (byte) (i - 48 + 52);
        decodingTable[43] = 62;
        decodingTable[47] = 63;
    }
    
    public static byte[] decode(byte[] data)
    {
        byte[] bytes;
        if (data[data.length - 2] == 61)
        {
            bytes = new byte[(data.length / 4 - 1) * 3 + 1];
        }
        else if (data[data.length - 1] == 61)
        {
            bytes = new byte[(data.length / 4 - 1) * 3 + 2];
        }
        else
        {
            bytes = new byte[data.length / 4 * 3];
        }
        for (int i = 0, j = 0; i < data.length - 4; i += 4, j += 3)
        {
            byte b1 = decodingTable[data[i]];
            byte b2 = decodingTable[data[i + 1]];
            byte b3 = decodingTable[data[i + 2]];
            byte b4 = decodingTable[data[i + 3]];
            
            bytes[j] = (byte) (b1 << 2 | b2 >> 4);
            bytes[j + 1] = (byte) (b2 << 4 | b3 >> 2);
            bytes[j + 2] = (byte) (b3 << 6 | b4);
        }
        
        if (data[data.length - 2] == 61)
        {
            byte b1 = decodingTable[data[data.length - 4]];
            byte b2 = decodingTable[data[data.length - 3]];
            
            bytes[bytes.length - 1] = (byte) (b1 << 2 | b2 >> 4);
        }
        else if (data[data.length - 1] == 61)
        {
            byte b1 = decodingTable[data[data.length - 4]];
            byte b2 = decodingTable[data[data.length - 3]];
            byte b3 = decodingTable[data[data.length - 2]];
            
            bytes[bytes.length - 2] = (byte) (b1 << 2 | b2 >> 4);
            bytes[bytes.length - 1] = (byte) (b2 << 4 | b3 >> 2);
        }
        else
        {
            byte b1 = decodingTable[data[data.length - 4]];
            byte b2 = decodingTable[data[data.length - 3]];
            byte b3 = decodingTable[data[data.length - 2]];
            byte b4 = decodingTable[data[data.length - 1]];
            
            bytes[bytes.length - 3] = (byte) (b1 << 2 | b2 >> 4);
            bytes[bytes.length - 2] = (byte) (b2 << 4 | b3 >> 2);
            bytes[bytes.length - 1] = (byte) (b3 << 6 | b4);
        }
        
        return bytes;
    }
    
    public static void main(String[] args)
    {
        byte[] b = "1234567890123456".getBytes();
        byte[] b64 = encode(b);
        String s = new String(b64);
        
        System.out.println(s.length() + "\t" + s);
    }
}