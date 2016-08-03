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

import static br.com.battista.arcadiacaller.repository.contract.DatabaseContract.HeroEntry;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString(includeFieldNames = true, callSuper = true)
@EqualsAndHashCode(of = {"name"}, callSuper = false)
@Table(name = HeroEntry.TABLE_NAME)
public class Hero extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = HeroEntry.COLUMN_NAME_NAME, notNull = true, index = true, unique = true)
    private String name;

    @Column(name = HeroEntry.COLUMN_NAME_URL_PHOTO, notNull = true)
    private String urlPhoto;

    @Column(name = HeroEntry.COLUMN_NAME_DEFENSE, notNull = true)
    private Integer defense;

    @Column(name = HeroEntry.COLUMN_NAME_LIFE, notNull = true)
    private Integer life;

    @Column(name = HeroEntry.COLUMN_NAME_ACTIVE, notNull = true, index = true)
    private Boolean active = Boolean.TRUE;

    @Column(name = HeroEntry.COLUMN_NAME_REVISE, notNull = true, index = true)
    private Boolean revise = Boolean.FALSE;

    @Column(name = HeroEntry.COLUMN_NAME_DENOUNCE, notNull = true, index = true)
    private Boolean denounce = Boolean.FALSE;

    @Column(name = HeroEntry.COLUMN_NAME_DELETED, notNull = true, index = true)
    private Boolean deleted = Boolean.FALSE;

    @Override
    public Object getPk() {
        return getId();
    }
}
