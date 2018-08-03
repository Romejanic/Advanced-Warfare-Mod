package com.romejanic.aw.client;

import com.romejanic.aw.AdvancedWarfare;
import com.romejanic.aw.client.item.ItemRendererManager;
import com.romejanic.aw.client.item.renderers.ItemRendererAK12;
import com.romejanic.aw.client.model.ModelAtlasExo;
import com.romejanic.aw.client.model.ModelSentinelExo;
import com.romejanic.aw.common.CommonProxy;
import com.romejanic.aw.common.ModContent;
import com.romejanic.aw.common.item.ItemExo.EnumExoType;
import com.romejanic.aw.common.item.ItemGun;
import com.romejanic.aw.common.network.PacketUpdatePlayerVelocity;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ClientProxy extends CommonProxy {

	private final ModelAtlasExo atlasExoModel;
	private final ModelSentinelExo sentinelExoModel;
	
	private final ItemRendererManager itemRenderers;
	
	public ClientProxy() {
		this.atlasExoModel    = new ModelAtlasExo();
		this.sentinelExoModel = new ModelSentinelExo();
		
		this.itemRenderers    = new ItemRendererManager();
	}
	
	@Override
	public void initClient() {
		ModContent content = AdvancedWarfare.content;
		this.itemRenderers.registerItem(content.ak12, new ItemRendererAK12());
		
		MinecraftForge.EVENT_BUS.register(new ClientEvents());
	}
	
	@SubscribeEvent
	public void registerModels(ModelRegistryEvent event) {
		ModContent content = AdvancedWarfare.content;
		ModelLoader.setCustomModelResourceLocation(content.atlasExo, 0, new ModelResourceLocation("aw:atlas_exo", "inventory"));
		ModelLoader.setCustomModelResourceLocation(content.sentinelExo, 0, new ModelResourceLocation("aw:sentinel_exo", "inventory"));
		ModelLoader.setCustomModelResourceLocation(content.kvaExo, 0, new ModelResourceLocation("aw:kva_exo", "inventory"));
		ModelLoader.setCustomModelResourceLocation(content.ak12, 0, new ModelResourceLocation("aw:ak12", "inventory"));
	}
	
	@Override
	public void setClientPlayerVelocity(PacketUpdatePlayerVelocity packet) {
		EntityPlayer player = Minecraft.getMinecraft().player;
		player.motionX = packet.getMotionX();
		player.motionY = packet.getMotionY();
		player.motionZ = packet.getMotionZ();
	}
	
	@Override
	public void tickGun(ItemStack stack, EntityPlayer player, World world, ItemGun gun) {
		NBTTagCompound tag = gun.getCompoundTagFromStack(stack);
		if(!gun.isReadyToFire(stack)) {
			tag.setInteger("fireCooldown", tag.getInteger("fireCooldown") - 1);
			stack.setTagCompound(tag);
			return;
		}
		
	}
	
	public ModelBiped getExoModel(EnumExoType type, ModelBiped _default) {
		switch(type) {
		case SENTINEL:
		case KVA:
			return this.sentinelExoModel.orient(_default);
		case ATLAS:
		default:
			return this.atlasExoModel.orient(_default);
		}
	}
	
}