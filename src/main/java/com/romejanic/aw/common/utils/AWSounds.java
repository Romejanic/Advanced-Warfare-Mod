package com.romejanic.aw.common.utils;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.registries.IForgeRegistry;

public class AWSounds {

	public static SoundEvent EXO_HOVER;
	public static SoundEvent AK12_FIRE;
	
	public static void registerSounds(IForgeRegistry<SoundEvent> registry) {
		registry.register(EXO_HOVER = getSound("aw:exo_hover"));
		registry.register(AK12_FIRE = getSound("aw:gun_ak12_fire"));
	}
	
	private static SoundEvent getSound(String location) {
		ResourceLocation resource = new ResourceLocation(location);
		SoundEvent event = new SoundEvent(resource);
		event.setRegistryName(resource);
		return event;
	}
	
}