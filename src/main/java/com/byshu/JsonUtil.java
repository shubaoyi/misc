package com.byshu;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.elasticsearch.common.lang3.StringUtils;

import java.io.IOException;
import java.util.*;

/**
 * 基于jackson json 工具类
 */
public class JsonUtil {
    private static final ObjectMapper DEFAULT_MAPPER = createSimpleMapper();
    public static final String BLANK_JSON = "{}";

    private static ObjectMapper createSimpleMapper() {
        ObjectMapper mapper = new ObjectMapper();
        // Include.Include.ALWAYS 默认
        // Include.NON_DEFAULT 属性为默认值不序列化
        // Include.NON_EMPTY 属性为 "" 或者为 NULL 都不序列化
        // Include.NON_NULL 属性为NULL 不序列化
        mapper.setSerializationInclusion(Include.NON_NULL);
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        return mapper;
    }

    /**
     * 取JSON的一个节点，不支持数组
     *
     * @param json
     * @param nodeList 分级节点以"."分隔，从根节点开始
     * @return
     */
    public static String loadSubNode(String json, String nodeList) {
        if (checkIsNull(json)) {
            return null;
        }
        if (StringUtils.isBlank(nodeList)) {
            return json;
        }
        try {
            JsonNode root = DEFAULT_MAPPER.readTree(json);
            for (String key : StringUtils.split(nodeList, ".")) {
                root = root.get(key);
                if (root == null) {
                    return null;
                }
            }
            return root.toString();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 对象转Json，不抛异常
     *
     * @param obj
     * @return
     */
    public static String toJsonSilently(Object obj) {
        try {
            return toJson(obj);
        } catch (IOException e) {
            return BLANK_JSON;
        }
    }

    /**
     * 对象转Json，抛异常
     *
     * @param obj
     * @return
     * @throws IOException
     */
    public static String toJson(Object obj) throws IOException {
        return DEFAULT_MAPPER.writeValueAsString(obj);
    }

    /**
     * json转对象，不抛异常
     *
     * @param json
     * @param cls
     * @return
     */
    public static <T> T toObjectSilently(String json, Class<T> cls) {
        try {
            return toObject(json, cls);
        } catch (IOException e) {
            return null;
        }
    }

    /**
     * json转对象，抛异常
     *
     * @param json
     * @param cls
     * @return
     * @throws IOException
     */
    public static <T> T toObject(String json, Class<T> cls) throws IOException {
        if (json == null || json.isEmpty() || BLANK_JSON.equals(json)) {
            return null;
        }

        return DEFAULT_MAPPER.readValue(json, cls);
    }

    /**
     * json转Map，不抛异常
     *
     * @param json
     * @param keyCls
     * @param valueCls
     * @return
     */
    public static <K, V> Map<K, V> toMapSilently(String json, Class<K> keyCls, Class<V> valueCls) {
        try {
            return toMap(json, keyCls, valueCls);
        } catch (IOException e) {
            return null;
        }
    }

    /**
     * json转Map，抛异常
     *
     * @param json
     * @param keyCls
     * @param valueCls
     * @return
     * @throws IOException
     */
    public static <K, V> Map<K, V> toMap(String json, Class<K> keyCls, Class<V> valueCls)
            throws IOException {
        if (json == null || json.isEmpty() || BLANK_JSON.equals(json)) {
            return null;
        }

        JavaType javaType = DEFAULT_MAPPER.getTypeFactory().constructMapType(HashMap.class, keyCls, valueCls);
        return DEFAULT_MAPPER.readValue(json, javaType);
    }

    /**
     * json转List，不抛异常
     *
     * @param json
     * @param eleCls
     * @return
     */
    public static <T> List<T> toListSilently(String json, Class<T> eleCls) {
        try {
            return toList(json, eleCls);
        } catch (IOException e) {
            return null;
        }
    }

    /**
     * json转List，抛异常
     *
     * @param json
     * @param eleCls
     * @return
     * @throws IOException
     */
    public static <T> List<T> toList(String json, Class<T> eleCls) throws IOException {
        if (json == null || json.isEmpty() || BLANK_JSON.equals(json)) {
            return null;
        }

        JavaType javaType = DEFAULT_MAPPER.getTypeFactory().constructCollectionType(ArrayList.class, eleCls);
        return DEFAULT_MAPPER.readValue(json, javaType);
    }

    /**
     * json转Set
     *
     * @param json
     * @param eleCls
     * @return
     * @throws IOException
     */
    public static <T> Set<T> toSet(String json, Class<T> eleCls) throws IOException {
        if (json == null || json.isEmpty()) {
            return null;
        }
        JavaType javaType = DEFAULT_MAPPER.getTypeFactory().constructCollectionType(HashSet.class, eleCls);
        return DEFAULT_MAPPER.readValue(json, javaType);
    }

    /**
     * json转HashSet
     *
     * @param json
     * @param eleCls
     * @return
     * @throws IOException
     */
    public static <T> HashSet<T> toHashSet(String json, Class<T> eleCls) throws IOException {
        if (json == null || json.isEmpty()) {
            return null;
        }
        JavaType javaType = DEFAULT_MAPPER.getTypeFactory().constructCollectionType(HashSet.class, eleCls);
        return DEFAULT_MAPPER.readValue(json, javaType);
    }

    /**
     * json转linkedList，抛异常
     *
     * @param json
     * @param eleCls
     * @return
     * @throws IOException
     */
    public static <T> LinkedList<T> toLinkedList(String json, Class<T> eleCls) throws IOException {
        if (json == null || json.isEmpty()) {
            return null;
        }

        JavaType javaType = DEFAULT_MAPPER.getTypeFactory().constructCollectionType(LinkedList.class, eleCls);
        return DEFAULT_MAPPER.readValue(json, javaType);
    }

    /**
     * json转Hashtable
     *
     * @param json
     * @param keyCls
     * @param valueCls
     * @return
     * @throws IOException
     */
    public static <K, V> Hashtable<K, V> toHashtable(String json, Class<K> keyCls, Class<V> valueCls)
            throws IOException {
        if (json == null || json.isEmpty() || BLANK_JSON.equals(json)) {
            return null;
        }
        JavaType javaType = DEFAULT_MAPPER.getTypeFactory().constructMapLikeType(Hashtable.class, keyCls, valueCls);
        return DEFAULT_MAPPER.readValue(json, javaType);
    }

    /**
     * json转MapMap
     *
     * @param json
     * @param keyCls
     * @param key1Cls
     * @param val1Cls
     * @return
     * @throws IOException
     */
    public static <K, K1, V1> Map<K, Map<K1, V1>> toMapMap(String json, Class<K> keyCls, Class<K1> key1Cls,
                                                           Class<V1> val1Cls) throws IOException {
        if (json == null || json.isEmpty() || BLANK_JSON.equals(json)) {
            return null;
        }
        JavaType javaTypeKey = DEFAULT_MAPPER.getTypeFactory().constructType(new TypeReference<K>() {
        });
        JavaType javaTypeValue = DEFAULT_MAPPER.getTypeFactory().constructMapType(HashMap.class, key1Cls, val1Cls);
        JavaType javaTypeResult = DEFAULT_MAPPER.getTypeFactory().constructMapType(HashMap.class, javaTypeKey, javaTypeValue);
        return DEFAULT_MAPPER.readValue(json, javaTypeResult);
    }

    /**
     * json转MapHashSet
     *
     * @param json
     * @param classOfK
     * @param classOfE
     * @return
     * @throws IOException
     */
    public static <K, E> Map<K, HashSet<E>> toMapHashSet(String json, Class<K> classOfK, Class<E> classOfE)
            throws IOException {
        if (json == null || json.isEmpty()) {
            return null;
        }

        JavaType keyType = DEFAULT_MAPPER.getTypeFactory().constructType(classOfK);
        JavaType valType = DEFAULT_MAPPER.getTypeFactory().constructCollectionType(HashSet.class, classOfE);
        JavaType javaType = DEFAULT_MAPPER.getTypeFactory().constructMapType(HashMap.class, keyType, valType);
        return DEFAULT_MAPPER.readValue(json, javaType);
    }

    /**
     * json转数组
     *
     * @param json
     * @param cls
     * @return
     * @throws IOException
     */
    public static <T> T[] toArray(String json, Class<T> cls) throws IOException {
        if (json == null || json.isEmpty() || BLANK_JSON.equals(json)) {
            return null;
        }
        JavaType javaType = DEFAULT_MAPPER.getTypeFactory().constructArrayType(cls);
        return DEFAULT_MAPPER.readValue(json, javaType);
    }

    private static boolean checkIsNull(String json) {
        return StringUtils.isBlank(json) || BLANK_JSON.equals(json);
    }

    /**
     * json转MapList
     *
     * @param json
     * @param keyCls
     * @param valueCls
     * @return
     * @throws IOException
     */
    public static <K, V> Map<K, List<V>> toMapList(String json, Class<K> keyCls, Class<V> valueCls) throws IOException {
        if (json == null || json.isEmpty() || BLANK_JSON.equals(json)) {
            return null;
        }
        JavaType javaTypeKey = DEFAULT_MAPPER.getTypeFactory().constructType(new TypeReference<K>() {
        });
        JavaType javaTypeValue = DEFAULT_MAPPER.getTypeFactory().constructCollectionType(ArrayList.class, valueCls);
        JavaType javaTypeResult = DEFAULT_MAPPER.getTypeFactory().constructMapType(HashMap.class, javaTypeKey,
                javaTypeValue);
        return DEFAULT_MAPPER.readValue(json, javaTypeResult);
    }

    public static ObjectMapper getDefaultMapper() {
        return DEFAULT_MAPPER;
    }

}
