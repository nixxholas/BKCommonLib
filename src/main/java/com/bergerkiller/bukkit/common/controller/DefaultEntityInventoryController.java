package com.bergerkiller.bukkit.common.controller;

import com.bergerkiller.bukkit.common.entity.nms.NMSEntityInventoryHook;
import com.bergerkiller.bukkit.common.internal.CommonNMS;
import net.minecraft.server.v1_11_R1.IInventory;
import org.bukkit.inventory.ItemStack;

@SuppressWarnings("rawtypes")
public final class DefaultEntityInventoryController extends EntityInventoryController {

    public DefaultEntityInventoryController() {
    }

    @Override
    public void onItemSet(int index, ItemStack item) {
        final Object handle = entity.getHandle();
        if (handle instanceof NMSEntityInventoryHook) {
            super.onItemSet(index, item);
        } else {
            ((IInventory) handle).setItem(index, CommonNMS.getNative(item));
        }
    }
}
