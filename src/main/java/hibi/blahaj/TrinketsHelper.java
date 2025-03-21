package hibi.blahaj;

import dev.emi.trinkets.api.TrinketsApi;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;

public class TrinketsHelper {
    public static ItemStack getHatItem(ServerPlayerEntity player) {
        try {
            Class.forName("dev.emi.trinkets.api.TrinketsApi", true, TrinketsApi.class.getClassLoader());
            ItemStack hatSlot = TrinketsApi.getTrinketComponent(player).orElse(null).getInventory().get("head")
                    .get("hat")
                    .getStack(0);
            if (Blahaj.DEV_ENV) {
                Blahaj.LOGGER.info(player.getName().getString() + "'s hat slot: " + hatSlot.toString());
            }
            return hatSlot;
        } catch (ClassNotFoundException e) {
            Blahaj.LOGGER.warn("Trinkets is not installed!");
            Blahaj.LOGGER.warn(e.getMessage());
            return new ItemStack(Items.AIR);
        }
    }
}
