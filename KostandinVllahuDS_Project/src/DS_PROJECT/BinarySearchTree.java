/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DS_PROJECT;

//import java.util.Stack;

import java.util.ArrayList;
import java.util.LinkedList;


/**
 *
 * @author KostandinVllahu
 */
public class BinarySearchTree {
Node root;
ArrayList<Integer> duplicate = new ArrayList<Integer>(); //Krijohet nje arraylist qe te rruaj te gjitha duplikatat me shume se nje
    class Node{
        int key; // key mban vlerat e futura ne tree
        Node left, right;
        int count; //Krijohet nje count qe te numeroje sa here behet duplikate nje x numer
        public Node(int key){
            this.key = key;
            left = right = null;
            this.count  = 1; // Count fillon nga 1 dhe shtohet me +1 kur behet duplikate
        }
    }
    
    public BinarySearchTree(){
        root = null;
    }
    
    public void insert(int value){
        root = insertNode(root, value); //Ne insertNode futen vlerat 
    }

    public Node insertNode(Node root, int key){
        if(root == null){ //nese root eshte null krijohet nje node i ri.
            root = new Node(key);
            return root;
        }   
        //shiko ketu
     //Funksioni i counter merr parasysh key qe ka vleren e futur dhe shikohet nese key eshte e njejt me nje
     // numer te futur ajo behet count +1 dhe kthen root.
    if(key == root.key){
        root.count++;
       //root.right = insertNode(root.right, key);
        return root;
    }
        
    if(key > root.key){  //Nese vlera e futur eshte me e madhe se rrenja ajo futet ne te djathte te pemes
        root.right = insertNode(root.right, key);
    }
    else if(key < root.key){
    root.left = insertNode(root.left, key); //Nese eshte me e vogel ajo futet ne te majten e pemes.
    }
    return root;
}
    
//   
    
    public void inorder(){
        inorderTree(root); //Inorder pema printohet nga me e vogla (me majtas) deri te me e madhja (me djathtas)
    }
    
    public void inorderTree(Node node){
        if(node == null) return;
       //Nese nodi eshte eshte null behet return dhe nuk printohet gje, 
        inorderTree(node.left); //Ne inorder tree printohen e para node.left dhe pastaj node.right
        System.out.println(node.key + " (" + node.count + ")"); //count printon duplikatat e cdo numeri
        inorderTree(node.right); //node.right printohet
    }

    
    
    
    public void preorder() {
        preorderTree(root); //Rradha qe printohet preorder eshte root, kalon ne te majte dhe pastaj ne te djathte
    }
    
    public  void preorderTree(Node node){
        if (node == null) return; //Ketu shikohet nese node eshte null do japi return nuk printohet gje
        
        System.out.println(node.key + " (" + node.count + ")"); //Printohet ne fillim ana e majte 
        preorderTree(node.left);
        preorderTree(node.right); //Pastaj printohet ana e djathte
    }
    
    //Rradha qe printohet postorder eshte e majta e fundit me e vogle vete te parent i saj aty kalon
    //ne te djathten e fundit me te madhen dhe aty vete te parent i te djathtes dhe perfundon ne root
    public void postorder(){
        postorderTree(root); 
       
    }
    
    
    public void postorderTree(Node node){
        if(node == null) return;
            postorderTree(node.left);
            postorderTree(node.right);
            System.out.print(node.key + " (" + node.count + ")");
    }
    
    //Ne levelOrder rradha e printimit eshte root dhe kalon majtas, djathtas, majtas dhe perfundon djathtas
     public void levelOrder(){
        int height = height(root); 
        for(int i=1; i <=height; i++){ 
            printLevel(root, i);
        }
    }
    
    public int height(Node root){
        if(root == null) return 0;
        int leftHeight = height(root.left); 
        int rightHeight = height(root.right);
        
        if(leftHeight > rightHeight){
            return leftHeight  + 1; //Nese root.left eshte me e madhe se root.right , atehere root.left rritet me +1
        }else {
            return rightHeight + 1; //Nese root.right eshte me e madhe se root.left , atehere root.right rritet me +1
        }
    }
    
    //Menyre tjeter per level order
   // @author KostandinVllahu
//    public void levelOrderTree(Node root){
//        if(root == null) return;
//        LinkedList<Node> list = new LinkedList<>();
//        list.add(root);
//        while(list.isEmpty()){
//            Node currentNode = list.poll();
//            System.out.println(currentNode.key + " ");
//            if(currentNode.left != null) list.add(currentNode.left);
//            if(currentNode.right != null) list.add(currentNode.right);
//        }
//    }
//    
//    public void levelOrderList(){
//        levelOrderTree(root);
//    }
    //====KETU MBARON==========
     
