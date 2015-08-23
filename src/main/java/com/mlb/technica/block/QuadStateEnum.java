/*
 * BlockRubbertreeLogEnum
 *
 * 21.08.15 20:41
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

import net.minecraft.util.IStringSerializable;

public enum QuadStateEnum implements IStringSerializable{
    STATE1("a"),
    STATE2("b"),
    STATE3("c"),
    STATE4("d");
    private final String name;

    private QuadStateEnum(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }

    public static Class<QuadStateEnum> toClass() {
        return QuadStateEnum.class;
    }
}
