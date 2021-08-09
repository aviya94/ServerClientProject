package PartOneProject;

import java.util.Collection;
/**
 * ממשק היורש מHasUUID ויש לו מתודה אחת שמכתיבה פונקציונליות בשםgetCollection
 */

public interface Node extends HasUUID {
    public Collection<Node> getCollection(Class<HasUUID> hasUuidClass);
}
