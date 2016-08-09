package br.com.battista.arcadiacaller.model;

import com.google.common.base.MoreObjects;
import com.orm.dsl.Column;
import com.orm.dsl.Table;

import java.io.Serializable;

import static br.com.battista.arcadiacaller.repository.contract.DatabaseContract.HeroGuildEntry;

@Table(name = HeroGuildEntry.TABLE_NAME)
public class HeroGuild extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = HeroGuildEntry.COLUMN_NAME_HERO, notNull = true)
    private Hero hero;

    @Column(name = HeroGuildEntry.COLUMN_NAME_CARD_01)
    private Card card1;

    @Column(name = HeroGuildEntry.COLUMN_NAME_CARD_02)
    private Card card2;

    @Column(name = HeroGuildEntry.COLUMN_NAME_CARD_03)
    private Card card3;

    @Column(name = HeroGuildEntry.COLUMN_NAME_CARD_04)
    private Card card4;

    @Column(name = HeroGuildEntry.COLUMN_NAME_CURSE_CARD)
    private Card curseCard;

    @Column(name = HeroGuildEntry.COLUMN_NAME_ACTIVE, notNull = true)
    private Boolean active = Boolean.TRUE;

    @Column(name = HeroGuildEntry.COLUMN_NAME_DELETED, notNull = true)
    private Boolean deleted = Boolean.FALSE;

    public Hero getHero() {
        return hero;
    }

    public void setHero(Hero hero) {
        this.hero = hero;
    }

    public Card getCard1() {
        return card1;
    }

    public void setCard1(Card card1) {
        this.card1 = card1;
    }

    public Card getCard2() {
        return card2;
    }

    public void setCard2(Card card2) {
        this.card2 = card2;
    }

    public Card getCard3() {
        return card3;
    }

    public void setCard3(Card card3) {
        this.card3 = card3;
    }

    public Card getCard4() {
        return card4;
    }

    public void setCard4(Card card4) {
        this.card4 = card4;
    }

    public Card getCurseCard() {
        return curseCard;
    }

    public void setCurseCard(Card curseCard) {
        this.curseCard = curseCard;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("hero", hero)
                .add("card1", card1)
                .add("card2", card2)
                .add("card3", card3)
                .add("card4", card4)
                .add("curseCard", curseCard)
                .add("active", active)
                .add("deleted", deleted)
                .toString();
    }


    public HeroGuild hero(Hero hero) {
        this.hero = hero;
        return this;
    }

    public HeroGuild card1(Card card1) {
        this.card1 = card1;
        return this;
    }

    public HeroGuild card2(Card card2) {
        this.card2 = card2;
        return this;
    }

    public HeroGuild card3(Card card3) {
        this.card3 = card3;
        return this;
    }

    public HeroGuild card4(Card card4) {
        this.card4 = card4;
        return this;
    }

    public HeroGuild curseCard(Card curseCard) {
        this.curseCard = curseCard;
        return this;
    }

    public HeroGuild active(Boolean active) {
        this.active = active;
        return this;
    }

    public HeroGuild deleted(Boolean deleted) {
        this.deleted = deleted;
        return this;
    }
}
