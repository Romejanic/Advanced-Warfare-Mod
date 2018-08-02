package com.romejanic.aw.client.item;

import java.util.HashMap;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntityItemStackRenderer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class ItemRendererManager extends TileEntityItemStackRenderer {

	private final HashMap<Item, IItemRenderer> customRenderers;
	
	public ItemRendererManager() {
		this.customRenderers = new HashMap<Item, IItemRenderer>();
	}
	
	public void registerItem(Item item, IItemRenderer customRenderer) {
		if(this.customRenderers.containsKey(item)) {
			throw new IllegalArgumentException("A custom renderer for " + item.getRegistryName() + " is already registered!");
		}
		this.customRenderers.put(item, customRenderer);
		item.setTileEntityItemStackRenderer(this);
	}
	
	@Override
	public void renderByItem(ItemStack stack, float partialTicks) {
		if(this.customRenderers.containsKey(stack.getItem())) {
			this.customRenderers.get(stack.getItem()).renderItem(stack);
			return;
		}
		super.renderByItem(stack, partialTicks);
    }
	
	// calling it IItemRenderer cuz i'm salty :P
	public interface IItemRenderer {
		
		void renderItem(ItemStack stack);
		
		default void bindTexture(ResourceLocation resource) {
			Minecraft.getMinecraft().getTextureManager().bindTexture(resource);
		}
		
		default void pushMatrix() {
			GlStateManager.pushMatrix();
		}
		
		default void popMatrix() {
			GlStateManager.popMatrix();
		}
		
		default void translate(float x, float y, float z) {
			GlStateManager.translate(x, y, z);
		}
		
		default void rotate(float angle, float x, float y, float z) {
			GlStateManager.rotate(angle, x, y, z);
		}
		
		default void rotateX(float angle) {
			rotate(angle, 1f, 0f, 0f);
		}
		
		default void rotateY(float angle) {
			rotate(angle, 0f, 1f, 0f);
		}
		
		default void rotateZ(float angle) {
			rotate(angle, 0f, 0f, 1f);
		}
		
		default void scale(float x, float y, float z) {
			GlStateManager.scale(x, y, z);
		}
		
		default void scale(float s) {
			scale(s, s, s);
		}
		
	}
	
}