package refactoring_guru.iterator.example.iterators;

import refactoring_guru.iterator.example.profile.Profile;
import refactoring_guru.iterator.example.social_networks.Instagram;

import java.util.ArrayList;
import java.util.List;

public class InstagramIterator implements ProfileIterator {
    private Instagram instagram;
    private String type;
    private String nickname;
    private int currentPosition = 0;
    private List<String> nicknames = new ArrayList<>();
    private List<Profile> profiles = new ArrayList<>();

    public InstagramIterator(Instagram instagram, String type, String nickname) {
        this.instagram = instagram;
        this.type = type;
        this.nickname = nickname;
    }

    private void lazyLoad() {
        if (nicknames.size() == 0) {
            List<String> profiles = instagram.requestProfileFriendsFromInstagram(this.nickname, this.type);
            for (String profile : profiles) {
                this.nicknames.add(profile);
                this.profiles.add(null);
            }
        }
    }

    @Override
    public boolean hasNext() {
        lazyLoad();
        return currentPosition < nicknames.size();
    }

    @Override
    public Profile getNext() {
        if (!hasNext()) {
            return null;
        }

        String friendEmail = nicknames.get(currentPosition);
        Profile friendProfile = profiles.get(currentPosition);
        if (friendProfile == null) {
            friendProfile = instagram.requestProfileFromInstagram(friendEmail);
            profiles.set(currentPosition, friendProfile);
        }
        currentPosition++;
        return friendProfile;
    }

    @Override
    public void reset() {
        currentPosition = 0;
    }
}
