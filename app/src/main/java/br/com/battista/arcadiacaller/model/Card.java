package br.com.battista.arcadiacaller.model;

import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.io.Serializable;

import br.com.battista.arcadiacaller.model.enuns.GroupCardEnum;
import br.com.battista.arcadiacaller.model.enuns.TypeCardEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import static br.com.battista.arcadiacaller.repository.contract.DatabaseContract.BaseEntry;
import static br.com.battista.arcadiacaller.repository.contract.DatabaseContract.CardEntry;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString(includeFieldNames = true, callSuper = true)
@EqualsAndHashCode(of = {"name"}, callSuper = false)
@Table(name = CardEntry.TABLE_NAME, id = BaseEntry.COLUMN_NAME_PK)
public class Card extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = CardEntry.COLUMN_NAME_NAME, notNull = true, index = true, unique = true)
    private String name;

    @Column(name = CardEntry.COLUMN_NAME_KEY, notNull = true, index = true, unique = true)
    private String key;

    @Column(name = CardEntry.COLUMN_NAME_TYPE, notNull = true, index = true)
    private TypeCardEnum type;

    @Column(name = CardEntry.COLUMN_NAME_GROUP, notNull = true, index = true)
    private GroupCardEnum group;

    @Column(name = CardEntry.COLUMN_NAME_COST)
    private Integer cost;

    @Column(name = CardEntry.COLUMN_NAME_ACTIVE, notNull = true, index = true)
    private Boolean active = Boolean.TRUE;

    @Column(name = CardEntry.COLUMN_NAME_REVISE, notNull = true, index = true)
    private Boolean revise = Boolean.FALSE;

    @Column(name = CardEntry.COLUMN_NAME_DENOUNCE, notNull = true, index = true)
    private Boolean denounce = Boolean.FALSE;

    @Column(name = CardEntry.COLUMN_NAME_DELETED, notNull = true, index = true)
    private Boolean deleted = Boolean.FALSE;
}
