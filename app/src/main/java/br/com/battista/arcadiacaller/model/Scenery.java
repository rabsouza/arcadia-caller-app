package br.com.battista.arcadiacaller.model;

import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.google.common.collect.Lists;

import java.io.Serializable;
import java.util.List;

import br.com.battista.arcadiacaller.model.enuns.DifficultySceneryEnum;
import br.com.battista.arcadiacaller.model.enuns.LocationSceneryEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import static br.com.battista.arcadiacaller.repository.contract.DatabaseContract.SceneryEntry;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString(includeFieldNames = true, callSuper = true)
@EqualsAndHashCode(of = {"name"}, callSuper = false)
@Table(name = SceneryEntry.TABLE_NAME)
public class Scenery extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = SceneryEntry.COLUMN_NAME_NAME, notNull = true, index = true, unique = true)
    private String name;

    @Column(name = SceneryEntry.COLUMN_NAME_URL_SYMBOL, index = true)
    private String urlSymbol;

    @Column(name = SceneryEntry.COLUMN_NAME_WON_TITLE, index = true)
    private String wonTitle;

    @Column(name = SceneryEntry.COLUMN_NAME_WON_REWARD, index = true)
    private Card wonReward;

    @Column(name = SceneryEntry.COLUMN_NAME_DIFFICULTY, notNull = true, index = true)
    private DifficultySceneryEnum difficulty;

    @Column(name = SceneryEntry.COLUMN_NAME_BENEFIT_TITLES)
    private List<String> benefitTitles = Lists.newArrayList();

    @Column(name = SceneryEntry.COLUMN_NAME_LOCATION, notNull = true, index = true)
    private LocationSceneryEnum location;

    @Column(name = SceneryEntry.COLUMN_NAME_ACTIVE, notNull = true, index = true)
    private Boolean active = Boolean.TRUE;

    @Column(name = SceneryEntry.COLUMN_NAME_REVISE, notNull = true, index = true)
    private Boolean revise = Boolean.FALSE;

    @Column(name = SceneryEntry.COLUMN_NAME_DENOUNCE, notNull = true, index = true)
    private Boolean denounce = Boolean.FALSE;

    @Column(name = SceneryEntry.COLUMN_NAME_DELETED, notNull = true, index = true)
    private Boolean deleted = Boolean.FALSE;

    @Override
    public Object getPk() {
        return getId();
    }
}
