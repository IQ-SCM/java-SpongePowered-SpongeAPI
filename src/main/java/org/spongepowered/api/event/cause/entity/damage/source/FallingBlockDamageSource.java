/*
 * This file is part of SpongeAPI, licensed under the MIT License (MIT).
 *
 * Copyright (c) SpongePowered <https://www.spongepowered.org>
 * Copyright (c) contributors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.spongepowered.api.event.cause.entity.damage.source;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.block.BlockState;
import org.spongepowered.api.data.Keys;
import org.spongepowered.api.data.value.Value;
import org.spongepowered.api.entity.FallingBlock;
import org.spongepowered.api.util.Ticks;

public interface FallingBlockDamageSource extends EntityDamageSource {

    /**
     * Creates a new {@link Builder} for constructing a new {@link FallingBlockDamageSource}.
     *
     * @return A new builder
     */
    static Builder builder() {
        return Sponge.game().builderProvider().provide(Builder.class);
    }

    @Override
    FallingBlock source();


    /**
     * Gets the {@link org.spongepowered.api.data.value.Value.Immutable} for the damage to deal per block
     * the {@link FallingBlock} has fallen.
     *
     * @return The immutable value for the damage per block of falling
     */
    default Value.Immutable<Double> fallDamagePerBlock() {
        return this.source().requireValue(Keys.DAMAGE_PER_BLOCK).asImmutable();
    }

    /**
     * Gets the maximum damage the {@link FallingBlock} can deal to another
     * entity for falling on the entity.
     *
     * @return The maximum damage the block can deal
     */
    default Value.Immutable<Double> maxFallDamage() {
        return this.source().requireValue(Keys.MAX_FALL_DAMAGE).asImmutable();
    }

    /**
     * Gets the {@link BlockState} the falling block is representing.
     *
     * @return The falling block's block state
     */
    default Value.Immutable<BlockState> blockState() {
        return this.source().requireValue(Keys.BLOCK_STATE).asImmutable();
    }

    /**
     * Gets whether this falling block will try to place itself where
     * it lands.
     *
     * @return True if this block will attempt to place itself when it lands
     */
    default Value.Immutable<Boolean> canPlaceAsBlock() {
        return this.source().requireValue(Keys.CAN_PLACE_AS_BLOCK).asImmutable();
    }

    /**
     * Gets whether this falling block can drop as an item if it lands in a
     * way that it can not be placed.
     *
     * @return Whether this falling block can drop as an item
     */
    default Value.Immutable<Boolean> canDropAsItem() {
        return this.source().requireValue(Keys.CAN_DROP_AS_ITEM).asImmutable();
    }

    /**
     * Gets the time the block has been falling if spawning a entity in air
     * this will need to be set to 1 or it will be instantly removed.
     *
     * @return The time the block has been falling
     */
    default Value.Immutable<Ticks> fallTime() {
        return this.source().fallTime().asImmutable();
    }

    /**
     * Gets whether this falling block will damage entities where it lands.
     *
     * @return Whether this falling block will damage entities where it lands
     */
    default Value.Immutable<Boolean> canHurtEntities() {
        return this.source().requireValue(Keys.CAN_HURT_ENTITIES).asImmutable();
    }


    interface Builder extends EntityDamageSource.EntityDamageSourceBuilder<FallingBlockDamageSource, Builder> {

        Builder places(boolean canPlace);

        Builder fallTime(Ticks time);

        Builder hurtsEntities(boolean hurts);

        Builder maxDamage(double damage);

        Builder damagePerBlock(double damagePer);

    }
}
