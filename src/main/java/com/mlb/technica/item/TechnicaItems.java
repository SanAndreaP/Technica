/*
 * TechnicaItems
 *
 * 20.08.15 21:43
 * Copyright (c) 2015, Moritz Bust. All rights reserved.
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 */

package com.mlb.technica.item;

import com.mlb.technica.Technica;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.common.util.EnumHelper;

public class TechnicaItems {

    private static final Item.ToolMaterial xpTool = EnumHelper.addToolMaterial("XPTool", 10, 150, 2F, 0.0F, 16);

    public static BaseItem glowstick;
    public static Item powerCrusher;

    public static void preInit() {
        glowstick = (BaseItem) new ItemGlowstick("glowstick").setCreativeTab(Technica.tabTechnica());
        powerCrusher = new ItemPowerCrusher("powerCrusher", xpTool);

    }

    public static void initRenderers(RenderItem renderer) {
        renderer.getItemModelMesher().register(glowstick, 0, new ModelResourceLocation(Technica.MODID() + ":glowstick", "inventory"));
        renderer.getItemModelMesher().register(powerCrusher, 0, new ModelResourceLocation(Technica.MODID() + ":powerCrusher", "inventory"));
    }
}