    public void printLevel(Node root, int level){
       if(root == null) return; 
       
       if(level == 1){
           System.out.println(root.key + " (" + root.count + ")");
       }else if(level > 0){ // mos te jete 1
           printLevel(root.left, level-1);
           printLevel(root.right, level-1); //ishte -1
       }
        
    }
    public void deleteValue(int value){
        root = deleteNode(root, value); //Ky funksion fshin vlerat
    }
    public Node deleteNode(Node root, int value){
        if(root == null) return root;
        
        //Vlera e futur ne program kontrollohet nese eshte me e vogel se root dhe nese eshte e me vogel iken majtas
        //Ndryshe verifikon nese eshte me e madhe dhe nese eshte me e madhe kalon djathtas dhe fshin vleren qe eshte cakturar
        //Nese del qe vlera e futur eshte duplikate atehere fshihet nje duplikate e vleres 
        if(value < root.key){ 
            root.left = deleteNode(root.left, value);
        }else if(value > root.key){
            root.right = deleteNode(root.right, value);
        }else{
            //================Fshin duplikatat ketu=================
            if(root.count > 1){
                root.count--;
                return root;
            }
            //==============Mbaron ketu==========================
            if(root.left == null){
                return root.right;
            }else if(root.right == null){
                return root.left;
            }
            
           root.key = minValue(root.right);
           root.right = deleteNode(root.right, root.key);
        }
        return root;
    }
    
    public int minValue(Node root){
        int min = root.key;
        while(root.left != null){
            min = root.left.key;
            root = root.left;
        }
        return min;
    }
    
    //funskion qe do marre nje vlere dhe do me ktheje nr e duplikatave
    //int dupl(int value){
    //return duplicate????????????????????????
    
    //Liste qe gjen numerin e vlerave  qe printohen me shume se 1 here
    public void inorderDuplicate(Node root){
        if(root == null) return;
       
        inorderDuplicate(root.left);
        if(root.count > 1){ 
            System.out.println(root.key + "( " + root.count + ")");
            duplicate.add(root.key); //nqs counter +1 futet ne list
        }
        inorderDuplicate(root.right);
    }
    
    public ArrayList<Integer> getDuplicate(){ //ketu kapet lista e duplikatave dhe futet ne array 
        inorderDuplicate(root);
        return duplicate;
    }
    //Metode qe ben replace value me vleren e replaceWith
    public void replaceValue(int value, int replaceWith){
        replaceNode(root, value, replaceWith);
    }
    
    public void replaceNode(Node root, int value, int replaceWith){
        //ndiq inorder tree
        if(root == null) return;
        if(root.key == value && root.count > 1){
            //root.key = replaceWith;
            int count = root.count;
            //Kjo eshte nqs dua te zevendosej vetem duplikatat
            /*root.count = 1;
            for(int i = 0; i< count -1; i++){
            insert(replaceWith);
            }*/
            for(int i = 0; i< count; i++){
                deleteValue(root.key);
                insert(replaceWith);
            }
            return;
        }
        
        replaceNode(root.left, value, replaceWith);
        replaceNode(root.right, value, replaceWith);
    }
    //===================ESHTE TESTIM PER TE HEQUR TE GJITHA DUPLIKATAT=================================
    //===================NUK PO E PERDOR====================
    //printon size e array

//     public ArrayList<Integer> TotalDuplicate(){ //ketu kapet lista
//       // inorderDuplicate(root);
//       if(duplicate.size() >  0){
//           int a = duplicate.size();
//           System.out.println(a);
//           //System.out.println("Update size");
//          // duplicate.clear();
//          // System.out.println(a);
//           }
//       
//        return duplicate;
//    }
    //==================MBARON KETU TESTIMI=========
     
     
    //Printon sa here eshte duplikat nje X numer.
     private int duplicates = 0;
     
     public int nrOfDuplicates(int nr){
         nrOfDuplicates(root, nr);
         return duplicates;
     }
     // Ky kodi ben count duplicaten e nr te futur
     //Nr merr vleren nga value i futur dhe kontrollon a ka duplikate te njejte me vleren e futur
     //dhe shikon eshte ne te majte apo te djathte qe te gjej numerin e vleres se futur
     //Pasi e gjen shikon sa duplikata ka kjo vlera dhe printon
     public void nrOfDuplicates(Node root, int nr){
         if(root == null){
             return;
         }
         if(root.key == nr){
             duplicates = root.count;
             return;
         }
         nrOfDuplicates(root.left, nr);
         nrOfDuplicates(root.right, nr);
     }
     
     
     // Ky kodi fshin te gjitha duplicatat e nr te futur
     //Nr merr vleren nga value i futur dhe kontrollon a ka duplikate te njejte me vleren e futur
     //dhe shikon eshte ne te majte apo te djathte qe te gjej numerin e vleres se futur
     //Pasi e gjen duplikaten i fshin te gjitha
     
