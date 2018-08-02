package com.romejanic.aw.client.item.renderers;

import com.romejanic.aw.client.item.ItemRendererManager.IItemRenderer;

import net.minecraft.client.model.ModelPig;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

@Deprecated
/**
 * @Deprecated This was just a test of the 3D item rendering.
 */
public class ItemRendererAtlasExo implements IItemRenderer {

	private ModelPig pig;
	private ResourceLocation tex;
	
	public ItemRendererAtlasExo() {
		this.pig = new ModelPig();
		this.tex = new ResourceLocation("minecraft:textures/entity/pig/pig.png");
	}
	
	@Override
	public void renderItem(ItemStack stack) {
		bindTexture(this.tex);
		pushMatrix();
		this.pig.render(null, 0f, 0f, 0f, 0f, 0f, 0.0625f);
		popMatrix();
	}

}