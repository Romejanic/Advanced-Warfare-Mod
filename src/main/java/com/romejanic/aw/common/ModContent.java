package com.romejanic.aw.common;

import com.romejanic.aw.common.item.ItemExo;
import com.romejanic.aw.common.item.ItemExo.EnumExoType;
import com.romejanic.aw.common.utils.AWSounds;

import net.minecraft.item.Item;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ModContent {

	public Item atlasExo;
	public Item sentinelExo;
	public Item kvaExo;
	
	public void init() {
		this.atlasExo = new ItemExo(EnumExoType.ATLAS).setRegistryName("atlas_exo").setUnlocalizedName("aw.atlas_exo");
		this.sentinelExo = new ItemExo(EnumExoType.SENTINEL).setRegistryName("sentinel_exo").setUnlocalizedName("aw.sentinel_exo");
		this.kvaExo = new ItemExo(EnumExoType.KVA).setRegistryName("kva_exo").setUnlocalizedName("aw.kva_exo");
	}
	
	@SubscribeEvent
	public void registerItems(RegistryEvent.Register<Item> event) {
		event.getRegistry().registerAll(
				this.atlasExo,
				this.sentinelExo,
				this.kvaExo
		);
	}
	
	@SubscribeEvent
	public void registerSounds(RegistryEvent.Register<SoundEvent> event) {
		AWSounds.registerSounds(event.getRegistry());
	}
	
}