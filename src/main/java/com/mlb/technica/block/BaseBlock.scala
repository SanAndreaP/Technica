/*
 * BaseBlock.scala
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

package com.mlb.technica.block

import com.mlb.technica.Technica
import net.minecraft.block.Block
import net.minecraft.block.material.Material
import net.minecraftforge.fml.common.registry.GameRegistry

class BaseBlock(id: String, material: Material) extends Block(material) {
  GameRegistry.registerBlock(this, id)
  setUnlocalizedName(id)
  setCreativeTab(Technica.tabTechnica)

}
