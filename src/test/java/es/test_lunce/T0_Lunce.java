//package es;
//
//import java.io.File;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//import org.apache.lucene.analysis.Analyzer;
//import org.apache.lucene.analysis.standard.StandardAnalyzer;
//import org.apache.lucene.document.Document;
//import org.apache.lucene.document.Field.Store;
//import org.apache.lucene.document.LongField;
//import org.apache.lucene.document.StringField;
//import org.apache.lucene.document.TextField;
//import org.apache.lucene.index.DirectoryReader;
//import org.apache.lucene.index.IndexReader;
//import org.apache.lucene.index.IndexWriter;
//import org.apache.lucene.index.IndexWriterConfig;
//import org.apache.lucene.index.IndexWriterConfig.OpenMode;
//import org.apache.lucene.index.Term;
//import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
//import org.apache.lucene.queryparser.classic.QueryParser;
//import org.apache.lucene.search.BooleanClause.Occur;
//import org.apache.lucene.search.BooleanQuery;
//import org.apache.lucene.search.FuzzyQuery;
//import org.apache.lucene.search.IndexSearcher;
//import org.apache.lucene.search.NumericRangeQuery;
//import org.apache.lucene.search.Query;
//import org.apache.lucene.search.ScoreDoc;
//import org.apache.lucene.search.Sort;
//import org.apache.lucene.search.SortField;
//import org.apache.lucene.search.SortField.Type;
//import org.apache.lucene.search.TermQuery;
//import org.apache.lucene.search.TopDocs;
//import org.apache.lucene.search.WildcardQuery;
//import org.apache.lucene.search.highlight.Formatter;
//import org.apache.lucene.search.highlight.Highlighter;
//import org.apache.lucene.search.highlight.QueryScorer;
//import org.apache.lucene.search.highlight.Scorer;
//import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
//import org.apache.lucene.store.Directory;
//import org.apache.lucene.store.FSDirectory;
//import org.apache.lucene.util.Version;
//import org.wltea.analyzer.lucene.IKAnalyzer;
//
//public class Test1 {
//	public static void main(String[] args) throws Exception {
////		核心API:创建索引-----IndexWriter--方法.addDocument(...)
////			搜索------IndexReader---方法.search(...)
////		高亮---Highlighter
////		排序---query添加参数--new Sort(new SortField("long", Type.LONG, true))
//
//
//		Object o;
//
////		 createIndex();
//		 addMoreData();
//		 ikTest();
//
//		// 读取索引库---构造核心API：indexSearch----创造query对象--可以是自定义query（Query的子类）也可以是默认Query----调用search对象搜寻
//		 searchTest();
//		// duoTiaoJianQuery();
//		// 自定义条件查询
//		// 词条查询
//		// termQuery();
//		// 通配符查询----？*
//		 wildCardQuery();
//		// 模糊查询
//		// fuzzyQuery();
//		// 数值范围查询
//		// numberQuery();
//		// 组合查询
//		// booleanQuery();
//
//		// 修改索引-----查询出来--删除--添加新的
////		 updateIndex();
//		// 两种删除----查询一条删除---删除所有
//		// delete();
//		// 高亮----查询---条件-----替换原本的显示--hl.getBestFragment(new IKAnalyzer(),
//		// "title", doc.get("title"));
////		heightLigter();
//		// 排序-----与普通查询的不同之处：加参数Sort--new Sort(new SortField("long", Type.LONG, true))---第三个参数：boolean reverce
//		// sortQuery();
//
//		// 分页---lunece不支持分页---此处做的是逻辑分页----排好序---限制-id来查询document
//		// limit();
//
//		// 设置激励因子---提高排名---为Filed添加---相同搜索条件的时候排名靠前
//		// jiLi();
////		fuzzyQuery();
//	}
//
//
//	private static void jiLi() throws IOException {
//		// 过程：读取文件写入索引库----需要分词设置，需要读取文件目录(索引库)
//
//		Version matchVersion = Version.LATEST;
//		// 索引写入配置文件
//		IndexWriterConfig iwc = new IndexWriterConfig(matchVersion, new IKAnalyzer());
//		// 文件目录(索引库)
//		Directory dir = FSDirectory.open(new File("D:/develop/Lucene索引查看工具/index"));
//		// 文件写入对象
//		IndexWriter indexWriter = new IndexWriter(dir, iwc);
//
//		Document doc = new Document();
//		TextField textField = new TextField("title", "黑马程序员成就it黑马", Store.YES);
//		textField.setBoost(30);//30倍
//		doc.add(new StringField("id", "黑马程序员成就it黑马", Store.YES));// 不分词--有索引
//		doc.add(textField);
//		doc.add(new TextField("content", "激励因子", Store.YES));
//
//
//		// 写入文件
//		indexWriter.addDocument(doc);
//
//		indexWriter.commit();
//		indexWriter.close();
//	}
//
//	private static void limit() throws Exception {
//		int nowPage=1;
//		int pageSize=10;
//
//		int start=(nowPage-1)*pageSize;
//		int end =start+pageSize;
//
//
//		FSDirectory directory = FSDirectory.open(new File("D:/develop/Lucene索引查看工具/index"));
//		IndexReader indexReader = DirectoryReader.open(directory);
//
//		IndexSearcher indexSearcher = new IndexSearcher(indexReader);
//
//		Query query = new FuzzyQuery(new Term("title", "ik"), 2);
//		// ************************************************************************************************
//		TopDocs search = indexSearcher.search(query, Integer.MAX_VALUE,	new Sort(new SortField("long", Type.LONG, true)));
//		// ************************************************************************************************
//
//		System.out.println("命中数：" + search.totalHits);
//		ScoreDoc[] scoreDocs = search.scoreDocs;
//
//
//		for (int i = start; i < end; i++) {
//			Document doc = indexSearcher.doc(i);
//			System.out.println(doc.get("long"));
//
//		}
//
//		indexReader.close();
//
//	}
//
//	private static void sortQuery() throws Exception {
//		FSDirectory directory = FSDirectory.open(new File("D:/develop/Lucene索引查看工具/index"));
//		IndexReader indexReader = DirectoryReader.open(directory);
//
//		IndexSearcher indexSearcher = new IndexSearcher(indexReader);
//
//		Query query = new FuzzyQuery(new Term("title", "ik"), 2);
//		// ************************************************************************************************
//		TopDocs search = indexSearcher.search(query, Integer.MAX_VALUE,	new Sort(new SortField("long", Type.LONG, true)));
//		// ************************************************************************************************
//
//		System.out.println("命中数：" + search.totalHits);
//		ScoreDoc[] scoreDocs = search.scoreDocs;
//		for (ScoreDoc sc : scoreDocs) {
//
//			Document doc = indexSearcher.doc(sc.doc);
//
//			// 替换原来的输出
//			System.out.println(doc.get("long"));
//
//		}
//
//		indexReader.close();
//
//	}
//
//	private static void heightLigter() throws Exception {
//		FSDirectory directory = FSDirectory.open(new File("D:/develop/Lucene索引查看工具/index"));
//		IndexReader indexReader = DirectoryReader.open(directory);
//
//		IndexSearcher indexSearcher = new IndexSearcher(indexReader);
//
//		Query query = new FuzzyQuery(new Term("title", "ik"), 2);
//		// ************************************************************************************************
//		Scorer score = new QueryScorer(query);
//		Formatter format = new SimpleHTMLFormatter("<前缀>", "<后缀>");
//		Highlighter hl = new Highlighter(format, score);
//		// ************************************************************************************************
//		// Query query = new FuzzyQuery(new Term("title", "题"),2);
//		TopDocs search = indexSearcher.search(query, Integer.MAX_VALUE);
//
//		System.out.println("命中数：" + search.totalHits);
//		ScoreDoc[] scoreDocs = search.scoreDocs;
//		for (ScoreDoc sc : scoreDocs) {
//
//			Document doc = indexSearcher.doc(sc.doc);
//			// ************************************************************************************************
//
//			// 替换原来的输出
//			String bestFragment = hl.getBestFragment(new IKAnalyzer(), "title", doc.get("title"));
//			System.out.println(bestFragment);
//
//		}
//
//		indexReader.close();
//
//	}
//
//	private static void delete() throws Exception {
//		FSDirectory directory = FSDirectory.open(new File("D:/develop/Lucene索引查看工具/index"));
//
//		IndexWriter indexWriter = new IndexWriter(directory, new IndexWriterConfig(Version.LATEST, new IKAnalyzer()));
//		BooleanQuery query = new BooleanQuery();
//		query.add(NumericRangeQuery.newLongRange("long", 10L, 15L, true, true), Occur.MUST);
//		// 删除操作----根据条件删除
//		indexWriter.deleteDocuments(query);
//
//		// indexWriter.deleteAll();//删除所有
//
//		indexWriter.commit();
//		indexWriter.close();
//
//	}
//
//	private static void updateIndex() throws Exception {
//		FSDirectory directory = FSDirectory.open(new File("D:/develop/Lucene索引查看工具/index"));
//
//		IndexWriterConfig config = new IndexWriterConfig(Version.LATEST, new IKAnalyzer());
//
//		IndexWriter indexWriter = new IndexWriter(directory, config);
//
//		Document doc = new Document();
//		doc.add(new StringField("id", "update2", Store.YES));
//		// 更新操作
//		indexWriter.updateDocument(new Term("id", "update1"), doc);
//
//		indexWriter.commit();
//		indexWriter.close();
//
//	}
//
//	private static void booleanQuery() throws Exception {
//		FSDirectory directory = FSDirectory.open(new File("D:/develop/Lucene索引查看工具/index"));
//		IndexReader indexReader = DirectoryReader.open(directory);
//
//		IndexSearcher indexSearcher = new IndexSearcher(indexReader);
//
//		BooleanQuery query = new BooleanQuery();
//		NumericRangeQuery<Long> query1 = NumericRangeQuery.newLongRange("long", 10L, 20L, true, true);
//		NumericRangeQuery<Long> query2 = NumericRangeQuery.newLongRange("long", 15L, 30L, true, true);
//
//		// 交集
//		// query.add(query1,Occur.MUST);
//		// query.add(query2,Occur.MUST);
//
//		// 并集
//		// query.add(query1,Occur.SHOULD);
//		// query.add(query2,Occur.SHOULD);
//
//		// 在第一个的基础上剔除第二个
//		query.add(query1, Occur.MUST);
//		query.add(query2, Occur.MUST_NOT);
//
//		TopDocs search = indexSearcher.search(query, Integer.MAX_VALUE);
//
//		ScoreDoc[] scoreDocs = search.scoreDocs;
//		for (ScoreDoc sc : scoreDocs) {
//
//			Document doc = indexSearcher.doc(sc.doc);
//			System.out.println(doc.get("long"));
//
//		}
//
//		indexReader.close();
//
//	}
//
//	private static void numberQuery() throws Exception {
//
//		FSDirectory directory = FSDirectory.open(new File("D:/develop/Lucene索引查看工具/index"));
//		IndexReader indexReader = DirectoryReader.open(directory);
//
//		IndexSearcher indexSearcher = new IndexSearcher(indexReader);
//		NumericRangeQuery<Long> query = NumericRangeQuery.newLongRange("long", 10L, 20L, true, true);
//		TopDocs search = indexSearcher.search(query, Integer.MAX_VALUE);
//
//		ScoreDoc[] scoreDocs = search.scoreDocs;
//		for (ScoreDoc sc : scoreDocs) {
//
//			Document doc = indexSearcher.doc(sc.doc);
//			System.out.println(doc.get("long"));
//
//		}
//
//		indexReader.close();
//
//	}
//
//	private static void fuzzyQuery() throws Exception {
//
//		FSDirectory directory = FSDirectory.open(new File("D:/develop/Lucene索引查看工具/index"));
//		IndexReader indexReader = DirectoryReader.open(directory);
//
//		IndexSearcher indexSearcher = new IndexSearcher(indexReader);
//
//		Query query = new FuzzyQuery(new Term("title", "程序激励"), 2);
//		// Query query = new FuzzyQuery(new Term("title", "题"),2);
//		TopDocs search = indexSearcher.search(query, Integer.MAX_VALUE);
//
//		System.out.println("命中数：" + search.totalHits);
//		ScoreDoc[] scoreDocs = search.scoreDocs;
//		for (ScoreDoc sc : scoreDocs) {
//
//			Document doc = indexSearcher.doc(sc.doc);
//			System.out.println(sc.score+"--"+doc.get("id") + ":" + doc.get("title"));
//
//		}
//
//		indexReader.close();
//
//	}
//
//	/**
//	 * ?占位一个 *占位无限 没有指定分词器用的是啥？？？？？
//	 *
//	 * @throws Exception
//	 */
//	private static void wildCardQuery() throws Exception {
//		// 读取索引库
//		FSDirectory directory = FSDirectory.open(new File("D:/develop/Lucene索引查看工具/index"));
//		IndexReader indexReader = DirectoryReader.open(directory);
//
//		IndexSearcher indexSearcher = new IndexSearcher(indexReader);
//
//		WildcardQuery wildcardQuery = new WildcardQuery(new Term("title", "it*"));
//		// WildcardQuery wildcardQuery = new WildcardQuery(new Term("id",
//		// "ik?"));
//		TopDocs search = indexSearcher.search(wildcardQuery, Integer.MAX_VALUE);
//
//		System.out.println("命中数：" + search.totalHits);
//
//		ScoreDoc[] scoreDocs = search.scoreDocs;
//		for (ScoreDoc sc : scoreDocs) {
//			Document doc = indexSearcher.doc(sc.doc);
//
//			System.out.println(sc.score+"--"+doc.get("id") + ":" + doc.get("content")+":"+doc.get("title"));
//
//		}
//
//		indexReader.close();
//
//	}
//
//	private static void termQuery() throws Exception {
//		// 读取索引库
//		FSDirectory open = FSDirectory.open(new File("D:/develop/Lucene索引查看工具/index"));
//		IndexReader indexReader = DirectoryReader.open(open);
//
//		IndexSearcher searcher = new IndexSearcher(indexReader);
//
//		TermQuery query = new TermQuery(new Term("id", "ik10"));
//
//		TopDocs search = searcher.search(query, Integer.MAX_VALUE);
//		ScoreDoc[] scoreDocs = search.scoreDocs;
//		System.out.println("明中数：" + search.totalHits);
//		for (ScoreDoc sc : scoreDocs) {
//			Document doc = searcher.doc(sc.doc);
//			System.out.println(doc.get("id") + ":" + doc.get("content"));
//
//		}
//
//	}
//
//	/**
//	 * 基本多条件查询
//	 *
//	 * @throws Exception
//	 */
//	private static void duoTiaoJianQuery() throws Exception {
//		// 读取索引库
//		FSDirectory open = FSDirectory.open(new File("D:/develop/Lucene索引查看工具/index"));
//		// 文件读取器读取--生成
//		IndexReader reader = DirectoryReader.open(open);
//		// 核心API----IndexSearch
//		IndexSearcher indexSearcher = new IndexSearcher(reader);
//		// 多个域查询
//		MultiFieldQueryParser multiFieldQueryParser = new MultiFieldQueryParser(new String[] { "content", "title" },
//				new IKAnalyzer());
//		// 解析查询条件
//		Query parse = multiFieldQueryParser.parse("黑马程序员");
//
//		TopDocs search = indexSearcher.search(parse, Integer.MAX_VALUE);
//
//		int totalHits = search.totalHits;
//		System.out.println("命中数：" + totalHits);
//		// 得分数组
//		ScoreDoc[] scoreDocs = search.scoreDocs;
//
//		for (ScoreDoc sc : scoreDocs) {
//			// document的id
//			int doc = sc.doc;
//			// 分数
//			float score = sc.score;
//			System.out.println("分数：" + score);
//			Document doc2 = indexSearcher.doc(doc);
//			System.out.println(doc2.get("id") + ":" + doc2.get("content"));
//
//		}
//
//		// 关闭
//		open.close();
//
//	}
//
//	/**
//	 * 简单单条件查询
//	 *
//	 * @throws Exception
//	 */
//	private static void searchTest() throws Exception {
//		// 加载索引库
//		FSDirectory open = FSDirectory.open(new File("D:/develop/Lucene索引查看工具/index"));
//		// 获取读取对象----读取索引库
//		IndexReader reader = DirectoryReader.open(open);
//		// 搜索对象
//		IndexSearcher search = new IndexSearcher(reader);
//		// 构造query对象---查询解析器对象--封装查询条件
//		QueryParser parser = new QueryParser("content", new IKAnalyzer());
//		Query query = parser.parse("传智播客");
//		TopDocs topDocs = search.search(query, Integer.MAX_VALUE);
//		System.out.println("命中数：" + topDocs.totalHits);
//
//		// 获取得分对象数组
//		ScoreDoc[] scoreDocs = topDocs.scoreDocs;
//		for (ScoreDoc scoreDoc : scoreDocs) {
//			System.out.println("得分：" + scoreDoc.score);
//			// 读取对象通过编号获取document
//			int i = scoreDoc.doc;
//			System.out.println("编号：" + scoreDoc.doc);
//			Document doc = search.doc(i);
//			System.out.println("id:" + doc.get("id"));
//			System.out.println("content:" + doc.get("content"));
//		}
//
//		open.close();
//	}
//
//	private static void ikTest() throws Exception {
//
//		IndexWriterConfig config = new IndexWriterConfig(Version.LATEST, new IKAnalyzer());
//		config.setOpenMode(OpenMode.CREATE);// create是覆盖----append是追加
//		// indexWriter
//		IndexWriter indexWriter = new IndexWriter(FSDirectory.open(new File("D:/develop/Lucene索引查看工具/index")),
//				config);
//
//
//		for (int i = 4; i < 30; i++) {
//			Document doc = new Document();
//			doc.add(new StringField("id", "ik" + i, Store.YES));
//			doc.add(new TextField("title", "黑马程序员成就it黑马", Store.YES));
//			doc.add(new TextField("content", "传智播客黑马程序员", Store.YES));
//			doc.add(new LongField("long", new Long(i), Store.YES));
//
//			indexWriter.addDocument(doc);
//
//			indexWriter.commit();
//		}
//		indexWriter.close();
//	}
//
//	private static void addMoreData() throws IOException {
//		IndexWriterConfig config = new IndexWriterConfig(Version.LATEST, new StandardAnalyzer());
//		// 文件写入
//		IndexWriter write = new IndexWriter(FSDirectory.open(new File("D:/develop/Lucene索引查看工具/index")),
//				config);
//
//		config.setOpenMode(OpenMode.CREATE);// create是覆盖----append是追加
//
//		List<Document> list = new ArrayList<>();
//		Document doc1 = new Document();
//		doc1.add(new StringField("id", "1", Store.YES));
//		doc1.add(new TextField("text", "标题1", Store.YES));
//		doc1.add(new LongField("long", 1L, Store.YES));
//		Document doc2 = new Document();
//		doc2.add(new StringField("id", "2", Store.YES));
//		doc2.add(new TextField("text", "标题2", Store.YES));
//		doc2.add(new LongField("long", 2L, Store.YES));
//		Document doc3 = new Document();
//		doc3.add(new StringField("id", "3", Store.YES));
//		doc3.add(new TextField("text", "标题3", Store.YES));
//		doc3.add(new LongField("long", 3L, Store.YES));
//
//		list.add(doc1);
//		list.add(doc2);
//		list.add(doc3);
//
//		write.addDocuments(list);
//
//		write.commit();
//		write.close();
//
//	}
//
//	private static void createIndex() throws IOException {
//		// 过程：读取文件写入索引库----需要分词设置，需要读取文件目录(索引库)
//
//		Analyzer analyzer = new StandardAnalyzer();// 标准分词
//		Version matchVersion = Version.LATEST;
//		// 索引写入配置文件
//		IndexWriterConfig iwc = new IndexWriterConfig(matchVersion, analyzer);
////		iwc.setOpenMode(OpenMode.)
//
//		// 文件目录(索引库)
//		Directory dir = FSDirectory.open(new File("D:/develop/Lucene索引查看工具/index"));
//		// 文件写入对象
//		IndexWriter indexWriter = new IndexWriter(dir, iwc);
//
//		Document doc = new Document();
//		doc.add(new StringField("id", "1", Store.YES));// 不分词--有索引
//		doc.add(new TextField("title", "标题", Store.YES));
//		doc.add(new TextField("content", "奥特曼打怪兽", Store.YES));
//
//		// 写入文件
//		indexWriter.addDocument(doc);
//
//		indexWriter.commit();
//		indexWriter.close();
//	}
//
//}
