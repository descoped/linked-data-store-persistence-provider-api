package io.descoped.lds.api.persistence.flattened;

import io.descoped.lds.api.persistence.DocumentKey;
import io.descoped.lds.api.persistence.streaming.Fragment;
import io.descoped.lds.api.persistence.streaming.FragmentType;
import org.testng.annotations.Test;

import java.nio.charset.StandardCharsets;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Iterator;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class FlattenedDocumentLeafNodeTest {

    @Test
    public void thatLeafNodeWithSmallValueProduceSingleCorrectFragment() {
        ZonedDateTime now = ZonedDateTime.now(ZoneId.of("Etc/UTC"));
        DocumentKey key = new DocumentKey("ns", "E", "1", now);
        FlattenedDocumentLeafNode node = new FlattenedDocumentLeafNode(key, "name", FragmentType.STRING, "My Name", 8 * 1024);
        Iterator<Fragment> iterator = node.fragmentIterator();
        assertTrue(iterator.hasNext());
        Fragment fragment = iterator.next();
        Fragment expected = new Fragment("ns", "E", "1", now, "name", FragmentType.STRING, 0, "My Name".getBytes(StandardCharsets.UTF_8));
        assertEquals(fragment, expected);
        assertFalse(iterator.hasNext());
    }

    @Test
    public void thatLeafNodeWithLargeValueProduceSeveralCorrectFragments() {
        ZonedDateTime now = ZonedDateTime.now(ZoneId.of("Etc/UTC"));
        DocumentKey key = new DocumentKey("ns", "E", "1", now);
        FlattenedDocumentLeafNode node = new FlattenedDocumentLeafNode(key, "name", FragmentType.STRING, "My Name", 3);
        Iterator<Fragment> iterator = node.fragmentIterator();
        assertTrue(iterator.hasNext());
        Fragment acutal0 = iterator.next();
        Fragment expected0 = new Fragment("ns", "E", "1", now, "name", FragmentType.STRING, 0, "My ".getBytes(StandardCharsets.UTF_8));
        assertEquals(acutal0, expected0);
        assertTrue(iterator.hasNext());
        Fragment acutal3 = iterator.next();
        Fragment expected3 = new Fragment("ns", "E", "1", now, "name", FragmentType.STRING, 3, "Nam".getBytes(StandardCharsets.UTF_8));
        assertEquals(acutal3, expected3);
        assertTrue(iterator.hasNext());
        Fragment acutal6 = iterator.next();
        Fragment expected6 = new Fragment("ns", "E", "1", now, "name", FragmentType.STRING, 6, "e".getBytes(StandardCharsets.UTF_8));
        assertEquals(acutal6, expected6);
        assertFalse(iterator.hasNext());
    }
}
