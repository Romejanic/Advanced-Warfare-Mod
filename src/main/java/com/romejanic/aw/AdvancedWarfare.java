package com.romejanic.aw;

import com.romejanic.aw.common.CommonProxy;
import com.romejanic.aw.common.EventListener;
import com.romejanic.aw.common.ModContent;
import com.romejanic.aw.common.network.PacketExoAction;
import com.romejanic.aw.common.network.PacketUpdatePlayerVelocity;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

@Mod(modid=AdvancedWarfare.modid, name=AdvancedWarfare.name, version=AdvancedWarfare.version)
public class AdvancedWarfare {

	public static final String modid   = "aw";
	public static final String name    = "Advanced Warfare";
	public static final String version = "1.0.0";
	
	public static final ModContent content = new ModContent();
	
	@Mod.Instance(AdvancedWarfare.modid)
	public static AdvancedWarfare instance;
	
	@SidedProxy(clientSide="com.romejanic.aw.client.ClientProxy", serverSide="com.romejanic.aw.common.CommonProxy")
	public static CommonProxy proxy;
	
	public static SimpleNetworkWrapper network;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		proxy.loadModInfo(event.getModMetadata());
		content.init();
		
		MinecraftForge.EVENT_BUS.register(content);
		MinecraftForge.EVENT_BUS.register(proxy);
		MinecraftForge.EVENT_BUS.register(new EventListener());
		
		network = NetworkRegistry.INSTANCE.newSimpleChannel(modid);
		network.registerMessage(PacketExoAction.Handler.class, PacketExoAction.class, 0, Side.SERVER);
		network.registerMessage(PacketUpdatePlayerVelocity.Handler.class, PacketUpdatePlayerVelocity.class, 1, Side.CLIENT);
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event) {
		proxy.initClient();
	}
	
}