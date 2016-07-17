package br.com.battista.arcadiacaller.model;

import static br.com.battista.arcadiacaller.repository.contract.DatabaseContract.HeroGuildEntry;

import com.activeandroid.annotation.Table;

import java.io.Serializable;

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
@EqualsAndHashCode(callSuper = false)
@Table(name = HeroGuildEntry.TABLE_NAME)
public class HeroGuild extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private Hero hero;

    private Card card1;

    private Card card2;

    private Card card3;

    private Card card4;

    private Card curseCard;

    @Override
    public Object getPk() {
        return getId();
    }
}
