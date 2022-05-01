package com.lianzhu.hanlphub.common;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.corpus.document.sentence.Sentence;
import com.hankcs.hanlp.dictionary.py.Pinyin;
import com.hankcs.hanlp.model.crf.CRFLexicalAnalyzer;
import com.hankcs.hanlp.seg.Dijkstra.DijkstraSegment;
import com.hankcs.hanlp.seg.NShort.NShortSegment;
import com.hankcs.hanlp.seg.Segment;
import com.hankcs.hanlp.suggest.Suggester;
import com.hankcs.hanlp.tokenizer.IndexTokenizer;
import com.hankcs.hanlp.tokenizer.NLPTokenizer;
import com.hankcs.hanlp.tokenizer.SpeedTokenizer;
import com.hankcs.hanlp.tokenizer.StandardTokenizer;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * HanLP 常用功能封装
 *
 * @author wanghc
 * @link {https://github.com/hankcs/HanLP/tree/1.x}
 * @since 2020-07-28
 **/
@SuppressWarnings("unused")
public class HanlpCommonApi {

    /**
     * 1. 分词
     * HanLP 中有一系列“开箱即用”的静态分词器，以 Tokenizer 结尾。
     * HanLP.segment 其实是对 StandardTokenizer.segment 的包装。
     *
     * @param content String
     * @return List -> [["word", "nature"], ["word", "nature"]]
     */
    public List<String[]> hanlpSegment(String content, Object... args) {
        List<String[]> result = new ArrayList<>();
        HanLP.segment(content)
                .forEach(term -> result.add(new String[]{term.word, String.valueOf(term.nature)}));

        return result;
    }

    /**
     * 2. 标准分词
     *
     * @param content String
     * @return List -> [["word", "nature"], ["word", "nature"]]
     */
    public List<String[]> standardTokenizerSegment(String content, Object... args) {
        List<String[]> result = new ArrayList<>();
        StandardTokenizer.segment(content)
                .forEach(term -> result.add(new String[]{term.word, String.valueOf(term.nature)}));

        return result;
    }

    /**
     * 3. NLP分词
     *
     * @param content String
     * @return List -> [["word", "nature"], ["word", "nature"]]
     */
    public List<String[]> nlpTokenizerSegment(String content, Object... args) {
        List<String[]> result = new ArrayList<>();
        NLPTokenizer.segment(content)
                .forEach(term -> result.add(new String[]{term.word, String.valueOf(term.nature)}));

        return result;
    }

    /**
     * 4. 索引分词
     *
     * @param content String
     * @return List -> [["word", "nature"], ["word", "nature"]]
     */
    public List<String[]> indexTokenizerSegment(String content, Object... args) {
        List<String[]> result = new ArrayList<>();
        IndexTokenizer.segment(content)
                .forEach(term -> result.add(new String[]{term.word, String.valueOf(term.nature)}));

        return result;
    }

    /**
     * 5. N-最短路径分词
     * N最短路分词器 NShortSegment 比最短路分词器慢，但是效果稍微好一些，对命名实体识别能力更强。
     * 一般场景下最短路分词的精度已经足够，而且速度比N最短路分词器快几倍，请酌情选择。
     * 0 - N最短路分词器
     * 1 - 最短路分词器【缺省值】
     *
     * @param content String
     * @return List -> [["word", "nature"], ["word", "nature"]]
     */
    public List<String[]> nShortSegmentSeg(String content, Object... args) {
        List<String[]> result = new ArrayList<>();
        // 关键词提取数量缺省值为 1
        Integer type = (Integer) Arrays.stream(args).toArray()[1];
        type = type == null ? 1 : type;
        if (type == 0) {
            // N最短路分词器
            Segment nShortSegment = new NShortSegment().enableCustomDictionary(false)
                    .enablePlaceRecognize(true).enableOrganizationRecognize(true);

            nShortSegment.seg(content)
                    .forEach(term -> result.add(new String[]{term.word, String.valueOf(term.nature)}));

            return result;
        }
        // 最短路分词器
        Segment shortestSegment = new DijkstraSegment().enableCustomDictionary(false)
                .enablePlaceRecognize(true).enableOrganizationRecognize(true);

        shortestSegment.seg(content)
                .forEach(term -> result.add(new String[]{term.word, String.valueOf(term.nature)}));

        return result;
    }

    /**
     * 6. CRF分词
     *
     * @param content String
     * @return Sentence
     */
    public Sentence crfAnalyze(String content, Object... args) throws IOException {
        CRFLexicalAnalyzer analyzer = new CRFLexicalAnalyzer();

        return analyzer.analyze(content);
    }

    /**
     * 7. 极速词典分词
     *
     * @param content String
     * @return List -> [["word", "nature"], ["word", "nature"]]
     */
    public List<String[]> speedTokenizerSegment(String content, Object... args) {
        List<String[]> result = new ArrayList<>();
        SpeedTokenizer.segment(content)
                .forEach(term -> result.add(new String[]{term.word, String.valueOf(term.nature)}));

        return result;
    }

// 8. 用户自定义词典

    /**
     * 9. 中国人名识别
     *
     * @param content String
     * @return List -> [["word", "nature"], ["word", "nature"]]
     */
    public List<String[]> chinaNameRecognize(String content, Object... args) {
        List<String[]> result = new ArrayList<>();
        Segment segment = HanLP.newSegment().enableNameRecognize(true);
        segment.seg(content)
                .forEach(term -> result.add(new String[]{term.word, String.valueOf(term.nature)}));

        return result;
    }

    /**
     * 10. 音译人名识别
     *
     * @param content String
     * @return List -> [["word", "nature"], ["word", "nature"]]
     */
    public List<String[]> translatedNameRecognize(String content, Object... args) {
        List<String[]> result = new ArrayList<>();
        Segment segment = HanLP.newSegment().enableTranslatedNameRecognize(true);
        segment.seg(content)
                .forEach(term -> result.add(new String[]{term.word, String.valueOf(term.nature)}));

        return result;
    }

    /**
     * 11. 日本人名识别
     *
     * @param content String
     * @return List -> [["word", "nature"], ["word", "nature"]]
     */
    public List<String[]> japaneseNameRecognize(String content, Object... args) {
        List<String[]> result = new ArrayList<>();
        Segment segment = HanLP.newSegment().enableJapaneseNameRecognize(true);
        segment.seg(content)
                .forEach(term -> result.add(new String[]{term.word, String.valueOf(term.nature)}));

        return result;
    }

    /**
     * 12. 地名识别
     *
     * @param content String
     * @return List -> [["word", "nature"], ["word", "nature"]]
     */
    public List<String[]> placeRecognize(String content, Object... args) {
        List<String[]> result = new ArrayList<>();
        Segment segment = HanLP.newSegment().enablePlaceRecognize(true);
        segment.seg(content)
                .forEach(term -> result.add(new String[]{term.word, String.valueOf(term.nature)}));

        return result;
    }

    /**
     * 13. 机构名识别
     *
     * @param content String
     * @return List -> [["word", "nature"], ["word", "nature"]]
     */
    public List<String[]> organizationRecognize(String content, Object... args) {
        List<String[]> result = new ArrayList<>();
        Segment segment = HanLP.newSegment().enableOrganizationRecognize(true);
        segment.seg(content)
                .forEach(term -> result.add(new String[]{term.word, String.valueOf(term.nature)}));

        return result;
    }

    /**
     * 14. 关键词提取
     *
     * @param content String
     * @param args    args[0] int [default:1]
     * @return List
     */
    public List<String> extractKeyword(String content, Object... args) {
        // 关键词提取数量缺省值为 1
        Integer size = (Integer) Arrays.stream(args).toArray()[0];
        size = size == null || size == 0 ? 1 : size;

        return HanLP.extractKeyword(content, size);
    }

    /**
     * 15. 自动摘要
     *
     * @param content String
     * @param args    args[0] int [default:1, max:5]
     * @return List
     */
    public List<String> extractSummary(String content, Object... args) {
        // 自动摘要数量缺省值为 1
        Integer size = (Integer) Arrays.stream(args).toArray()[0];
        size = size == null || size == 0 ? 1 : size;

        return HanLP.extractSummary(content, size);
    }

    /**
     * 16. 短语提取
     *
     * @param content String
     * @param args    args[0] int [default:1]
     * @return List
     */
    public List<String> extractPhrase(String content, Object... args) {
        // 提取短语数量缺省值为 1
        Integer size = (Integer) Arrays.stream(args).toArray()[0];
        size = size == null || size == 0 ? 1 : size;

        return HanLP.extractPhrase(content, size);
    }

    /**
     * 17. 拼音转换
     * 0 - 拼音（数字音调）【缺省值】
     * 1 - 拼音（符号音调）
     * 2 - 拼音（无音调）
     * 3 - 声调
     * 4 - 声母
     * 5 - 韵母
     * 6 - 输入法头
     *
     * @param content String
     * @param args    args[0] int [default:1]
     * @return List
     */
    public List<Object> convertToPinyinList(String content, Object... args) {
        List<Object> result = new ArrayList<>();
        List<Pinyin> pinyinList = HanLP.convertToPinyinList(content);
        int type = (int) Arrays.stream(args).toArray()[1];
        switch (type) {
            // 拼音（符号音调）
            case 1:
                pinyinList.forEach(pinyin -> result.add(pinyin.getPinyinWithToneMark()));
                break;
            // 拼音（无音调）
            case 2:
                pinyinList.forEach(pinyin -> result.add(pinyin.getPinyinWithoutTone()));
                break;
            // 声调
            case 3:
                pinyinList.forEach(pinyin -> result.add(pinyin.getTone()));
                break;
            // 声母
            case 4:
                pinyinList.forEach(pinyin -> result.add(pinyin.getShengmu()));
                break;
            // 韵母
            case 5:
                pinyinList.forEach(pinyin -> result.add(pinyin.getYunmu()));
                break;
            // 输入法头
            case 6:
                pinyinList.forEach(pinyin -> result.add(pinyin.getHead()));
                break;
            default:
                // 拼音（数字音调）
                result.addAll(pinyinList);
                break;
        }

        return result;
    }

    /**
     * 18. 简繁转换
     * 0 - 简体转繁体
     * 1 - 繁体转简体
     * 其它 - 不做转换【缺省值】
     *
     * @param content String
     * @param args    [0] int
     * @return String
     */
    public String convertToChineseCase(String content, Object... args) {
        Integer type = (Integer) Arrays.stream(args).toArray()[1];
        if (type == null || type > 1) {
            return content;
        }

        return type == 1 ? HanLP.convertToSimplifiedChinese(content) : HanLP.convertToTraditionalChinese(content);
    }

    /**
     * 19. 文本推荐
     * (句子级别，从一系列句子中挑出与输入句子最相似的那一个)
     *
     * @param content String
     * @param args    args[0] key String; args[1] size [default:1]
     * @return List
     */
    public List<String> textSuggest(String content, Object... args) {
        // 文本推荐数量缺省值为 1
        Integer size = (Integer) Arrays.stream(args).toArray()[0];
        size = size == null || size == 0 ? 1 : size;
        String keyword = (String) Arrays.stream(args).toArray()[2];
        if (StringUtils.isEmpty(keyword)) {
            return null;
        }

        String[] splitArray = content.split("\\n");
        Suggester suggester = new Suggester();
        Arrays.asList(splitArray).forEach(suggester::addSentence);

        return suggester.suggest(keyword, size);
    }

// 20. 语义距离

// 21. 依存句法分析

}
