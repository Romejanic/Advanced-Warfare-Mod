package com.romejanic.aw.common.utils;

import java.util.UUID;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.FMLCommonHandler;

public class AWUtils {

	public static EntityPlayerMP getPlayerByUUID(UUID uuid) {
		MinecraftServer server = FMLCommonHandler.instance().getMinecraftServerInstance();
		if(server == null) {
			return null;
		}
		return server.getPlayerList().getPlayerByUUID(uuid);
	}
	
	public static void spawnParticles(World world, EnumParticleTypes type, int count, double x, double y, double z, double dx, double dy, double dz, double speed) {
		if(!world.isRemote && world instanceof WorldServer) {
			((WorldServer)world).spawnParticle(type, x, y, z, count, dx, dy, dz, speed);
		}
	}
	
	public static float randomRange(float min, float max) {
		return min + (max - min) * (float)Math.random();
	}
	
	public static int nextRandomInt(int n) {
		return (int)(Math.random() * n);
	}
	
}