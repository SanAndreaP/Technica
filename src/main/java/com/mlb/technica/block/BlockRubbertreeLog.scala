/*
 * BlockRubbertreeLog.scala
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
import net.minecraft.block.BlockLog
import net.minecraft.block.properties.PropertyEnum
import net.minecraft.block.state.{BlockState, IBlockState}
import net.minecraft.entity.EntityLivingBase
import net.minecraft.util.{BlockPos, EnumFacing}
import net.minecraft.world.World
import net.minecraftforge.fml.common.registry.GameRegistry

class BlockRubbertreeLog(id:String) extends BlockLog {
  setUnlocalizedName(id)
  setCreativeTab(Technica.tabTechnica)
  GameRegistry.registerBlock(this, id)

  setDefaultState(blockState.getBaseState.withProperty(BlockLog.LOG_AXIS, BlockLog.EnumAxis.Y))//.withProperty(BlockRubbertreeLog.CONDITION, QuadStateEnum.STATE1))

  override def createBlockState():BlockState = new BlockState(this, BlockLog.LOG_AXIS)

  override def onBlockPlaced(worldIn: World, pos: BlockPos, facing: EnumFacing, hitX: Float, hitY: Float, hitZ: Float, meta: Int, placer: EntityLivingBase): IBlockState = {
    super.onBlockPlaced(worldIn, pos, facing, hitX, hitY, hitZ, meta, placer)
  }

  override def getStateFromMeta(meta: Int): IBlockState = {
    getDefaultState.withProperty(BlockLog.LOG_AXIS, BlockLog.EnumAxis.values()(meta))
  }

  override def getMetaFromState(state: IBlockState): Int = {
    state.getValue(BlockLog.LOG_AXIS).asInstanceOf[BlockLog.EnumAxis].ordinal()
  }
}

object BlockRubbertreeLog {
  val CONDITION:PropertyEnum = PropertyEnum.create("condition", QuadStateEnum.toClass)
}
