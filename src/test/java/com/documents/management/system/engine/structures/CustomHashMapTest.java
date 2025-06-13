package com.documents.management.system.engine.structures;

import org.junit.jupiter.api.Test;

public class CustomHashMapTest {
    @Test
    public void testPutAndGet() {
        CustomHashMap<String, String> map = new CustomHashMap<>();
        map.put("key1", "value1");
        map.put("key2", "value2");

        assert map.get("key1").equals("value1");
        assert map.get("key2").equals("value2");
    }

    @Test
    public void testUpdateValue() {
        CustomHashMap<String, String> map = new CustomHashMap<>();
        map.put("key1", "value1");
        map.put("key1", "value2");

        assert map.get("key1").equals("value2");
    }

    @Test
    public void testResize() {
        CustomHashMap<String, String> map = new CustomHashMap<>();
        for (int i = 0; i < 20; i++) {
            map.put("key" + i, "value" + i);
        }

        assert map.get("key19").equals("value19");
        assert map.get("key0").equals("value0");
    }

    @Test
    public void testSize() {
        CustomHashMap<String, String> map = new CustomHashMap<>();
        map.put("key1", "value1");
        map.put("key2", "value2");

        assert map.size() == 2;

        map.put("key3", "value3");
        assert map.size() == 3;

        map.put("key1", "newValue1");
        assert map.size() == 3;
    }

    @Test
    public void testForEach() {
        CustomHashMap<String, String> map = new CustomHashMap<>();
        map.put("key1", "value1");
        map.put("key2", "value2");

        map.forEach((key, value) -> {
            assert key.equals("key1") || key.equals("key2");
            assert value.equals("value1") || value.equals("value2");
        });
    }

    @Test
    public void testGetNonExistentKey() {
        CustomHashMap<String, String> map = new CustomHashMap<>();
        map.put("key1", "value1");

        assert map.get("nonExistentKey") == null;
    }

    @Test
    public void testGetNullValue() {
        CustomHashMap<String, String> map = new CustomHashMap<>();
        map.put("key1", null);

        assert map.get("key1") == null;
    }
}