package com.krakenrek.rotatingitems;

import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class Config {
    public static final ForgeConfigSpec CLIENT_SPEC;
    public static final Client CLIENT;

    static {
        final Pair<Client, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder()
                .configure(Client::new);
        CLIENT_SPEC = specPair.getRight();
        CLIENT = specPair.getLeft();
    }

    public static class Client {

        public final ForgeConfigSpec.EnumValue<RotationType> rotationType;

        public final ForgeConfigSpec.DoubleValue scaleInHovered;

        public final ForgeConfigSpec.BooleanValue disableHighlightInHovered;

        public final ForgeConfigSpec.EnumValue<ClockType> clockType;
        public final ForgeConfigSpec.DoubleValue rotationTime;
        public final ForgeConfigSpec.DoubleValue anglePerTick;

        Client(ForgeConfigSpec.Builder builder) {

            rotationType = builder.comment("Change rotation type", "GLOBAL - all items rotate", "HOVERED - only hovered in inventories or selected in hotbar items rotate").translation("rotationType").defineEnum("rotationType", RotationType.HOVERED);

            scaleInHovered = builder.comment("Change focused item scale in HOVERED mode").translation("scaleInHovered").defineInRange("scaleInHovered", 1.1d, Float.MIN_VALUE, Float.MAX_VALUE);

            disableHighlightInHovered = builder.comment("Enable to disable hovered item highlighting").translation("disableHighlightInHovered").define("disableHighlightInHovered", true);

            clockType = builder.comment("Change clock type", "TIME - seconds per full rotation, left to right", "TIME_REVERSE - seconds per full rotation, right to left", "TICK - angle per tick, both").translation("clockType").defineEnum("clockType", ClockType.TIME);

            rotationTime = builder.comment("Change full rotation time (1 unit ~= 1 second)").translation("rotationTime").defineInRange("rotationTime", 5d, Float.MIN_VALUE, Float.MAX_VALUE);

            anglePerTick = builder.defineInRange("anglePerTick", 4, 0d, 360d);

            builder.build();
        }
    }

    public enum RotationType {
        GLOBAL, HOVERED
    }

    public enum ClockType {
        TIME, TIME_REVERSE, TICK
    }
}
