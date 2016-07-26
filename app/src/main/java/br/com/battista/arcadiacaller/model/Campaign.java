package br.com.battista.arcadiacaller.model;

import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

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

    @Override
    public Object getPk() {
        return getId();
    }
}
