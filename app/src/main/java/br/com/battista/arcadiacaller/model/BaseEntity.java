package br.com.battista.arcadiacaller.model;

import static br.com.battista.arcadiacaller.repository.contract.DatabaseContract.BaseEntry;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.orm.SugarRecord;
import com.orm.dsl.Column;

import java.io.Serializable;
import java.util.Date;

import br.com.battista.arcadiacaller.constants.EntityConstant;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(includeFieldNames = true, callSuper = false)
@EqualsAndHashCode(callSuper = false)
@JsonAutoDetect
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class BaseEntity extends SugarRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = BaseEntry.COLUMN_NAME_ID, notNull = true, unique = true)
    private Long id;

    @Column(name = BaseEntry.COLUMN_NAME_CREATED_AT, notNull = true)
    private Date createdAt;

    @Column(name = BaseEntry.COLUMN_NAME_UPDATED_AT, notNull = true)
    private Date updatedAt;

    @Column(name = BaseEntry.COLUMN_NAME_VERSION, notNull = true)
    private Long version;

    @Column(name = BaseEntry.COLUMN_NAME_ENTITY_SYNCHRONIZED, notNull = true)
    private Boolean entitySynchronized;

    @Column(name = BaseEntry.COLUMN_NAME_SYNCHRONIZED_AT)
    private Date synchronizedAt;

    public void initEntity() {
        createdAt = new Date();
        updatedAt = createdAt;
        version = EntityConstant.DEFAULT_VERSION;
        entitySynchronized = Boolean.FALSE;
    }

    public void synchronize() {
        if (getVersion() == null || getVersion() == 0) {
            initEntity();
        }

        entitySynchronized = Boolean.TRUE;
        synchronizedAt = new Date();
    }

    public void updateEntity() {
        updatedAt = new Date();
        entitySynchronized = Boolean.FALSE;
        version++;
    }

}
