package com.romejanic.aw.client;

import java.util.HashMap;
import java.util.UUID;

import com.romejanic.aw.AdvancedWarfare;
import com.romejanic.aw.common.ModContent;
import com.romejanic.aw.common.exo.ExoAction;
import com.romejanic.aw.common.exo.ExoHandler;
import com.romejanic.aw.common.item.ItemExo;
import com.romejanic.aw.common.network.PacketExoAction;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;

public class ClientEvents {

	private static final boolean DEBUG = false;
	
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
	public void livingUpdate(LivingUpdateEvent event) {
		if(event.getEntityLiving().isEntityEqual(mc.player)) {
			if(this.exoJumped && mc.player.onGround) {
				this.exoJumped = false;
			}
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
		}
	}
	
}