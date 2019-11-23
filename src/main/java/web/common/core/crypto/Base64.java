package web.common.core.crypto;

public class Base64
{
    private static byte[] a;
    
    private static byte[] b;
    
    public static byte[] decode(String var0)
    {
        int var10000;
        byte var10001;
        byte[] var1;
        boolean var8;
        label60:
        {
            label74:
            {
                var8 = JCEUtil.a;
                var10000 = var0.charAt(var0.length() - 2);
                var10001 = 61;
                if (!var8)
                {
                    if (var10000 == 61)
                    {
                        var1 = new byte[(var0.length() / 4 - 1) * 3 + 1];
                        if (!var8)
                        {
                            break label60;
                        }
                        
                        AsymKeyParameter.b = !AsymKeyParameter.b;
                    }
                    
                    var10000 = var0.charAt(var0.length() - 1);
                    var10001 = 61;
                    if (var8)
                    {
                        break label74;
                    }
                }
                
                if (var10000 == var10001)
                {
                    var1 = new byte[(var0.length() / 4 - 1) * 3 + 2];
                    if (!var8)
                    {
                        break label60;
                    }
                }
                
                var10000 = var0.length() / 4;
                var10001 = 3;
            }
            
            var1 = new byte[var10000 * var10001];
        }
        
        int var6 = 0;
        int var7 = 0;
        byte var2;
        byte var3;
        byte var4;
        byte var5;
        if (var8)
        {
            var2 = a[var0.charAt(var6)];
            var3 = a[var0.charAt(var6 + 1)];
            var4 = a[var0.charAt(var6 + 2)];
            var5 = a[var0.charAt(var6 + 3)];
            var1[var7] = (byte) (var2 << 2 | var3 >> 4);
            var1[var7 + 1] = (byte) (var3 << 4 | var4 >> 2);
            var1[var7 + 2] = (byte) (var4 << 6 | var5);
            var6 += 4;
            var7 += 3;
        }
        
        while (true)
        {
            int var9;
            if (var6 >= var0.length() - 4)
            {
                var10000 = var0.charAt(var0.length() - 2);
                if (!var8)
                {
                    label76:
                    {
                        var10001 = 61;
                        if (!var8)
                        {
                            if (var10000 == 61)
                            {
                                var2 = a[var0.charAt(var0.length() - 4)];
                                var3 = a[var0.charAt(var0.length() - 3)];
                                var1[var1.length - 1] = (byte) (var2 << 2 | var3 >> 4);
                                if (!var8)
                                {
                                    return var1;
                                }
                            }
                            
                            var10000 = var0.charAt(var0.length() - 1);
                            if (var8)
                            {
                                break label76;
                            }
                            
                            var10001 = 61;
                        }
                        
                        if (var10000 == var10001)
                        {
                            var2 = a[var0.charAt(var0.length() - 4)];
                            var3 = a[var0.charAt(var0.length() - 3)];
                            var4 = a[var0.charAt(var0.length() - 2)];
                            var1[var1.length - 2] = (byte) (var2 << 2 | var3 >> 4);
                            var1[var1.length - 1] = (byte) (var3 << 4 | var4 >> 2);
                            if (!var8)
                            {
                                return var1;
                            }
                        }
                        
                        var10000 = a[var0.charAt(var0.length() - 4)];
                    }
                    
                    var9 = var10000;
                    var3 = a[var0.charAt(var0.length() - 3)];
                    var4 = a[var0.charAt(var0.length() - 2)];
                    var5 = a[var0.charAt(var0.length() - 1)];
                    var1[var1.length - 3] = (byte) (var9 << 2 | var3 >> 4);
                    var1[var1.length - 2] = (byte) (var3 << 4 | var4 >> 2);
                    var1[var1.length - 1] = (byte) (var4 << 6 | var5);
                    return var1;
                }
            }
            else
            {
                var10000 = a[var0.charAt(var6)];
            }
            
            var9 = var10000;
            var3 = a[var0.charAt(var6 + 1)];
            var4 = a[var0.charAt(var6 + 2)];
            var5 = a[var0.charAt(var6 + 3)];
            var1[var7] = (byte) (var9 << 2 | var3 >> 4);
            var1[var7 + 1] = (byte) (var3 << 4 | var4 >> 2);
            var1[var7 + 2] = (byte) (var4 << 6 | var5);
            var6 += 4;
            var7 += 3;
        }
    }
    
