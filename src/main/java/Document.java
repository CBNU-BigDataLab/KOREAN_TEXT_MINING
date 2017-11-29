/**
 * @Author Dara Penhchet
 * @Created Date 30-11-2017
 * @Description Document for Searching Results
 * @Implements Comparable<Document>
 */
public class Document implements Comparable<Document>{
    /**
     * Document Text
     */
    private String text;
    /**
     * Term Frequency Inversion Document Frequency of the term result
     */
    private double tfIdf;
    /**
     * Offset of the Search Keyword in the Document
     */
    private int offset;
    /**
     * Length of the Search Keyword in the Document
     */
    private int length;

    public Document(){
        this(null, null, 0, 0);
    }

    public Document(String text, Double tfidf){
        this(text, tfidf, 0, 0);
    }

    public Document(String text, Double tfIdf, int offset, int length) {
        this.text = text;
        this.tfIdf = tfIdf;
        this.offset = offset;
        this.length = length;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public double getTfIdf() {
        return tfIdf;
    }

    public void setTfIdf(double tfIdf) {
        this.tfIdf = tfIdf;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    /**
     * Comparison the Document with TfIdf
     * @param compareDocument Compare with the other Document
     * @return int
     */
    public int compareTo(Document compareDocument) {

        double compareTfIdf = ((Document) compareDocument).getTfIdf();

        //Ascending order
        //return Double.compare(this.tfidf, compareTfIdf);

        //Descending order
        return Double.compare(compareTfIdf, this.tfIdf);
    }

    /**
     * ToString to display when we print the document
     * @return String
     */
    @Override
    public String toString() {
        return "Document{" +
                "text='" + text + '\'' +
                ", tfIdf=" + tfIdf +
                ", offset=" + offset +
                ", length=" + length +
                '}';
    }
}
