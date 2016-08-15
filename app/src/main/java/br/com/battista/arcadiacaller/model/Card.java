package br.com.battista.arcadiacaller.model;

import static br.com.battista.arcadiacaller.repository.contract.DatabaseContract.CardEntry;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.orm.dsl.Column;
import com.orm.dsl.Table;

import java.io.Serializable;

import br.com.battista.arcadiacaller.model.enuns.GroupCardEnum;
import br.com.battista.arcadiacaller.model.enuns.TypeCardEnum;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public TypeCardEnum getType() {
        return type;
    }

    public void setType(TypeCardEnum type) {
        this.type = type;
    }

    public GroupCardEnum getGroup() {
        return group;
    }

    public void setGroup(GroupCardEnum group) {
        this.group = group;
    }

    public String getTypeEffect() {
        return typeEffect;
    }

    public void setTypeEffect(String typeEffect) {
        this.typeEffect = typeEffect;
    }

    public String getGroupEffect() {
        return groupEffect;
    }

    public void setGroupEffect(String groupEffect) {
        this.groupEffect = groupEffect;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Boolean getRevise() {
        return revise;
    }

    public void setRevise(Boolean revise) {
        this.revise = revise;
    }

    public Boolean getDenounce() {
        return denounce;
    }

    public void setDenounce(Boolean denounce) {
        this.denounce = denounce;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Card)) return false;
        if (!super.equals(o)) return false;
        Card card = (Card) o;
        return Objects.equal(getKey(), card.getKey());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(super.hashCode(), getKey());
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("name", name)
                .add("key", key)
                .add("type", type)
                .add("group", group)
                .add("typeEffect", typeEffect)
                .add("groupEffect", groupEffect)
                .add("cost", cost)
                .add("active", active)
                .add("revise", revise)
                .add("denounce", denounce)
                .add("deleted", deleted)
                .toString();
    }


    public Card name(String name) {
        this.name = name;
        return this;
    }

    public Card key(String key) {
        this.key = key;
        return this;
    }

    public Card type(TypeCardEnum type) {
        this.type = type;
        return this;
    }

    public Card group(GroupCardEnum group) {
        this.group = group;
        return this;
    }

    public Card typeEffect(String typeEffect) {
        this.typeEffect = typeEffect;
        return this;
    }

    public Card groupEffect(String groupEffect) {
        this.groupEffect = groupEffect;
        return this;
    }

    public Card cost(Integer cost) {
        this.cost = cost;
        return this;
    }

    public Card active(Boolean active) {
        this.active = active;
        return this;
    }

    public Card revise(Boolean revise) {
        this.revise = revise;
        return this;
    }

    public Card denounce(Boolean denounce) {
        this.denounce = denounce;
        return this;
    }

    public Card deleted(Boolean deleted) {
        this.deleted = deleted;
        return this;
    }
}
