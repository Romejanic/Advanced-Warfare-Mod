package com.romejanic.aw.common.item;

import net.minecraft.item.Item;
import net.minecraft.util.SoundEvent;

public abstract class ItemGun extends Item {

	public ItemGun() {
		this.setMaxStackSize(1);
		this.setMaxDamage(0);
	}
	
	protected abstract SoundEvent getFireSound();
	protected abstract int getFireRate();
	
}