/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DS_PROJECT;

/**
 *
 * @author KostandinVllahu
 */
public class Driver {
    
    public static void main(String[] args){
        BinarySearchTree bst = new BinarySearchTree();
        
        bst.insert(5);
        bst.insert(4);
        bst.insert(4);
        bst.insert(7);
        bst.insert(9);
        bst.insert(9);
        bst.insert(9);
        bst.insert(3);
        bst.insert(3);
        bst.insert(3);
        bst.insert(3);
        
       
        System.out.println("In order: ");
        bst.inorder();
        System.out.println("--------------------");
        System.out.println("Pre order: ");
        bst.preorder();
        System.out.println("--------------------");
        System.out.println("Post order: ");
        bst.postorder();
        System.out.println("\n--------------------");
        System.out.println("Level Order: ");
        bst.levelOrder();
        System.out.println("--------------------");
        System.out.println("Delete 1 duplicate: ");
        bst.deleteValue(5);
        bst.inorder();
        System.out.println("--------------------");
//        System.out.println("Total Duplicates");
//        System.out.println(bst.getDuplicate());
//        System.out.println(" ");
        System.out.println("Duplicated Numbers and Not duplicated numbers: ");
        System.out.println("Numbers with duplicates: ");
        bst.nodesDuplicated(bst.root);
        System.out.println("--------------------");
        System.out.println("Number with no duplicates: ");
        bst.nodesNotDuplicated(bst.root);
        System.out.println("--------------------");
        System.out.println("Elements that have duplicates witht their duplicates: ");
        bst.inorderDuplicate(bst.root);
        System.out.println("--------------------");
        System.out.println("Replace value");
        bst.replaceValue(4, 10);
        bst.inorder();
        System.out.println("--------------------");
        System.out.println("Number of duplicates of a specific number: ");
        System.out.println(bst.nrOfDuplicates(9));
        System.out.println("--------------------");
        System.out.println("Total amount of duplicated numbers  with their total of duplicates: ");
        bst.duplicateTraversal();
        System.out.println("--------------------");
        System.out.println("Delete all duplicates of a duplicated element: ");
        bst.deleteAllDuplicates(bst.root, 9);
        bst.inorder();
        System.out.println("--------------------");
        System.out.println("Delete only one copy of a duplicate from an element: ");
        System.out.println("KETU KA NJE BUG QE FSHIN TE GJITHA DHE JO 1");
        bst.deleteOnlyDuplicates(bst.root, 3);
        bst.inorder();
        System.out.println("--------------------");
        System.out.println("Delete all duplicates from the tree: ");
        bst.deleteAllDuplcatesOfTree(bst.root);
        bst.inorder();
        System.out.println("--------------------");
    }
    
}
