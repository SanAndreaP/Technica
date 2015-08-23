/*
 * ItemPowerCrusher.scala
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

import codechicken.lib.raytracer.RayTracer
import com.google.common.collect.ImmutableSet
import com.mlb.technica.Technica
import com.mlb.technica.util.XPUtils
import net.minecraft.block.material.Material
import net.minecraft.block.{Block, BlockOre, BlockRedstoneOre}
import net.minecraft.client.gui.GuiScreen
import net.minecraft.enchantment.EnchantmentHelper
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.entity.{Entity, EntityLivingBase}
import net.minecraft.init.Blocks
import net.minecraft.item.Item.ToolMaterial
import net.minecraft.item.{Item, ItemStack, ItemTool}
import net.minecraft.util.{BlockPos, EnumFacing}
import net.minecraft.world.World
import net.minecraftforge.fml.common.registry.GameRegistry

class ItemPowerCrusher(id: String, material: ToolMaterial) extends ItemTool(1F, material, null) {
  val effectiveOn: Set[Material] = Set(Material.clay, Material.craftedSnow, Material.ground, Material.grass, Material.iron, Material.piston, Material.redstoneLight, Material.rock, Material.sand, Material.snow)

  GameRegistry.registerItem(this, id)
  setCreativeTab(Technica.tabTechnica)

  override def getToolClasses(stack: ItemStack): java.util.Set[String] = ImmutableSet.of("pickaxe", "shovel")

  override def getStrVsBlock(stack: ItemStack, block: Block): Float = {
    if (effectiveOn.contains(block.getMaterial)) efficiencyOnProperMaterial else 1F
  }

  override def canHarvestBlock(blockIn: Block): Boolean = {
    effectiveOn.contains(blockIn.getMaterial)
  }


  override def onItemUse(stack: ItemStack, playerIn: EntityPlayer, worldIn: World, pos: BlockPos, side: EnumFacing, hitX: Float, hitY: Float, hitZ: Float): Boolean = {
    if (XPUtils.useSingleXP(playerIn)) {
      if (playerIn.inventory.consumeInventoryItem(Item.getItemFromBlock(Blocks.torch))) {
        val state = worldIn.getBlockState(pos)
        val block = state.getBlock
        var posT = pos

        if (!block.isReplaceable(worldIn, pos)) {
          posT = pos.offset(side)
        }

        if (playerIn.canPlayerEdit(posT, side, stack) && worldIn.canBlockBePlaced(Blocks.torch, posT, false, side, null.asInstanceOf[Entity], stack)) {
          val torchState = Blocks.torch.onBlockPlaced(worldIn, posT, side, hitX, hitY, hitZ, 0, playerIn)
          if (worldIn.setBlockState(posT, torchState, 3)) {
            worldIn.playSoundEffect(posT.getX.toDouble, posT.getX.toDouble, posT.getX.toDouble, Blocks.torch.stepSound.getPlaceSound, 1F, 1F)
            return true
          }
        }
      }
    }
    false
  }

  override def onBlockDestroyed(stack: ItemStack, worldIn: World, blockIn: Block, posIn: BlockPos, playerIn: EntityLivingBase): Boolean = {
    if (!worldIn.isRemote && !playerIn.isSneaking) {
      playerIn match {
        case player: EntityPlayer =>
          val face = RayTracer.retraceBlock(worldIn, player, posIn).sideHit
          val posMid = posIn.offset(face.getOpposite)
          blockIn match {
            case blockMatch: BlockRedstoneOre =>
              plotCube(blockMatch, posMid, 0, worldIn, posIn, player)
            case blockMatch: BlockOre =>
              plotCube(blockMatch, posMid, 0, worldIn, posIn, player)
            case _ =>
              for (x <- -1 to 1) {
                for (y <- -1 to 1) {
                  for (z <- -1 to 1) {
                    val posD = posMid.add(x, y, z)
                    val block = worldIn.getBlockState(posD).getBlock
                    if (!posIn.equals(posD) && block.equals(blockIn)) {
                      if (XPUtils.useSingleXP(player)) {
                        block.harvestBlock(worldIn, player, posD, worldIn.getBlockState(posD), worldIn.getTileEntity(posD))
                        worldIn.destroyBlock(posD, false)
                      }
                    }
                  }
                }
              }
          }
        case _ =>
          worldIn.createExplosion(playerIn, posIn.getX, posIn.getY, posIn.getZ, 5F, false)
      }
    }
    true
  }

  def plotCube(blockIn: Block, posIn: BlockPos, m: Int, worldIn: World, posSource: BlockPos, player: EntityPlayer): Int = {
    var mined = m
    for (x <- -1 to 1) {
      for (y <- -1 to 1) {
        for (z <- -1 to 1) {
          val posD = posIn.add(x, y, z)
          val block = worldIn.getBlockState(posD).getBlock
          if ((block.equals(blockIn) || (block.isInstanceOf[BlockRedstoneOre] && blockIn.isInstanceOf[BlockRedstoneOre])) && mined < 27) {
            if (XPUtils.useSingleXP(player)) {
              mined += 1
              block.harvestBlock(worldIn, player, posD, worldIn.getBlockState(posD), worldIn.getTileEntity(posD))
              block.dropXpOnBlockBreak(worldIn, posD, block.getExpDrop(worldIn, posD, EnchantmentHelper.getFortuneModifier(player)))
              worldIn.destroyBlock(posD, false)
              mined = plotCube(blockIn, posD, mined, worldIn, posSource, player)
            }
          }
        }
      }
    }
    mined
  }

  override def addInformation(stack: ItemStack, playerIn: EntityPlayer, tooltip: java.util.List[_], advanced: Boolean) {
    if (GuiScreen.isShiftKeyDown) {
      tooltip.asInstanceOf[java.util.List[String]].add("I wonder what happens")
      tooltip.asInstanceOf[java.util.List[String]].add("when I use a Ball of Energetic")
      tooltip.asInstanceOf[java.util.List[String]].add("Experience to crush Blocks?")
      tooltip.asInstanceOf[java.util.List[String]].add("")
      tooltip.asInstanceOf[java.util.List[String]].add("Crushes a 3x3x3 area of Blocks")
      tooltip.asInstanceOf[java.util.List[String]].add("or a vein of ores and consumes XP")
      tooltip.asInstanceOf[java.util.List[String]].add("Press shift to Suppress this effect")
    } else {
      tooltip.asInstanceOf[java.util.List[String]].add("<Shift>")
    }
    tooltip.asInstanceOf[java.util.List[String]].add("")
  }

  override def getIsRepairable(toRepair: ItemStack, repair: ItemStack): Boolean = false

  override def onEntitySwing(entityLiving: EntityLivingBase, stack: ItemStack): Boolean = true
}