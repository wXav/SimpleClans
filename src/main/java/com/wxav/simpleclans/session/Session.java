package com.wxav.simpleclans.session;

import cn.nukkit.Player;
import cn.nukkit.Server;
import com.wxav.simpleclans.SimpleClans;
import com.wxav.simpleclans.clan.Clan;
import com.wxav.simpleclans.clan.ClanFactory;
import com.wxav.simpleclans.clan.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Getter
public class Session {

    private String name;

    private UUID uniqueId;
    @Setter
    private String clanName;
    @Setter
    private Role role;

    private List<String> invites;

    public Clan getClan() {
        return ClanFactory.getInstance().getClanName(this.clanName);
    }

    public Player getInstance() {
        return Server.getInstance().getPlayerExact(this.name);
    }

    public void sendTranslatedMessage(String message, String... args) {
        Player instance = getInstance();

        if (instance == null || instance.isClosed()) {
            return;
        }

        instance.sendMessage(SimpleClans.translateMessage(message, args));
    }

    public boolean hasInvite(String uniqueId) {
        return this.invites.contains(uniqueId);
    }

    public void addInvite(String uniqueId) {
        this.invites.add(uniqueId);
    }

    public void removeInvite(String uniqueId) {
        if (uniqueId == null) {
            this.invites.clear();
        } else {
            this.invites.remove(uniqueId);
        }
    }
}