/*
 * Technica.scala
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

package com.mlb.technica

import com.mlb.technica.block.TechnicaBlocks
import com.mlb.technica.item.TechnicaItems
import net.minecraft.client.Minecraft
import net.minecraft.client.renderer.entity.RenderItem
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.Item
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.Mod.EventHandler
import net.minecraftforge.fml.common.event.{FMLInitializationEvent, FMLPostInitializationEvent, FMLPreInitializationEvent}
import org.apache.logging.log4j.{Level, LogManager, Logger}

@Mod(modid = "technica", useMetadata = true, modLanguage = "scala")
object Technica {
  final val MODID: String = "technica"
  final val LOGGER: Logger = LogManager.getLogger("Technica")

  final val itemReg = new TechnicaItems

  final val tabTechnica:CreativeTabs = new TabTechnica(CreativeTabs.getNextID, "tabTechnica")

  @EventHandler
  def preInit(e: FMLPreInitializationEvent) {
    LOGGER.log(Level.INFO, "Starting Technica 2")

    TechnicaItems.preInit()
    TechnicaBlocks.preInit()
  }

  @EventHandler
  def init(e: FMLInitializationEvent) {
    if (e.getSide.isClient) {
      val renderer:RenderItem = Minecraft.getMinecraft.getRenderItem
      TechnicaItems.initRenderers(renderer)
      TechnicaBlocks.initRenderers(renderer)
    }
  }

  @EventHandler
  def postInit(e: FMLPostInitializationEvent) {

  }

}

class TabTechnica(index: Int, label:String) extends CreativeTabs(index: Int, label:String) {
  override def getTabIconItem: Item = TechnicaItems.glowstick
}