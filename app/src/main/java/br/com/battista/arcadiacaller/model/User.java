package br.com.battista.arcadiacaller.model;

import static br.com.battista.arcadiacaller.repository.contract.DatabaseContract.UserEntry;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.collect.Sets;
import com.orm.dsl.Column;
import com.orm.dsl.Table;

import java.io.Serializable;
import java.util.Set;

import br.com.battista.arcadiacaller.constants.ProfileAppConstant;
import br.com.battista.arcadiacaller.model.dto.FriendDto;

@Table(name = UserEntry.TABLE_NAME)
public class User extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = UserEntry.COLUMN_NAME_USERNAME, notNull = true, unique = true)
    private String username;

    @Column(name = UserEntry.COLUMN_NAME_MAIL, notNull = true)
    private String mail;

    @Column(name = UserEntry.COLUMN_NAME_URL_AVATAR, notNull = true)
    private String urlAvatar;

    @Column(name = UserEntry.COLUMN_NAME_PROFILE, notNull = true)
    private ProfileAppConstant profile;

    @Column(name = UserEntry.COLUMN_NAME_FRIENDS)
    private Set<String> friends = Sets.newLinkedHashSet();

    @Column(name = UserEntry.COLUMN_NAME_FRIENDS)
    private Set<FriendDto> friendsDto = Sets.newLinkedHashSet();

    public Boolean addFriend(String friend) {
        if (friends == null) {
            friends = Sets.newLinkedHashSet();
        }
        return friends.add(friend);
    }

    public Boolean addFriend(FriendDto friend) {
        if (friendsDto == null) {
            friendsDto = Sets.newLinkedHashSet();
        }
        return friendsDto.add(friend);
    }

    public Boolean removeFriend(String friend) {
        if (friends == null) {
            friends = Sets.newLinkedHashSet();
        }
        return friends.remove(friend);
    }

    public Boolean removeFriend(FriendDto friend) {
        if (friendsDto == null) {
            friendsDto = Sets.newLinkedHashSet();
        }
        return friendsDto.remove(friend);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
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

    public Set<String> getFriends() {
        return friends;
    }

    public void setFriends(Set<String> friends) {
        this.friends = friends;
    }

    public Set<FriendDto> getFriendsDto() {
        return friendsDto;
    }

    public void setFriendsDto(Set<FriendDto> friendsDto) {
        this.friendsDto = friendsDto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        if (!super.equals(o)) return false;
        User user = (User) o;
        return Objects.equal(getUsername(), user.getUsername());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(super.hashCode(), getUsername());
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("username", username)
                .add("mail", mail)
                .add("urlAvatar", urlAvatar)
                .add("profile", profile)
                .add("friends", friends)
                .toString();
    }

    public User username(String username) {
        this.username = username;
        return this;
    }

    public User mail(String mail) {
        this.mail = mail;
        return this;
    }

    public User urlAvatar(String urlAvatar) {
        this.urlAvatar = urlAvatar;
        return this;
    }

    public User profile(ProfileAppConstant profile) {
        this.profile = profile;
        return this;
    }

    public User friends(Set<String> friends) {
        this.friends = friends;
        return this;
    }

    public User friendsDto(Set<FriendDto> friendsDto) {
        this.friendsDto = friendsDto;
        return this;
    }
}
