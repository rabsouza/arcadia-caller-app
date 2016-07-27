package br.com.battista.arcadiacaller.model;

import static br.com.battista.arcadiacaller.repository.contract.DatabaseContract.CardEntry;

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

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString(includeFieldNames = true, callSuper = true)
@EqualsAndHashCode(of = {"name"}, callSuper = false)
@Table(name = CardEntry.TABLE_NAME)
public class Card extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = CardEntry.COLUMN_NAME_NAME, notNull = true, index = true, unique = true)
    private String name;

    private String key;

    private TypeCardEnum type;

    private GroupCardEnum group;

    private Integer cost;

    private Boolean active = Boolean.TRUE;

    private Boolean revise = Boolean.FALSE;

    private Boolean denounce = Boolean.FALSE;

    private Boolean deleted = Boolean.FALSE;

    @Override
    public Object getPk() {
        return getId();
    }
}
