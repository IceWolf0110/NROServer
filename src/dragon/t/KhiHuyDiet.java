package dragon.t;

import java.util.ArrayList;

/**
 *
 * @author TGDD
 */
public class KhiHuyDiet {
    
    public int level;
    public long lastOpen;
    public int miliTime;
    public int miliTime2;
    public Clan clan;
    
    public final int[] mapIds = new int[] {149, 147, 152, 151, 148};
    public final int[] maxMobDieNextMap = new int[] {15, 17, 30, 9, 11};
    public ArrayList<Map> maps = new ArrayList<>();
    
    private final ArrayList<Integer> players = new ArrayList<>();
    
    public void initDestronGas(Clan clan, int level) {
        this.clan = clan;
        this.level = level;
        this.lastOpen = System.currentTimeMillis();
        this.miliTime = 1000 * 60 * 30;
        clan.destronGas = this;
        int i;
        int k;
        for (i = 0; i < mapIds.length; ++i) {
            Map map = new Map(mapIds[i], 1, 25, 0);
            map.destronGas = this;
            for (k = 0; k < map.zones.size(); k++) {
                map.zones.get(k).initMob();
            }
            map.isOpen = i == 0;
            maps.add(map);
            Map.add(map);
        }
    }
    
    public int getIndexMaps(int mapId) {
        int i;
        for (i = 0; i < mapIds.length; i++) {
            if (mapIds[i] == mapId) {
                return i;
            }
        }
        return -1;
    }
    
    public void updateWin(int indexMap) {
        if (indexMap + 1 < mapIds.length) {
            this.maps.get(indexMap + 1).isOpen = true;
        } else {
            Char b = Player.addBoss(11, 0, -1, -1, true, 400, 150, null, 5000, -1);
            b.countLevel = level;
            b.downDamage_percent = 50;
            b.cHP = b.cHPFull = b.cHPGoc = b.cHPGoc * b.countLevel;
            maps.get(indexMap).zones.get(0).join(b, 0, -1, -1);
        }
    }
    
    public void close() {
        int i;
        int j;
        this.clan.destronGas = null;
        
        Map map;
        for (i = 0; i < maps.size(); i++) {
            map = maps.get(i);
            for (j = 0; j < map.zones.size(); j++) {
                map.zones.get(j).pushPlayers(0);
            }
        }
        for (i = maps.size() - 1; i >= 0; --i) {
            maps.get(i).close();
        }
    }
    
    public boolean isJoin(int playerId) {
        synchronized (this.players) {
            return this.players.contains(playerId);
        }
    }
    
    public void join(int playerId) {
        synchronized (this.players) {
            this.players.add(playerId);
        }
    }
    
}