    public static byte[] decode(byte[] var0)
    {
        byte var10001;
        byte[] var1;
        boolean var8;
        label62:
        {
            int var10000;
            label76:
            {
                var8 = JCEUtil.a;
                var10000 = var0[var0.length - 2];
                var10001 = 61;
                if (!var8)
                {
                    if (var10000 == 61)
                    {
                        var1 = new byte[(var0.length / 4 - 1) * 3 + 1];
                        if (!var8)
                        {
                            break label62;
                        }
                    }
                    
                    var10000 = var0[var0.length - 1];
                    var10001 = 61;
                    if (var8)
                    {
                        break label76;
                    }
                }
                
                if (var10000 == var10001)
                {
                    var1 = new byte[(var0.length / 4 - 1) * 3 + 2];
                    if (!var8)
                    {
                        break label62;
                    }
                }
                
                var10000 = var0.length / 4;
                var10001 = 3;
            }
            
            var1 = new byte[var10000 * var10001];
        }
        
        int var6 = 0;
        int var7 = 0;
        byte var2;
        byte var3;
        byte var4;
        byte var5;
        if (var8)
        {
            var2 = a[var0[var6]];
            var3 = a[var0[var6 + 1]];
            var4 = a[var0[var6 + 2]];
            var5 = a[var0[var6 + 3]];
            var1[var7] = (byte) (var2 << 2 | var3 >> 4);
            var1[var7 + 1] = (byte) (var3 << 4 | var4 >> 2);
            var1[var7 + 2] = (byte) (var4 << 6 | var5);
            var6 += 4;
            var7 += 3;
        }
        
        while (true)
        {
            byte var9;
            if (var6 >= var0.length - 4)
            {
                var9 = var0[var0.length - 2];
                if (!var8)
                {
                    label48:
                    {
                        label78:
                        {
                            var10001 = 61;
                            if (!var8)
                            {
                                if (var9 == 61)
                                {
                                    var2 = a[var0[var0.length - 4]];
                                    var3 = a[var0[var0.length - 3]];
                                    var1[var1.length - 1] = (byte) (var2 << 2 | var3 >> 4);
                                    if (!var8)
                                    {
                                        break label48;
                                    }
                                }
                                
                                var9 = var0[var0.length - 1];
                                if (var8)
                                {
                                    break label78;
                                }
                                
                                var10001 = 61;
                            }
                            
                            if (var9 == var10001)
                            {
                                var2 = a[var0[var0.length - 4]];
                                var3 = a[var0[var0.length - 3]];
                                var4 = a[var0[var0.length - 2]];
                                var1[var1.length - 2] = (byte) (var2 << 2 | var3 >> 4);
                                var1[var1.length - 1] = (byte) (var3 << 4 | var4 >> 2);
                                if (!var8)
                                {
                                    break label48;
                                }
                            }
                            
                            var9 = a[var0[var0.length - 4]];
                        }
                        
                        var2 = var9;
                        var3 = a[var0[var0.length - 3]];
                        var4 = a[var0[var0.length - 2]];
                        var5 = a[var0[var0.length - 1]];
                        var1[var1.length - 3] = (byte) (var2 << 2 | var3 >> 4);
                        var1[var1.length - 2] = (byte) (var3 << 4 | var4 >> 2);
                        var1[var1.length - 1] = (byte) (var4 << 6 | var5);
                    }
                    
                    if (AsymKeyParameter.b)
                    {
                        JCEUtil.a = !var8;
                    }
                    
                    return var1;
                }
            }
            else
            {
                var9 = a[var0[var6]];
            }
            
            var2 = var9;
            var3 = a[var0[var6 + 1]];
            var4 = a[var0[var6 + 2]];
            var5 = a[var0[var6 + 3]];
            var1[var7] = (byte) (var2 << 2 | var3 >> 4);
            var1[var7 + 1] = (byte) (var3 << 4 | var4 >> 2);
            var1[var7 + 2] = (byte) (var4 << 6 | var5);
            var6 += 4;
            var7 += 3;
        }
    }
    
