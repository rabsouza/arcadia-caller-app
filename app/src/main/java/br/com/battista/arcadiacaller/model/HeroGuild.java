package br.com.battista.arcadiacaller.model;

import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import static br.com.battista.arcadiacaller.repository.contract.DatabaseContract.BaseEntry;
import static br.com.battista.arcadiacaller.repository.contract.DatabaseContract.HeroEntry;
import static br.com.battista.arcadiacaller.repository.contract.DatabaseContract.HeroGuildEntry;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString(includeFieldNames = true, callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Table(name = HeroGuildEntry.TABLE_NAME, id = BaseEntry.COLUMN_NAME_PK)
public class HeroGuild extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = HeroEntry.COLUMN_NAME_NAME, notNull = true, index = true, unique = false)
    private Hero hero;

    private Card card1;

    private Card card2;

    private Card card3;

    private Card card4;

    private Card curseCard;

    private Boolean active = Boolean.TRUE;

    private Boolean deleted = Boolean.FALSE;

}
