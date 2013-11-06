package soaryn.xycraft.core;

import java.util.HashMap;
import java.util.Set;
import java.util.TreeMap;

public class MetaMap {

   private TreeMap metaDataMap = new TreeMap();
   private HashMap idMetaMap = new HashMap();


   public Object get(int meta) {
      return this.metaDataMap.get(Integer.valueOf(meta));
   }

   public Object get(String identifier) {
      return this.metaDataMap.get(this.idMetaMap.get(identifier));
   }

   public int getMeta(String identifier) {
      return ((Integer)this.idMetaMap.get(identifier)).intValue();
   }

   public void add(int meta, String identifier, Object data) {
      this.metaDataMap.put(Integer.valueOf(meta), data);
      this.idMetaMap.put(identifier, Integer.valueOf(meta));
   }

   public Set entrySet() {
      return this.metaDataMap.entrySet();
   }
}
