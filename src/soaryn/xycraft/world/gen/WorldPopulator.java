package soaryn.xycraft.world.gen;

import cpw.mods.fml.common.IWorldGenerator;
import java.util.Random;
import java.util.logging.Level;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.ChunkProviderEnd;
import net.minecraft.world.gen.ChunkProviderHell;
import net.minecraft.world.gen.feature.WorldGenerator;
import soaryn.xycraft.core.XyCraft;
import soaryn.xycraft.world.XyCraftWorldBlocks;
import soaryn.xycraft.world.gen.WorldPopCrystal;
import soaryn.xycraft.world.gen.WorldPopMinable;

public class WorldPopulator implements IWorldGenerator {

   protected World currentWorld;
   protected int chunkX;
   protected int chunkZ;
   protected Random rand;
   public static WorldGenerator crystalGen = new WorldPopCrystal();
   public static WorldGenerator blueGen = new WorldPopMinable(XyCraftWorldBlocks.xyOre.blockID, 0, 6);
   public static WorldGenerator greenGen = new WorldPopMinable(XyCraftWorldBlocks.xyOre.blockID, 1, 6);
   public static WorldGenerator redGen = new WorldPopMinable(XyCraftWorldBlocks.xyOre.blockID, 2, 6);
   public static WorldGenerator darkGen = new WorldPopMinable(XyCraftWorldBlocks.xyOre.blockID, 3, 6);
   public static WorldGenerator lightGen = new WorldPopMinable(XyCraftWorldBlocks.xyOre.blockID, 4, 6);
   public static WorldGenerator aluminumGen = new WorldPopMinable(XyCraftWorldBlocks.xyOre.blockID, 5, 8);
   private final int gemHeight = 56;


   public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
      this.currentWorld = world;
      this.rand = random;
      this.chunkX = chunkX;
      this.chunkZ = chunkZ;
      if(!(chunkProvider instanceof ChunkProviderHell) && !(chunkProvider instanceof ChunkProviderEnd)) {
         this.genOre(this.rand.nextInt(8) + 2, blueGen, 4, 56);
         this.genOre(this.rand.nextInt(8) + 2, greenGen, 4, 56);
         this.genOre(this.rand.nextInt(8) + 2, redGen, 4, 56);
         this.genOre(this.rand.nextInt(8) + 2, darkGen, 4, 56);
         this.genOre(this.rand.nextInt(8) + 2, lightGen, 4, 56);
         this.genOre(16, aluminumGen, 2, 64);
         this.genOre(56, crystalGen, 8, 64);
      }

   }

   public void genOre(int amount, WorldGenerator worldGen, int yMin, int yMax) {
      for(int i = 0; i < amount; ++i) {
         try {
            int e = this.chunkX * 16 + this.rand.nextInt(16);
            int y = this.rand.nextInt(yMax - yMin) + yMin;
            int z = this.chunkZ * 16 + this.rand.nextInt(16);
            worldGen.generate(this.currentWorld, this.rand, e, y, z);
         } catch (Exception var9) {
            XyCraft.logger.log(Level.WARNING, "Please calmly inform Soaryn about this: (provide a full log file when presenting)", var9);
         }
      }

   }

}
