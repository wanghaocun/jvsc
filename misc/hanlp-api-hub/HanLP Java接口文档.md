# HanLP Java接口文档

- **url**: `http://{IP}:{PORT}/lianzhu/hanlphub/common/api`

- **method**:`POST`

- **Body**:

  |  参数名称   | 参数类型    | 必选      | 参数说明                    |
  | :---------: | ----------- | --------- | --------------------------- |
  | **method**  | **String**  | **true**  | Hanlp 处理方法（详见附1表） |
  | **content** | **String**  | **true**  | Hanlp 处理内容              |
  |  **size**   | **Integer** | **false** | Hanlp 处理类型              |
  |  **type**   | **Integer** | **false** | Hanlp 处理数量              |
  | **keyword** | **String**  | **false** | Hanlp 文本推荐关键字        |

---

**附1**

> API排序详情参考[GitHub](https://github.com/hankcs/HanLP/tree/1.x)
>
> 加粗函数有可选参数，详见说明
>
> 删除线标志的是暂未实现的函数

| 序号     | 函数名                   | 说明                 |
| -------- | ------------------------ | -------------------- |
| 1        | hanlpSegment             | 分词                 |
| 2        | standardTokenizerSegment | 标准分词             |
| 3        | nlpTokenizerSegment      | NLP分词              |
| 4        | indexTokenizerSegment    | 索引分词             |
| **5**    | **nShortSegmentSeg**     | **N-最短路径分词**   |
| 6        | crfAnalyze               | CRF分词              |
| 7        | speedTokenizerSegment    | 极速词典分词         |
| ~~*8*~~  | ~~*未实现*~~             | ~~*用户自定义词典*~~ |
| 9        | chinaNameRecognize       | 中国人名识别         |
| 10       | translatedNameRecognize  | 音译人名识别         |
| 11       | japaneseNameRecognize    | 日本人名识别         |
| 12       | placeRecognize           | 地名识别             |
| 13       | organizationRecognize    | 机构名识别           |
| **14**   | **extractKeyword**       | **关键词提取**       |
| **15**   | **extractSummary**       | **自动摘要**         |
| **16**   | **extractPhrase**        | **短语提取**         |
| **17**   | **convertToPinyinList**  | **拼音转换**         |
| **18**   | **convertToChineseCase** | **简繁转换**         |
| **19**   | **textSuggest**          | **文本推荐**         |
| *~~20~~* | ~~*未实现*~~             | *~~语义距离~~*       |
| *~~21~~* | ~~*未实现*~~             | *~~依存句法分析~~*   |

- **5. nShortSegmentSeg**（N-最短路径分词）

  > N最短路分词器 NShortSegment 比最短路分词器慢，但是效果稍微好一些，对命名实体识别能力更强。
  > 一般场景下最短路分词的精度已经足够，而且速度比N最短路分词器快几倍，请酌情选择。
  >
  > 参数：`type`
  >
  > * 0 - N最短路分词器
  > * 1 - 最短路分词器【缺省值】
  >
  
- **14. extractKeyword**（关键词提取）

  > 参数：`size`
  >
  > - 关键词提取数量缺省值为 1

- **15. extractSummary**（自动摘要）

  > 参数：`size`
  >
  > - 自动摘要数量缺省值为 1

- **16. extractPhrase**（短语提取）

  > 参数：`size`
  >
  > - 提取短语数量缺省值为 1

- **17. convertToPinyinList**（拼音转换）

  > 参数：`type`
  >
  > - 0 - 拼音（数字音调）【缺省值】
  > - 1 - 拼音（符号音调）
  > -  2 - 拼音（无音调）
  > - 3 - 声调
  > -  4 - 声母
  > - 5 - 韵母
  > -  6 - 输入法头

- **18. convertToChineseCase**（简繁转换）

  > 参数：`type`
  >
  > - 0 - 繁体转简体
  > - 1 - 简体转繁体
  > - 其它 - 不做转换【缺省值】

- **19. textSuggest**（文本推荐）

  > 参数：`size`
  >
  > - 文本推荐数量缺省值为 1
  >
  > 参数：`keyword`
  >
  > - 关键字【必填】，否则返回为空
