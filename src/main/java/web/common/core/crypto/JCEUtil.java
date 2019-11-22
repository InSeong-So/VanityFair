package web.common.core.crypto;

import java.security.Provider;
import java.security.Security;

public class JCEUtil
{
    static boolean a;
    
    //    public static byte[] BigToBytes(final BigInteger bigInteger)
    //    {
    //        final byte[] byteArray = bigInteger.toByteArray();
    //        if (bigInteger.bitLength() % 8 != 0)
    //        {
    //            return byteArray;
    //        }
    //        final byte[] array = new byte[bigInteger.bitLength() / 8];
    //        System.arraycopy(byteArray, 1, array, 0, array.length);
    //        return array;
    //    }
    //    
    //    public static synchronized boolean changePrivateKey(final String s, final String s2, final char[] array, final char[] array2, final SecureRandom secureRandom, final int n)
    //    {
    //        final boolean a = JCEUtil.a;
    //        final byte[] array3 = new byte[8];
    //        secureRandom.nextBytes(array3);
    //        try
    //        {
    //            final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    //            boolean b = n != 0;
    //            boolean b2 = n != 0;
    //            if (!a)
    //            {
    //                if (n == 0)
    //                {
    //                    final FileInputStream fileInputStream = new FileInputStream(s + "/" + s2 + b("4\u001bb4gr^d\u0001l"));
    //                    final PrivateKey a2 = a(array, fileInputStream);
    //                    fileInputStream.close();
    //                    if (!a)
    //                    {
    //                        if (a2 == null)
    //                        {
    //                            return false;
    //                        }
    //                        PKCS5.PKCS5S1Encode(PKCS5.SEEDV1, array2, array3, 1024, a2.getEncoded(), (OutputStream) byteArrayOutputStream);
    //                    }
    //                    final FileOutputStream fileOutputStream = new FileOutputStream(s + "/" + s2 + b("4\u001bb4gr^d\u0001l"));
    //                    fileOutputStream.write(byteArrayOutputStream.toByteArray());
    //                    fileOutputStream.close();
    //                    byteArrayOutputStream.reset();
    //                    if (!a)
    //                    {
    //                        return true;
    //                    }
    //                }
    //                b = (n != 0);
    //                b2 = (n != 0);
    //            }
    //            final boolean b3 = true;
    //            if (!a)
    //            {
    //                if (b2 == b3)
    //                {
    //                    final FileInputStream fileInputStream2 = new FileInputStream(s + "/" + s2 + b("4\u0003f\u0003{K\u0002fJ~~\t"));
    //                    final PrivateKey a3 = a(array, fileInputStream2);
    //                    fileInputStream2.close();
    //                    if (!a)
    //                    {
    //                        if (a3 == null)
    //                        {
    //                            return false;
    //                        }
    //                        PKCS5.PKCS5S1Encode(PKCS5.SEEDV1, array2, array3, 1024, a3.getEncoded(), (OutputStream) byteArrayOutputStream);
    //                    }
    //                    final FileOutputStream fileOutputStream2 = new FileOutputStream(s + "/" + s2 + b("4\u0003f\u0003{K\u0002fJ~~\t"));
    //                    fileOutputStream2.write(byteArrayOutputStream.toByteArray());
    //                    fileOutputStream2.close();
    //                    byteArrayOutputStream.reset();
    //                    if (!a)
    //                    {
    //                        return true;
    //                    }
    //                }
    //                b = (n != 0);
    //            }
    //            if (b == b3)
    //            {
    //                final FileInputStream fileInputStream3 = new FileInputStream(s + "/" + s2 + b("4\u001bb4gr^d\u0001l"));
    //                final PrivateKey a4 = a(array, fileInputStream3);
    //                fileInputStream3.close();
    //                if (!a)
    //                {
    //                    if (a4 == null)
    //                    {
    //                        return false;
    //                    }
    //                    PKCS5.PKCS5S1Encode(PKCS5.SEEDV1, array2, array3, 1024, a4.getEncoded(), (OutputStream) byteArrayOutputStream);
    //                }
    //                final FileOutputStream fileOutputStream3 = new FileOutputStream(s + "/" + s2 + b("4\u001bb4gr^d\u0001l"));
    //                fileOutputStream3.write(byteArrayOutputStream.toByteArray());
    //                fileOutputStream3.close();
    //                byteArrayOutputStream.reset();
    //                secureRandom.nextBytes(array3);
    //                final FileInputStream fileInputStream4 = new FileInputStream(s + "/" + s2 + b("4\u0003f\u0003{K\u0002fJ~~\t"));
    //                final PrivateKey a5 = a(array, fileInputStream4);
    //                fileInputStream4.close();
    //                if (!a)
    //                {
    //                    if (a5 == null)
    //                    {
    //                        return false;
    //                    }
    //                    PKCS5.PKCS5S1Encode(PKCS5.SEEDV1, array2, array3, 1024, a5.getEncoded(), (OutputStream) byteArrayOutputStream);
    //                    final FileOutputStream fileOutputStream4 = new FileOutputStream(s + "/" + s2 + b("4\u0003f\u0003{K\u0002fJ~~\t"));
    //                    fileOutputStream4.write(byteArrayOutputStream.toByteArray());
    //                    fileOutputStream4.close();
    //                    byteArrayOutputStream.reset();
    //                }
    //                if (!a)
    //                {
    //                    return true;
    //                }
    //            }
    //            throw new Exception(b("U\u001fa\u00015K\u0002f\u0012to\u0015D\u0001l;$v\u0014p5"));
    //        }
    //        catch (Exception ex)
    //        {
    //            System.out.println(b("K\u0002f\u0012to\u0015D\u0001l;\u0013g\u0005{|\u0015kDsz\u0019cD/;") + ex.toString());
    //            return false;
    //        }
    //    }
    //    
    //    public static boolean contains(final String other, final String other2) {
    //        final boolean a = JCEUtil.a;
    //        final int length = other.length();
    //        final int length2 = other2.length();
    //        final int n = length;
    //        if (!a && n < length2) {
    //            int ooffset = 0;
    //            while (true) {
    //                while (true) {
    //                    Label_0056: {
    //                        if (!a) {
    //                            break Label_0056;
    //                        }
    //                        final boolean regionMatches = other.regionMatches(true, 0, other2, ooffset, length);
    //                        if (a || regionMatches) {
    //                            return regionMatches;
    //                        }
    //                        ++ooffset;
    //                    }
    //                    if (ooffset <= length2 - length) {
    //                        continue;
    //                    }
    //                    break;
    //                }
    //                if (!a) {
    //                    goto Label_0070;
    //                }
    //                continue;
    //            }
    //        }
    //        else {
    //            int ooffset2 = n;
    //            while (true) {
    //                while (true) {
    //                    Label_0100: {
    //                        if (!a) {
    //                            break Label_0100;
    //                        }
    //                        final boolean regionMatches2;
    //                        boolean b = regionMatches2 = other2.regionMatches((boolean)(1 != 0), 0, other, ooffset2, length2);
    //                        if (a) {
    //                            return b;
    //                        }
    //                        if (!regionMatches2) {
    //                            ++ooffset2;
    //                            break Label_0100;
    //                        }
    //                        b = true;
    //                        return b;
    //                    }
    //                    if (ooffset2 <= length2 - length) {
    //                        continue;
    //                    }
    //                    break;
    //                }
    //                boolean regionMatches2;
    //                final boolean b2 = regionMatches2 = false;
    //                if (!a) {
    //                    return b2;
    //                }
    //                continue;
    //            }
    //        }
    //    }
    //    
    //    public static X509Certificate getCert(final InputStream inStream)
    //    {
    //        try
    //        {
    //            return (X509Certificate) CertificateFactory.getInstance(b("C^:T,"), b("P\u0003f\u0003{")).generateCertificate(inStream);
    //        }
    //        catch (Exception ex)
    //        {
    //            if (JCEUtil.a)
    //            {
    //            }
    //            return null;
    //        }
    //    }
    //    
    //    public static String getHostid()
    //    {
    //        final boolean a = JCEUtil.a;
    //        try
    //        {
    //            InputStream inputStream;
    //            try
    //            {
    //                inputStream = Runtime.getRuntime().exec(b("n\u001en\tp;]f")).getInputStream();
    //            }
    //            catch (Exception ex2)
    //            {
    //                try
    //                {
    //                    inputStream = Runtime.getRuntime().exec(b("s\u001f|\u0010|\u007f")).getInputStream();
    //                }
    //                catch (Exception ex3)
    //                {
    //                    try
    //                    {
    //                        inputStream = new FileInputStream(b("4\u0004b\u0014:s\u001f|\u0010|\u007f"));
    //                    }
    //                    catch (Exception ex)
    //                    {
    //                        throw new Exception(b("X\u0011aCa;\u0016f\nq;\u0018`\u0017ar\u0014/^5") + ex.toString());
    //                    }
    //                    if (a)
    //                    {
    //                    }
    //                }
    //                if (a)
    //                {
    //                }
    //            }
    //            final byte[] array = new byte[2000];
    //            int read = 0;
    //            int n = 0;
    //            Label_0134_Outer: while (true)
    //            {
    //                if (!a)
    //                {
    //                    break Label_0143;
    //                }
    //                while (true)
    //                {
    //                    array[n] = (byte) read;
    //                    ++n;
    //                    if ((read = inputStream.read()) != -1)
    //                    {
    //                        continue;
    //                    }
    //                    break;
    //                }
    //                if (a)
    //                {
    //                    continue Label_0134_Outer;
    //                }
    //                break;
    //            }
    //            final int n2 = n;
    //            if (!a && n2 == 0)
    //            {
    //                throw new Exception(b("X\u0011aCa;\u0016`\u0011{\u007fPg\u000bfo\u0019kJ"));
    //            }
    //            final byte[] bytes = new byte[n2];
    //            System.arraycopy(array, 0, bytes, 0, n);
    //            return new StringTokenizer(new String(bytes), b("\u0016z")).nextToken().trim();
    //        }
    //        catch (Exception ex4)
    //        {
    //            return null;
    //        }
    //    }
    
