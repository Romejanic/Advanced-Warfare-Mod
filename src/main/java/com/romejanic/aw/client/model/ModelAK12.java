package com.romejanic.aw.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelAK12 extends ModelBase {

	public ModelRenderer body1;
	public ModelRenderer body2;
	public ModelRenderer body3;
	public ModelRenderer ironsight1;
	public ModelRenderer body4;
	public ModelRenderer body5;
	public ModelRenderer barrel1;
	public ModelRenderer barrel2;
	public ModelRenderer barrel3;
	public ModelRenderer magazine;
	public ModelRenderer grip;
	public ModelRenderer trigger;
	public ModelRenderer stock1;
	public ModelRenderer stock2;
	public ModelRenderer stock3;

	public ModelAK12() {
		this.textureWidth = 64;
		this.textureHeight = 32;
		this.body4 = new ModelRenderer(this, 7, 8);
		this.body4.setRotationPoint(-0.7F, -1.05F, 1.7F);
		this.body4.addBox(0.0F, 0.0F, 0.0F, 13, 1, 1, 0.0F);
		this.setRotateAngle(this.body4, -1.2292354F, 0.0F, 0.0F);
		this.barrel3 = new ModelRenderer(this, 16, 11);
		this.barrel3.setRotationPoint(15.0F, -0.2F, 0.5F);
		this.barrel3.addBox(0.0F, 0.0F, 0.0F, 5, 1, 1, 0.0F);
		this.trigger = new ModelRenderer(this, 10, 15);
		this.trigger.setRotationPoint(2.9F, 1.9F, 1.0F);
		this.trigger.addBox(0.0F, 0.0F, 0.0F, 1, 1, 0, 0.0F);
		this.ironsight1 = new ModelRenderer(this, 0, 14);
		this.ironsight1.setRotationPoint(0.0F, -1.6F, 0.5F);
		this.ironsight1.addBox(0.0F, 0.0F, 0.0F, 2, 1, 1, 0.0F);
		this.body2 = new ModelRenderer(this, 0, 5);
		this.body2.setRotationPoint(-0.6F, -1.04F, 0.55F);
		this.body2.addBox(0.0F, 0.0F, 0.0F, 13, 1, 1, 0.0F);
		this.body1 = new ModelRenderer(this, 0, 0);
		this.body1.setRotationPoint(-1.0F, 0.0F, 0.0F);
		this.body1.addBox(0.0F, 0.0F, 0.0F, 14, 2, 2, 0.0F);
		this.body5 = new ModelRenderer(this, 7, 8);
		this.body5.setRotationPoint(-0.7F, -0.04F, 0.0F);
		this.body5.addBox(0.0F, 0.0F, 0.0F, 13, 1, 1, 0.0F);
		this.setRotateAngle(this.body5, 1.2292354F, 0.0F, 0.0F);
		this.grip = new ModelRenderer(this, 7, 17);
		this.grip.setRotationPoint(1.7F, 1.9F, 0.5F);
		this.grip.addBox(0.0F, 0.0F, 0.0F, 1, 3, 1, 0.0F);
		this.barrel1 = new ModelRenderer(this, 7, 11);
		this.barrel1.setRotationPoint(12.3F, -0.9F, 0.5F);
		this.barrel1.addBox(0.0F, 0.0F, 0.0F, 3, 2, 1, 0.0F);
		this.body3 = new ModelRenderer(this, 0, 8);
		this.body3.setRotationPoint(-0.6F, -1.2F, 0.0F);
		this.body3.addBox(0.0F, 0.0F, 0.0F, 1, 3, 2, 0.0F);
		this.setRotateAngle(this.body3, 0.0F, 0.0F, 0.4553564F);
		this.stock3 = new ModelRenderer(this, 22, 14);
		this.stock3.setRotationPoint(-7.5F, 0.0F, 0.5F);
		this.stock3.addBox(0.0F, 0.0F, 0.0F, 1, 3, 1, 0.0F);
		this.stock2 = new ModelRenderer(this, 12, 18);
		this.stock2.setRotationPoint(-6.5F, -0.3F, 0.5F);
		this.stock2.addBox(0.0F, 0.0F, 0.0F, 3, 2, 1, 0.0F);
		this.barrel2 = new ModelRenderer(this, 7, 14);
		this.barrel2.setRotationPoint(14.8F, -1.8F, 0.5F);
		this.barrel2.addBox(0.0F, 0.0F, 0.0F, 0, 1, 1, 0.0F);
		this.magazine = new ModelRenderer(this, 0, 17);
		this.magazine.setRotationPoint(4.1F, 1.9F, 0.5F);
		this.magazine.addBox(0.0F, 0.0F, 0.0F, 2, 4, 1, 0.0F);
		this.setRotateAngle(this.magazine, 0.0F, 0.0F, -0.27314404F);
		this.stock1 = new ModelRenderer(this, 13, 15);
		this.stock1.setRotationPoint(-3.5F, 0.7F, 0.5F);
		this.stock1.addBox(0.0F, 0.0F, 0.0F, 3, 1, 1, 0.0F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		this.body4.render(f5);
		this.barrel3.render(f5);
		this.trigger.render(f5);
		this.ironsight1.render(f5);
		this.body2.render(f5);
		this.body1.render(f5);
		this.body5.render(f5);
		this.grip.render(f5);
		this.barrel1.render(f5);
		this.body3.render(f5);
		this.stock3.render(f5);
		this.stock2.render(f5);
		this.barrel2.render(f5);
		this.magazine.render(f5);
		this.stock1.render(f5);
	}

	public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}
