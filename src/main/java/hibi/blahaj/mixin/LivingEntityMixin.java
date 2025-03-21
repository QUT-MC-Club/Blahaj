package hibi.blahaj.mixin;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;

import java.util.List;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Redirect;

import hibi.blahaj.Blahaj;
import hibi.blahaj.TrinketsHelper;

import org.spongepowered.asm.mixin.injection.At;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {
    @Redirect(method = "method_30120", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;copy()Lnet/minecraft/item/ItemStack;", ordinal = 0))
    ItemStack modifyHeadSlotItem(ItemStack instance, @SuppressWarnings("rawtypes") List list, EquipmentSlot slot,
            ItemStack stack) {
        if (Blahaj.DEV_ENV) {
            Blahaj.LOGGER.info("modifyHeadSlotItem (LivingEntityMixin) Mixin called");
        }
        if ((LivingEntity) (Object) this instanceof PlayerEntity) {
            if (slot.getEntitySlotId() == 3) {
                LivingEntity livingEntity = (LivingEntity) (Object) this;
                PlayerEntity playerEntity = (PlayerEntity) livingEntity;
                ItemStack hatStack = TrinketsHelper.getHatItem((ServerPlayerEntity) playerEntity);
                if (hatStack != ItemStack.EMPTY) {
                    return hatStack;
                }
            }
        }
        return instance.copy();
    }
}
