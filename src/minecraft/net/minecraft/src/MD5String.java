// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 
// Source File Name:   SourceFile

package net.minecraft.src;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5String
{

    public MD5String(String s)
    {
        salt = s;
    }

    public String getMD5String(String s)
    {
        try
        {
            String s1 = (new StringBuilder()).append(salt).append(s).toString();
            MessageDigest messagedigest = MessageDigest.getInstance("MD5");
            messagedigest.update(s1.getBytes(), 0, s1.length());
            return (new BigInteger(1, messagedigest.digest())).toString(16);
        }
        catch(NoSuchAlgorithmException nosuchalgorithmexception)
        {
            throw new RuntimeException(nosuchalgorithmexception);
        }
    }

    private String salt;
}
