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
	 * 1.��֮main������ִ�������漸������                                                    
	 * 2.Schema()����3�����ݿ�汾�ţ���com.cn.speedchat.greendao���ǰ�����
	 *   Ҳ����˵���ɵ�Dao�ļ�����������£����Խ�Schema���Ϊ���ݿ������İ�
	 * 3.addNote() addSession() addReplay()�����������൱�ڽ�����������
	 *   �����㶼���Բ��ù��˻��Զ�����
	 * 4.new DaoGenerator().generateAll()���������Dao�ļ���·����λ�ã�
	 *   ������src-gen�ļ������棬��srcͬһ��Ŀ¼���������Լ�Ҫ��srcͬһ��Ŀ¼���½�һ��src-gen�ļ��д���Ҫ���ɵ��ļ�
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
        //true�����У�false����Ů
        note.addBooleanProperty("sex").notNull();   
        note.addStringProperty("time").notNull(); 
    }
    
    /**
     * "MqttChatEntity"�൱���Ǳ����������MqttChatEntity���ɶ���Ϳ��Է�������������ˣ�
     * Ҳ����������Ӧ������࣬����ʹ����ͻ�������,note.addStringProperty("sessionid").notNull()��
     * �൱�ڸ��������һ������Ϊsessionid���ֶΣ����Ҳ���Ϊ��
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
     * ��Ҳ�����һ����
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
     * ��Ҳ�����һ����
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
     * ����ճ�
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