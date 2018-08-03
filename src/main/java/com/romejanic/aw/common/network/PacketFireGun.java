package com.romejanic.aw.common.network;

import java.util.UUID;

import com.romejanic.aw.common.item.ItemGun;
import com.romejanic.aw.common.utils.AWUtils;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketFireGun implements IMessage {
	
	private UUID player;
	
	public PacketFireGun() {}
	
	public PacketFireGun(EntityPlayer player) {
		this(player.getUniqueID());
	}
	
	public PacketFireGun(UUID player) {
		this.player = player;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		this.player = UUID.fromString(ByteBufUtils.readUTF8String(buf));
	}

	@Override
	public void toBytes(ByteBuf buf) {
		ByteBufUtils.writeUTF8String(buf, this.player.toString());
	}
	
	public static class Handler implements IMessageHandler<PacketFireGun, IMessage> {

		@Override
		public IMessage onMessage(PacketFireGun message, MessageContext ctx) {
			EntityPlayerMP player = AWUtils.getPlayerByUUID(message.player);
			if(player != null) { 
				ItemStack mainHand = player.getHeldItem(EnumHand.MAIN_HAND);
				if(mainHand != null && mainHand.getItem() instanceof ItemGun) {
					((ItemGun)mainHand.getItem()).fire(mainHand, player, player.world);
				}
			}
			return null;
		}
		
	}

}
