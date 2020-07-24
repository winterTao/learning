package com.tao;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * @author DongTao
 * @since 2020-07-20
 */
public class AliJson {

    public static void main(String[] args) {

        String jsonStr = "{\"age\":18,\"email\":\"123456789\",\"name\":\"dong\",\"sex_2\":0}\n";

        JSONObject jsonObject = JSONObject.parseObject(jsonStr);

        User user = jsonObject.toJavaObject(User.class);
        System.out.println(user);


        System.out.println(JSONObject.toJSON(user));
        System.out.println(JSONObject.toJSONString(user));


//        User user = new User();
//        user.setName("dong");
//        user.setAge(18);
//        user.setEmail("123456789");
//        user.setSex(UserType.NAN);
//
//        int features = SerializerFeature.config(JSON.DEFAULT_GENERATE_FEATURE, SerializerFeature.WriteEnumUsingName, false);
//        String s = JSONObject.toJSONString(user, features, SerializerFeature.EMPTY);
//
//        System.out.println(s);
    }
}
