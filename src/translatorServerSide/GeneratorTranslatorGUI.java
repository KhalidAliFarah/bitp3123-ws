package translatorServerSide;

/**
 * GeneratorTranslatorGUI class represents a translator for generating translations of text.
 * It contains a predefined set of words in different languages.
 * 
 * @author: Khalid
 * 
 */
public class GeneratorTranslatorGUI {
    private String[][] words = {
        {"Good morning", "Good night", "How are you?", "Thank you", "Goodbye", "What’s up?"},
        {"Selamat pagi", "Selamat malam", "Apa khabar?", "Terima kasih", "Selamat tinggal", "Ada apa?"},
        {"صباح الخير", "طاب مساؤك", "كيف حالك؟", "شكرا لك", "مع السلامة", "ماأخبارك؟"},
        {"좋은 아침", "안녕히 주무세요", "어떻게 지내세요?", "감사합니다", "안녕", "뭐야?"}
    };

    /**
     * Constructor for the GeneratorTranslatorGUI class.
     * Initializes the words array.
     */
    public GeneratorTranslatorGUI() {
    }

    /**
     * Translates the given target text and language indices into the corresponding translation.
     *
     * @param targetTextIndex     Index of the target text
     * @param targetLanguageIndex Index of the target language
     * @return Translation result as a string
     */
    public String translate(int targetTextIndex, int targetLanguageIndex) {
        if (targetTextIndex >= 0 && targetTextIndex < words[0].length &&
            targetLanguageIndex >= 0 && targetLanguageIndex < words.length) {
            String originalText = words[0][targetTextIndex];
            String translatedText = words[targetLanguageIndex][targetTextIndex];
            return originalText + " = " + translatedText;
        } else {
            return "Invalid translation indices";
        }
    }
}
