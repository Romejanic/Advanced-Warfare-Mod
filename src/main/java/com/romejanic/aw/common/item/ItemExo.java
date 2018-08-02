package com.romejanic.aw.common.item;

import com.romejanic.aw.AdvancedWarfare;
import com.romejanic.aw.client.ClientProxy;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemExo extends ItemArmor {

	private static ArmorMaterial material = EnumHelper.addArmorMaterial("exo", "", 10, new int[]{ 0, 10, 0, 0 }, 0, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 100f);
	
	private final EnumExoType exoType;
	
	public ItemExo(EnumExoType exoType) {
		super(material, 1, EntityEquipmentSlot.CHEST);
		this.exoType = exoType;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, EntityEquipmentSlot armorSlot, ModelBiped _default) {
		return ((ClientProxy)AdvancedWarfare.proxy).getExoModel(this.exoType, _default);
    }
	
	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
        return this.exoType.texture;
    }
	
	public enum EnumExoType {
		ATLAS("aw:textures/model/exo_atlas.png"),
		SENTINEL("aw:textures/model/exo_sentinel.png"),
		KVA("aw:textures/model/exo_kva.png");
		
		public final String texture;
		EnumExoType(String textureName) {
			this.texture = textureName;
		}
	}

}