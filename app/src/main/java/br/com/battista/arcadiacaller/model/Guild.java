package br.com.battista.arcadiacaller.model;

import static br.com.battista.arcadiacaller.repository.contract.DatabaseContract.GuildEntry;

import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.io.Serializable;
import java.util.List;

import br.com.battista.arcadiacaller.model.enuns.NameGuildEnum;
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
@EqualsAndHashCode(of = {"id"}, callSuper = false)
@Table(name = GuildEntry.TABLE_NAME)
public class Guild extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer victories = 0;

    private Integer defeats = 0;

    private List<String> benefitTitles;

    @Column(name = GuildEntry.COLUMN_NAME_NAME, notNull = true, index = true, unique = false)
    private NameGuildEnum name;

    private User user;

    private HeroGuild hero1;

    private HeroGuild hero2;

    private HeroGuild hero3;

    private Boolean savedMoney = Boolean.FALSE;

    @Override
    public Object getPk() {
        return getId();
    }
}
