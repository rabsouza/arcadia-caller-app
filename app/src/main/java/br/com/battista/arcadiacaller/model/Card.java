package br.com.battista.arcadiacaller.model;

import static br.com.battista.arcadiacaller.repository.contract.DatabaseContract.CardEntry;

import com.orm.dsl.Column;
import com.orm.dsl.Table;

import java.io.Serializable;

import br.com.battista.arcadiacaller.model.enuns.GroupCardEnum;
import br.com.battista.arcadiacaller.model.enuns.TypeCardEnum;
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
@EqualsAndHashCode(of = {"name"}, callSuper = false)
@Table(name = CardEntry.TABLE_NAME)
public class Card extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = CardEntry.COLUMN_NAME_NAME, notNull = true, unique = true)
    private String name;

    @Column(name = CardEntry.COLUMN_NAME_KEY, notNull = true, unique = true)
    private String key;

    @Column(name = CardEntry.COLUMN_NAME_TYPE, notNull = true)
    private TypeCardEnum type;

    @Column(name = CardEntry.COLUMN_NAME_GROUP, notNull = true)
    private GroupCardEnum group;

    @Column(name = CardEntry.COLUMN_NAME_TYPE_EFFECT, notNull = true)
    private String typeEffect;

    @Column(name = CardEntry.COLUMN_NAME_GROUP_EFFECT, notNull = true)
    private String groupEffect;

    @Column(name = CardEntry.COLUMN_NAME_COST)
    private Integer cost;

    @Column(name = CardEntry.COLUMN_NAME_ACTIVE, notNull = true)
    private Boolean active = Boolean.TRUE;

    @Column(name = CardEntry.COLUMN_NAME_REVISE, notNull = true)
    private Boolean revise = Boolean.FALSE;

    @Column(name = CardEntry.COLUMN_NAME_DENOUNCE, notNull = true)
    private Boolean denounce = Boolean.FALSE;

    @Column(name = CardEntry.COLUMN_NAME_DELETED, notNull = true)
    private Boolean deleted = Boolean.FALSE;
}
