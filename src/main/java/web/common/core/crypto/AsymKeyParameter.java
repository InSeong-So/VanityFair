package web.common.core.crypto;

public class AsymKeyParameter
{
    boolean a;
    
    public static boolean b;
    
    public AsymKeyParameter(boolean paramBoolean)
    {
        this.a = paramBoolean;
    }
    
    public boolean isPrivate()
    {
        return this.a;
    }
}
