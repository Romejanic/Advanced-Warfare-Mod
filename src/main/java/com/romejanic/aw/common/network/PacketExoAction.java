package com.romejanic.aw.common.network;

import java.util.UUID;

import com.romejanic.aw.common.exo.ExoAction;
import com.romejanic.aw.common.exo.ExoHandler;
import com.romejanic.aw.common.utils.AWUtils;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketExoAction implements IMessage {

	private UUID player;
	private ExoAction action;
	
	public PacketExoAction() {}
	
	public PacketExoAction(UUID player, ExoAction action) {
		this.player = player;
		this.action = action;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		this.player = UUID.fromString(ByteBufUtils.readUTF8String(buf));
		this.action = ExoAction.valueOf(ByteBufUtils.readUTF8String(buf));
	}

	@Override
	public void toBytes(ByteBuf buf) {
		ByteBufUtils.writeUTF8String(buf, this.player.toString());
		ByteBufUtils.writeUTF8String(buf, this.action.name());
	}
	
	public static class Handler implements IMessageHandler<PacketExoAction, PacketUpdatePlayerVelocity> {

		@Override
		public PacketUpdatePlayerVelocity onMessage(PacketExoAction message, MessageContext ctx) {
			EntityPlayerMP player = AWUtils.getPlayerByUUID(message.player);
			if(player != null) {
				return ExoHandler.performExoAction(player, message.action);
			}
			return null;
		}
		
	}

}