    //    private static PrivateKey a(final char[] array, final InputStream inputStream)
    //    {
    //        try
    //        {
    //            final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    //            return PKCS5.PKCS5DecodeForPrivateKey(inputStream, array);
    //        }
    //        catch (Exception ex)
    //        {
    //            System.out.println(b("_\u0015l\u0016lk\u0004/!gi\u001f}D/;") + ex.toString());
    //            if (JCEUtil.a)
    //            {
    //            }
    //            return null;
    //        }
    //    }
    
    //    public static String getSystemHostDN(final String pathname)
    //    {
    //        final boolean a = JCEUtil.a;
    //        int n = 0;
    //        String name = null;
    //        try
    //        {
    //            final File file = new File(pathname);
    //            final File[] listFiles = file.listFiles();
    //            if (listFiles == null)
    //            {
    //                throw new Exception(b("X\u0011aD{t\u0004/\u0002|u\u0014/") + file.getAbsolutePath() + ".");
    //            }
    //            int n2 = 0;
    //            Label_0236: while (true)
    //            {
    //                Label_0228:
    //                {
    //                    if (!a)
    //                    {
    //                        break Label_0228;
    //                    }
    //                    System.out.println(listFiles[n2].getAbsolutePath());
    //                    final boolean includedHostid = isIncludedHostid(listFiles[n2].getName());
    //                    Label_0225:
    //                    {
    //                        File file2 = null;
    //                        Label_0151:
    //                        {
    //                            if (!a)
    //                            {
    //                                if (!includedHostid && !a)
    //                                {
    //                                    break Label_0225;
    //                                }
    //                                file2 = listFiles[n2];
    //                                if (a)
    //                                {
    //                                    break Label_0151;
    //                                }
    //                                file2.isDirectory();
    //                            }
    //                            if (!includedHostid && !a)
    //                            {
    //                                break Label_0225;
    //                            }
    //                            final File file3 = new File(listFiles[n2], b("h\u0019h\nV~\u0002{Jq~\u0002"));
    //                        }
    //                        final File file4 = file2;
    //                        if (file4.exists() || a)
    //                        {
    //                            final FileInputStream fileInputStream = new FileInputStream(file4);
    //                            final X509Certificate cert = getCert(fileInputStream);
    //                            fileInputStream.close();
    //                            final boolean includedHostid2 = isIncludedHostid(cert.getSubjectDN().getName());
    //                            if (a || includedHostid2)
    //                            {
    //                                n = (includedHostid2 ? 1 : 0);
    //                                name = listFiles[n2].getName();
    //                                break Label_0236;
    //                            }
    //                        }
    //                    }
    //                    ++n2;
    //                }
    //                if (n2 < listFiles.length)
    //                {
    //                    continue;
    //                }
    //                break;
    //            }
    //            if (n == 0)
    //            {
    //                throw new Exception(b("X\u0011aD{t\u0004/\u0002|u\u0014/\fzh\u0004K*;"));
    //            }
    //            return name;
    //        }
    //        catch (Exception ex)
    //        {
    //            return null;
    //        }
    //    }
    