     public void deleteAllDuplicates(Node root, int nr){
         if(root == null) return;
         
         if(root.key == nr){
            int count = root.count;
            for(int i = 0; i<count - 1; i++){
                deleteValue(root.key);
            }
         }
         deleteAllDuplicates(root.left, nr);
         deleteAllDuplicates(root.right, nr);
     }
 
     
     // Ky kodi fshin vetem nje duplicate e nr te futur
     //Nr merr vleren nga value i futur dhe kontrollon a ka duplikate te njejte me vleren e futur
     //dhe shikon eshte ne te majte apo te djathte qe te gjej numerin e vleres se futur
     //Pasi e gjen duplikaten fshin vetem nje
     
     public void deleteOnlyDuplicates(Node root, int nr){
         if(root == null) return;
         
         if(root.key == nr && root.count > 1){
            //int count = root.count;
            
                deleteValue(root.key);
         }
         deleteOnlyDuplicates(root.left, nr);
         deleteOnlyDuplicates(root.right, nr);
     }
     
     // Ky kodi fshin duplicaten e te gjithe numerave te duplikuar
     //Kerkon te gjithe numerat qe jane futur me shume se 1 here 
     //Pasi e gjen duplikatat i fshin te gjitha
     
     public void deleteAllDuplcatesOfTree(Node root){
         if(root == null) return;
         
         /*===OPSION TJETER====
         
         if(root.count > 1){
         int count = root.count;
         for(int i=0; i<count; i++){
         deleteValue(root.key);
            }
         }
         
         deleteAllDuplcatesOfTree(root.left);
         deleteAllDuplcatesOfTree(root.right);
         
         */
         
         if(root.count > 1){
            root.count = 1;
         }
         deleteAllDuplcatesOfTree(root.left);
         deleteAllDuplcatesOfTree(root.right);
     }
     int nrOfDupl = 0;
     int totalDupl = 0;
     
     //Ky kod printon numerat qe jane duplikat dhe numerin total te duplikatave te tyre
     //Kam krijuar nje Integer nrOfDuplicates qe do bej count numerat qe kane duplikata dhe do i printoj
     //Kam krijuar nje Integer tjeter qe jep shumen totale te duplikatave
     public void duplicateTraversal(){
         duplicateTraversal(root);
         System.out.println(nrOfDupl + " this numbers are found duplicated");
         System.out.println(totalDupl + " this is total number of duplicates");
     }
     //nese ka nje root.count qe eshte e duplikuar me shume se 1 here nrOfDuplicates e printon dhe totalDupl mbledh duplikatat
     //Ky veprim do behet majtas dhe djathtas pemes ku e merr si veprim nga root.left dhe root.right
     public void duplicateTraversal(Node root){
         if(root == null) return;
         
         if(root.count > 1){
             nrOfDupl++;
             totalDupl += root.count;
         }
         duplicateTraversal(root.left);
         duplicateTraversal(root.right);
     }
     //==========Pika e fundit============
     //Ky kod printon numerat qe jane duplikate
     //Verifikon root.count nese eshte me e madhe se 1 dhe ata qe jane me shume se 1 printohen ketu
     //Dhe behet majtas dhe djathtas te pema
     public void nodesDuplicated(Node root){
          if(root == null) return;
         
         if(root.count > 1){
             System.out.println(root.key + " ");
         }
         nodesDuplicated(root.left);
         nodesDuplicated(root.right);
     }
     
     
     //Ky kod printon numerat qe nuk jane duplikate
     //Verifikon nese root.count eshte baraz me 1 dhe kontrollon te dyja anet
     //Ato numera qe jane baraz me 1 do printohen numera qe nuk kane duplikate
     //Dhe behet majtas dhe djathtas te pema
     public void nodesNotDuplicated(Node root){
          if(root == null) return;
         
         if(root.count == 1){
             System.out.println(root.key + " ");
         }
         nodesNotDuplicated(root.left);
         nodesNotDuplicated(root.right);
     }
     
     //====================TEST===================
    /*public void print(){
      while(root.left != null){
           System.out.println(root.key);
           root = root.left;
       }
   }
     
     public void print2(){
         Node temp = root;
         if(root != null){
             System.out.println(root.key);
             root = root.left;
             print2();
         }
         root = temp;
     }
     
//     public void replace2(int key, int value){
//         Node temp = root;
//         if(root == null){
//             root = temp;
//         }else if(root.key < key){
//             root = root.left;
//             this.replace2(key, value);
//         }else if(root.key > key){
//             
//             root = root.right;
//             this.replace2(key, value);
//         }else if(root.key == key){
//               root.key = value;
//               root = temp;            
//         }
//         
//     }
     */
}    

    
    

