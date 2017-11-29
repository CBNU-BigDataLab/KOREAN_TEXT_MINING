import com.twitter.penguin.korean.TwitterKoreanProcessorJava;
import com.twitter.penguin.korean.tokenizer.KoreanTokenizer;
import scala.collection.JavaConversions;
import scala.collection.Seq;

import java.util.*;

/**
 * Term Frequency and Inversion Document Frequency
 */
public class TfIdfMain001 {

    public static void main(String args[]){
        List<String> documents = Arrays.asList(
                "쉬벌...쌤숭 갤7 배터리 스웰링났네요-_-",
                "ㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋ 갤7 배터리 스웰링 서비스센터에서 자연스러운 현상입니다 했던 게시글 누가 신고해서 삭제되었네요.",
                "배터리 스웰링이 애플 삼성 LG 상관없이 일어날 수 있는 건 사실인데",
                "배터리 스웰링이 자연스러운 현상이면,",
                "미맥스 배터리 스웰링이 생겼네요..",
                "아이폰8+ 새 제품 배터리 스웰링이 있었네요",
                "아이폰 8 시리즈 &  노트 8 이슈 사항들(폭발,스웰링,갭,빛샘,코팅)",
                "애플 아이폰 8 배터리 스웰링 국내 발생",
                "아이폰 8+ 중국에서도 배터리 스웰링 현상 나타나",
                "이번 아이폰8 배터리 스웰링 생각보다 문제가 좀 생길수도 있을 것 같네요",
                "애플 아이폰 8 \"균열(배터리 스웰링) 문제 조사 중\" 짧은 성명",
                "국내 아이폰 8 배터리 스웰링 추가 발생.",
                "갤럭시 노트3 배터리 스웰링현상있네요",
                "아임백 배터리도 스웰링 증상이 발생하는 걸까요?",
                "배터리 부풀음(스웰링 현상) 무섭군요..",
                "삼성 배터리 스웰링은 종특인가요??",
                "배터리 스웰링이 핸드폰 때문일수도..",
                "아이언2  배터리 스웰링",
                "노트4 스웰링(부풀음) 줸장할 삼성",
                "스웰링이 언제부터 일반적이었나요.",
                "갤7 배터리 스웰링? 사진보니 사고싶은 맘이 사라지네요 ㅠㅠ"
        );

        List<Collection<String>> documentsOfTerms = new LinkedList<>();

        List<Iterable<String>> documentsOfTermss = new LinkedList<>();
        for(String document: documents) {

            // Normalize
            CharSequence normalized = TwitterKoreanProcessorJava.normalize(document);
            System.out.println(normalized);

            // Tokenize
            Seq<KoreanTokenizer.KoreanToken> tokens = TwitterKoreanProcessorJava.tokenize(normalized);
            System.out.println(TwitterKoreanProcessorJava.tokensToJavaStringList(tokens));
            System.out.println(TwitterKoreanProcessorJava.tokensToJavaKoreanTokenList(tokens));

            List<KoreanTokenizer.KoreanToken> tokenss = JavaConversions.seqAsJavaList(tokens);

            List<String> docs = new ArrayList<>();
            for(KoreanTokenizer.KoreanToken token: tokenss){
                if(!token.text().equalsIgnoreCase(" ")) {
                    docs.add(token.text());
                }
            }

            //TODO: TF for a single Document
            //Map<String, Double> terms = TfIdf.tf(Arrays.asList(document.split(" ")));
            Map<String, Double> terms = TfIdf.tf(docs);
            documentsOfTerms.add(docs);
            documentsOfTermss.add(docs);
        }

        //TODO: TF for a set of documents
        Iterable<Map<String, Double>> tfs = TfIdf.tfs(documentsOfTerms);

        Map<String, Double> idfs = TfIdf.idf(documentsOfTermss, false, false);

        System.out.println(TfIdf.idfFromTfs(tfs));

        for (Map.Entry<String, Double> e : idfs.entrySet()) {
            System.out.println(e.getKey() + ", " + e.getValue());
        }

        //TODO: Searching by Keywords
        String searchWord = "아이폰";
        System.out.println("찾고있습니다 ==> " + searchWord);
        int documentIndex = 1;
        List<Document> searchedDocuments = new ArrayList<>();
        for(Map<String, Double> tf: tfs){
            Map<String, Double> tfidf = TfIdf.tfIdf(tf, idfs, TfIdf.Normalization.COSINE);
            if(tfidf.containsKey(searchWord)){
                System.out.println("Document "+ documentIndex + " ==> " + documents.get(documentIndex-1));
                System.out.println(searchWord + " = " + tfidf.get("아이폰"));
                searchedDocuments.add(new Document(documents.get(documentIndex-1), tfidf.get("아이폰")));
            }
            documentIndex++;
        }
        Collections.sort(searchedDocuments);
        System.out.println(searchedDocuments);

    }

    //TODO: Finding Important 3 Words in Text Using TF-IDF
    public static void findTop3Keywords(){

    }
}