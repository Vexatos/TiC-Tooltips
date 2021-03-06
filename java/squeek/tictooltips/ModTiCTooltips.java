package squeek.tictooltips;

import java.util.logging.Logger;
import net.minecraftforge.common.MinecraftForge;
import squeek.tictooltips.helpers.ToolHelper;
import squeek.tictooltips.helpers.ToolPartHelper;
import squeek.tictooltips.proxy.ProxyExtraTiC;
import squeek.tictooltips.proxy.ProxyIguanaTweaks;
import squeek.tictooltips.proxy.ProxyMariculture;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@Mod(modid = ModInfo.MODID, version = ModInfo.VERSION, dependencies = "required-after:TConstruct;after:ExtraTiC;after:TSteelworks;after:Mariculture")
public class ModTiCTooltips
{
	public static boolean hasIguanaTweaks;
	public static final Logger Log = Logger.getLogger(ModInfo.MODID);
	static
	{
		Log.setParent(FMLLog.getLogger());
	}

	@SideOnly(Side.CLIENT)
	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		MinecraftForge.EVENT_BUS.register(new TooltipHandler());
	}

	@SideOnly(Side.CLIENT)
	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		if (Loader.isModLoaded("ExtraTiC"))
		{
			ProxyExtraTiC.init();
		}
		if (Loader.isModLoaded("Mariculture"))
		{
			ProxyMariculture.init();
		}
		if (Loader.isModLoaded("IguanaTweaksTConstruct"))
		{
			hasIguanaTweaks = true;
			ProxyIguanaTweaks.init();
		}

		ToolHelper.init();
		ToolPartHelper.determineMinAndMaxValues();
	}
}
