package com.romejanic.aw.common.utils;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;

public class DamageSourceGroundSlam extends EntityDamageSource {

	public DamageSourceGroundSlam(Entity damageSourceEntity) {
		super("groundslam", damageSourceEntity);
	}
	
	public ITextComponent getDeathMessage(EntityLivingBase entityLivingBaseIn) {
		return new TextComponentTranslation("death.aw." + this.damageType, entityLivingBaseIn.getDisplayName().getFormattedText(), this.damageSourceEntity.getDisplayName().getFormattedText());
	}

}