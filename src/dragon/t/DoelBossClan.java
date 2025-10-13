package dragon.t;

import Models.server.mResources;

/**
 *
 * @author Admin
 */
public class DoelBossClan extends Instancing {
    
    public void init(Clan clan, int level) {
        super.clan = clan;
        super.level = level;
        super.lastOpen = System.currentTimeMillis();
        super.miliTime = 1800000;
        clan.doelBossClan = this;
        short arrMapId[] = new short[]{165};
        for (int i = 0; i < arrMapId.length; ++i) {
            Map map = new Map(arrMapId[i], 1, 20, 0);
            map.phoban = this;
            map.isOpen = i == 0;
            super.maps.add(map);
            Map.add(map);
        }
        //Create boss and skill
        if (level == 0) {
            Player fideDaiCa = Player.addBoss(163, 0, -1, -1, true, 120, 552, null, 2000, -1);
            //Dua vao map
            super.maps.get(0).zones.get(0).join(fideDaiCa, 0, -1, -1);
        }
        if (level == 1) {
            Player sieuBoHung = Player.addBoss(164, 0, -1, -1, true, 120, 552, null, 2000, -1);
            //Dua vao map
            super.maps.get(0).zones.get(0).join(sieuBoHung, 0, -1, -1);
        }
        if (level == 2) {
            Player mabu = Player.addBoss(165, 0, -1, -1, true, 120, 552, null, 2000, -1);
            //Dua vao map
            super.maps.get(0).zones.get(0).join(mabu, 0, -1, -1);
        }
        if (level == 3) {
            Player zamasu = Player.addBoss(166, 0, -1, -1, true, 120, 552, null, 2000, -1);
            //Dua vao map
            super.maps.get(0).zones.get(0).join(zamasu, 0, -1, -1);
        }
    }
    
    @Override
    public void close() {
        super.clan.doelBossClanLevel++;
        super.clan.doelBossClan = null;
        super.close();
    }
    
    @Override
    public void update() {
        super.update();
    }
    
}