    public static byte[] encode(byte[] var0)
    {
        byte[] var1;
        boolean var11;
        int var10000;
        label48:
        {
            var11 = JCEUtil.a;
            var10000 = var0.length % 3;
            if (!var11)
            {
                if (var10000 == 0)
                {
                    var1 = new byte[4 * var0.length / 3];
                    if (!var11)
                    {
                        break label48;
                    }
                }
                
                var10000 = 4 * (var0.length / 3 + 1);
            }
            
            var1 = new byte[var10000];
        }
        
        int var2 = 0;
        int var3 = 0;
        int var4;
        int var5;
        int var6;
        int var7;
        int var8;
        int var9;
        int var10;
        if (var11)
        {
            var8 = var0[var2] & 255;
            var9 = var0[var2 + 1] & 255;
            var10 = var0[var2 + 2] & 255;
            var4 = var8 >>> 2 & 63;
            var5 = (var8 << 4 | var9 >>> 4) & 63;
            var6 = (var9 << 2 | var10 >>> 6) & 63;
            var7 = var10 & 63;
            var1[var3] = b[var4];
            var1[var3 + 1] = b[var5];
            var1[var3 + 2] = b[var6];
            var1[var3 + 3] = b[var7];
            var2 += 3;
            var3 += 4;
        }
        
        byte[] var12;
        while (true)
        {
            if (var2 >= var0.length / 3 * 3)
            {
                var12 = var0;
                if (var11)
                {
                    break;
                }
                
                var10000 = var0.length % 3;
                if (!var11)
                {
                    switch (var10000)
                    {
                        case 0:
                            if (!var11)
                            {
                                break;
                            }
                        case 1:
                            var7 = var0[var0.length - 1] & 255;
                            var4 = var7 >>> 2 & 63;
                            var5 = var7 << 4 & 63;
                            var1[var1.length - 4] = b[var4];
                            var1[var1.length - 3] = b[var5];
                            var1[var1.length - 2] = 61;
                            var1[var1.length - 1] = 61;
                            if (!var11)
                            {
                                break;
                            }
                        case 2:
                            var7 = var0[var0.length - 2] & 255;
                            var8 = var0[var0.length - 1] & 255;
                            var4 = var7 >>> 2 & 63;
                            var5 = (var7 << 4 | var8 >>> 4) & 63;
                            var6 = var8 << 2 & 63;
                            var1[var1.length - 4] = b[var4];
                            var1[var1.length - 3] = b[var5];
                            var1[var1.length - 2] = b[var6];
                            var1[var1.length - 1] = 61;
                    }
                    
                    var12 = var1;
                    break;
                }
            }
            else
            {
                var10000 = var0[var2] & 255;
            }
            
            var8 = var10000;
            var9 = var0[var2 + 1] & 255;
            var10 = var0[var2 + 2] & 255;
            var4 = var8 >>> 2 & 63;
            var5 = (var8 << 4 | var9 >>> 4) & 63;
            var6 = (var9 << 2 | var10 >>> 6) & 63;
            var7 = var10 & 63;
            var1[var3] = b[var4];
            var1[var3 + 1] = b[var5];
            var1[var3 + 2] = b[var6];
            var1[var3 + 3] = b[var7];
            var2 += 3;
            var3 += 4;
        }
        
        return var12;
    }
}