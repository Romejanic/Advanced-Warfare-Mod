package com.romejanic.aw.common.exo;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.romejanic.aw.common.item.ItemExo;
import com.romejanic.aw.common.network.PacketUpdatePlayerVelocity;
import com.romejanic.aw.common.utils.AWSounds;
import com.romejanic.aw.common.utils.AWUtils;
import com.romejanic.aw.common.utils.DamageSourceGroundSlam;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;

public class ExoHandler {

	public static final double JUMP_BOOST    = 2.5d;
	public static final double DIRECTION_MUL = 1d;
	public static final float  SLAM_DAMAGE   = 10f;
	public static final float  SLAM_SPREAD   = 20f;
	public static final float  SLAM_RADIUS   = 2.5f;
	
	private static final ArrayList<UUID> exoJumpCandidates = new ArrayList<UUID>();
	private static final ArrayList<UUID> groundSlamCandidates = new ArrayList<UUID>();
	private static final ArrayList<UUID> groundSlamPerformed  = new ArrayList<UUID>();

	public static PacketUpdatePlayerVelocity performExoAction(EntityPlayerMP player, ExoAction action) {
		if(player.world.isRemote || !entityHasExo(player)) {
			return null;
		}
		switch(action) {
		case EXO_BOOST:
			if(!player.onGround && exoJumpCandidates.contains(player.getUniqueID())) {
				double mul = 0.5d;
				Potion jumpBoost = Potion.getPotionFromResourceLocation("minecraft:jump_boost");
				if(player.isPotionActive(jumpBoost)) {
					PotionEffect effect = player.getActivePotionEffect(jumpBoost);
					mul += (double)(effect.getAmplifier() + 1) * 0.1d;
				}
				player.motionX *= JUMP_BOOST * DIRECTION_MUL;
				player.motionY  = JUMP_BOOST * mul;
				player.motionZ *= JUMP_BOOST * DIRECTION_MUL;
				exoJumpCandidates.remove(player.getUniqueID());
				groundSlamCandidates.add(player.getUniqueID());
				player.world.playSound(null, player.posX, player.posY, player.posZ, AWSounds.EXO_HOVER, SoundCategory.PLAYERS, 1f, AWUtils.randomRange(0.9f, 1.2f));
				AWUtils.spawnParticles(player.world, EnumParticleTypes.EXPLOSION_NORMAL, 3 + AWUtils.nextRandomInt(4), player.posX, player.posY, player.posZ, -player.motionX, -player.motionY, -player.motionZ, 1d/JUMP_BOOST);
				return new PacketUpdatePlayerVelocity(player);
			}
			break;
		case GROUND_SLAM:
			if(!player.onGround && groundSlamCandidates.contains(player.getUniqueID())) {
				double mul = 0.5d;
				player.motionX = -MathHelper.sin(player.rotationYaw * (float)Math.PI / 180f) * JUMP_BOOST * DIRECTION_MUL;
				player.motionY = -JUMP_BOOST * mul;
				player.motionZ =  MathHelper.cos(player.rotationYaw * (float)Math.PI / 180f) * JUMP_BOOST * DIRECTION_MUL;
				groundSlamCandidates.remove(player.getUniqueID());
				groundSlamPerformed.add(player.getUniqueID());
				return new PacketUpdatePlayerVelocity(player);
			}
			break;
		default:
			break;
		}
		return null;
	}
	
	private static void doGroundSlam(EntityPlayer player) {
		if(!groundSlamPerformed.contains(player.getUniqueID())) {
			return;
		}
		groundSlamPerformed.remove(player.getUniqueID());
		AxisAlignedBB box = player.getEntityBoundingBox().grow(SLAM_SPREAD * 0.5f);
		List<Entity> list = player.world.getEntitiesInAABBexcluding(player, box, null);
		for(Entity entity : list) {
			float inverseDist = 1f / (entity.getDistance(player) / SLAM_RADIUS);
			entity.attackEntityFrom(new DamageSourceGroundSlam(player), SLAM_DAMAGE * inverseDist);
		}
	}

	public static boolean entityHasExo(EntityLivingBase entity) {
		if(entity == null) {
			return false;
		}
		ItemStack chest = entity.getItemStackFromSlot(EntityEquipmentSlot.CHEST);
		return chest != null && chest.getItem() instanceof ItemExo;
	}

	public static void onPlayerJumped(UUID uuid) {
		if(!exoJumpCandidates.contains(uuid)) {
			exoJumpCandidates.add(uuid);
		}
		if(groundSlamCandidates.contains(uuid)) {
			groundSlamCandidates.remove(uuid);
		}
		if(groundSlamPerformed.contains(uuid)) {
			groundSlamPerformed.remove(uuid);
		}
	}

	public static void onPlayerLanded(EntityPlayer player, UUID uuid) { 
		if(exoJumpCandidates.contains(uuid)) {
			exoJumpCandidates.remove(uuid);
		}
		if(groundSlamCandidates.contains(uuid)) {
			groundSlamCandidates.remove(uuid);
		}
		if(groundSlamPerformed.contains(uuid)) {
			doGroundSlam(player);
		}
	}

}