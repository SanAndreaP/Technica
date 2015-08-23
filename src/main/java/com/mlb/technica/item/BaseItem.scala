/*
 * BaseItem.scala
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

package com.mlb.technica.item

import com.mlb.technica.Technica
import net.minecraft.item.Item
import net.minecraftforge.fml.common.registry.GameRegistry

class BaseItem(id: String) extends Item {
  GameRegistry.registerItem(this, id)
  setUnlocalizedName(Technica.MODID + "_" + id)
  setCreativeTab(Technica.tabTechnica)
}
