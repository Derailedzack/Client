// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 
// Source File Name:   SourceFile

package net.minecraft.src;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Referenced classes of package net.minecraft.src:
//            J_JsonNode, EnumJsonNodeType

final class J_JsonNumberNode extends J_JsonNode
{

    J_JsonNumberNode(String s)
    {
        if(s == null)
        {
            throw new NullPointerException("Attempt to construct a JsonNumber with a null value.");
        }
        if(!PATTERN.matcher(s).matches())
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Attempt to construct a JsonNumber with a String [").append(s).append("] that does not match the JSON number specification.").toString());
        } else
        {
            value = s;
            return;
        }
    }

    public EnumJsonNodeType getType()
    {
        return EnumJsonNodeType.NUMBER;
    }

    public String getText()
    {
        return value;
    }

    public Map getFields()
    {
        throw new IllegalStateException("Attempt to get fields on a JsonNode without fields.");
    }

    public List getElements()
    {
        throw new IllegalStateException("Attempt to get elements on a JsonNode without elements.");
    }

    public boolean equals(Object obj)
    {
        if(this == obj)
        {
            return true;
        }
        if(obj == null || getClass() != obj.getClass())
        {
            return false;
        } else
        {
            J_JsonNumberNode j_jsonnumbernode = (J_JsonNumberNode)obj;
            return value.equals(j_jsonnumbernode.value);
        }
    }

    public int hashCode()
    {
        return value.hashCode();
    }

    public String toString()
    {
        return (new StringBuilder()).append("JsonNumberNode value:[").append(value).append("]").toString();
    }

    private static final Pattern PATTERN = Pattern.compile("(-?)(0|([1-9]([0-9]*)))(\\.[0-9]+)?((e|E)(\\+|-)?[0-9]+)?");
    private final String value;

}
