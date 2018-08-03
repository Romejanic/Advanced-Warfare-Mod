package com.romejanic.aw.client;

import com.romejanic.aw.AdvancedWarfare;
import com.romejanic.aw.common.ModContent;
import com.romejanic.aw.common.exo.ExoAction;
import com.romejanic.aw.common.exo.ExoHandler;
import com.romejanic.aw.common.item.ItemGun;
import com.romejanic.aw.common.network.PacketExoAction;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumHand;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;

public class ClientEvents {

	private static final boolean DEBUG = true;
	
	private final Minecraft mc;
	private final ModContent content;
	
	private boolean exoJumped = false;
	
	public ClientEvents() {
		this.mc = Minecraft.getMinecraft();
		this.content = AdvancedWarfare.content;
	}
	
	@SubscribeEvent
	public void keyInput(InputEvent.KeyInputEvent event) {
		if(!this.exoJumped && mc.gameSettings.keyBindJump.isPressed() && !mc.player.isCreative() && !mc.player.isSpectator()) {
			EntityPlayer player = mc.player;
			if(ExoHandler.entityHasExo(player) && !player.onGround) {
				AdvancedWarfare.network.sendToServer(new PacketExoAction(player.getUniqueID(), ExoAction.EXO_BOOST));
				this.exoJumped = true;
			}
		}
		if(this.exoJumped && mc.gameSettings.keyBindSneak.isPressed() && !mc.player.isCreative() && !mc.player.isSpectator()) {
			EntityPlayer player = mc.player;
			if(ExoHandler.entityHasExo(player) && !player.onGround) {
				AdvancedWarfare.network.sendToServer(new PacketExoAction(player.getUniqueID(), ExoAction.GROUND_SLAM));
			}
		}
	}
	
	@SubscribeEvent
	public void clientTick(ClientTickEvent event) {
		if(event.phase == TickEvent.Phase.END) {
			EntityPlayer player = mc.player;
			if(player != null && mc.gameSettings.keyBindAttack.isKeyDown() && !player.isSneaking()) {
				ItemStack mainHand = player.getItemStackFromSlot(EntityEquipmentSlot.MAINHAND);
				if(mainHand != null && mainHand.getItem() instanceof ItemGun) {
					AdvancedWarfare.proxy.tickGun(mainHand, player, player.world, (ItemGun)mainHand.getItem());
				}
			}
		}
	}
	
	@SubscribeEvent
	public void livingUpdate(LivingUpdateEvent event) {
		if(event.getEntityLiving().isEntityEqual(mc.player)) {
			if(this.exoJumped && mc.player.onGround) {
				this.exoJumped = false;
			}
		}
	}
	
	@SubscribeEvent
	public void renderPlayer(RenderPlayerEvent.Pre event) {
		ItemStack mainHand = event.getEntityPlayer().getHeldItem(EnumHand.MAIN_HAND);
		if(mainHand != null && mainHand.getItem() instanceof ItemGun) {
			ModelPlayer model  = event.getRenderer().getMainModel();
			model.rightArmPose = ModelBiped.ArmPose.BOW_AND_ARROW; // TODO: get this working
		}
	}
	
	@SubscribeEvent
	public void drawHud(RenderGameOverlayEvent.Text event) {
		if(!DEBUG) {
			return;
		}
		if(event.getType() == ElementType.TEXT) {
			FontRenderer fr = mc.fontRenderer;
			EntityPlayer  p = mc.player;
			
			fr.drawStringWithShadow("isAirBorne: " + p.isAirBorne, 10, 10, 0xFFFFFF);
			fr.drawStringWithShadow("onGround: " + p.onGround, 10, 20, 0xFFFFFF);
			fr.drawStringWithShadow("motionY: " + p.motionY, 10, 30, 0xFFFFFF);
			fr.drawStringWithShadow("exoJumped: " + exoJumped, 10, 40, 0xFFFFFF);
			
			ItemStack mainHand = p.getHeldItem(EnumHand.MAIN_HAND);
			if(mainHand != null && mainHand.getItem() instanceof ItemGun) {
				NBTTagCompound nbt = ((ItemGun)mainHand.getItem()).getCompoundTagFromStack(mainHand);
				fr.drawStringWithShadow("fireCooldown: " + nbt.getInteger("fireCooldown"), 10, 50, 0xFFFFFF);
			}
		}
	}
	
}