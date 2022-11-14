import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

class Node implements Comparable<Node>{
    private final int freq;
    private Node left;
    private Node right;

    public Node(int freq)
    {
        this.freq = freq;
    }

    public Node(Node left, Node right) {
        this.freq = left.getFreq() + right.getFreq();
        this.left = left;
        this.right = right;
    }

    int getFreq(){
        return this.freq;
    }

    Node getLeft(){
        return this.left;
    }

    Node getRight(){
        return this.right;
    }

    @Override
    public int compareTo(Node node) {
       return Integer.compare(freq, node.getFreq());
    }
}

class Leaf extends Node{
    private final char chr;

    public Leaf(char chr, int freq)
    {
        super(freq);
        this.chr = chr;
    }

    char getChr()
    {
        return this.chr;
    }
}

public class Huffman{

    private Map<Character, Integer> chrFrequencies;
    private final Map<Character, String> huffmanCodes;
    private final String text;
    private Node root;

    public Huffman(String text)
    {
        this.text = text;
        fillCharFreqMap();
        huffmanCodes = new HashMap<>();
    }
    
    private void fillCharFreqMap()
    {
        chrFrequencies = new HashMap<>();
        for(char chr : text.toCharArray()) {
            Integer integer = chrFrequencies.get(chr);
            chrFrequencies.put(chr, integer != null ? integer + 1 : 1);
        }
    }

    private void generateHuffmanCodes(Node node, String code)
    {
        if(node instanceof Leaf)
        {
            huffmanCodes.put(((Leaf) node).getChr(), code);
            return;
        }
        generateHuffmanCodes(node.getLeft(), code.concat("0"));
        generateHuffmanCodes(node.getRight(), code.concat("1"));
    }

    private String getEncodedText() {
        StringBuilder sb = new StringBuilder();
        for(char chr : text.toCharArray()) {
            sb.append(huffmanCodes.get(chr));
        }
        return sb.toString();
    }

    public String encode() {
        Queue<Node> queue = new PriorityQueue<>();
        chrFrequencies.forEach((chr, freq) -> queue.add(new Leaf(chr, freq)));
        while(queue.size() > 1){
            queue.add(new Node(queue.poll(), queue.poll()));
        }
        generateHuffmanCodes(root = queue.poll(), "");
        return getEncodedText();
    }

    public String decode(String encText)
    {
        StringBuilder sb = new StringBuilder();
        Node curr = root;
        for(char chr : encText.toCharArray()) {
            curr = chr == '0' ? curr.getLeft() : curr.getRight();
            if(curr instanceof Leaf) {
                sb.append(((Leaf) curr).getChr());
                curr = root;
            }
        }
        return sb.toString();
    }

    public void printCodes()
    {
        huffmanCodes.forEach((chr, code) -> 
            System.out.println(chr + " ==> " + code));
    }
    public static void main(String[] args) {
        Huffman huffman = new Huffman("aaaaaaaaabbbbbbbccccccdddddaababc");
        String encText = huffman.encode();
        System.out.println(encText);

        huffman.printCodes();

        String decText = huffman.decode(encText);
        System.out.println(decText);
    }
}