package br.com.battista.arcadiacaller.model;

import static br.com.battista.arcadiacaller.repository.contract.DatabaseContract.HeroGuildEntry;

import com.orm.dsl.Column;
import com.orm.dsl.Table;

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
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Table(name = HeroGuildEntry.TABLE_NAME)
public class HeroGuild extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = HeroGuildEntry.COLUMN_NAME_HERO, notNull = true)
    private Hero hero;

    @Column(name = HeroGuildEntry.COLUMN_NAME_CARD_01)
    private Card card1;

    @Column(name = HeroGuildEntry.COLUMN_NAME_CARD_02)
    private Card card2;

    @Column(name = HeroGuildEntry.COLUMN_NAME_CARD_03)
    private Card card3;

    @Column(name = HeroGuildEntry.COLUMN_NAME_CARD_04)
    private Card card4;

    @Column(name = HeroGuildEntry.COLUMN_NAME_CURSE_CARD)
    private Card curseCard;

    @Column(name = HeroGuildEntry.COLUMN_NAME_ACTIVE, notNull = true)
    private Boolean active = Boolean.TRUE;

    @Column(name = HeroGuildEntry.COLUMN_NAME_DELETED, notNull = true)
    private Boolean deleted = Boolean.FALSE;

}
