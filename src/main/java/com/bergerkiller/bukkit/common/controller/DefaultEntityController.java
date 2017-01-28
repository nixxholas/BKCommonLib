package com.bergerkiller.bukkit.common.controller;

import com.bergerkiller.bukkit.common.entity.nms.NMSEntityHook;
import com.bergerkiller.bukkit.common.internal.CommonNMS;
import com.bergerkiller.bukkit.common.reflection.classes.EntityRef;
import net.minecraft.server.v1_11_R1.DamageSource;
import net.minecraft.server.v1_11_R1.Entity;
import net.minecraft.server.v1_11_R1.EnumHand;
import org.bukkit.entity.HumanEntity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.MainHand;

/**
 * Does nothing but redirect to the default entity behavior
 */
@SuppressWarnings("rawtypes")
public final class DefaultEntityController extends EntityController {

    public DefaultEntityController() {
    }

    @Override
    public void onDie() {
        final Object handle = entity.getHandle();
        if (handle instanceof NMSEntityHook) {
            super.onDie();
        } else {
//			((Entity) handle).isI
            ((Entity) handle).die();
        }
    }

    @Override
    public void onTick() {
        final Object handle = entity.getHandle();
        if (handle instanceof NMSEntityHook) {
            super.onTick();
        } else {
            ((Entity) handle).U(); //Tick the entity
        }
    }

    @Override
    public boolean onInteractBy(HumanEntity interacter, ItemStack is, MainHand hand) {
        final Object handle = entity.getHandle();
        if (handle instanceof NMSEntityHook) {
            return super.onInteractBy(interacter, is, hand);
        } else {
            return ((Entity) handle).a(CommonNMS.getNative(interacter), CommonNMS.getNative(interacter).getItemInMainHand(), EnumHand.MAIN_HAND);
        }
    }

    @Override
    public void onDamage(com.bergerkiller.bukkit.common.wrappers.DamageSource damageSource, double damage) {
        if (entity.getHandle() instanceof NMSEntityHook) {
            super.onDamage(damageSource, damage);
        }
        ((Entity) entity.getHandle()).damageEntity((DamageSource) damageSource.getHandle(), (float) damage);
    }

    @Override
    public void onBurnDamage(double damage) {
        final Object handle = entity.getHandle();
        if (handle instanceof NMSEntityHook) {
            super.onBurnDamage(damage);
        } else {
            EntityRef.burn(handle, (float) damage);
        }
    }

    @Override
    public String getLocalizedName() {
        final Object handle = entity.getHandle();
        if (handle instanceof NMSEntityHook) {
            return super.getLocalizedName();
        } else {
            return ((Entity) handle).getName();
        }
    }

    @Override
    public void onPush(double dx, double dy, double dz) {
        final Object handle = entity.getHandle();
        if (handle instanceof NMSEntityHook) {
            ((NMSEntityHook) handle).super_g(dx, dy, dz);
        } else {
            ((Entity) handle).setPosition(dx, dy, dz);
        }
    }

    @Override
    public void onMove(double dx, double dy, double dz) {
        final Object handle = entity.getHandle();
        if (handle instanceof NMSEntityHook) {
            ((NMSEntityHook) handle).super_move(dx, dy, dz);
        } else {
            ((Entity) handle).setPosition(dx, dy, dz);
        }
    }
}
