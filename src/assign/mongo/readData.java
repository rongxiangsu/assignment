package assign.mongo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;


public class readData implements Runnable {
	private String folderPath;
	private DB db;
	private String encoding = "GBK";
	private String collectionName;
	
	public readData(String folderPath, DB db,String collectionName) {
		// TODO Auto-generated constructor stub
		this.folderPath = folderPath;
		this.db = db;
		this.collectionName = collectionName;
	}

	private static String[] getFilePaths(String folderPath) {
		File files = new File(folderPath);
		String[] result = files.list();
		for (int i = 0; i < result.length; i++) {
			result[i] = folderPath + "/" + result[i];
		}
		return result;
	}

	public void run() {
		String[] filePaths = getFilePaths(folderPath);
		BufferedReader bufferedReader = null;
		try {
			DBCollection collection = db.getCollection(collectionName);
			for (int i = 0; i < filePaths.length; i++) {
				System.out.println("开始读取" + folderPath + "第" + (i + 1) + "个文本。。。");
				File file = new File(filePaths[i]);
				if (file.exists() && file.isFile()) {
					InputStreamReader reader = new InputStreamReader(new FileInputStream(file), encoding);
					bufferedReader = new BufferedReader(reader, 1024 * 1024 * 80);
					String lineTxt = "";
					BasicDBObject doc = new BasicDBObject();
					while ((lineTxt = bufferedReader.readLine()) != null) {
						String[] fields = lineTxt.split("\t");
						// List<Document>documents=new ArrayList<Document>();
						doc = constructDoc.construct(fields);
						if (doc != null) {
							collection.insert(doc);
						}
					}
					reader.close();
				}
				System.out.println("读取" + folderPath + "第" + (i + 1) + "个文本结束！");
			}
			System.out.println("存储任务结束！");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return;
		}
	}

}