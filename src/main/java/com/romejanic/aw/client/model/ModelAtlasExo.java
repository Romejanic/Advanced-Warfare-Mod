package com.romejanic.aw.client.model;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;

public class ModelAtlasExo extends ModelBiped {

	public ModelRenderer exoLegRight1;
	public ModelRenderer exoLegRight2;
	public ModelRenderer exoArmRight;
	public ModelRenderer exoArmRight1;
	public ModelRenderer exoBack;
	public ModelRenderer exoBack1;
	public ModelRenderer exoBack2;
	public ModelRenderer exoBack4;
	public ModelRenderer exoConnector;
	public ModelRenderer exoConnector1;
	public ModelRenderer exoConnector2;
	public ModelRenderer exoConnector3;
	public ModelRenderer exoLegLeft1;
	public ModelRenderer exoLegLeft2;
	public ModelRenderer exoArmLeft;
	public ModelRenderer exoArmLeft1;

	public ModelAtlasExo() {
		this.textureWidth = 64;
		this.textureHeight = 64;
		this.bipedHeadwear.isHidden = true;
		this.bipedBody.cubeList.clear();
		this.bipedHead.cubeList.clear();
		this.bipedLeftArm.cubeList.clear();
		this.bipedLeftLeg.cubeList.clear();
		this.bipedRightArm.cubeList.clear();
		this.bipedRightLeg.cubeList.clear();
		this.exoBack = new ModelRenderer(this, 17, 34);
		this.exoBack.setRotationPoint(-3.1F, 0.6F, 2.0F);
		this.exoBack.addBox(0.0F, 0.0F, 0.0F, 6, 1, 1, 0.0F);
		this.exoArmRight1 = new ModelRenderer(this, 8, 40);
		this.exoArmRight1.setRotationPoint(-3.4F, -0.4F, 0.2F);
		this.exoArmRight1.addBox(0.0F, 0.0F, 0.0F, 2, 10, 3, 0.0F);
		this.exoConnector1 = new ModelRenderer(this, 32, 34);
		this.exoConnector1.setRotationPoint(2.5F, 0.6F, 2.1F);
		this.exoConnector1.addBox(0.0F, 0.0F, 0.0F, 3, 1, 1, 0.0F);
		this.setRotateAngle(this.exoConnector1, 0.0F, 0.4098033F, 0.0F);
		this.exoConnector = new ModelRenderer(this, 32, 34);
		this.exoConnector.setRotationPoint(-5.5F, 0.6F, 0.9F);
		this.exoConnector.addBox(0.0F, 0.0F, 0.0F, 3, 1, 1, 0.0F);
		this.setRotateAngle(this.exoConnector, 0.0F, -0.4098033F, 0.0F);
		this.exoBack4 = new ModelRenderer(this, 19, 41);
		this.exoBack4.setRotationPoint(-1.1F, 3.5F, 2.0F);
		this.exoBack4.addBox(0.0F, 0.0F, 0.0F, 2, 6, 1, 0.0F);
		this.exoLegRight2 = new ModelRenderer(this, 8, 33);
		this.exoLegRight2.setRotationPoint(-1.8F, 11.5F, -0.9F);
		this.exoLegRight2.addBox(0.0F, 0.0F, 0.0F, 2, 1, 2, 0.0F);
		this.exoArmRight = new ModelRenderer(this, 8, 37);
		this.exoArmRight.setRotationPoint(-3.1F, -1.4F, 2.0F);
		this.exoArmRight.addBox(0.0F, 0.0F, 0.0F, 4, 1, 1, 0.0F);
		this.exoConnector3 = new ModelRenderer(this, 32, 37);
		this.exoConnector3.setRotationPoint(2.0F, 9.4F, 0.0F);
		this.exoConnector3.addBox(0.0F, 0.0F, 0.0F, 1, 4, 3, 0.0F);
		this.setRotateAngle(this.exoConnector3, 0.0F, 0.0F, -0.4098033F);
		this.exoArmLeft1 = new ModelRenderer(this, 8, 40);
		this.exoArmLeft1.setRotationPoint(1.3F, -0.4F, 0.3F);
		this.exoArmLeft1.addBox(0.0F, 0.0F, 0.0F, 2, 10, 3, 0.0F);
		this.exoConnector2 = new ModelRenderer(this, 32, 37);
		this.exoConnector2.setRotationPoint(-3.1F, 9.0F, 0.0F);
		this.exoConnector2.addBox(0.0F, 0.0F, 0.0F, 1, 4, 3, 0.0F);
		this.setRotateAngle(this.exoConnector2, 0.0F, 0.0F, 0.4098033F);
		this.exoLegRight1 = new ModelRenderer(this, 1, 33);
		this.exoLegRight1.setRotationPoint(-2.8F, 0.5F, -0.9F);
		this.exoLegRight1.addBox(0.0F, 0.0F, 0.0F, 1, 12, 2, 0.0F);
		this.exoArmLeft = new ModelRenderer(this, 8, 37);
		this.exoArmLeft.setRotationPoint(-1.0F, -1.4F, 2.0F);
		this.exoArmLeft.addBox(0.0F, 0.0F, 0.0F, 4, 1, 1, 0.0F);
		this.exoBack1 = new ModelRenderer(this, 19, 37);
		this.exoBack1.setRotationPoint(-2.6F, 1.5F, 2.0F);
		this.exoBack1.addBox(0.0F, 0.0F, 0.0F, 5, 2, 1, 0.0F);
		this.exoLegLeft1 = new ModelRenderer(this, 1, 33);
		this.exoLegLeft1.setRotationPoint(1.9F, 0.5F, -0.9F);
		this.exoLegLeft1.addBox(0.0F, 0.0F, 0.0F, 1, 12, 2, 0.0F);
		this.exoLegLeft2 = new ModelRenderer(this, 8, 33);
		this.exoLegLeft2.setRotationPoint(-0.1F, 11.5F, -0.9F);
		this.exoLegLeft2.addBox(0.0F, 0.0F, 0.0F, 2, 1, 2, 0.0F);
		this.exoBack2 = new ModelRenderer(this, 17, 34);
		this.exoBack2.setRotationPoint(-3.1F, 9.0F, 2.0F);
		this.exoBack2.addBox(0.0F, 0.0F, 0.0F, 6, 1, 1, 0.0F);
		this.bipedBody.addChild(this.exoBack);
		this.bipedRightArm.addChild(this.exoArmRight1);
		this.bipedBody.addChild(this.exoConnector1);
		this.bipedBody.addChild(this.exoConnector);
		this.bipedBody.addChild(this.exoBack4);
		this.bipedRightLeg.addChild(this.exoLegRight2);
		this.bipedRightArm.addChild(this.exoArmRight);
		this.bipedBody.addChild(this.exoConnector3);
		this.bipedLeftArm.addChild(this.exoArmLeft1);
		this.bipedBody.addChild(this.exoConnector2);
		this.bipedRightLeg.addChild(this.exoLegRight1);
		this.bipedLeftArm.addChild(this.exoArmLeft);
		this.bipedBody.addChild(this.exoBack1);
		this.bipedLeftLeg.addChild(this.exoLegLeft1);
		this.bipedLeftLeg.addChild(this.exoLegLeft2);
		this.bipedBody.addChild(this.exoBack2);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		this.bipedLeftLeg.showModel  = true;
		this.bipedRightLeg.showModel = true;
		super.render(entity, f, f1, f2, f3, f4, f5);
	}
	
	// copy attributes from player model
	public ModelBiped orient(ModelBiped parent) {
		this.isChild       = parent.isChild;
		this.isRiding      = parent.isRiding;
		this.isSneak       = parent.isSneak;
		this.leftArmPose   = parent.leftArmPose;
		this.rightArmPose  = parent.rightArmPose;
		this.swingProgress = parent.swingProgress;
		return this;
	}

	public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}
