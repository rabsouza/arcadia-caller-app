package br.com.battista.arcadiacaller.model.dto;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import java.io.Serializable;

public class SceneryDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;

    private Boolean completed = Boolean.FALSE;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SceneryDto)) return false;
        SceneryDto that = (SceneryDto) o;
        return Objects.equal(getName(), that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getName());
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("name", name)
                .add("completed", completed)
                .toString();
    }

    public SceneryDto name(String name) {
        this.name = name;
        return this;
    }

    public SceneryDto completed(Boolean completed) {
        this.completed = completed;
        return this;
    }
}
