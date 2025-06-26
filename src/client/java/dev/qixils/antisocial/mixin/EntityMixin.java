package dev.qixils.antisocial.mixin;

import dev.qixils.antisocial.Antisocial;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public abstract class EntityMixin {
    @Inject(method = "shouldSpawnSprintingParticles", at = @At("HEAD"), cancellable = true)
    private void shouldSpawnSprintingParticles(CallbackInfoReturnable<Boolean> cir) {
        Entity entity = (Entity)(Object)this;
        MinecraftClient client = MinecraftClient.getInstance();
        boolean isOtherPlayer = entity instanceof AbstractClientPlayerEntity && entity != client.player;

        if (Antisocial.SKIP_RENDER && isOtherPlayer) {
            cir.setReturnValue(false);
        }
    }
}
