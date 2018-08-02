package com.romejanic.aw.client.item.renderers;

import com.romejanic.aw.client.model.ModelAK12;

import net.minecraft.client.model.ModelBase;
import net.minecraft.util.ResourceLocation;

public class ItemRendererAK12 extends ItemRendererGun {

	public ItemRendererAK12() {
		super(new ModelAK12(), "textures/model/gun_ak12.png");
	}

	@Override
	protected void transformAmmoCounter() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void transformMuzzleFlash() {
		// TODO Auto-generated method stub

	}

}
