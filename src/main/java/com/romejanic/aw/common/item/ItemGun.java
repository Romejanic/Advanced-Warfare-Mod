package com.romejanic.aw.common.item;

import com.romejanic.aw.common.utils.AWSounds;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public abstract class ItemGun extends Item {

	public ItemGun() {
		this.setMaxStackSize(1);
		this.setMaxDamage(0);
	}

	protected abstract SoundEvent getFireSound();
	protected abstract int getFireRate();
	protected abstract int getMaxAmmo();

	public void fire(ItemStack stack, EntityPlayer player, World world) {
		NBTTagCompound tag = getCompoundTagFromStack(stack);
		if(!isReadyToFire(stack) || (getRemainingAmmo(stack) <= 0 && !player.isCreative())) {
			return;
		}
		tag.setInteger("remainingAmmo", tag.getInteger("remainingAmmo") - 1);
		tag.setInteger("fireCooldown", getFireRateTicks());
		stack.setTagCompound(tag);

		if(!world.isRemote) {
			float frp    = getFireRatePartial();
			int nBullets = 1;
			if(frp < 1f) {
				nBullets = Math.round(1f / frp);
			}
			
			// TODO: loop through nbullets and spawn bullets
			world.playSound(null, player.posX, player.posY, player.posZ, getFireSound(), SoundCategory.PLAYERS, 1f, 1f);
		}
	}

	public NBTTagCompound getCompoundTagFromStack(ItemStack stack) {
		if(stack.hasTagCompound()) {
			return stack.getTagCompound();
		}
		NBTTagCompound nbt = new NBTTagCompound();
		return nbt;
	}

	public void reload(ItemStack stack, EntityPlayer player) {
		if(player.isCreative()) { // TODO: check if player has ammo and take one from them
			NBTTagCompound tag = getCompoundTagFromStack(stack);
			if(tag.getInteger("remainingAmmo") < getMaxAmmo()) {
				tag.setInteger("remainingAmmo", getMaxAmmo());
				stack.setTagCompound(tag);
			}
		}
	}

	public boolean isReadyToFire(ItemStack stack) {
		return getCompoundTagFromStack(stack).getInteger("fireCooldown") <= 0;
	}

	public int getRemainingAmmo(ItemStack stack) {
		return Math.max(getCompoundTagFromStack(stack).getInteger("remainingAmmo"), 0);
	}

	private int getFireRateTicks() {
		return Math.max(1, Math.round(getFireRatePartial()));
	}
	
	private float getFireRatePartial() {
		return Math.max(0f, 20f / (float)getFireRate());
	}
	
	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		NBTTagCompound nbt = getCompoundTagFromStack(stack);
		if(nbt.getInteger("fireCooldown") > 0) {
			nbt.setInteger("fireCooldown", nbt.getInteger("fireCooldown") - 1);
			stack.setTagCompound(nbt);
		}
    }
	
	@Override
	public void onCreated(ItemStack stack, World worldIn, EntityPlayer playerIn) {
		NBTTagCompound nbt = getCompoundTagFromStack(stack);
		nbt.setInteger("fireCooldown", 0);
		nbt.setInteger("remainingAmmo", getMaxAmmo());
		stack.setTagCompound(nbt);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		return new ActionResult<ItemStack>(EnumActionResult.FAIL, playerIn.getHeldItem(handIn));
    }
	
	@Override
	public boolean onEntitySwing(EntityLivingBase entityLiving, ItemStack stack) {
		return !entityLiving.isSneaking();
	}

	@Override
	public boolean onBlockStartBreak(ItemStack stack, BlockPos pos, EntityPlayer player) {
		return !player.isSneaking();
	}

	@Override
	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
		return !player.isSneaking();
	}
	
	@Override
	public boolean canDestroyBlockInCreative(World world, BlockPos pos, ItemStack stack, EntityPlayer player) {
		return !player.isSneaking();
    }

}