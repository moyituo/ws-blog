package work.xiaoying.test;

import com.google.common.collect.Maps;
import org.apache.ibatis.annotations.Select;
import work.xiaoying.entity.User;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

interface UserMapper{
    @Select("select * from t_user where id=#{id} and name=#{name}")
    List<User> selectUserList(Integer id,String name);
}
public class MainTest {
    public static void main(String[] args) {
        UserMapper userMapper=(UserMapper) Proxy.newProxyInstance(MainTest.class.getClassLoader(), new Class<?>[]{UserMapper.class}, (proxy, method, args1) -> {
            Select annotation = method.getAnnotation(Select.class);
            Map<String, Object> argNameMap = buildMethodArgNameMap(method, args1);
            if (null!=annotation){
                String[] value=annotation.value();
                String sql=value[0];
                sql=parseSQL(sql,argNameMap);
                System.out.println(sql);
                System.out.println(method.getReturnType());
                System.out.println(method.getGenericReturnType());
            }
            return null;
        });
        userMapper.selectUserList(1,"xiaoying");
    }

    public static String parseSQL(String sql,Map<String,Object> argNameMap){
        StringBuilder stringBuilder = new StringBuilder();
        int length = sql.length();
        for (int i = 0; i < length; i++) {
            char c=sql.charAt(i);
            if (c=='#'){
                int nextIndex=i+1;
                char nextChar = sql.charAt(nextIndex);
                if (nextChar!='{'){
                    throw new RuntimeException(String.format("这里应该为#{\nsql:%s\nindex:%d",stringBuilder.toString(),nextIndex));
                }
                StringBuilder sb = new StringBuilder();
                i = parseSQLArg(sql, nextIndex, sb);
                String argName = sb.toString();
                Object argValue = argNameMap.get(argName);
                if (argValue==null){
                    throw new RuntimeException(String.format("找不到参数值:%s",argName));
                }
                stringBuilder.append(argValue.toString());
                continue;
            }
            stringBuilder.append(c);
        }
        return stringBuilder.toString();
    }

    private static int parseSQLArg(String sql, int nextIndex, StringBuilder sb) {
        nextIndex++;
        for (;nextIndex<sql.length();nextIndex++) {
            char c = sql.charAt(nextIndex);
            if (c != '}') {
                sb.append(c);
                continue;
            }
            return nextIndex;
        }
        throw new RuntimeException(String.format("缺少右括号\nindex:%d",nextIndex));
    }

    public static Map<String,Object> buildMethodArgNameMap(Method method, Object[] args){
        Map<String,Object> nameArgMap = Maps.newHashMap();
        int[] index={0};
        Parameter[] parameters = method.getParameters();
        Arrays.asList(parameters).forEach(parameter -> {
            String name=parameter.getName();
            nameArgMap.put(name,args[index[0]]);
            index[0]++;
        });
        return nameArgMap;
    }
}
