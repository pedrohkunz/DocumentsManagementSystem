package com.documents.management.system;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

import com.documents.management.system.engine.structures.CustomAVLTreeTest;
import com.documents.management.system.engine.structures.CustomBTreeTest;
import com.documents.management.system.engine.structures.CustomBtreePlusTest;
import com.documents.management.system.engine.structures.CustomHashMapTest;
import com.documents.management.system.engine.structures.CustomLinkedListTest;

@Suite
@SelectClasses({
    CustomAVLTreeTest.class,
    CustomBtreePlusTest.class,
    CustomBTreeTest.class,
    CustomHashMapTest.class,
    CustomLinkedListTest.class

})
@SuiteDisplayName("All Tests Suite")
public class AllTestsSuite {

}
