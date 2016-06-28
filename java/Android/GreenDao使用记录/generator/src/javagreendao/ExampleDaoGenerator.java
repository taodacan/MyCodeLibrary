package javagreendao;
import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Property;
import de.greenrobot.daogenerator.Schema;
import de.greenrobot.daogenerator.ToMany;
/**
 * Generates entities and DAOs for the example project DaoExample.
 *
 * Run it as a Java application (not Android).
 *
 * @author Markus
 */
public class ExampleDaoGenerator
{
	/**
	 * 1.总之main函数就执行了下面几个函数                                                    
	 * 2.Schema()参数3是数据库版本号，“com.cn.speedchat.greendao”是包名，
	 *   也就是说生成的Dao文件会在这个包下，可以将Schema理解为数据库上下文吧
	 * 3.addNote() addSession() addReplay()这三个函数相当于建立了三个表，
	 *   表名你都可以不用管了会自动生成
	 * 4.new DaoGenerator().generateAll()这个是生成Dao文件的路径的位置，
	 *   生成在src-gen文件夹里面，跟src同一级目录，所以你自己要在src同一级目录下新建一个src-gen文件夹待会要生成的文件
	 * @param args
	 * @throws Exception
	 */
    public static void main(String[] args) throws Exception
    {
        Schema schema = new Schema(4, "com.cn.speedchat.greendao");
//        addNote(schema);
//        addSession(schema);
//        addReplay(schema);
//        addCustomerOrder(schema);
        addUser(schema);
        new DaoGenerator().generateAll(schema, "src-gen");
    }
    
    private static void addUser(Schema schema)  
    {
        Entity note = schema.addEntity("UserEntity");    
        note.addIdProperty().autoincrement();
        note.addStringProperty("name").notNull();
        note.addIntProperty("age").notNull();
        //true代表男，false代表女
        note.addBooleanProperty("sex").notNull();   
        note.addStringProperty("time").notNull(); 
    }
    
    /**
     * "MqttChatEntity"相当于是表的类名，用MqttChatEntity生成对象就可以访问这个表属性了，
     * 也就是这个表对应了这个类，待会使用你就会明白了,note.addStringProperty("sessionid").notNull()，
     * 相当于给表里面加一个名字为sessionid的字段，而且不能为空
     * @param schema
     */
    private static void addNote(Schema schema)
    {
        Entity note = schema.addEntity("MqttChatEntity");
        note.addIdProperty().autoincrement();
        note.addIntProperty("mode").notNull();
        note.addStringProperty("sessionid").notNull();
        note.addStringProperty("from").notNull();
        note.addStringProperty("to").notNull();
        note.addStringProperty("v_code");
        note.addStringProperty("timestamp").notNull();
        note.addStringProperty("platform");
        note.addStringProperty("message");
        note.addBooleanProperty("isread").notNull();
        note.addLongProperty("gossipid");
        note.addStringProperty("gossip");
        note.addIntProperty("chattype").notNull();
        note.addStringProperty("imagepath");
        note.addStringProperty("base64image");
    }
    
    /**
     * 这也是添加一个表
     * @param schema
     */
    private static void addSession(Schema schema)
    {
        Entity note = schema.addEntity("SessionEntity");
        note.addIdProperty().autoincrement();
        note.addStringProperty("from").notNull();
        note.addStringProperty("to").notNull();
        note.addLongProperty("gossipid").notNull();
        note.addStringProperty("gossip");
        note.addIntProperty("sessiontype").notNull();
        note.addBooleanProperty("asdasd").notNull();
    }
    
    /**
     * 这也是添加一个表
     * @param schema
     */
    private static void addReplay(Schema schema)
    {
        Entity note = schema.addEntity("ReplayEntity");
        note.addIdProperty().autoincrement();
        note.addIntProperty("mode").notNull();
        note.addStringProperty("from").notNull();
        note.addStringProperty("to").notNull();
        note.addStringProperty("v_code");
        note.addStringProperty("timestamp").notNull();
        note.addStringProperty("platform");
        note.addStringProperty("message");
        note.addIntProperty("msgtype").notNull();
        note.addBooleanProperty("isread").notNull();
    } 
   
    /**
     * 这个照抄
     * @param schema
     */
    private static void addCustomerOrder(Schema schema)
    {
        Entity customer = schema.addEntity("Customer");
        customer.addIdProperty();
        customer.addStringProperty("name").notNull();                                                                   
        Entity order = schema.addEntity("Order");
        order.setTableName("ORDERS"); // "ORDER" is a reserved keyword
        order.addIdProperty();
        Property orderDate = order.addDateProperty("date").getProperty();
        Property customerId = order.addLongProperty("customerId").notNull().getProperty();
        order.addToOne(customer, customerId);                                               
        ToMany customerToOrders = customer.addToMany(order, customerId);
        customerToOrders.setName("orders");
        customerToOrders.orderAsc(orderDate);
    }                                                                                 
}