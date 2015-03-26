package de.idos.guava.treetraverser;

import java.util.Enumeration;
import java.util.List;

import javax.swing.tree.DefaultMutableTreeNode;

import org.junit.Test;
import com.google.common.collect.Lists;
import com.google.common.collect.TreeTraverser;

public class TreeTraverserDemoTest {

    @Test
    public void name() throws Exception {
        DemoNode root = rootNode();

        System.out.println(root.toString());

        Iterable children = new TreeTraverser<DemoNode>() {
            @Override
            public Iterable<DemoNode> children(DemoNode root) {
                Enumeration children1 = root.children();
                List<DemoNode> result = Lists.newArrayList();
                while(children1.hasMoreElements()) {
                    result.add((DemoNode) children1.nextElement());
                }

                return result;


            }
        }.breadthFirstTraversal(root);

        for (Object child : children) {
            System.out.println(child.toString());
        }



    }

    private DemoNode rootNode() {
        DemoNode root = new DemoNode("h");
        DemoNode child1 = new DemoNode("d");
        child1.add(new DemoNode("a"));
        child1.add(new DemoNode("b"));
        child1.add(new DemoNode("c"));
        root.add(child1);
        root.add(new DemoNode("e"));
        DemoNode g = new DemoNode("g");
        g.add(new DemoNode("f"));
        root.add(g);
        return root;
    }

    public static class DemoNode extends DefaultMutableTreeNode {

        private final String name;

        public DemoNode(String name) {
            super(name, true);
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }
}
