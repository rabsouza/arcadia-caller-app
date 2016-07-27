package br.com.battista.arcadiacaller.model;

import static br.com.battista.arcadiacaller.repository.contract.DatabaseContract.SceneryCampaignEntry;

import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString(includeFieldNames = true, callSuper = true)
@EqualsAndHashCode(of = {"name"}, callSuper = false)
@Table(name = SceneryCampaignEntry.TABLE_NAME)
public class SceneryCampaign extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = SceneryCampaignEntry.COLUMN_NAME_NAME, notNull = true, index = true, unique = false)
    private String name;

    private Scenery scenery;

    private String winner;

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
