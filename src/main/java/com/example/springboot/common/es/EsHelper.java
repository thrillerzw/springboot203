package com.example.springboot.common.es;

import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.DisMaxQueryBuilder;
import org.elasticsearch.index.query.MatchPhrasePrefixQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.functionscore.FieldValueFactorFunctionBuilder;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.xpack.client.PreBuiltXPackTransportClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class EsHelper {
    private static Logger logger= LoggerFactory.getLogger(EsHelper.class);
    private static TransportClient client;

    @Value("${elasticsearch.cluster-nodes}")
    private String clusterNodes;

    @Value("${elasticsearch.cluster-name}")
    private String clusterName;

    @Value("${elasticsearch.xpack.security.user}")
    private String xpackSecurityUser;

    @PostConstruct
    protected void init() {
        try {
            Settings settings = Settings.builder()
                    //安装后默认的kibana登录用户 elastic:changeme ,可以通过kibana创建新用户分配权限。
                    .put("xpack.security.user", xpackSecurityUser)
                    .put("cluster.name", clusterName)
                    .put("client.transport.sniff", true).build();
            client = new PreBuiltXPackTransportClient(settings);//x-pack控制权限
            // client = new PreBuiltTransportClient(settings); //没有权限控制
            if (!"".equals(clusterNodes)) {
                for (String nodes : clusterNodes.split(",")) {
                    String InetSocket[] = nodes.split(":");
                    String Address = InetSocket[0];
                    Integer port = Integer.valueOf(InetSocket[1]);
                    client.addTransportAddress(new
                            InetSocketTransportAddress(InetAddress.getByName(Address), port));
                }
            }
            logger.info("init elasticsearch client success");
        } catch (UnknownHostException e) {
            logger.error("init elasticsearch client error: {}", e.getMessage());
        }
    }

    /*static {
        try {
            InputStream is = EsHelper.class.getClassLoader().getResourceAsStream("es.properties");
            Properties properties = new Properties();
            properties.load(is);
            Settings settings = Settings.builder()
                    //安装后默认的kibana登录用户 elastic:changeme ,可以通过kibana创建新用户分配权限。
                    .put("xpack.security.user", properties.get("xpack.security.user"))
                    .put("cluster.name", properties.get("cluster.name"))
                    .put("client.transport.sniff", true).build();
            client = new PreBuiltXPackTransportClient(settings);//x-pack控制权限
            // client = new PreBuiltTransportClient(settings); //没有权限控制
            List<String> list = Arrays.asList(properties.getProperty("host").split(";"));
            for (String h : list) {
                client.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(h), Integer.valueOf(properties.getProperty("port"))));
            }
            logger.info("get elasticsearch client success");

        }catch (Throwable throwable){
            logger.error("elastic search initialized error",throwable);
        }
    }*/
    //索引
    public static boolean addDocument(String index, String type, String id,String jsonString) {
        IndexResponse response;
        if(id==null||id.equals("")){
            response = client.prepareIndex(index, type)
                    .setSource(jsonString,XContentType.JSON)
                    .get(); //get又调用了.execute().actionGet();
        }else {
            response = client.prepareIndex(index, type,id)
                    .setSource(jsonString, XContentType.JSON)
                    .get();
        }
        return "UPDATED".equals(response.getResult())?true:false;
    }
    //搜索
    public static List<Stock> searchStockMatch(String index, String type, int from, int size, String keyword) {

        //A query that generates the union of documents produced by its sub-queries
        DisMaxQueryBuilder disMaxQueryBuilder= QueryBuilders.disMaxQuery();
        //前缀查询
        MatchPhrasePrefixQueryBuilder matchPhrasePrefixQueryBuilder = QueryBuilders.matchPhrasePrefixQuery("code", keyword).maxExpansions(10).boost(10);
        //new_score = old_score * number_of_codeLength ,   number_of_codeLength=1/长度
        FieldValueFactorFunctionBuilder fieldValueFactorFunction= ScoreFunctionBuilders.fieldValueFactorFunction("codeLength");
        FunctionScoreQueryBuilder functionScoreQueryBuilder=QueryBuilders.functionScoreQuery(matchPhrasePrefixQueryBuilder,fieldValueFactorFunction);
        disMaxQueryBuilder.add(functionScoreQueryBuilder);
        //分词后匹配查询
       /* disMaxQueryBuilder.add(QueryBuilders.matchQuery("englishName",keyword));
        disMaxQueryBuilder.add(QueryBuilders.matchQuery("chineseName",keyword));*/
        disMaxQueryBuilder.add(QueryBuilders.multiMatchQuery(keyword,"englishName","chineseName"));

        SearchRequestBuilder searchRequestBuilder = client.prepareSearch(index).setTypes(type)
                .setQuery(disMaxQueryBuilder)
//               .addSort(scoreSortBuilder)
                .setFrom(from).setSize(size);
        SearchResponse response = searchRequestBuilder.execute().actionGet();
        SearchHits hits = response.getHits();
        if(hits==null || hits.getHits().length<=0){
            return new ArrayList<Stock>();
        }
        SearchHit[] searchHits=hits.getHits();
        List<Stock> stocks=new ArrayList<Stock>();
        for(int i=0;i<searchHits.length;i++){
            Stock stock=new Stock();
            SearchHit searchHitFields=searchHits[i];
            try {
                //文档的_source的值
                Map<String, Object> sourceMap = searchHits[i].getSourceAsMap();
                stock.code=(String)sourceMap.get("code");
                stock.englishName=(String)sourceMap.get("englishName");
                stock.chineseName=(String)sourceMap.get("chineseName");
            }catch (Exception e){
                logger.error("generate stock error",e);
                continue;
            }
            stocks.add(stock);
        }
        return stocks;
    }


}
