package br.com.battista.arcadiacaller.model;

import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import br.com.battista.arcadiacaller.model.dto.GuildDto;
import br.com.battista.arcadiacaller.model.dto.SceneryDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import static br.com.battista.arcadiacaller.repository.contract.DatabaseContract.CampaignEntry;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString(includeFieldNames = true, callSuper = true)
@EqualsAndHashCode(of = {"key"}, callSuper = false)
@Table(name = CampaignEntry.TABLE_NAME)
public class Campaign extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private String alias;

    private Date when;

    private String guild01;

    private Guild heroesGuild01;

    private String guild02;

    private Guild heroesGuild02;

    private String guild03;

    private Guild heroesGuild03;

    private String guild04;

    private Guild heroesGuild04;

    @Column(name = CampaignEntry.COLUMN_NAME_KEY, notNull = true, index = true, unique = true)
    private String key;

    private SceneryCampaign scenery1;

    private SceneryCampaign scenery2;

    private SceneryCampaign scenery3;

    private SceneryCampaign scenery4;

    private SceneryCampaign scenery5;

    private SceneryCampaign scenery6;

    private String created;

    private List<String> winner;

    private List<String> leastDeaths;

    private List<String> mostCoins;

    private List<String> wonReward;

    private List<String> wonTitle;

    private Boolean active = Boolean.TRUE;

    private Boolean completed = Boolean.FALSE;

    private Boolean deleted = Boolean.FALSE;

    public List<GuildDto> generateGuildsDto() {
        List<GuildDto> guilds = Lists.newArrayList();

        if (Strings.isNullOrEmpty(guild01) && heroesGuild01 != null) {
            GuildDto guildDto = GuildDto.builder().username(guild01).name(heroesGuild01.getName()).build();
            guilds.add(guildDto);
        }

        if (Strings.isNullOrEmpty(guild02) && heroesGuild02 != null) {
            GuildDto guildDto = GuildDto.builder().username(guild02).name(heroesGuild02.getName()).build();
            guilds.add(guildDto);
        }

        if (Strings.isNullOrEmpty(guild03) && heroesGuild03 != null) {
            GuildDto guildDto = GuildDto.builder().username(guild03).name(heroesGuild03.getName()).build();
            guilds.add(guildDto);
        }

        if (Strings.isNullOrEmpty(guild04) && heroesGuild04 != null) {
            GuildDto guildDto = GuildDto.builder().username(guild04).name(heroesGuild04.getName()).build();
            guilds.add(guildDto);
        }
        return guilds;
    }

    public List<SceneryDto> generateSceneriesDto() {
        List<SceneryDto> sceneries = Lists.newArrayList();

        if (scenery1 != null) {
            SceneryDto sceneryDto = SceneryDto.builder().name(scenery1.getName()).completed(scenery1.getCompleted()).build();
            sceneries.add(sceneryDto);
        }

        if (scenery2 != null) {
            SceneryDto sceneryDto = SceneryDto.builder().name(scenery2.getName()).completed(scenery2.getCompleted()).build();
            sceneries.add(sceneryDto);
        }

        if (scenery3 != null) {
            SceneryDto sceneryDto = SceneryDto.builder().name(scenery3.getName()).completed(scenery3.getCompleted()).build();
            sceneries.add(sceneryDto);
        }

        if (scenery4 != null) {
            SceneryDto sceneryDto = SceneryDto.builder().name(scenery4.getName()).completed(scenery4.getCompleted()).build();
            sceneries.add(sceneryDto);
        }

        if (scenery5 != null) {
            SceneryDto sceneryDto = SceneryDto.builder().name(scenery5.getName()).completed(scenery5.getCompleted()).build();
            sceneries.add(sceneryDto);
        }

        if (scenery6 != null) {
            SceneryDto sceneryDto = SceneryDto.builder().name(scenery6.getName()).completed(scenery6.getCompleted()).build();
            sceneries.add(sceneryDto);
        }

        return sceneries;
    }

    @Override
    public Object getPk() {
        return getId();
    }
}