    //    public static String getSystemHostDN()
    //    {
    //        final boolean a = JCEUtil.a;
    //        int n = 0;
    //        String name = null;
    //        try
    //        {
    //            final File file = new File(b("4\u001f\u007f\u0010:~\u0011fK^H9H*:U3N7||\u001e 1F^\""));
    //            final File[] listFiles = file.listFiles();
    //            if (listFiles == null)
    //            {
    //                throw new Exception(b("X\u0011aD{t\u0004/\u0002|u\u0014/") + file.getAbsolutePath() + ".");
    //            }
    //            int n2 = 0;
    //            Label_0232: while (true)
    //            {
    //                Label_0225:
    //                {
    //                    if (!a)
    //                    {
    //                        break Label_0225;
    //                    }
    //                    System.out.println(listFiles[n2].getAbsolutePath());
    //                    final boolean includedHostid = isIncludedHostid(listFiles[n2].getName());
    //                    Label_0222:
    //                    {
    //                        File file2 = null;
    //                        Label_0149:
    //                        {
    //                            if (!a)
    //                            {
    //                                if (!includedHostid && !a)
    //                                {
    //                                    break Label_0222;
    //                                }
    //                                file2 = listFiles[n2];
    //                                if (a)
    //                                {
    //                                    break Label_0149;
    //                                }
    //                                file2.isDirectory();
    //                            }
    //                            if (!includedHostid && !a)
    //                            {
    //                                break Label_0222;
    //                            }
    //                            final File file3 = new File(listFiles[n2], b("h\u0019h\nV~\u0002{Jq~\u0002"));
    //                        }
    //                        final File file4 = file2;
    //                        if (file4.exists() || a)
    //                        {
    //                            final FileInputStream fileInputStream = new FileInputStream(file4);
    //                            final X509Certificate cert = getCert(fileInputStream);
    //                            fileInputStream.close();
    //                            final boolean includedHostid2 = isIncludedHostid(cert.getSubjectDN().getName());
    //                            if (a || includedHostid2)
    //                            {
    //                                n = (includedHostid2 ? 1 : 0);
    //                                name = listFiles[n2].getName();
    //                                break Label_0232;
    //                            }
    //                        }
    //                    }
    //                    ++n2;
    //                }
    //                if (n2 < listFiles.length)
    //                {
    //                    continue;
    //                }
    //                break;
    //            }
    //            if (n == 0)
    //            {
    //                throw new Exception(b("X\u0011aD{t\u0004/\u0002|u\u0014/\fzh\u0004K*;"));
    //            }
    //            return name;
    //        }
    //        catch (Exception ex)
    //        {
    //            return null;
    //        }
    //    }
    
