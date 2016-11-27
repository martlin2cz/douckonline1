package cz.martlin.douckonline.web.rest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author m@rtlin <martlin@seznam.cz>
 */
public class PathInfo {

    private final List<String> path;
    private final boolean hasFile;
    
    protected PathInfo(String filePattern, String ...parts) {
	this.path = new ArrayList(Arrays.asList(parts));
	this.hasFile = (parts[parts.length - 1]).matches(filePattern);
    }
    
    public String getFileName() {
	if (hasFile) {
	    return path.get(path.size() - 1);
	} else {
	    return null;
	}
    }
    
    public int getFoldersCount() {
	int count = path.size();
	if (hasFile) {
	    return count - 1;
	} else {
	    return count;
	}
    }
    
    protected boolean isFolderIndex(int index) {
	return index >= 0 && index < getFoldersCount();
    }
    
    public String getFolder(int index) {
	if (isFolderIndex(index)) {
	    return path.get(index);
	} else {
	    return null;
	}
    }
    
    public void changeFolder(int index, String newFolder) {
	if (isFolderIndex(index)) {
	    path.set(index, newFolder);
	} 
    }
    
    
    public void pushFolder(String newFolder) {
	path.add(0, newFolder);
    }
    
    
    public String popFolder() {
	if (isFolderIndex(0)) {
	    return path.remove(0);
	} else {
	    return null;
	}
    }
    
    public String toPath(boolean withRootSlash) {
	String joined = String.join("/", path);
	if (withRootSlash) {
	    return '/' + joined;
	} else {
	    return joined;
	}
    }

    @Override
    public int hashCode() {
	int hash = 3;
	hash = 79 * hash + Objects.hashCode(this.path);
	hash = 79 * hash + (this.hasFile ? 1 : 0);
	return hash;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj) {
	    return true;
	}
	if (obj == null) {
	    return false;
	}
	if (getClass() != obj.getClass()) {
	    return false;
	}
	final PathInfo other = (PathInfo) obj;
	if (this.hasFile != other.hasFile) {
	    return false;
	}
	if (!Objects.equals(this.path, other.path)) {
	    return false;
	}
	return true;
    }


    @Override
    public String toString() {
	return "PathInfo{" + path + "}";
    }
    
    
    
    public static PathInfo create(String path, String root, String filePattern) {
	if (!path.startsWith(root)) {
	    throw new IllegalArgumentException("The path " + path + " is not inside of root " + root);
	}
	
	if (path.charAt(0) == '/') {
	    path = path.substring(1);
	}
	
	String[] parts = path.split("\\/");
	return new PathInfo(filePattern, parts);
    }

    
}
