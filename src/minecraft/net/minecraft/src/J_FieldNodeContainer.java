// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 
// Source File Name:   SourceFile

package net.minecraft.src;


// Referenced classes of package net.minecraft.src:
//            J_NodeContainer, J_JsonFieldBuilder, J_JsonListenerToJdomAdapter, J_JsonNodeBuilder

class J_FieldNodeContainer
    implements J_NodeContainer
{

    J_FieldNodeContainer(J_JsonListenerToJdomAdapter j_jsonlistenertojdomadapter, J_JsonFieldBuilder j_jsonfieldbuilder)
    {
//        super();
        listenerToJdomAdapter = j_jsonlistenertojdomadapter;
        fieldBuilder = j_jsonfieldbuilder;
    }

    public void addNode(J_JsonNodeBuilder j_jsonnodebuilder)
    {
        fieldBuilder.withValue(j_jsonnodebuilder);
    }

    public void addField(J_JsonFieldBuilder j_jsonfieldbuilder)
    {
        throw new RuntimeException("Coding failure in Argo:  Attempt to add a field to a field.");
    }

    final J_JsonFieldBuilder fieldBuilder; /* synthetic field */
    final J_JsonListenerToJdomAdapter listenerToJdomAdapter; /* synthetic field */
}
