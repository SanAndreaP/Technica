/*
 * TechnicaBlocks
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

package com.mlb.technica.block;

import com.mlb.technica.Technica;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;

public class TechnicaBlocks {

    private static RenderItem renderItem;

    public static BaseBlock researchTable;
    public static BlockRubbertreeLog rubbertreeLog;

    public static void preInit() {
        researchTable = new BaseBlock("researchTable", Material.wood);
        rubbertreeLog = new BlockRubbertreeLog("rubbertreeLog");
    }

    public static void initRenderers(RenderItem renderer) {
        renderItem = renderer;
        registerTexture(researchTable, "researchTable");
        registerTexture(rubbertreeLog, "rubbertreeLog");
    }

    private static void registerTexture(Block block, String id) {
        renderItem.getItemModelMesher().register(Item.getItemFromBlock(block), 0, new ModelResourceLocation(Technica.MODID() + ":" + id, "inventory"));
    }

}
