package com.romejanic.aw.common;

import com.romejanic.aw.common.item.ItemAK12;
import com.romejanic.aw.common.item.ItemExo;
import com.romejanic.aw.common.item.ItemExo.EnumExoType;
import com.romejanic.aw.common.utils.AWSounds;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ModContent {

	public Item atlasExo;
	public Item sentinelExo;
	public Item kvaExo;
	
	public Item ak12;

	public void init() {
		this.atlasExo = new ItemExo(EnumExoType.ATLAS).setCreativeTab(this.tabAW).setRegistryName("atlas_exo").setUnlocalizedName("aw.atlas_exo");
		this.sentinelExo = new ItemExo(EnumExoType.SENTINEL).setCreativeTab(this.tabAW).setRegistryName("sentinel_exo").setUnlocalizedName("aw.sentinel_exo");
		this.kvaExo = new ItemExo(EnumExoType.KVA).setCreativeTab(this.tabAW).setRegistryName("kva_exo").setUnlocalizedName("aw.kva_exo");
		
		this.ak12 = new ItemAK12().setCreativeTab(this.tabAW).setRegistryName("ak12").setUnlocalizedName("aw.ak12");
	}

	@SubscribeEvent
	public void registerItems(RegistryEvent.Register<Item> event) {
		event.getRegistry().registerAll(
			this.atlasExo,
			this.sentinelExo,
			this.kvaExo,
			
			this.ak12
		);
	}

	@SubscribeEvent
	public void registerSounds(RegistryEvent.Register<SoundEvent> event) {
		AWSounds.registerSounds(event.getRegistry());
	}

	public CreativeTabs tabAW = new CreativeTabs("aw") {
		@Override
		public ItemStack getTabIconItem() {
			return new ItemStack(ModContent.this.atlasExo);
		}
	};

}