package no.ntnu.idatg2001.gr13;

import java.util.*;

import lombok.Getter;

/**
 * A class representing a story, part of the WiNG application.
 */
public class Story {
    @Getter
    private final String title;
    private final Map<Link, Passage> passages;
    @Getter
    private final Passage openingPassage;

    // Constructor
    public Story(String title, Passage openingPassage) {
        this.title = title;
        this.openingPassage = openingPassage;
        this.passages = new HashMap<>();
    }

    /**
     * A method to remove a specified passage from Passages. The
     * passage will not be removed if other passages are linked to the passage.
     *
     * @param link The Link key to the Passage object to be removed.
     */
    public void removePassage(Link link) throws IllegalStateException {
        if (passages.containsKey(link)) {
            // The string of the passage to be removed
            String passageToBeRemoved = passages.get(link).getTitle();
            // Returns a list of links in each passage
            List<Link> linksInPassages = passages.values().stream()
                    .map(Passage::getLinks)
                    .flatMap(Collection::stream)
                    .toList();
            linksInPassages.stream()
                    // Removes the original link from the list since this will always match
                    .filter(l -> l != link)
                    // Filter on the link (l) reference and checks that link reference does not match the passage.
                    .filter(l -> !l.getReference().matches(passageToBeRemoved))
                    .forEach(passages::remove);
        }
    }


    /**
     * A method for getting Link objects that are not referring to any Passage Title.
     *
     * @return A list of broken Links.
     */
    public List<Link> getBrokenLinks() {
        List<Link> brokenLinks = new ArrayList<>();
        Set<Link> allLinks = new HashSet<>(passages.keySet());

        passages.values().forEach(passage -> allLinks.addAll(passage.getLinks()));
        allLinks.forEach(link -> {
            if (!passages.containsKey(link)) {
                brokenLinks.add(link);
            }
        });
        return brokenLinks;
    }

    /**
     * A method for putting the passage into a Map.
     *
     * @param passage to be added to the Map.
     */
    public void addPassage(Passage passage) {
        passages.put(new Link(passage.getTitle(), passage.getTitle()), passage);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Story story = (Story) o;
        if (!Objects.equals(title, story.title)) {
            return false;
        }
        if (!Objects.equals(passages, story.passages)) {
            return false;
        }
        for (Map.Entry<Link, Passage> entry : passages.entrySet()) {
            Link key = entry.getKey();
            Passage value = entry.getValue();
            Passage otherValue = story.passages.get(key);
            if (!Objects.equals(value, otherValue)) {
                return false;
            }
        }
        return Objects.equals(openingPassage, story.openingPassage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, passages, openingPassage);
    }

    /**
     * Given a link, return the passage that the link points to.
     *
     * @param link The link that the passage is associated with.
     * @return The passage that is associated with the link.
     */
    public Passage getPassage(Link link) {
        return passages.get(link);
    }

    public Collection<Passage> getPassages() {
        return passages.values();
    }
}
