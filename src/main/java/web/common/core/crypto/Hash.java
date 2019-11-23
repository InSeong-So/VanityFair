package web.common.core.crypto;

public interface Hash
{
    byte[] digest(byte[] bArr);
    
    String hash(String str);
    
    String hash(String str, byte[] bArr) throws Exception;
}
