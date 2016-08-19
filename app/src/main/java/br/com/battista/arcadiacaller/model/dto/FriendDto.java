package br.com.battista.arcadiacaller.model.dto;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Strings;

import java.io.Serializable;

import br.com.battista.arcadiacaller.constants.ProfileAppConstant;

public class FriendDto implements Serializable, Comparable<FriendDto> {

    private static final long serialVersionUID = 1L;

    private String username;

    private String urlAvatar;

    private ProfileAppConstant profile;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUrlAvatar() {
        return urlAvatar;
    }

    public void setUrlAvatar(String urlAvatar) {
        this.urlAvatar = urlAvatar;
    }

    public ProfileAppConstant getProfile() {
        return profile;
    }

    public void setProfile(ProfileAppConstant profile) {
        this.profile = profile;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FriendDto)) return false;
        if (!super.equals(o)) return false;
        FriendDto friendDto = (FriendDto) o;
        return Objects.equal(getUsername(), friendDto.getUsername());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(super.hashCode(), getUsername());
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("username", username)
                .add("urlAvatar", urlAvatar)
                .add("profile", profile)
                .toString();
    }

    public FriendDto username(String username) {
        this.username = username;
        return this;
    }

    public FriendDto urlAvatar(String urlAvatar) {
        this.urlAvatar = urlAvatar;
        return this;
    }

    public FriendDto profile(ProfileAppConstant profile) {
        this.profile = profile;
        return this;
    }

    @Override
    public int compareTo(FriendDto friendDto) {
        if (friendDto == null) {
            return -1;
        }
        if (Strings.isNullOrEmpty(getUsername())) {
            return 1;
        }
        return this.getUsername().compareTo(friendDto.getUsername());
    }
}
