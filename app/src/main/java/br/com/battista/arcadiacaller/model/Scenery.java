package br.com.battista.arcadiacaller.model;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.collect.Lists;
import com.orm.dsl.Column;
import com.orm.dsl.Table;

import java.io.Serializable;
import java.util.List;

import br.com.battista.arcadiacaller.model.enuns.DifficultySceneryEnum;
import br.com.battista.arcadiacaller.model.enuns.LocationSceneryEnum;

import static br.com.battista.arcadiacaller.repository.contract.DatabaseContract.SceneryEntry;

@Table(name = SceneryEntry.TABLE_NAME)
public class Scenery extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = SceneryEntry.COLUMN_NAME_NAME, notNull = true, unique = true)
    private String name;

    @Column(name = SceneryEntry.COLUMN_NAME_URL_SYMBOL)
    private String urlSymbol;

    @Column(name = SceneryEntry.COLUMN_NAME_WON_TITLE)
    private String wonTitle;

    @Column(name = SceneryEntry.COLUMN_NAME_WON_REWARD)
    private Card wonReward;

    @Column(name = SceneryEntry.COLUMN_NAME_KEY_WON_REWARD)
    private String keyWonReward;

    @Column(name = SceneryEntry.COLUMN_NAME_DIFFICULTY, notNull = true)
    private DifficultySceneryEnum difficulty;

    @Column(name = SceneryEntry.COLUMN_NAME_BENEFIT_TITLES)
    private List<String> benefitTitles = Lists.newArrayList();

    @Column(name = SceneryEntry.COLUMN_NAME_LOCATION, notNull = true)
    private LocationSceneryEnum location;

    @Column(name = SceneryEntry.COLUMN_NAME_ACTIVE, notNull = true)
    private Boolean active = Boolean.TRUE;

    @Column(name = SceneryEntry.COLUMN_NAME_REVISE, notNull = true)
    private Boolean revise = Boolean.FALSE;

    @Column(name = SceneryEntry.COLUMN_NAME_DENOUNCE, notNull = true)
    private Boolean denounce = Boolean.FALSE;

    @Column(name = SceneryEntry.COLUMN_NAME_DELETED, notNull = true)
    private Boolean deleted = Boolean.FALSE;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrlSymbol() {
        return urlSymbol;
    }

    public void setUrlSymbol(String urlSymbol) {
        this.urlSymbol = urlSymbol;
    }

    public String getWonTitle() {
        return wonTitle;
    }

    public void setWonTitle(String wonTitle) {
        this.wonTitle = wonTitle;
    }

    public Card getWonReward() {
        return wonReward;
    }

    public void setWonReward(Card wonReward) {
        this.wonReward = wonReward;
    }

    public String getKeyWonReward() {
        return keyWonReward;
    }

    public void setKeyWonReward(String keyWonReward) {
        this.keyWonReward = keyWonReward;
    }

    public DifficultySceneryEnum getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(DifficultySceneryEnum difficulty) {
        this.difficulty = difficulty;
    }

    public List<String> getBenefitTitles() {
        return benefitTitles;
    }

    public void setBenefitTitles(List<String> benefitTitles) {
        this.benefitTitles = benefitTitles;
    }

    public LocationSceneryEnum getLocation() {
        return location;
    }

    public void setLocation(LocationSceneryEnum location) {
        this.location = location;
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
        if (!(o instanceof Scenery)) return false;
        if (!super.equals(o)) return false;
        Scenery scenery = (Scenery) o;
        return Objects.equal(getName(), scenery.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(super.hashCode(), getName());
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("name", name)
                .add("urlSymbol", urlSymbol)
                .add("wonTitle", wonTitle)
                .add("wonReward", wonReward)
                .add("keyWonReward", keyWonReward)
                .add("difficulty", difficulty)
                .add("benefitTitles", benefitTitles)
                .add("location", location)
                .add("active", active)
                .add("revise", revise)
                .add("denounce", denounce)
                .add("deleted", deleted)
                .toString();
    }

    public Scenery name(String name) {
        this.name = name;
        return this;
    }

    public Scenery urlSymbol(String urlSymbol) {
        this.urlSymbol = urlSymbol;
        return this;
    }

    public Scenery wonTitle(String wonTitle) {
        this.wonTitle = wonTitle;
        return this;
    }

    public Scenery wonReward(Card wonReward) {
        this.wonReward = wonReward;
        return this;
    }

    public Scenery keyWonReward(String keyWonReward) {
        this.keyWonReward = keyWonReward;
        return this;
    }

    public Scenery difficulty(DifficultySceneryEnum difficulty) {
        this.difficulty = difficulty;
        return this;
    }

    public Scenery benefitTitles(List<String> benefitTitles) {
        this.benefitTitles = benefitTitles;
        return this;
    }

    public Scenery location(LocationSceneryEnum location) {
        this.location = location;
        return this;
    }

    public Scenery active(Boolean active) {
        this.active = active;
        return this;
    }

    public Scenery revise(Boolean revise) {
        this.revise = revise;
        return this;
    }

    public Scenery denounce(Boolean denounce) {
        this.denounce = denounce;
        return this;
    }

    public Scenery deleted(Boolean deleted) {
        this.deleted = deleted;
        return this;
    }
}