    //    public static byte[] getUsageBytes(final int n)
    //    {
    //        return new byte[] { (byte) (n & 0xFF), (byte) (n >> 8 & 0xFF) };
    //    }
    
    public static void initProvider()
    {
        final boolean a = JCEUtil.a;
        int n = 0;
        final Provider[] providers = Security.getProviders();
        int n2 = 0;
        while (true)
        {
            Label_0053: while (true)
            {
                Label_0047:
                {
                    if (!a)
                    {
                        break Label_0047;
                    }
                    int n3 = (providers[n2].getName().equals(b("P\u0003f\u0003{"))) ? 1 : 0;
                    Label_0038:
                    {
                        if (a)
                        {
                            break Label_0038;
                        }
                        //                            int n4;
                        //                            if (n4 == 0)
                        //                            {
                        //                                break Label_0043;
                        //                            }
                        n3 = 1;
                    }
                    n = n3;
                    if (!a)
                    {
                        break Label_0053;
                    }
                    ++n2;
                }
                if (n2 < providers.length)
                {
                    continue;
                }
                break;
            }
            final int n4 = n;
            if (!a)
            {
                if (!a)
                {
                    if (n4 == 0)
                    {
                        Security.addProvider((Provider) new KSignProvider());
                    }
                }
                return;
            }
            continue;
        }
    }
    
