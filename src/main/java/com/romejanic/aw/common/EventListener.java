package com.romejanic.aw.common;

import com.romejanic.aw.common.exo.ExoHandler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EventListener {
	
	@SubscribeEvent
	public void livingFall(LivingFallEvent event) {
		if(ExoHandler.entityHasExo(event.getEntityLiving())) {
			event.setCanceled(true);
			event.setDamageMultiplier(0f);
		}
	}
	
	@SubscribeEvent
	public void livingJump(LivingJumpEvent event) {
		if(event.getEntityLiving() instanceof EntityPlayer && ExoHandler.entityHasExo(event.getEntityLiving())) {
			ExoHandler.onPlayerJumped(event.getEntityLiving().getUniqueID());
		}
	}
	
	@SubscribeEvent
	public void livingUpdate(LivingUpdateEvent event) {
		if(event.getEntityLiving() instanceof EntityPlayer && ExoHandler.entityHasExo(event.getEntityLiving()) && event.getEntityLiving().onGround) {
			ExoHandler.onPlayerLanded((EntityPlayer)event.getEntityLiving(), event.getEntityLiving().getUniqueID());
		}
	}
	
}