package com.byshu.mybatis;

import com.byshu.mybatis.mapper.StudentMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import net.sf.cglib.core.DebuggingClassWriter;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;


public class Test {

    public static void main(String[] args) throws IOException {
//        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "D://cglib-class");
        System.setProperty("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        System.setProperty("jdk.proxy.ProxyGenerator.saveGeneratedFiles", "true");


        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        try (SqlSession session = sqlSessionFactory.openSession()) {
            StudentMapper mapper = session.getMapper(StudentMapper.class);
            PageHelper.offsetPage(2, 5, false);
            List<Integer> ids = mapper.selectAllId();
            System.out.println(ids);
            System.err.println(Arrays.toString(ids.toArray()));
        }
    }
}