    //    private static void a(final String pathname)
    //    {
    //        final boolean a = JCEUtil.a;
    //        int n = 0;
    //        boolean b = false;
    //        final Provider[] providers = Security.getProviders();
    //        int n2 = 0;
    //        Label_0165_Outer: while (true)
    //        {
    //            Label_0062: while (true)
    //            {
    //                Label_0055:
    //                {
    //                    if (!a)
    //                    {
    //                        break Label_0055;
    //                    }
    //                    final boolean equals;
    //                    int n3 = (equals = providers[n2].getName().equals(b("P\u0003f\u0003{"))) ? 1 : 0;
    //                    Label_0051:
    //                    {
    //                        Label_0045:
    //                        {
    //                            if (a)
    //                            {
    //                                break Label_0045;
    //                            }
    //                            final int n4;
    //                            if (n4 == 0)
    //                            {
    //                                break Label_0051;
    //                            }
    //                            n3 = 1;
    //                        }
    //                        n = n3;
    //                        if (!a)
    //                        {
    //                            break Label_0062;
    //                        }
    //                    }
    //                    ++n2;
    //                }
    //                if (n2 < providers.length)
    //                {
    //                    continue;
    //                }
    //                break;
    //            }
    //            final int n4 = n;
    //            if (!a)
    //            {
    //                if (n4 == 0)
    //                {
    //                    try
    //                    {
    //                        final File file = new File(pathname);
    //                        final File[] listFiles = file.listFiles();
    //                        if (listFiles == null)
    //                        {
    //                            throw new Exception(b("X\u0011aD{t\u0004/\u0002|u\u0014/") + file.getAbsolutePath() + ".");
    //                        }
    //                        int n5 = 0;
    //                        boolean b2 = false;
    //                        while (true)
    //                        {
    //                            Label_0305: while (true)
    //                            {
    //                                Label_0297:
    //                                {
    //                                    if (!a)
    //                                    {
    //                                        break Label_0297;
    //                                    }
    //                                    System.out.println(listFiles[n5].getAbsolutePath());
    //                                    final boolean includedHostid;
    //                                    boolean directory = includedHostid = isIncludedHostid(listFiles[n5].getName());
    //                                    Label_0294:
    //                                    {
    //                                        File file2 = null;
    //                                        Label_0216:
    //                                        {
    //                                            if (!a)
    //                                            {
    //                                                if (!b2 && !a)
    //                                                {
    //                                                    break Label_0294;
    //                                                }
    //                                                file2 = listFiles[n5];
    //                                                if (a)
    //                                                {
    //                                                    break Label_0216;
    //                                                }
    //                                                directory = file2.isDirectory();
    //                                            }
    //                                            if (!directory && !a)
    //                                            {
    //                                                break Label_0294;
    //                                            }
    //                                            final File file3 = new File(listFiles[n5], b("h\u0019h\nV~\u0002{Jq~\u0002"));
    //                                        }
    //                                        final File file4 = file2;
    //                                        if (file4.exists() || a)
    //                                        {
    //                                            final FileInputStream fileInputStream = new FileInputStream(file4);
    //                                            final X509Certificate cert = getCert(fileInputStream);
    //                                            fileInputStream.close();
    //                                            final boolean includedHostid2 = isIncludedHostid(cert.getSubjectDN().getName());
    //                                            Label_0293:
    //                                            {
    //                                                if (!a)
    //                                                {
    //                                                    if (!includedHostid2)
    //                                                    {
    //                                                        break Label_0293;
    //                                                    }
    //                                                    Security.addProvider((Provider) new KSignProvider());
    //                                                }
    //                                                b = includedHostid2;
    //                                                if (!a)
    //                                                {
    //                                                    break Label_0305;
    //                                                }
    //                                            }
    //                                        }
    //                                    }
    //                                    ++n5;
    //                                }
    //                                if (n5 < listFiles.length)
    //                                {
    //                                    continue Label_0165_Outer;
    //                                }
    //                                break;
    //                            }
    //                            boolean directory;
    //                            b2 = (directory = b);
    //                            if (a)
    //                            {
    //                                continue;
    //                            }
    //                            break;
    //                        }
    //                        if (!b2)
    //                        {
    //                            throw new Exception(b("X\u0011aD{t\u0004/\u0002|u\u0014/\fzh\u0004f\u00005}\u0002`\t5x\u0015}\u0010;"));
    //                        }
    //                    }
    //                    catch (Exception ex)
    //                    {
    //                        System.out.println(ex.toString());
    //                        System.exit(-1);
    //                        if (a)
    //                        {
    //                        }
    //                    }
    //                }
    //                return;
    //            }
    //            continue;
    //        }
    //    }
    
