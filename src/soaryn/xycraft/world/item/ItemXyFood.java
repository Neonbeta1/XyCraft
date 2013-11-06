package soaryn.xycraft.world.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import soaryn.xycraft.core.XyCraft;

public class ItemXyFood extends ItemFood {

   private PotionEffect[] effects;
   private boolean cureAll;


   public ItemXyFood(int id, int food, float sat) {
      this(id, food, sat, false, new PotionEffect[0]);
   }

   public ItemXyFood(int par1, int par2, float par3, boolean cureAll, PotionEffect ... effects) {
      super(par1, par2, par3, false);
      this.cureAll = false;
      this.effects = effects;
      this.cureAll = cureAll;
      this.setCreativeTab(XyCraft.tabItems);
   }

   public void onFoodEaten(ItemStack stack, World world, EntityPlayer player) {
      if(!world.isRemote && this.cureAll) {
         player.curePotionEffects(stack);
      }

      //return super.onFoodEaten(stack, world, player);
   }

   protected void func_77849_c(ItemStack stack, World world, EntityPlayer player) {
      if(!world.isRemote && this.effects.length > 0) {
         PotionEffect[] arr$ = this.effects;
         int len$ = arr$.length;

         for(int i$ = 0; i$ < len$; ++i$) {
            PotionEffect effect = arr$[i$];
            player.addPotionEffect(new PotionEffect(effect));
         }
      }

   }

   public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
      par3EntityPlayer.setItemInUse(par1ItemStack, this.getMaxItemUseDuration(par1ItemStack));
      return par1ItemStack;
   }
}
