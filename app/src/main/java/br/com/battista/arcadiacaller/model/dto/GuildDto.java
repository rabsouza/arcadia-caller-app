package br.com.battista.arcadiacaller.model.dto;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import java.io.Serializable;

import br.com.battista.arcadiacaller.model.enuns.NameGuildEnum;

public class GuildDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private NameGuildEnum name;

    private String username;

    public NameGuildEnum getName() {
        return name;
    }

    public void setName(NameGuildEnum name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GuildDto)) return false;
        GuildDto guildDto = (GuildDto) o;
        return Objects.equal(getUsername(), guildDto.getUsername());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getUsername());
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("name", name)
                .add("username", username)
                .toString();
    }

    public GuildDto name(NameGuildEnum name) {
        this.name = name;
        return this;
    }

    public GuildDto username(String username) {
        this.username = username;
        return this;
    }
}
