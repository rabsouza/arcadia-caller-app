package br.com.battista.arcadiacaller.model;

import static br.com.battista.arcadiacaller.repository.contract.DatabaseContract.HeroEntry;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.orm.dsl.Column;
import com.orm.dsl.Table;

import java.io.Serializable;

import br.com.battista.arcadiacaller.model.enuns.GroupHeroEnum;

@Table(name = HeroEntry.TABLE_NAME)
public class Hero extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = HeroEntry.COLUMN_NAME_NAME, notNull = true, unique = true)
    private String name;

    @Column(name = HeroEntry.COLUMN_NAME_URL_PHOTO, notNull = true)
    private String urlPhoto;

    @Column(name = HeroEntry.COLUMN_NAME_DEFENSE, notNull = true)
    private Integer defense;

    @Column(name = HeroEntry.COLUMN_NAME_LIFE, notNull = true)
    private Integer life;

    @Column(name = HeroEntry.COLUMN_NAME_ABILITY, notNull = true)
    private String ability;

    @Column(name = HeroEntry.COLUMN_NAME_GROUP, notNull = true)
    private GroupHeroEnum group;

    @Column(name = HeroEntry.COLUMN_NAME_ACTIVE, notNull = true)
    private Boolean active = Boolean.TRUE;

    @Column(name = HeroEntry.COLUMN_NAME_REVISE, notNull = true)
    private Boolean revise = Boolean.FALSE;

    @Column(name = HeroEntry.COLUMN_NAME_DENOUNCE, notNull = true)
    private Boolean denounce = Boolean.FALSE;

    @Column(name = HeroEntry.COLUMN_NAME_DELETED, notNull = true)
    private Boolean deleted = Boolean.FALSE;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrlPhoto() {
        return urlPhoto;
    }

    public void setUrlPhoto(String urlPhoto) {
        this.urlPhoto = urlPhoto;
    }

    public Integer getDefense() {
        return defense;
    }

    public void setDefense(Integer defense) {
        this.defense = defense;
    }

    public Integer getLife() {
        return life;
    }

    public void setLife(Integer life) {
        this.life = life;
    }

    public String getAbility() {
        return ability;
    }

    public void setAbility(String ability) {
        this.ability = ability;
    }

    public GroupHeroEnum getGroup() {
        return group;
    }

    public void setGroup(GroupHeroEnum group) {
        this.group = group;
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
        if (!(o instanceof Hero)) return false;
        if (!super.equals(o)) return false;
        Hero hero = (Hero) o;
        return Objects.equal(getName(), hero.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(super.hashCode(), getName());
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("name", name)
                .add("urlPhoto", urlPhoto)
                .add("defense", defense)
                .add("life", life)
                .add("ability", ability)
                .add("group", group)
                .add("active", active)
                .add("revise", revise)
                .add("denounce", denounce)
                .add("deleted", deleted)
                .toString();
    }

    public Hero name(String name) {
        this.name = name;
        return this;
    }

    public Hero urlPhoto(String urlPhoto) {
        this.urlPhoto = urlPhoto;
        return this;
    }

    public Hero defense(Integer defense) {
        this.defense = defense;
        return this;
    }

    public Hero life(Integer life) {
        this.life = life;
        return this;
    }

    public Hero ability(String ability) {
        this.ability = ability;
        return this;
    }

    public Hero group(GroupHeroEnum group) {
        this.group = group;
        return this;
    }

    public Hero active(Boolean active) {
        this.active = active;
        return this;
    }

    public Hero revise(Boolean revise) {
        this.revise = revise;
        return this;
    }

    public Hero denounce(Boolean denounce) {
        this.denounce = denounce;
        return this;
    }

    public Hero deleted(Boolean deleted) {
        this.deleted = deleted;
        return this;
    }
}
