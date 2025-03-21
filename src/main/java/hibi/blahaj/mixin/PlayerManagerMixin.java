package hibi.blahaj.mixin;

import org.spongepowered.asm.mixin.Mixin;

import net.minecraft.item.ItemStack;
import net.minecraft.network.ClientConnection;
import net.minecraft.network.packet.s2c.play.ScreenHandlerSlotUpdateS2CPacket;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.network.ConnectedClientData;
import net.minecraft.server.network.ServerPlayerEntity;

import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import hibi.blahaj.Blahaj;
import hibi.blahaj.TrinketsHelper;

@Mixin(PlayerManager.class)
public class PlayerManagerMixin {
    @Inject(
        method = "onPlayerConnect",
        at = @At( value = "TAIL" )
)
    void modifyHeadSlotItem (ClientConnection connection, ServerPlayerEntity player, ConnectedClientData clientData, CallbackInfo ci) {
        if (Blahaj.DEV_ENV) {
                    Blahaj.LOGGER.info("modifyHeadSlotItem (PlayerManagerMixin) Mixin called");
                }
        ScreenHandler handler = player.currentScreenHandler;
        ItemStack itemStack = TrinketsHelper.getHatItem(player);
        if (itemStack != ItemStack.EMPTY) {
            player.networkHandler.sendPacket(new ScreenHandlerSlotUpdateS2CPacket(handler.syncId, handler.nextRevision(), 5, itemStack));
        }
    }
    @Inject(
        method = "sendPlayerStatus",
        at = @At( value = "TAIL" )
    )
    void modifySendPlayerStatus(ServerPlayerEntity player, CallbackInfo ci) {
        if (Blahaj.DEV_ENV) {
            Blahaj.LOGGER.info("modifySendPlayerStatus (PlayerManagerMixin) Mixin called");
        }
        ScreenHandler handler = player.currentScreenHandler;
        ItemStack itemStack = TrinketsHelper.getHatItem(player);
        if (itemStack != ItemStack.EMPTY) {
            player.networkHandler.sendPacket(new ScreenHandlerSlotUpdateS2CPacket(handler.syncId, handler.nextRevision(), 5, itemStack));
        }

    }
}