    //    private static void a()
    //    {
    //        final boolean a = JCEUtil.a;
    //        int n = 0;
    //        boolean b = false;
    //        final Provider[] providers = Security.getProviders();
    //        int n2 = 0;
    //        Label_0166_Outer: while (true)
    //        {
    //            Label_0059: while (true)
    //            {
    //                Label_0053:
    //                {
    //                    if (!a)
    //                    {
    //                        break Label_0053;
    //                    }
    //                    final boolean equals;
    //                    int n3 = (equals = providers[n2].getName().equals(b("P\u0003f\u0003{"))) ? 1 : 0;
    //                    Label_0049:
    //                    {
    //                        Label_0043:
    //                        {
    //                            if (a)
    //                            {
    //                                break Label_0043;
    //                            }
    //                            final int n4;
    //                            if (n4 == 0)
    //                            {
    //                                break Label_0049;
    //                            }
    //                            n3 = 1;
    //                        }
    //                        n = n3;
    //                        if (!a)
    //                        {
    //                            break Label_0059;
    //                        }
    //                    }
    //                    ++n2;
    //                }
    //                if (n2 < providers.length)
    //                {
    //                    continue;
    //                }
    //                break;
    //            }
    //            final int n4 = n;
    //            if (!a)
    //            {
    //                if (n4 == 0)
    //                {
    //                    try
    //                    {
    //                        final File file = new File(b("4\u001f\u007f\u0010:~\u0011fK^H9H*:U3N7||\u001e 1F^\""));
    //                        final File[] listFiles = file.listFiles();
    //                        if (listFiles == null)
    //                        {
    //                            throw new Exception(b("X\u0011aD{t\u0004/\u0002|u\u0014/") + file.getAbsolutePath() + ".");
    //                        }
    //                        int n5 = 0;
    //                        boolean b2 = false;
    //                        while (true)
    //                        {
    //                            Label_0306: while (true)
    //                            {
    //                                Label_0298:
    //                                {
    //                                    if (!a)
    //                                    {
    //                                        break Label_0298;
    //                                    }
    //                                    System.out.println(listFiles[n5].getAbsolutePath());
    //                                    final boolean includedHostid;
    //                                    boolean directory = includedHostid = isIncludedHostid(listFiles[n5].getName());
    //                                    Label_0295:
    //                                    {
    //                                        File file2 = null;
    //                                        Label_0217:
    //                                        {
    //                                            if (!a)
    //                                            {
    //                                                if (!b2 && !a)
    //                                                {
    //                                                    break Label_0295;
    //                                                }
    //                                                file2 = listFiles[n5];
    //                                                if (a)
    //                                                {
    //                                                    break Label_0217;
    //                                                }
    //                                                directory = file2.isDirectory();
    //                                            }
    //                                            if (!directory && !a)
    //                                            {
    //                                                break Label_0295;
    //                                            }
    //                                            final File file3 = new File(listFiles[n5], b("h\u0019h\nV~\u0002{Jq~\u0002"));
    //                                        }
    //                                        final File file4 = file2;
    //                                        if (file4.exists() || a)
    //                                        {
    //                                            final FileInputStream fileInputStream = new FileInputStream(file4);
    //                                            final X509Certificate cert = getCert(fileInputStream);
    //                                            fileInputStream.close();
    //                                            final boolean includedHostid2 = isIncludedHostid(cert.getSubjectDN().getName());
    //                                            Label_0294:
    //                                            {
    //                                                if (!a)
    //                                                {
    //                                                    if (!includedHostid2)
    //                                                    {
    //                                                        break Label_0294;
    //                                                    }
    //                                                    Security.addProvider((Provider) new KSignProvider());
    //                                                }
    //                                                b = includedHostid2;
    //                                                if (!a)
    //                                                {
    //                                                    break Label_0306;
    //                                                }
    //                                            }
    //                                        }
    //                                    }
    //                                    ++n5;
    //                                }
    //                                if (n5 < listFiles.length)
    //                                {
    //                                    continue Label_0166_Outer;
    //                                }
    //                                break;
    //                            }
    //                            boolean directory;
    //                            b2 = (directory = b);
    //                            if (a)
    //                            {
    //                                continue;
    //                            }
    //                            break;
    //                        }
    //                        if (!b2)
    //                        {
    //                            throw new Exception(b("X\u0011aD{t\u0004/\u0002|u\u0014/\fzh\u0004f\u00005}\u0002`\t5x\u0015}\u0010;"));
    //                        }
    //                    }
    //                    catch (Exception ex)
    //                    {
    //                        System.out.println(ex.toString());
    //                        System.exit(-1);
    //                        if (a)
    //                        {
    //                        }
    //                    }
    //                }
    //                return;
    //            }
    //            continue;
    //        }
    //    }
    //    
    //    public static boolean isEquals(final byte[] array, final byte[] array2)
    //    {
    //        final boolean a = JCEUtil.a;
    //        int length;
    //        final int n = length = array.length;
    //        if (!a)
    //        {
    //            if (n == array2.length)
    //            {
    //                int n2 = 0;
    //                while (true)
    //                {
    //                    while (true)
    //                    {
    //                        Label_0042:
    //                        {
    //                            if (!a)
    //                            {
    //                                break Label_0042;
    //                            }
    //                            final byte b = array[n2];
    //                            if (a || b != array2[n2])
    //                            {
    //                                return b != 0;
    //                            }
    //                            ++n2;
    //                        }
    //                        if (n2 < array.length)
    //                        {
    //                            continue;
    //                        }
    //                        break;
    //                    }
    //                    if (a)
    //                    {
    //                        continue;
    //                    }
    //                    break;
    //                }
    //                if (!a)
    //                {
    //                    return true;
    //                }
    //            }
    //            length = (false ? 1 : 0);
    //        }
    //        return length != 0;
    //    }
    //    
    //    public static boolean isIncludedHostid(final String s)
    //    {
    //        try
    //        {
    //            final String hostid = getHostid();
    //            if (hostid == null)
    //            {
    //                throw new Exception();
    //            }
    //            return contains(hostid, s);
    //        }
    //        catch (Exception ex)
    //        {
    //            System.out.println(b("O\u0018f\u00175H\t|\u0010pvPf\u00175u\u001f{D@u\u0019wIXz\u0013g\r{~^"));
    //            return false;
    //        }
    //    }
    //    
    private static String b(final String s)
    {
        final char[] charArray = s.toCharArray();
        final int length = charArray.length;
        int n = 0;
        while (true)
        {
            Label_0089:
            {
                if (length > 1)
                {
                    break Label_0089;
                }
                char[] array2;
                char[] array = array2 = charArray;
                int n3;
                int n2 = n3 = n;
                while (true)
                {
                    final char c = array2[n3];
                    char c2 = '\0';
                    switch (n % 5)
                    {
                        case 0:
                        {
                            c2 = '\u001b';
                            break;
                        }
                        case 1:
                        {
                            c2 = 'p';
                            break;
                        }
                        case 2:
                        {
                            c2 = '\u000f';
                            break;
                        }
                        case 3:
                        {
                            c2 = 'd';
                            break;
                        }
                        default:
                        {
                            c2 = '\u0015';
                            break;
                        }
                    }
                    array[n2] = (char) (c ^ c2);
                    ++n;
                    if (length != 0)
                    {
                        break;
                    }
                    array = (array2 = charArray);
                    n2 = (n3 = length);
                }
            }
            if (n >= length)
            {
                return new String(charArray);
            }
            continue;
        }
    }
}