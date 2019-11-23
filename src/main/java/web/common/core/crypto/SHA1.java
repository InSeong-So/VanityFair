package web.common.core.crypto;

public class SHA1 implements Hash {
    public String hash(String data) {
        return CryptoImpl.doDigestEncode(data.getBytes());
    }

    public String hash(String data, byte[] key) {
        return null;
    }

    public byte[] digest(byte[] data) {
        return CryptoImpl.doDigest(data);
    }
}