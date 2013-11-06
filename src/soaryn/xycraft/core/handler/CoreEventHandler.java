package soaryn.xycraft.core.handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.stats.StatBase;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.Event.Result;
import net.minecraftforge.event.entity.player.BonemealEvent;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.liquids.LiquidDictionary;
import net.minecraftforge.liquids.LiquidStack;
import net.minecraftforge.liquids.LiquidDictionary.LiquidRegisterEvent;
import soaryn.xycraft.api.liquid.LiquidObject;
import soaryn.xycraft.world.XyCraftWorldBlocks;
import soaryn.xycraft.world.block.BlockCorn;
import soaryn.xycraft.world.block.BlockHenequen;

public class CoreEventHandler {

   public static HashMap achievAquireIds = new HashMap();
   public List liquidList = new ArrayList();


   public CoreEventHandler() {
      this.liquidList.add(new LiquidObject("Water", LiquidDictionary.getOrCreateLiquid("Water", new LiquidStack(Block.waterStill, 1000))));
      this.liquidList.add(new LiquidObject("Lava", LiquidDictionary.getOrCreateLiquid("Lava", new LiquidStack(Block.lavaStill, 1000))));
   }

   @ForgeSubscribe
   public void aquireItem(EntityItemPickupEvent event) {
      EntityPlayer player = event.entityPlayer;
      int id = event.item.getEntityItem().itemID;
      if(achievAquireIds.containsKey(Integer.valueOf(id))) {
         player.triggerAchievement((StatBase)achievAquireIds.get(Integer.valueOf(id)));
      }

   }

   @ForgeSubscribe
   public void liquidCatch(LiquidRegisterEvent event) {
      LiquidObject liquid = new LiquidObject(event.Name, event.Liquid);
      if(!this.liquidList.contains(liquid)) {
         this.liquidList.add(liquid);
      }

   }

   @ForgeSubscribe
   public void fertilize(BonemealEvent event) {
      World world = event.world;
      int blockId = event.ID;
      int x = event.X;
      int y = event.Y;
      int z = event.Z;
      if(blockId == XyCraftWorldBlocks.corn.blockID) {
         if(!((BlockCorn)XyCraftWorldBlocks.corn).isFullyGrown(world, x, y, z)) {
            if(!world.isRemote) {
               ((BlockCorn)XyCraftWorldBlocks.corn).fertilize(world, x, y, z);
               event.setResult(Result.ALLOW);
            }

         } else {
            event.setCanceled(true);
         }
      } else if(blockId == XyCraftWorldBlocks.henequen.blockID) {
         if(!((BlockHenequen)XyCraftWorldBlocks.henequen).isFullyGrown(world, x, y, z)) {
            if(!world.isRemote) {
               ((BlockHenequen)XyCraftWorldBlocks.henequen).fertilize(world, x, y, z);
               event.setResult(Result.ALLOW);
            }

         } else {
            event.setCanceled(true);
         }
      }
   }

}
