package cz.martlin.douckonline.web.utils;

import cz.martlin.douckonline.web.utils.PathInfo;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author m@rtlin <martlin@seznam.cz>
 */
public class PathInfoTest {

    public PathInfoTest() {
    }

    @Test
    public void testCreateAndGet() {
	final String root = "/foo";
	String path1 = "/foo/bar/baz.aux";
	PathInfo expected1 = new PathInfo("^(.*)\\.aux$", "foo", "bar", "baz.aux");
	PathInfo actual1 = PathInfo.create(path1, root, "^(.*)\\.aux$");
	assertEquals(expected1, actual1);

	assertEquals("baz.aux", actual1.getFileName());
	assertEquals(2, actual1.getFoldersCount());
	assertEquals("foo", actual1.getFolder(0));
	assertEquals("bar", actual1.getFolder(1));
	assertEquals(null, actual1.getFolder(2));

	String path2 = "/foo/bar/baz/aux.qux";
	PathInfo expected2 = new PathInfo("\\.qux", "foo", "bar", "baz", "aux.qux");
	PathInfo actual2 = PathInfo.create(path2, root, "\\.qux");
	assertEquals(expected2, actual2);
    }

    @Test
    public void testVariousPaths() {
	testPath("/foo/bar.qux", "/foo", "^(.*)\\.qux$", "bar.qux", "foo");
	testPath("foo/bar.qux", "foo", "^(.*)\\.qux$", "bar.qux", "foo");
	testPath("/foo/bar/baz/aux.qux", "/foo", "^(.*)\\.qux$", "aux.qux", "foo", "bar", "baz");

	testPath("/foo/bar", "/foo", "^(.*)\\.qux$", null, "foo", "bar");
	testPath("/foo/bar/", "/foo", "^(.*)\\.qux$", null, "foo", "bar");
	testPath("/foo/bar/baz.blah", "/foo", "^(.*)\\.qux$", null, "foo", "bar", "baz.blah");

	testPath("/foo/bar.qux/baz.qux", "/foo", "^(.*)\\.qux$", "baz.qux", "foo", "bar.qux");
    }

    private void testPath(String path, String root, String filePattern, String file, String... folders) {
	PathInfo info = PathInfo.create(path, root, filePattern);

	assertEquals(file, info.getFileName());

	assertEquals(folders.length, info.getFoldersCount());

	for (int i = 0; i < folders.length; i++) {
	    String expected = folders[i];
	    String actual = info.getFolder(i);

	    assertEquals(expected, actual);
	}

    }

    @Test
    public void testModify() {
	PathInfo info1 = new PathInfo("\\.aux", "foo", "bar", "baz.aux");
	info1.changeFolder(1, "Lorem");
	PathInfo expected1 = new PathInfo("\\.aux", "foo", "Lorem", "baz.aux");
	assertEquals(expected1, info1);

	PathInfo info2 = new PathInfo("\\.aux", "foo", "bar", "baz.aux");
	info2.pushFolder("Lorem");
	PathInfo expected2 = new PathInfo("\\.aux", "Lorem", "foo", "bar", "baz.aux");
	assertEquals(expected2, info2);

	PathInfo info3 = new PathInfo("\\.aux", "foo", "bar", "baz.aux");
	String poped3 = info3.popFolder();
	assertEquals("foo", poped3);
	PathInfo expected3 = new PathInfo("\\.aux", "bar", "baz.aux");
	assertEquals(expected3, info3);
    }

    @Test
    public void testToPath() {
	PathInfo info1 = new PathInfo("\\.aux", "foo", "bar", "baz.aux");
	String expected1 = "foo/bar/baz.aux";
	String actual1 = info1.toPath(false);

	assertEquals(expected1, actual1);

	String expected2 = "/foo/bar/baz.aux";
	String actual2 = info1.toPath(true);

	assertEquals(expected2, actual2);
    }

}
