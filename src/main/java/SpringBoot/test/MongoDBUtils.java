package SpringBoot.test;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

public class MongoDBUtils {
	
	public static MongoDatabase getMongodbConnection(){
		
		try{
//		连接Mongodb服务
		MongoClient mongoClient = new MongoClient( "127.0.0.1" , 27017 );  
//		连接数据库
		MongoDatabase mongoDatabase = mongoClient.getDatabase("admin");
		System.out.println("连接成功");
		return mongoDatabase;
		}catch(Exception e){
			System.out.println("失败");
			System.out.println( e.getClass().getName() + ": " + e.getMessage() );
			return null;
		}
		
	}
	  /** 
	    *   <p>方法描述: 创建集合</p> 
	  
	    */  
	    private static void createCollection (String  collectionName) {  
	        getMongodbConnection().createCollection(collectionName);  
	        System.out.println("集合创建成功");  
	    }  
	    /**
	     * 选择集合
	     * @param collectionName
	     * @return
	     */
	    private static MongoCollection<Document> selectCollection(String collectionName){
	    	MongoCollection<Document> collection = getMongodbConnection().getCollection(collectionName);
	        System.out.println("集合选择"+collectionName +"成功");  	    
	        return collection;  
	    }
	    /**
	     * 插入文档
	     * @param collection
	     */
	    private static void insertDocument(MongoCollection<Document> collection){
	    	Document document = new Document("likes",200);
	    	Document document1 = new Document("title","MongoDB1").append("description", document).append("likes", 100);
	    	List<Document> documents = new ArrayList<Document>();
	    	documents.add(document1);
	    	collection.insertMany(documents);
	         System.out.println("文档插入成功");  
	    	
	    }
	    /**
	     * 查询文档
	     * @param collection
	     */
	    private static void selectDocument(MongoCollection<Document> collection){
	    	FindIterable<Document>findIterable = collection.find();
	    	MongoCursor<Document> mongoCursor = findIterable.iterator();
	    	while(mongoCursor.hasNext()){
	    		System.out.println(mongoCursor.next());
	    	}
	    }
	    /**
	     * 更新文档
	     * @param collection
	     */
	    private static void updateDocument(MongoCollection<Document> collection){
	    	collection.updateMany(Filters.eq("likes",200), new Document("$set",new Document("likes",100)));
	        System.out.println("文档更新成功");  
	    }
	    /**
	     * 删除文档
	     * @param collection
	     */
	    private static void deleteDocument(MongoCollection<Document> collection){
	    	//删除符合条件的第一个文档
//	    	collection.deleteOne(Filters.eq("likes",100));
	    	//删除所有符合条件的文档
	    	collection.deleteMany(Filters.eq("likes",200));
	    	
	    }
	    
	    public static void main(String[] args) {
//	    	createCollection("test");  
	        MongoCollection<Document> collection = selectCollection("test");  
//	        insertDocument(collection);
//	        updateDocument(collection);
	        deleteDocument(collection);
	        selectDocument(collection);
		}
	      
	
	
}
