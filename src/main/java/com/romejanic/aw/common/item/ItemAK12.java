package com.romejanic.aw.common.item;

import com.romejanic.aw.common.utils.AWSounds;

import net.minecraft.util.SoundEvent;

public class ItemAK12 extends ItemGun {

	@Override
	protected SoundEvent getFireSound() {
		return AWSounds.AK12_FIRE;
	}

	@Override
	protected int getFireRate() {
		return 13;
	}

	@Override
	protected int getMaxAmmo() {
		return 30;
	}

}