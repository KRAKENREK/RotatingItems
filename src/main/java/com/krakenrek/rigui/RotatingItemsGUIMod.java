package com.krakenrek.rigui;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;

@Mod(RotatingItemsGUIMod.MODID)
public class RotatingItemsGUIMod
{
    public static final String MODID = "rigui";

    public RotatingItemsGUIMod()
    {
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, Config.CLIENT_SPEC);
        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, ClientProxy::init);
    }

}
