package cn.itcast.hotel.constants;

/**
 * @Classname MqConstants
 * @Description
 * @Date 2024/1/15 下午7:51
 * @Created by joneelmo
 */
public class MqConstants {
    /* exchange */
    public final static String HOTEL_EXCHANGE = "hotel.topic";
    /*监听新增和修改的队列*/
    public final static String HOTEL_INSERT_QUEUE = "hotel.insert.queue";
    /*监听删除的队列*/
    public final static String HOTEL_DELETE_QUEUE = "hotel.delete.queue";
    /*routing key*/
    public final static String HOTEL_INSERT_KEY = "hotel.insert";
    /*routing key*/
    public final static String HOTEL_DELETE_KEY = "hotel.delete";
}
