package core.crypto;

public class SHA256 implements Hash {
    public String hash(String data) {
        return new String(CuBASE64.encode(digest(data.getBytes())));
    }

    public String hash(String data, byte[] key) {
        return hash(data);
    }

    public byte[] digest(byte[] data) {
        try {
            CuSHA256 hash = new CuSHA256();
            CuSHA256 cuSHA256;
            try {
                cuSHA256 = hash;
                return hash.hash(data);
            } catch (Exception e) {
                cuSHA256 = hash;
                return null;
            }
        } catch (Exception e2) {
            return null;
        }
    }

    public static void main(String[] args) {
        System.out.println("sha256 length\t" + new SHA256().digest("1234567890123456".getBytes()).length);
    }
}