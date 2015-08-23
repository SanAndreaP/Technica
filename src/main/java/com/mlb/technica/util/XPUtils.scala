/*
 * XPUtils.scala
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

package com.mlb.technica.util

import net.minecraft.entity.player.EntityPlayer

object XPUtils {

  /**
   * Attempts to consume a single point of player experience.
   * @return whether it has been successful...
   */
  def useSingleXP(playerIn: EntityPlayer): Boolean = {
    var singleXp = 1 / playerIn.xpBarCap().toFloat
    if (playerIn.experience > singleXp) {
      playerIn.experience -= singleXp
      playerIn.experienceTotal -= 1
      return true
    } else if (playerIn.experienceLevel > 0) {
      playerIn.removeExperienceLevel(1)
      playerIn.experience = 1F - (1F / playerIn.xpBarCap().toFloat)
      playerIn.experienceTotal -= 1
      return true
    }
    false
  }

  def useXP(playerIn: EntityPlayer, amount: Int):Int = {
    //TODO: Research decreasing for loop...
    0
  }

}
