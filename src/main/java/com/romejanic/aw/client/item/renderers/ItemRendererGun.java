package com.romejanic.aw.client.item.renderers;

import com.romejanic.aw.client.item.ItemRendererManager.IItemRenderer;

import net.minecraft.client.model.ModelBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public abstract class ItemRendererGun implements IItemRenderer {

	private final ModelBase model;
	private final ResourceLocation texture;
	
	public ItemRendererGun(ModelBase model, String texture) {
		this.model = model;
		this.texture = new ResourceLocation("aw", texture);
	}
	
	protected abstract void transformAmmoCounter();
	protected abstract void transformMuzzleFlash();
	
	@Override
	public void renderItem(ItemStack stack) {
		bindTexture(this.texture);
		pushMatrix();
		this.model.render(null, 0f, 0f, 0f, 0f, 0f, 0.0625f);
		popMatrix();
	}

}