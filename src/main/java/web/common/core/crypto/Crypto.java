package web.common.core.crypto;

import java.util.HashMap;

public class Crypto
{
    private static Crypto m_Crypto = null;
    
    private HashMap<String, Object> m_AlgorithmTable;
    
    private HashMap<String, Object> m_HashTable;
    
    private Crypto()
    {
        this.m_AlgorithmTable = new HashMap<String, Object>();
        this.m_HashTable = new HashMap<String, Object>();
        
        this.m_AlgorithmTable.put("3DES", new TripleDES());
        this.m_AlgorithmTable.put("AES128", new AES128());
        this.m_AlgorithmTable.put("AES256", new AES256());
        this.m_AlgorithmTable.put("ARIA", new ARIA());
        this.m_AlgorithmTable.put("SEED", new SEED());
        
        this.m_HashTable.put("HMAC", new HMAC());
        this.m_HashTable.put("SHA1", new SHA1());
        this.m_HashTable.put("SHA256", new SHA256());
    }
    
    public static final String ALGORITHM_3DES = "3DES";
    
    public static final String ALGORITHM_AES128 = "AES128";
    
    public static final String ALGORITHM_AES256 = "AES256";
    
    public static final String ALGORITHM_ARIA = "ARIA";
    
    public static final String ALGORITHM_SEED = "SEED";
    
    public static final String HASH_HMAC = "HMAC";
    
    public static final String HASH_SHA1 = "SHA1";
    
    public static final String HASH_SHA256 = "SHA256";
    
    public static void initialize()
    {
        JCEUtil.initProvider();
    }
    
    public static int getAlgorithmId(String name)
    {
        if (name.compareToIgnoreCase("SEED") == 0)
            return 5;
        if (name.compareToIgnoreCase("AES128") == 0)
            return 2;
        if (name.compareToIgnoreCase("AES256") == 0)
            return 3;
        if (name.compareToIgnoreCase("ARIA") == 0)
            return 4;
        if (name.compareToIgnoreCase("3DES") == 0)
        {
            return 1;
        }
        
        return 0;
    }
    
    public class AlgorithmId
    {
        public static final int None = 0;
        
        public static final int TripleDES = 1;
        
        public static final int AES128 = 2;
        
        public static final int AES256 = 3;
        
        public static final int ARIA = 4;
        
        public static final int SEED = 5;
    }
    
    public static Crypto getInstance()
    {
        if (m_Crypto != null)
        {
            return m_Crypto;
        }
        
        m_Crypto = new Crypto();
        
        return m_Crypto;
    }
    
    public Algorithm getAlgorithm(String name)
    {
        return (Algorithm) this.m_AlgorithmTable.get(name);
    }
    
    public Algorithm getAlgorithm(int id)
    {
        switch (id)
        {
            case 2:
                return (Algorithm) this.m_AlgorithmTable.get("AES128");
            
            case 3:
                return (Algorithm) this.m_AlgorithmTable.get("AES256");
            
            case 4:
                return (Algorithm) this.m_AlgorithmTable.get("ARIA");
            
            case 5:
                return (Algorithm) this.m_AlgorithmTable.get("SEED");
            
            case 1:
                return (Algorithm) this.m_AlgorithmTable.get("3DES");
        }
        
        return null;
    }
    
    public Hash getHash(String name)
    {
        return (Hash) this.m_HashTable.get(name);
    }
    
    public static byte[] encodeBase64(byte[] data)
    {
        return CuBASE64.encode(data);
    }
    
    public static byte[] decodeBase64(byte[] data)
    {
        return CuBASE64.decode(data);
    }
    
    public static void main(String[] args)
    {
        long start = 0L;
        long end = 0L;
        Crypto crypto = null;
        byte[] key = { -114, 9, -112, 6, -39, -115, 96, 1, 32, -18, -127, 26, -3, -3, -3, -3 };
        byte[] iv = { -55, -87, -27, -58, -84, 42, -97, -100, -53, 13, -30, -91, -3, -3, -3, -3 };
        Algorithm seed = null;
        String input = null;
        byte[] inputBytes = null;
        String output = null;
        double time = 0.0D;
        
        try
        {
            crypto = getInstance();
            seed = crypto.getAlgorithm("SEED");
            
            input = "999999999";
            inputBytes = input.getBytes();
            
            start = System.nanoTime();
            output = seed.encrypt_b64eOut(inputBytes, key, iv);
            end = System.nanoTime();
            
            System.out.println("'" + input + "'(" + input.length() + ") >>> '" + output + "'(" + output.length() + ")");
            
            time = (end - start) / 1.0E9D;
            System.out.println("Enc time(sec): " + time);
            
            start = System.nanoTime();
            inputBytes = seed.decrypt_b64eIn(output, key, iv);
            end = System.nanoTime();
            
            input = new String(inputBytes);
            System.out.println("'" + output + "'(" + output.length() + ") >>> '" + input + "'(" + input.length() + ")");
            
            time = (end - start) / 1.0E9D;
            System.out.println("Dec time(sec): " + time);
            
            input = "999999999";
            inputBytes = input.getBytes();
            start = System.nanoTime();
            for (int i = 0; i < 10000; i++)
            {
                output = seed.encrypt_b64eOut(inputBytes, key, iv);
            }
            end = System.nanoTime();
            
            time = (end - start) / 1.0E9D;
            System.out.println("Enc[10000] time(sec): " + time);
            System.out.println("Avg time(sec): " + (time / 10000.0D));
            
            input = output;
            start = System.nanoTime();
            for (int i = 0; i < 100; i++)
            {
                seed.decrypt_b64eIn(input, key, iv);
            }
            end = System.nanoTime();
            
            time = (end - start) / 1.0E9D;
            System.out.println("Dec[10000] time(sec): " + time);
            System.out.println("Avg time(sec): " + (time / 10000.0D));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}