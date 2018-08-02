package com.romejanic.aw.common.network;

import java.util.UUID;

import com.romejanic.aw.AdvancedWarfare;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketUpdatePlayerVelocity implements IMessage {

	private double motionX;
	private double motionY;
	private double motionZ;
	
	public PacketUpdatePlayerVelocity() {}
	
	public PacketUpdatePlayerVelocity(EntityPlayer player) {
		this(player.motionX, player.motionY, player.motionZ);
	}
	
	public PacketUpdatePlayerVelocity(double motionX, double motionY, double motionZ) {
		this.motionX = motionX;
		this.motionY = motionY;
		this.motionZ = motionZ;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		this.motionX = buf.readDouble();
		this.motionY = buf.readDouble();
		this.motionZ = buf.readDouble();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeDouble(this.motionX);
		buf.writeDouble(this.motionY);
		buf.writeDouble(this.motionZ);
	}
	
	public double getMotionX() {
		return this.motionX;
	}
	
	public double getMotionY() {
		return this.motionY;
	}
	
	public double getMotionZ() {
		return this.motionZ;
	}
	
	public static class Handler implements IMessageHandler<PacketUpdatePlayerVelocity, IMessage> {

		@Override
		public IMessage onMessage(PacketUpdatePlayerVelocity message, MessageContext ctx) {
			AdvancedWarfare.proxy.setClientPlayerVelocity(message);
			return null;
		}
		
	}

}
