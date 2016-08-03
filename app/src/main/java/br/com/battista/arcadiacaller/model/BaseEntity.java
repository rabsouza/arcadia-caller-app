package br.com.battista.arcadiacaller.model;

import static br.com.battista.arcadiacaller.repository.contract.DatabaseContract.BaseEntry;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
public abstract class BaseEntity extends Model implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = BaseEntry.COLUMN_NAME_CREATED_AT, notNull = true)
    private Date createdAt;

    @Column(name = BaseEntry.COLUMN_NAME_UPDATED_AT, notNull = true, index = true)
    private Date updatedAt;

    @Column(name = BaseEntry.COLUMN_NAME_VERSION, notNull = true, index = true)
    private Long version;

    @Column(name = BaseEntry.COLUMN_NAME_ENTITY_SYNCHONIZED, notNull = true, index = true)
    private Boolean entitySynchronized;

    @Column(name = BaseEntry.COLUMN_NAME_SYNCHONIZED_AT, index = true)
    private Date synchronizedAt;

    public abstract Object getPk();

    public void initEntity() {
        createdAt = new Date();
        updatedAt = createdAt;
        version = EntityConstant.DEFAULT_VERSION;
        entitySynchronized = Boolean.FALSE;
    }

    public void synchronize() {
        entitySynchronized = Boolean.TRUE;
        synchronizedAt = new Date();
    }

    public void updateEntity() {
        updatedAt = new Date();
        entitySynchronized = Boolean.FALSE;
        version++;
    }

}
