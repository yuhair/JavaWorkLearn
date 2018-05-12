package datascience;

import us.oculus360.text.ml.LSHSemanticVectors;

import java.io.FileWriter;
import java.io.IOException;

public class ExportWord2VectInTxt {

    public static void main(String[] args) throws IOException {

        String domainId = "80";

        String domainDir = "/Users/yuhair/hai/datascience/textmodels/domain/";
        String storeLocation = domainDir+domainId+"/"+domainId+".vectors";
        String txtLocation = domainDir+domainId+"/"+domainId+".vectors.txt";
        LSHSemanticVectors vectors = new LSHSemanticVectors(storeLocation, false);
        int dimension = vectors.dimension();
        int vocabSize = vectors.getWords().size();
        System.out.println("vector dimension: " + dimension);
        System.out.println("vocabulary size: " + vocabSize);

//        System.out.println(vectors.getVector("cancel"));
//        System.out.println(vectors.getNeighbors("cancel", 10));

        FileWriter fw = new FileWriter(txtLocation);
        String first_line = vocabSize + " " + dimension;
        fw.write(first_line + '\n');
        for (String word : vectors.getWords()) {
            String values = vectors.getVector(word).toString();
            String[] vector = values.substring(1, values.length() - 1).split(", ");
            String joinedWord = String.join("_", word.split(" "));
            String wordVector = joinedWord + ' ' + String.join(" ", vector);
            fw.write(wordVector + '\n');
        }
        fw.close();

        System.out.println("from: " + storeLocation);
        System.out.println("to: " + txtLocation);
    }
}
