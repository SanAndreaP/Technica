/*
 * ItemGlowstick.scala
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

import net.minecraft.entity.item.EntityItem
import net.minecraft.util.BlockPos
import net.minecraft.world.EnumSkyBlock

class ItemGlowstick(id: String) extends BaseItem(id: String) {
  override def onEntityItemUpdate(entityItem: EntityItem): Boolean = {
    val pos:BlockPos = new BlockPos(entityItem.posX.toInt, entityItem.posY.toInt, entityItem.posZ.toInt)
    val prevPos:BlockPos = new BlockPos(entityItem.prevPosX.toInt, entityItem.prevPosY.toInt, entityItem.prevPosZ.toInt)

    if (entityItem.getAge < 5500)
      entityItem.setPickupDelay(10)

    if (pos.equals(prevPos) && entityItem.getAge < 5400) {
      if (pos.getY > 0 && pos.getY < 256) {
        entityItem.worldObj.setLightFor(EnumSkyBlock.BLOCK, prevPos, 15)
        entityItem.worldObj.checkLight(pos.up())
      }
    } else {
      entityItem.worldObj.checkLight(prevPos)
    }
    false
  }
}