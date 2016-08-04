package br.com.battista.arcadiacaller.model;

import static br.com.battista.arcadiacaller.repository.contract.DatabaseContract.UserEntry;

import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.google.common.collect.Sets;

import java.io.Serializable;
import java.util.Set;

import br.com.battista.arcadiacaller.constants.ProfileAppConstant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString(includeFieldNames = true, callSuper = true)
@EqualsAndHashCode(of = {"username"}, callSuper = false)
@Table(name = UserEntry.TABLE_NAME)
public class User extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = UserEntry.COLUMN_NAME_USERNAME, notNull = true, index = true, unique = true)
    private String username;

    @Column(name = UserEntry.COLUMN_NAME_MAIL, notNull = true, index = true)
    private String mail;

    @Column(name = UserEntry.COLUMN_NAME_URL_AVATAR, notNull = true)
    private String urlAvatar;

    @Column(name = UserEntry.COLUMN_NAME_PROFILE, notNull = true, index = true)
    private ProfileAppConstant profile;

    @Column(name = UserEntry.COLUMN_NAME_FRIENDS)
    private Set<String> friends = Sets.newLinkedHashSet();

    public Boolean addFriend(String friend) {
        if (friends == null) {
            friends = Sets.newLinkedHashSet();
        }
        return friends.add(friend);
    }

    @Override
    public Object getPk() {
        return getId();
    }
}
