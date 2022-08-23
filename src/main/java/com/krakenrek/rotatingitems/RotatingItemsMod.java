package com.krakenrek.rotatingitems;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;

@Mod(RotatingItemsMod.MODID)
public class RotatingItemsMod
{
    public static final String MODID = "rotatingitems";

    public RotatingItemsMod()
    {
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, Config.CLIENT_SPEC);
        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, ClientProxy::init);
    }

}
