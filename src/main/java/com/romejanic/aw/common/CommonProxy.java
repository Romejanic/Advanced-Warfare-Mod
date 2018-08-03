package com.romejanic.aw.common;

import java.util.Arrays;

import com.romejanic.aw.AdvancedWarfare;
import com.romejanic.aw.common.item.ItemGun;
import com.romejanic.aw.common.network.PacketUpdatePlayerVelocity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.ModMetadata;

public class CommonProxy {

	public void initClient() {}
	public void tickGun(ItemStack stack, EntityPlayer player, World world, ItemGun item) {}
	public void setClientPlayerVelocity(PacketUpdatePlayerVelocity packet) {}
	
	public void loadModInfo(ModMetadata data) {
		data.autogenerated = false;
		data.useDependencyInformation = false;
		
		data.modId       = AdvancedWarfare.modid;
		data.name        = AdvancedWarfare.name;
		data.version     = AdvancedWarfare.version;
		data.authorList  = Arrays.asList(TextFormatting.RED + "R" + TextFormatting.GREEN + "omejanic");
		data.credits     = "by Romejanic";
		data.url         = "http://planetminecraft.com/member/RomejanicDev/";
		data.description = "Adds exosuits and guns from Call of Duty: Advanced Warfare";
	}
	
}